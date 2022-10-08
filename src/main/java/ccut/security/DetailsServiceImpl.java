package ccut.security;//package ccut.security;
//
//
//import ccut.mapper.AdminMapper;
//import ccut.model.pojo.Admin;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    AdminMapper adminMapper;
//
//    //TODO  3、第二步 重写security 的自动缓存 改成自己的查询数据库
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        //通过用户名来查找
//        LambdaQueryWrapper<Admin> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        userLambdaQueryWrapper.eq(Admin::getName,username);
//        Admin admin = adminMapper.selectOne(userLambdaQueryWrapper);
//
//        //把自己的用户信息封装到Details中
//        return new AdminDetails(admin);
//    }
//}
