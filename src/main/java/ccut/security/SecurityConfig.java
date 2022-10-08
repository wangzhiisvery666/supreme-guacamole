//package ccut.security;//package ccut.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////Security的配置类
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    @Autowired
//    JwtFilter jwtFilter;
//
//    @Autowired
//    AuthenticationEntryPointImpl authenticationEntryPoint;
//
//    @Autowired
//    DetailsServiceImpl detailsService;
//
//    //注入给IOC 用于Security对密码的加密解密
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        //跨站请求伪造 不用开启 前后端分离项目 天然抵抗该攻击
//        http
//                .csrf().disable()
//                //不使用session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                //  拦截器先于 拦截器执行 所以要路径保持一直  对于登录接口 允许匿名访问
//                .antMatchers("/LunBo/**", "/login/**", "/register/**", "/admin/**", "/StoreManage/**")
//                .anonymous()
//                .and().authorizeRequests()
//                .antMatchers("/swagger-resources/**", "/webjars/**", "/v3/**", "/swagger-ui/**")
//                // 除上面外的所有请求全部需要鉴权认证
//                .anonymous()
//                .anyRequest().authenticated();
//
//        //  加入过滤器 进行 过滤
//        http.addFilterBefore( jwtFilter, UsernamePasswordAuthenticationFilter.class);
//
//        //配置异常处理器
//        http.exceptionHandling()
//                //配置认证失败处理器
//                .authenticationEntryPoint(authenticationEntryPoint);
//
//
//    }
//
//    /**
//     * 将 自己 detailsService  加入到权限中
//     * @param auth :
//     * @throws Exception
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(detailsService);
//    }
//
//    /**
//     * 注入AuthenticationManager 需要依赖他的 authenticate() 来验证用户名密码
//     * @return : 注入AuthenticationManager
//     * @throws Exception
//     */
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//
//
//}
