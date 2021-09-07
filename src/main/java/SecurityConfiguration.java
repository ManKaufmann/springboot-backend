import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth
                        .inMemoryAuthentication()
                        .withUser( "admin" ).password(passwordEncoder().encode("admin")).roles( "ADMIN" )
                        .and()
                        .withUser("user").password(passwordEncoder().encode("user")).roles("USER");





    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
			.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/**").hasRole("USER");

                //.anyRequest().authenticated();
    }
    /*@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods(
                        HttpMethod.OPTIONS.name(),
                        HttpMethod.GET.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.POST.name());
    }
*/
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200","http://localhost:50148/"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
