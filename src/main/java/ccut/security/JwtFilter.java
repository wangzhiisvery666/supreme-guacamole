package ccut.security;//package ccut.security;
//
//import ccut.model.pojo.Admin;
//import ccut.utils.RedisCache;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Objects;
//
//@Component
//public class JwtFilter extends OncePerRequestFilter {
//
//        @Autowired
//        private RedisCache redisCache;
//
//        @Override
//        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//            //获取token
//            String token = request.getHeader("token");
//            if (!StringUtils.hasText(token)) {
//                //放行
//                filterChain.doFilter(request, response);
//                return;
//            }
//            //解析token
//            //从redis中获取用户信息
//            String redisKey = "login:admin";
//            Admin loginUser = redisCache.getCacheObject(redisKey);
//
//            if(Objects.isNull(loginUser)){
//                throw new RuntimeException("用户未登录");
//            }
//            //存入SecurityContextHolder
//            //TODO 获取权限信息封装到Authentication中
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,null);
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            //放行
//            filterChain.doFilter(request, response);
//        }
//    }
