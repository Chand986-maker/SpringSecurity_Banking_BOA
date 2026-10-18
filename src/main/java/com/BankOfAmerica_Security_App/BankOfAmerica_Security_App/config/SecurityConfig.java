package com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.config;

import com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.filter.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity  // this annatation will be make used as part of method security for API methods call
public class SecurityConfig {

    @Autowired
    JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http){
        // if we make used authenticated will ask credientails to validate basicauth else it will not ask
       /* http.
                authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .httpBasic(withDefaults());*/
        // if i want to make free from authentication then we have to use like this
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth1 -> auth1
                        .requestMatchers("/h2-console").permitAll()
                        .requestMatchers(HttpMethod.POST, "/authenticate").permitAll()
                        .requestMatchers("/api/users/register").permitAll()
                        /*.requestMatchers("/api/bank").hasRole("ADMIN")*/
                        // we can write role based or permission based
                        /*.requestMatchers("").hasRole(Role.ADMIN.name())*/
                        // this is permission based authotiryes
                        /* .requestMatchers(HttpMethod.GET, "/api/bank/**").hasAnyAuthority(Permissions.BANK_READ.name())
                         .requestMatchers(HttpMethod.POST , "/api/bank/**").hasAnyAuthority(Permissions.BANK_WRITE.name())
                         .requestMatchers(HttpMethod.DELETE, "/api/bank/**").hasAnyAuthority(Permissions.BANK_DELETE.name())
 */
                        .anyRequest().authenticated()
                );

        http.addFilterBefore(jwtAuthFilter , UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService ,
                                                       PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(daoAuthenticationProvider);
    }
}
