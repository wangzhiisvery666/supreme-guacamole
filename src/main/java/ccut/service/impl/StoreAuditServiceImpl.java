package ccut.service.impl;

import ccut.Exception.CustomizeException;
import ccut.common.BaseThreadLocal;
import ccut.common.CommonResponse;
import ccut.common.ErrorEnum;
import ccut.mapper.StoreAuditMapper;
import ccut.mapper.StoreMapper;
import ccut.model.VO.StoreApplicationVO;
import ccut.model.pojo.Store;
import ccut.model.pojo.StoreAudit;
import ccut.model.request.StoreAuditRequest;
import ccut.service.StoreAuditService;
import ccut.utils.CommonUtils;
import ccut.utils.FileUploadUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;


@Service
@Slf4j
public class StoreAuditServiceImpl extends ServiceImpl<StoreAuditMapper, StoreAudit> implements StoreAuditService {

    @Autowired
    StoreAuditMapper storeAuditMapper;

    @Autowired
    StoreMapper storeMapper;


    public CommonResponse<Boolean> addStoreAudit(StoreAuditRequest storeAuditRequest) {
        String auditName = storeAuditRequest.getAuditName();
        String creditCode = storeAuditRequest.getCreditCode();
        String contactName = storeAuditRequest.getContactName();
        String contactPhone = storeAuditRequest.getContactPhone();
        String companyAddress = storeAuditRequest.getCompanyAddress();

        String storeDescription = storeAuditRequest.getStoreDescription();

        if (StringUtils.isAllBlank(auditName, contactName, companyAddress, storeDescription)) {
            throw new CustomizeException(ErrorEnum.PARAMETER_EXCEPTION_NULL);
        }

        if (this.storeAuditMapper.storeName(auditName) > 0) {
            throw new CustomizeException(ErrorEnum.STORE_ALREADY_EXISTS);
        }


        CommonUtils.checkCreditCode(creditCode);
        CommonUtils.checkPhone(contactPhone);


        Integer currentId = BaseThreadLocal.getCurrentId();


        StoreAudit storeAudit = new StoreAudit();
        storeAudit.setUserId(currentId);
        storeAudit.setAuditName(auditName);
        storeAudit.setCreditCode(creditCode);
        storeAudit.setContactName(contactName);
        storeAudit.setContactPhone(contactPhone);
        storeAudit.setCompanyAddress(companyAddress);
        storeAudit.setStoreDescription(storeDescription);

        storeAudit.setBusinessLicense("");


        int insert = this.storeAuditMapper.insert(storeAudit);
        if (insert < 0) {
            throw new CustomizeException(ErrorEnum.INTERNAL_ERROR);
        }

        return new CommonResponse("申请表已提交", "200", Boolean.valueOf(true));
    }


    public CommonResponse<Boolean> addPicture(MultipartFile file) {
        if ("".equals(file.getOriginalFilename())) {
            throw new CustomizeException(ErrorEnum.WRONG_FILE_TYPE);
        }


        String storagePath = "img/Store/";

        Integer currentId1 = BaseThreadLocal.getCurrentId();
        String md5Id = CommonUtils.md5(currentId1 + "");

        String businessLicensePicture = md5Id + "businessLicense";


        Integer status = this.storeAuditMapper.getStatus(currentId1);
        if (status == null || status.intValue() == 0) {
            throw new CustomizeException(ErrorEnum.INTERNAL_ERROR);
        }


        String url = FileUploadUtils.upLoadImg(file, storagePath, businessLicensePicture);


        Integer currentId = BaseThreadLocal.getCurrentId();


        int insert = this.storeAuditMapper.updatePictrueByDate(currentId, url);
        if (insert < 0) {
            throw new CustomizeException(ErrorEnum.INTERNAL_ERROR);
        }

        return new CommonResponse("图片上传成功", "200", Boolean.valueOf(true));
    }


    public CommonResponse<List<StoreAudit>> getStoreAudit() {
        List<StoreAudit> allOrder = storeAuditMapper.getAllOrder();
        return new CommonResponse("获取审核表成功", "200", allOrder);
    }


    @Transactional(rollbackFor = {Exception.class})
    public CommonResponse<Boolean> approval(int Id, String approval_status, String audit_results) {
        if (StringUtils.isAllBlank(Id + "", approval_status, audit_results)) {
            throw new CustomizeException(ErrorEnum.PARAMETER_EXCEPTION_NULL);
        }
        //更新状态和审核结果 信息
        int i = this.storeAuditMapper.updateApproval(Id, approval_status, audit_results);


        //如果出错直接抛出错误
        if (i < 0) {
            throw new CustomizeException(ErrorEnum.INTERNAL_ERROR);
        }
        //状态为 2 则审核通过
        if (approval_status.equals("2")) {
            StoreAudit storeAudit = storeAuditMapper.selectById(Id);
            Store store = new Store();

            //店铺地址 默认为空
            store.setStoreImg("");

            Integer userId = storeAudit.getUserId();
            store.setUpdateTime(null);
            //初始修改人和创建人都为 userid
            store.setCreateUser(userId.longValue());
            store.setUpdateUser(userId.longValue());
            //把经营状态 设置为 1 营业状态
            store.setStatus(1);
            //名修改掉
            String auditName = storeAudit.getAuditName();
            store.setStoreName(auditName);

            BeanUtils.copyProperties(storeAudit, store);
            this.storeMapper.insert(store);
        }

        return new CommonResponse<>("审批成功", "200", Boolean.TRUE);
    }


    /**
     * 将结果信息返回
     * @return : 返回结果信息
     */
    public CommonResponse<HashMap<String,Object>> getStatus() {
        Integer currentId = BaseThreadLocal.getCurrentId();
        BaseThreadLocal.remove();
        //把记=记录查询过来 然后 获取状态和审核结果
        StoreAudit storeAudit = storeAuditMapper.getAuditResult(currentId);

        HashMap<String, Object> stringStringHashMap = new HashMap<>();
        //如果 查询结果为空  则返回为申请入驻
        if (Objects.isNull(storeAudit)){
            stringStringHashMap.put("status","0");
            stringStringHashMap.put("result","");
            return new CommonResponse<>("状态获取成功", "200", stringStringHashMap);
        }
        //如果查询不为空 则设置信息准备返回
        stringStringHashMap.put("status",storeAudit.getApprovalStatus()+"");
        stringStringHashMap.put("result",storeAudit.getAuditResults());
        stringStringHashMap.put("AuditTime",storeAudit.getUpdateDate());

        return new CommonResponse<>("状态获取成功", "200", stringStringHashMap);
    }


    public CommonResponse<StoreApplicationVO> putApplication() {
        Integer currentId = BaseThreadLocal.getCurrentId();
        StoreAudit application = this.storeAuditMapper.getApplication(currentId);
        if (application == null) {
            throw new CustomizeException(ErrorEnum.NOT_APPLIED);
        }

        StoreApplicationVO storeApplicationVO = new StoreApplicationVO();
        BeanUtils.copyProperties(application, storeApplicationVO);
        return new CommonResponse("查看成功", "200", storeApplicationVO);
    }


    public CommonResponse<StoreApplicationVO> deleteApplication() {
        Integer currentId = BaseThreadLocal.getCurrentId();
        BaseThreadLocal.remove();
        StoreAudit application = this.storeAuditMapper.getApplication(currentId);
        Long auditId = application.getAuditId();
        this.storeAuditMapper.deleteById(auditId);
        StoreApplicationVO storeApplicationVO = new StoreApplicationVO();
        BeanUtils.copyProperties(application, storeApplicationVO);

        return new CommonResponse<>("查看成功", "200", storeApplicationVO);
    }

//    @Override
//    public CommonResponse<List<StoreAudit>> getStoreAuditByStatus(int approvalStatus) {
//
//        LambdaQueryWrapper<StoreAudit> storeAuditLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        storeAuditLambdaQueryWrapper.eq(StoreAudit::getApprovalStatus,approvalStatus);
//        List<StoreAudit> storeAudits = storeAuditMapper.selectList(storeAuditLambdaQueryWrapper);
//
//
//        log.info("获取 {} 列表成功",approvalStatus);
//        return new CommonResponse<>("获取审核表成功","200",storeAudits);
//    }


    /**
     * 分页查询
     * @param approvalStatus :状态
     * @param pageSum :页数
     * @param size :大小
     * @return : 分页查询后的结果
     */
    public CommonResponse<HashMap<String, Object>> getStoreAuditByStatus(int approvalStatus,Integer pageSum,Integer size) {

        LambdaQueryWrapper<StoreAudit> storeAuditLambdaQueryWrapper = new LambdaQueryWrapper<>();
        storeAuditLambdaQueryWrapper.eq(StoreAudit::getApprovalStatus,approvalStatus);
        //逻辑分页分页
        Page<StoreAudit> Page = new Page<>(pageSum,size);
        Page<StoreAudit> storeAuditPage = storeAuditMapper.selectPage(Page, storeAuditLambdaQueryWrapper);
        long total = storeAuditPage.getTotal();
        long pages = storeAuditPage.getPages();
        List<StoreAudit> records = storeAuditPage.getRecords();
        log.info("获取状态为 {} 的第 {} 页的 {} 条数据显示成功 总条数为 {}",approvalStatus,pageSum,size,pages);
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("records",records);
        stringObjectHashMap.put("total",total);
        return new CommonResponse<>("获取审核表成功","200",stringObjectHashMap);
    }

    @Override
    public CommonResponse<Boolean> updateStatus() {
        Integer currentId = BaseThreadLocal.getCurrentId();
        BaseThreadLocal.remove();

        Integer integer = storeAuditMapper.updateStatus(currentId);
        if (integer<=0){
            throw new CustomizeException(ErrorEnum.INTERNAL_ERROR);
        }
        return  new CommonResponse<>("修改地址成功","200",true);

    }
}
