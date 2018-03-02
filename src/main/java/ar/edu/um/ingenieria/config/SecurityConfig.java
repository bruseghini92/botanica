package ar.edu.um.ingenieria.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ar.edu.um.ingenieria.service.impl.UsuarioSecurityServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private UsuarioSecurityServiceImpl usuarioSecurityServiceImpl;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioSecurityServiceImpl)
        .passwordEncoder(encoder());
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/**").permitAll()
		.antMatchers("/admin/**").authenticated()
		.antMatchers("/ventas/**").authenticated()
		.and().formLogin()
				.loginPage("/login")
				.failureUrl("/login?error=true")
				.loginProcessingUrl("/j_spring_security_check")
                .defaultSuccessUrl("/")	
		.and().logout() 
		.invalidateHttpSession(true)
		.logoutSuccessUrl("/login")
		.deleteCookies("JSESSIONID")
		.and().exceptionHandling();
	} 
	
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	
	
	
	
}
