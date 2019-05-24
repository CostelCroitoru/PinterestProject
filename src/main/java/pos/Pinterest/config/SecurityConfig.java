package pos.Pinterest.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pos.Pinterest.services.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                    .antMatchers("/webjars/**", "/css/loginStyle.css", "/js/**").permitAll()
                    .antMatchers(  "/public").permitAll()
                    .antMatchers("/images/background_image.jpg").permitAll()
                    .antMatchers("/index").permitAll()
                    .antMatchers("/admin").hasAuthority("ADMIN")
                    .antMatchers("/perform_register", "/perform_login").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                        .loginPage("/login")
                        //    .usernameParameter("username").passwordParameter("password")
                        //.loginProcessingUrl("/perform_login")
                       // .defaultSuccessUrl("/index")
                        .permitAll()
                .and()
                    .logout()
                        .logoutUrl("/perform_logout")
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/login?logout")
                .and()
                    .exceptionHandling().accessDeniedPage("/403");

        http.csrf().disable();

    }



    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

}
