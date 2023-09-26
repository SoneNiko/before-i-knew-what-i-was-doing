package eu.brickpics.maurice.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final String LOGIN_PROCESSING_URL = "/login";
	private static final String LOGIN_FAILURE_URL = "/login?error";
	private static final String LOGIN_URL = "/login";
	private static final String LOGOUT_SUCCESS_URL = "/login?logout";

	@Autowired
	Environment env;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (Arrays.stream(env.getActiveProfiles()).anyMatch(s -> s.equalsIgnoreCase("prod"))) {
			http.requiresChannel().anyRequest().requiresSecure();
		}
		http.csrf().disable()
				.requestCache().requestCache(new CustomRequestCache())

				.and().authorizeRequests()

				.requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll()

				.anyRequest().authenticated()
//				.antMatchers("/admin").hasAuthority("ADMIN")

				.and().formLogin().loginPage(LOGIN_URL).permitAll().loginProcessingUrl(LOGIN_PROCESSING_URL)
				.failureUrl(LOGIN_FAILURE_URL)
//				.and().rememberMe().key(
//						"6w9z$C&F)J@NcQfTjWnZr4u7x!A%D*G-KaPdSgUkXp2s5v8y/B?E(H+MbQeThWmZq3t6w9z$C&F)J@NcRfUjXn2r5u7x" +
//								"!A%D*G-KaPdSgVkYp3s6v9y/B?E(H+MbQeThWmZq4t7w!z%C&F)J@NcRfUjXn2r5u8x/A?D(G-KaPdSgVkYp" +
//								"3s6v9y$B&E)H@MbQeThWmZq4t7w!z%C*F-JaNdRfUjXn2r5u8x/A?D(G+KbPeShVkYp3s6v9y$B&E)H@McQf" +
//								"TjWnZq4t7w!z%C*F-JaNdRgUkXp2s5u8x/A?D(G+KbPeShVmYq3t6w9y$B&E)H@McQfTjWnZr4u7x!A%C*F-" +
//								"JaNdRgUkXp2s5v8y/B?E(G+KbPeShVmYq3t6w9z$C&F)J@McQfTjWnZr4u7x!A%D*G-KaPdRgUkXp2s5v8y/" +
//								"B?E(H+MbQeThVmYq3t6w9z$C&F)J@NcRfUjXnZr4u7x!A%D*G-KaPdSgVkYp3s5v8y/B?E(H+MbQeThWmZq4").alwaysRemember(true)
				.and().logout().logoutSuccessUrl(LOGOUT_SUCCESS_URL);

	}

	@Autowired
	public DataSource dataSource;

//	@Bean
//	DataSource dataSource() {
//		return DataSourceBuilder.create().build();
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery("SELECT username,password,enabled FROM maurice_users WHERE username = ?")
				.authoritiesByUsernameQuery("SELECT username,authority FROM maurice_authorities WHERE username = ?");
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers(
				"/VAADIN/**",
				"/favicon.ico",
				"/robots.txt",
				"/manifest.webmanifest",
				"/sw.js",
				"/offline-page.html",
				"/icons/**",
				"/images/**",
				"/frontend/**",
				"/webjars/**",
				"/h2-console/**",
				"/frontend-es5/**", "/frontend-es6/**");
	}


}
