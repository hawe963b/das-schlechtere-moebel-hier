// 
// Decompiled by Procyon v0.5.36
// 

package kickstart;

import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.context.annotation.Configuration;
import org.salespointframework.SalespointSecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.salespointframework.EnableSalespoint;

@EnableSalespoint
public class Application
{
    public static void main(final String[] args) {
        SpringApplication.run((Class)Application.class, args);
    }
    
    @Configuration
    static class WebSecurityConfiguration extends SalespointSecurityConfiguration
    {
        protected void configure(final HttpSecurity http) throws Exception {
            http.csrf().disable();
            ((HttpSecurity)((FormLoginConfigurer)((HttpSecurity)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)http.authorizeRequests().antMatchers(new String[] { "/**" })).permitAll().and()).formLogin().loginProcessingUrl("/login")).and()).logout().logoutUrl("/logout").logoutSuccessUrl("/");
        }
    }
}
