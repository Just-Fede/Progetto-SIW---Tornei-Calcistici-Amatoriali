package it.uniroma3.siw;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration 
{

	public static final String DEFAULT_ROLE = "ROLE_DEFAULT";
	public static final String ADMIN_ROLE = "ROLE_ADMIN";
	
	private final DataSource dataSource;
	
	  public SecurityConfiguration(DataSource dataSource) 
	  {
		    this.dataSource = dataSource;
	  }
	  
	  @Bean
	  public UserDetailsService userDetailsService() 
	  {
	    JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
	    
	    manager.setUsersByUsernameQuery
	    ("SELECT username, password, 1 as enabled FROM credenziali WHERE username=?");
	    
	    manager.setAuthoritiesByUsernameQuery
	    ("SELECT username, role FROM credenziali WHERE username=?");
	    
	    return manager;
	  }
	  
	  @Bean
	  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	      http
		      .csrf(csrf -> csrf.disable())
		      .formLogin(form -> form
		          .loginPage("/login")
		          .permitAll()
		      )
	          .authorizeHttpRequests(authorize -> {

	              authorize.requestMatchers(
	                      HttpMethod.GET,
	                      "/",
	                      "/index",
	                      "/register",
	                      "/css/**"
	              ).permitAll();

	              authorize.requestMatchers(
	                      HttpMethod.POST,
	                      "/register",
	                      "/login"
	              ).permitAll();

	              authorize.requestMatchers(
	                      HttpMethod.GET,
	                      "/admin/**"
	              ).hasAuthority(ADMIN_ROLE);

	              authorize.requestMatchers(
	                      HttpMethod.POST,
	                      "/admin/**"
	              ).hasAuthority(ADMIN_ROLE);

	              authorize.anyRequest().authenticated();
	          })

	          .formLogin(form -> {
	              form.loginPage("/login").permitAll();
	              form.defaultSuccessUrl("/home", true);
	              form.failureUrl("/login?error=true");
	          })

	      	.logout(logout -> {
	    	    logout.logoutUrl("/logout");
	    	    logout.logoutSuccessUrl("/");
	    	    logout.permitAll();
	    	});
	      
	      return http.build();
	  }
	  
	  @Bean
	  public PasswordEncoder passwordEncoder() 
	  {
	      return new BCryptPasswordEncoder();
	  }
	  
}
