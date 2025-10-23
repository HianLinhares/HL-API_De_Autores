package Hian.Linhares.HL_API_De_Autores.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
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
                .csrf(AbstractHttpConfigurer::disable)  //habilitando o acesso de outras aplicações a essa API
                .httpBasic(Customizer.withDefaults()) //habilitando http basic (autenticação através do chrome ou do postman)
                .formLogin(configurer -> {
                    configurer.loginPage("/login").permitAll(); //criando o próprio formulário de login para autenticação
                })
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/autores/**").hasAnyRole("ADMIN", "USER"); // adicionando uma role de acesso a essa API
                    authorize.requestMatchers(HttpMethod.POST, "/usuarios/**").permitAll();
                    authorize.requestMatchers(HttpMethod.DELETE, "/autores/**").hasRole("ADMIN"); //Sinalizando que apenas a role de admin pode realizar um delete
                    authorize.anyRequest().authenticated(); //definindo que todas as requisições a API precisam estar autenticadas
                })
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
        UserDetails user2 = User.builder().username("usuario2").password(encoder.encode("321")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user1, user2);
    }

         /*
        para utilizar o CustomUserDetailsService utilizar:
        parâmetro: (UsuárioService usuarioService)
        retorno: return new CustomUserDetailsService(usuarioService)
         */


    //Excluindo do filtro do security a interface do Swagger para realização da documentação
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> {
            web.ignoring().requestMatchers(
                    "/vw/api-docs",
                    "/v3/api-docs/**",
                    "/swagger-resources/**",
                    "/swagger-ui.html",
                    "swagger-ui/**",
                    "/webjars/**",
                    "/actuator/**"
            );
        };
    }


}


