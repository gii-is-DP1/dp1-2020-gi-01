package com.project.TabernasSevilla.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.TabernasSevilla.security.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/resources/**").permitAll()
			.antMatchers("/**").permitAll()
			.antMatchers("/register/**").permitAll()
			.antMatchers("/register/").permitAll()
			.antMatchers("/customer/**").hasAuthority("CUSTOMER")
			.antMatchers("/admin/**").hasAuthority("ADMIN")
			.antMatchers("/employee/**").hasAnyAuthority("ADMIN","MANAGER","WAITER","COOK")
			.antMatchers("/manager/**").hasAuthority("MANAGER")
			.antMatchers("/cook/**").hasAuthority("COOK")
			.antMatchers("/waiter/**").hasAuthority("WAITER")
			.antMatchers("/table/**").hasAnyAuthority("ADMIN","MANAGER","WAITER")
			.anyRequest().authenticated();
		http
	 		.formLogin()
	 		.loginPage("/login")
	 		.defaultSuccessUrl("/",true)
	 		.failureUrl("/login-error")
	 		.usernameParameter("username")
            .passwordParameter("password") 	
        .and()
	 		.logout()
	 		.logoutUrl("/logout")
	 		.logoutSuccessUrl("/");
	 	
	 	// Configuración para que funcione la consola de administración
		// de la BD H2 (deshabilitar las cabeceras de protección contra
		// ataques de tipo csrf y habilitar los framesets si su contenido
		// se sirve desde esta misma página.
		http.csrf().disable();
		//http.csrf().ignoringAntMatchers("/h2-console/**");
		http.headers().frameOptions().sameOrigin();
	}

	// USER LOGIN

	@Bean
	public UserDetailsService userDetailsService() {
		return super.userDetailsService();
	}


	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder, UserService userDetailsService){
	    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
	    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
	    daoAuthenticationProvider.setUserDetailsService(userDetailsService);
	    return daoAuthenticationProvider;
	}
	

}
