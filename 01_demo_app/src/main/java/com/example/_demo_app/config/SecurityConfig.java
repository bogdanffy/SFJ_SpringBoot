package com.example._demo_app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        System.out.println(encoder.encode("password"));
        auth
                .inMemoryAuthentication()
                .withUser("admin")
                .password(encoder.encode("password"))
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password(encoder.encode("pw"))
                .roles("USER");
    }

    // Itt állítjuk be melyik oldalhoz ki férhet hozzá
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.GET,"/api/**").hasRole("ADMIN")
                .antMatchers("/db/**").hasRole("ADMIN")
                .antMatchers("/newstory").access("hasRole('USER') or hasRole('ADMIN')") // így lehet több felhasználónak is hozzáférést biztosítani ugyan ahhoz a laphoz
                //.antMatchers("/newstory").hasRole("ADMIN") // ilyenkor a második utasítás felülírja az előzőt és az ADMIN kap csak hozzáférést
                .antMatchers("/title/**").access("hasRole('USER') or hasRole('ADMIN')")
                .and()
                    .formLogin().permitAll()
                .and()
                    .logout().permitAll()
                .and()
                    .csrf().ignoringAntMatchers("/db/**") // a h2 beépített db sikeres bejelentkezéséhez kell
                .and()
                    .headers().frameOptions().sameOrigin(); // a h2 beépített db konzolának helyes megjelenítéséhez kell
    }

}
