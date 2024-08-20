package inicio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	AuthenticationManager auth;
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration conf) throws Exception{
		auth=conf.getAuthenticationManager();
		return auth;
	}
	
	/*@Bean
	public InMemoryUserDetailsManager usersdetais() throws Exception{
		List<UserDetails> users=List.of(
				User
				.withUsername("user1")
				//.password("$2a$12$YUq1fO2Vbz.ONbIo./xmBeGCYFr5m4OLNC8H9HFafn4fpcOnUbqda")
				.password("{noop}user1")
				.roles("USERS")
				.build(),
				User
				.withUsername("user2")
				.password("{noop}user2")
				.roles("OPERATOR")
				.build(),
				User
				.withUsername("admin")
				.password("{noop}admin")
				.roles("USERS","ADMIN")
				.build());		
		return new InMemoryUserDetailsManager(users);					
	}*/
	@Bean
	public JdbcUserDetailsManager usersDetailsJdbc() {
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3307/springsecurity?serverTimezone=UTC");
		ds.setUsername("root");
		ds.setPassword("root");
		JdbcUserDetailsManager jdbcDetails=new JdbcUserDetailsManager(ds);
		
		jdbcDetails.setUsersByUsernameQuery("select user, pwd, enabled"
           	+ " from users where user=?");
		jdbcDetails.setAuthoritiesByUsernameQuery("select user, rol "
           	+ "from roles where user=?");
		return jdbcDetails;
	}
	/*@Bean //solo si se va a utilizar encriptación
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}*/
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf(cus->cus.disable())
		.authorizeHttpRequests(aut->
			aut.requestMatchers(HttpMethod.POST,"/contactos").hasRole("ADMIN")
			.requestMatchers(HttpMethod.DELETE,"/contactos/**").hasAnyRole("ADMIN","OPERATOR")
			.requestMatchers("/contactos").authenticated()
			.anyRequest().permitAll()
			)
		.addFilter(new JWTAuthorizationFilter(auth));
		return http.build();
	}
}
