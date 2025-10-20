package Hian.Linhares.HL_API_De_Autores.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) //habilitando o acesso de outras aplicações a essa API
                .formLogin(configurer -> configurer.loginPage("/login").permitAll()) //criando o próprio formulário de login para autenticação
                .httpBasic(Customizer.withDefaults()) //habilitando http basic (autenticação através do chrome ou do postman)
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated()) //definindo que todas requisições a API precisam estar autenticadas
                .build();
    }

    //Utilizando usuários em memória para configurações de segurança


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails user1 = User.builder().username("usuario").password(encoder.encode("123")).roles("USER").build();
        UserDetails user2 = User.builder().username("usuario2").password(encoder.encode("321")).roles("USER").build();
        return new InMemoryUserDetailsManager(user1, user2);
    }


}
