package ccut.controllerUser;

import ccut.common.CommonResponse;
import ccut.model.pojo.StoreAudit;
import ccut.model.request.StoreAuditRequest;
import ccut.service.StoreAuditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@Api(tags = {"店铺审核模块"})
@RequestMapping({"/Store"})
public class StoreAuditController {
    @PostMapping({"/addStoreAudit"})
    @ApiOperation("上传申请2")
    @ApiResponses({@ApiResponse(code = 200, message = "success"), @ApiResponse(code = 100, message = "错误：参数为空"), @ApiResponse(code = 133, message = "信用编号格式错误"), @ApiResponse(code = 131, message = "手机号格式有误"), @ApiResponse(code = 501, message = "内部异常")})
    public CommonResponse<Boolean> addStoreAudit(StoreAuditRequest storeAuditRequest) {
        return this.storeAuditService.addStoreAudit(storeAuditRequest);
    }


    @Autowired
    StoreAuditService storeAuditService;


    @PostMapping({"/addPicture"})
    @ApiOperation("上传申请1")
    @ApiResponses({@ApiResponse(code = 200, message = "success"), @ApiResponse(code = 100, message = "错误：参数为空"), @ApiResponse(code = 112, message = "图片超过5mb"), @ApiResponse(code = 111, message = "文件类型错误"), @ApiResponse(code = 113, message = "图片上传失败"), @ApiResponse(code = 501, message = "内部异常")})
    public CommonResponse<Boolean> uploadFile(@RequestPart("file") MultipartFile file) {
        return this.storeAuditService.addPicture(file);
    }


    @GetMapping({"/getStoreAudit"})
    @ApiOperation("获取申请表")
    @ApiResponses({@ApiResponse(code = 200, message = "success")})
    public CommonResponse<List<StoreAudit>> approval() {
        return this.storeAuditService.getStoreAudit();
    }


}

