//package net.xhalo.video.config;
//
//import net.xhalo.video.security.service.imp.SecurityUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//    @Autowired
//    private SecurityUserService securityUserService;
//
//    /*@Autowired
//    private DataSource dataSource;*/
//
//    /*@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select username,password,true from usertable" +
//                        "where username = ?")
//                .authoritiesByUsernameQuery("select username,'ROLE_USER' from usertable" +
//                        "where username = ?");
//
//    }*/
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(securityUserService);
//    }
//}
