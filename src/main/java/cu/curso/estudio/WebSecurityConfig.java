package cu.curso.estudio;
import cu.curso.estudio.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
}

   @Bean
   public PasswordEncoder passwordEncoder(){
       BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder(4);
       return bCryptPasswordEncoder;

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
             http.csrf().disable().httpBasic()
                     .and()
                     .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
               // .formLogin()
                     //.permitAll()
              //  .loginPage("/login")
               // .defaultSuccessUrl("/AdminUser/usuarios")
               // .failureUrl("/inicio?error=true")
              //  .usernameParameter("username")
               // .passwordParameter("password");


    }
}