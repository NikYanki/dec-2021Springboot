package com.example.dec2021springboot.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.*;
import java.io.IOException;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecuriyConfigs extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http = http.csrf().disable();
        http = http.authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/").permitAll()//дозволяємо доступ всім
                .antMatchers(HttpMethod.POST, "/").authenticated()//долволяємо доступ аутинтифікованим користувачам
                //.antMatchers(HttpMethod.POST,"/").hasRole("ADMIN").and();//долволяємо доступ користувачам з ролю адмін
                .antMatchers(HttpMethod.POST, "/users").hasAnyRole("ADMIN", "MANAGER").and();
        http = http.httpBasic().and();//базова аутентивікація
        http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()//для того щоб сесії не кешувалися
              .cors().configurationSource(corsConfigurationSource()).and();
                //можна впровадити ще додатково фільтри
//                .addFilterBefore(new Filter() {
//                    @Override
//                    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//                        System.out.println("filrer works");
//
//                        filterChain.doFilter(servletRequest, servletResponse);
//                    }
//                }, UsernamePasswordAuthenticationFilter.class);//щось не так
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:3000"));
        //дозволяємо робити запити з запущених ангулярівських та реактівських серверів
        configuration.addAllowedHeader("*");
        //дозволяємо всі хедери
        configuration.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PATCH.name(),
                HttpMethod.DELETE.name()));
        // дозволяємо методи
        configuration.addExposedHeader("Autorization");
        // робимо хедер Autorization видимим, в ньому токін
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        //застосовуємо наші налаштування(в данному випадку для усіх урл)
        return source;
    }

//    @Bean//робимо бін обєкт некодеру пасворда
//    public PasswordEncoder passwordEncoder(){
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        return bCryptPasswordEncoder;
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN")
                // в память аплікухи записуємо роль адмін з відповідними юзернейм і пасвордом
                .and()
                .withUser("manager").password("{noop}manager").roles("MANAGER");
    }
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder())
//                .withUser("admin").password("admin").roles("ADMIN")
//                // в память аплікухи записуємо роль адмін з відповідними юзернейм і пасвордом
//                .and()
//                .withUser("manager").password("manager").roles("MANAGER");
//    }
}
