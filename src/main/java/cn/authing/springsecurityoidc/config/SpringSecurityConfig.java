package cn.authing.springsecurityoidc.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity(debug = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //注意此处 callback 是在官网配置的回调地址后缀
        http.formLogin().disable();
        http.csrf().and().cors().disable();
        http.authorizeRequests()
                .mvcMatchers("callback","")
                .permitAll()
                .anyRequest().authenticated();
        // 授权码模式回调
         http.oauth2Login(withDefaults());
        // 密码模式及客户端模式
//        http.oauth2Login().loginPage("/loginByPassword").loginProcessingUrl("callback");

    }


}
