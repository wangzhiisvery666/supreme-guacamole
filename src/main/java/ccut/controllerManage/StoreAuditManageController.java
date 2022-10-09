package ccut.controllerManage;

import ccut.common.CommonResponse;
import ccut.service.StoreAuditService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/StoreAudit")
@Api(tags = "商家入驻表模块")
public class StoreAuditManageController {

    @Autowired
    StoreAuditService storeAuditService;


    @PutMapping({"/approval"})
    @ApiOperation("审核是否通过")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 100, message = "错误：参数为空"),
            @ApiResponse(code = 501, message = "内部异常")})
    @ApiImplicitParams({@ApiImplicitParam(name = "Id", value = "要审核的id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "approval_status", value = "审核中1、通过2、失败3、", required = true, paramType = "query"),
            @ApiImplicitParam(name = "audit_results", value = "审核后的信息", required = true, paramType = "query")})
    public CommonResponse<Boolean> approval(int Id, String approval_status, String audit_results) {

        return storeAuditService.approval(Id, approval_status, audit_results);
    }


    @GetMapping({"/getAuditRecords"})
    @ApiResponse(code = 200, message = "success")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status" ,value = "注册信息所处状态 1 2 3",required = true,paramType = "query"),
            @ApiImplicitParam(name = "pageNum" ,value = "页码",required = true,paramType = "query"),
            @ApiImplicitParam(name = "size" ,value = "条数",required = true,paramType = "query"),
    })
    @ApiOperation("按照状态获取分页信息")
    public CommonResponse<HashMap<String, Object>> getAuditRecords(@RequestParam("status")  Integer approvalStatus,
                                                                   @RequestParam("pageNum") Integer pageSum,
                                                                   @RequestParam("size") Integer size) {
        return storeAuditService.getStoreAuditByStatus(approvalStatus,pageSum,size);
    }

//    @GetMapping({"/getSumByStatus"})
//    @ApiResponse(code = 200, message = "success")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "status" ,value = "注册信息所处状态 1 2 3",required = true,paramType = "query"),
//    })
//    @ApiOperation("按照状态获取总条数")
//    public CommonResponse<Integer> getSumByStatus(@RequestParam("status")  Integer approvalStatus) {
//        return storeAuditService.getSumByStatus(approvalStatus);
//    }
}
