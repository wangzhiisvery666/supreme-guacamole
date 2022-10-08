package ccut.security;//package ccut.security;
//
//import ccut.Exception.CustomizeException;
//import ccut.common.CommonResponse;
//import ccut.common.ErrorEnum;
//import ccut.model.pojo.Admin;
//import ccut.utils.RedisCache;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Service;
//
//import java.util.Objects;
//
//
//@Service
//public class AdminLoginServiceImpl implements AdminLoginService {
//
//    @Autowired
//    RedisCache redisCache;
//
//    @Autowired
//    AuthenticationManager authenticationManager;
//
//    //TODO 1、 第一步  用户请求发到这
//
//    @Override
//    public CommonResponse<Boolean> adminLogin(String username ,String password) {
//
//        //封装 管理员对象
//        Admin admin = new Admin();
//        admin.setName(username);
//        admin.setPassword(password);
//
//        AdminDetails adminDetails = new AdminDetails();
//        adminDetails.setAdmin(admin);
//        //封装 UsernamePasswordAuthenticationToken
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(admin.getName(),admin.getPassword());
//
//        // TODO 2、第二步  ProviderManage  的 authenticate 继续调用
//        //  DaoAuthenticateManageProvider 的 authenticate   然后调用 loadUserByUsername() 这自己重写 了查数据库等操作
//        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
//
//
//        //如果为空 抛出异常
//        if (Objects.isNull(authenticate)){
//            throw new CustomizeException(ErrorEnum.WRONG_USER_NAME_OR_PASSWORD);
//        }
//
//        // TODO 4、有返回到自己写的接口 返回 UserDetails  进行自己的操作
//        AdminDetails principal =(AdminDetails) authenticate.getPrincipal();
//        //获取 自己的user 对象
//        Admin admin1 = principal.getAdmin();
//        //TODO  可以返回JWT 和保存用户信息
//
//
//        return new CommonResponse<>("成功登陆","200",true);
//    }
//}
