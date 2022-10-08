package ccut.controllerManage;


//import ccut.security.AdminLoginServiceImpl;

import ccut.common.CommonResponse;
import ccut.model.pojo.Admin;
import ccut.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"管理员登陆"})
@RequestMapping({"/admin"})
public class adminLoginController
{

//  @Autowired
//  AdminLoginServiceImpl adminLoginService;
  @ApiOperation("管理员登陆接口")
  @PostMapping({"/login"})
  public CommonResponse<Boolean> adminLogin(@RequestParam("name") String name, @RequestParam("password") String password) {

//  adminLoginService.adminLogin(name,password);
    Admin admin = new Admin();
    admin.setName(name);
    admin.setPassword(password);
    return this.adminService.adminLogin(admin);
  }

  @GetMapping("/get")
  public String test(){
    return "success";
  }


  @Autowired
  AdminService adminService;
}

