package ccut.service.impl;


import ccut.Exception.CustomizeException;
import ccut.common.BaseThreadLocal;
import ccut.common.CommonResponse;
import ccut.common.ErrorEnum;
import ccut.mapper.StoreMapper;
import ccut.model.VO.SStoreVO;
import ccut.model.pojo.Store;
import ccut.service.StoreService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class StoreServiceImpl  extends ServiceImpl<StoreMapper, Store> implements StoreService {

    @Autowired
    StoreMapper storeMapper;


    public CommonResponse<List<Store>> getAllStore() {

        List<Store> allStore = storeMapper.selectList(null);
        log.info("获取店铺列表成功");
        return new CommonResponse<>("查看列表成功", "200", allStore);
    }

    public CommonResponse<SStoreVO> storeGetStore() {
        Integer currentId = BaseThreadLocal.getCurrentId();
        BaseThreadLocal.remove();
        //根据id 取出 店铺
        LambdaQueryWrapper<Store> storeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        storeLambdaQueryWrapper.eq(Store::getCreateUser,currentId);
        Store store = storeMapper.selectOne(storeLambdaQueryWrapper);

        //如果为空则代表为注册店铺
        if (Objects.isNull(store)){
//            UNREGISTERED_STORE("136", "未注册店铺", ""),
            throw new CustomizeException(ErrorEnum.UNREGISTERED_STORE);
        }
        SStoreVO sStoreVO = new SStoreVO();
        BeanUtils.copyProperties(store,sStoreVO);
        log.info("商家获取店铺信息成功");
        return new CommonResponse<>("查看列表成功", "200", sStoreVO);
    }


    public CommonResponse<Boolean> putStoreStatus(int id, String message) {

        int i = this.storeMapper.updateStatus(id, message);
        if (i <= 0) {
            throw new CustomizeException(ErrorEnum.INTERNAL_ERROR);
        }
        log.info("id 为: {} 的店铺已经停用", id);
        return new CommonResponse<>("商家停止营业", "200", Boolean.TRUE);
    }
}
