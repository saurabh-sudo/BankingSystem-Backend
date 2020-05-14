package com.banking.OnlineBanking.config;



import com.common.BankData.dao.CustomerDao;
import com.common.BankData.service.AuthenticationProvider;
import com.common.BankData.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = CustomerDao.class)
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserSecurityService userSecurityService;

    AuthenticationProvider provider;

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new PasswordEncoder(){
//                    @Override
//                    public String encode(CharSequence charSequence) {
//                        return charSequence.toString();
//                    }
//                    @Override
//                    public boolean matches(CharSequence chaarSequence, String s) {
//                        return encode(charSequence).equals(s);
//                    }
//                };
//    }

    private static final RequestMatcher PROTECTED_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/accounts/**")
    );


    private static final String[] PUBLIC_MATCHERS = {
            "/webjars/**",
            "/css/**",
            "/js/**",
            "/images/**",
            "/",
           // "/login",
            "/about/**",
            "/contact/**",
            "/error/**/*",
            "/console/**",
            "/signup"
    };

    @Bean
    AuthenticationFilter authenticationFilter() throws Exception {
        final AuthenticationFilter filter = new AuthenticationFilter(PROTECTED_URLS);
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }


    public SecurityConfig(final AuthenticationProvider authenticationProvider) {
        super();
        this.provider = authenticationProvider;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(provider);
    }

    @Override
    public void configure(final WebSecurity webSecurity) {
      webSecurity.ignoring().antMatchers("/login/**");
        webSecurity.ignoring().antMatchers("**/secured/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
                .authenticationProvider(provider)
                .addFilterBefore(authenticationFilter(), AnonymousAuthenticationFilter.class)
                .authorizeRequests()
                .requestMatchers(PROTECTED_URLS)
                .authenticated()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .logout().disable();
    }



    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
  //   auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
    }

}