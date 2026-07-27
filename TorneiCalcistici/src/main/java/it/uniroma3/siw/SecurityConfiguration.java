package it.uniroma3.siw;

import javax.sql.DataSource;

import org.springframework.http.HttpMethod;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    public static final String DEFAULT_ROLE = "ROLE_DEFAULT";
    public static final String ADMIN_ROLE = "ROLE_ADMIN";

    private final DataSource dataSource;

    public SecurityConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);

        manager.setUsersByUsernameQuery("SELECT username, password, 1 as enabled FROM credenziali WHERE username=?");

        manager.setAuthoritiesByUsernameQuery("SELECT username, role FROM credenziali WHERE username=?");

        return manager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> {

            authorize.requestMatchers(
                    "/admin", "/admin/**",

                    // Giocatore
                    "/giocatoreForm", "/giocatori",
                    "/giocatoreListModifica", "/giocatoreModifica/**", "/giocatoreSalvaModifica",

                    // Squadra - form e viste di gestione (NON "/squadre/**" intero:
                    // "/squadre/{id}" e' presumibilmente una pagina di dettaglio pubblica)
                    "/squadraForm",
                    "/squadraListModifica", "/squadraModifica/**", "/squadraElimina/**",

                    // Torneo - stessa attenzione per "/tornei/{id}"
                    "/tornei/torneoForm",
                    "/torneoListModifica", "/torneoModifica/**", "/torneoSalvaModifica",

                    // Partita
                    "/partitaForm", "/partite",
                    "/partiteListModifica", "/partitaModifica/**",
                    "/partitaListElimina", "/partitaElimina/**"
            ).hasAuthority(ADMIN_ROLE);

            // "/squadre" e "/tornei" senza id sono condivisi tra GET pubblico
            // (lista) e POST di creazione (solo admin): li distinguo per metodo.
            authorize.requestMatchers(HttpMethod.POST, "/squadre").hasAuthority(ADMIN_ROLE);
            authorize.requestMatchers(HttpMethod.POST, "/tornei").hasAuthority(ADMIN_ROLE);

            authorize.requestMatchers(
                    "/home",
                    "/logout.html",
                    "/commentoNew/**"
            ).authenticated();

            authorize.anyRequest().permitAll();
        });

        http.formLogin(form -> {
            form.loginPage("/login");
            form.defaultSuccessUrl("/home", true);
            form.failureUrl("/login?error=true");
            form.permitAll();
        });

        http.logout(logout -> {
            logout.logoutSuccessUrl("/home");
        });

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
