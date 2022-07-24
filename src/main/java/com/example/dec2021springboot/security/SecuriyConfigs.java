package com.example.dec2021springboot.security;

import com.example.dec2021springboot.dao.CastomerDAO;
import com.example.dec2021springboot.models.Castomer;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecuriyConfigs extends WebSecurityConfigurerAdapter {
    CastomerDAO castomerDAO;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Castomer castomer = castomerDAO.findByLogin(username);
                System.out.println(username);
                User user = new User(castomer.getLogin(),
                        castomer.getPassword(),
                        castomer.getRoles().stream()
                                .map(role ->
                                new SimpleGrantedAuthority(role.name())).collect(Collectors.toList()));
               // return castomerDAO.findByLogin(username);
                return user;
            }
        });
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http = http.csrf().disable();
        http = http.authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/").permitAll()//дозволяємо доступ всім
                .antMatchers(HttpMethod.POST, "/").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/users").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers( "/users").hasAnyRole("ADMIN", "MANAGER", "USER").and();

        http = http.httpBasic().and();//базова аутентивікація
        http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()//для того щоб сесії не кешувалися
              .cors().configurationSource(corsConfigurationSource()).and();
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
}
