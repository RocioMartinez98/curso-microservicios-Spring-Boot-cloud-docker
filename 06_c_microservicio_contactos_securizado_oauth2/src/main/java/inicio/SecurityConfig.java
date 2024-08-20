package inicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	@Autowired
    private  JwtAuthConverter jwtAuthConverter;  

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf(cus->cus.disable())
		.authorizeHttpRequests(aut->
			aut.requestMatchers(HttpMethod.POST,"/contactos").hasRole("ADMIN")
			.requestMatchers(HttpMethod.DELETE,"/contactos/**").hasAnyRole("ADMIN","OPERATOR")
			.requestMatchers("/contactos").authenticated()
			.anyRequest().permitAll())
		.oauth2ResourceServer(oauth2ResourceServer->
        	oauth2ResourceServer.jwt(jwt->jwt
            .jwtAuthenticationConverter(jwtAuthConverter)))                                             
            .sessionManagement(sessionManagement->
                  sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); 
		return http.build();
	}
}
