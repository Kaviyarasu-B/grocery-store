
package GroceryStore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth
            		.anyRequest().authenticated()
            )
            .exceptionHandling(exceptions -> exceptions
                .defaultAuthenticationEntryPointFor(
                    new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
                    request -> request.getRequestURI().startsWith("/api/")
                )
            )
            .formLogin(Customizer.withDefaults())
            .logout(Customizer.withDefaults())
            .csrf(csrf -> csrf
            	    .csrfTokenRepository(
            	        CookieCsrfTokenRepository.withHttpOnlyFalse()
            	    )
            	    .csrfTokenRequestHandler(
            	        new org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler()
            	    )
            	);

        return http.build();
    }
}
