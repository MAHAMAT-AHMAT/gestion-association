package ma.casascolarisation.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableMethodSecurity // Pour activer les annotations @PreAuthorize
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // à activer si tu veux protéger les formulaires
                .authorizeHttpRequests(auth -> auth

                        // 🔓 Autoriser Swagger UI et OpenAPI (important pour accéder sans login)
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // Authentification publique
                        .requestMatchers("/api/auth/**").permitAll()

                        // ADMIN : accès complet
                        .requestMatchers("/api/utilisateurs/**").hasRole("ADMIN")
                        .requestMatchers("/api/eleves/**").hasRole("ADMIN")
                        .requestMatchers("/api/donateurs/**").hasRole("ADMIN")
                        .requestMatchers("/api/dons/**").hasAnyRole("ADMIN", "COMPTABLE")
                        .requestMatchers("/api/factures/**").hasAnyRole("ADMIN", "COMPTABLE")
                        .requestMatchers("/api/depenses/**").hasAnyRole("ADMIN", "COMPTABLE")

                        // COMPTABLE : pas accès aux utilisateurs ni élèves/donateurs
                        .requestMatchers("/api/dons/**").hasAnyRole("COMPTABLE")
                        .requestMatchers("/api/factures/**").hasAnyRole("COMPTABLE")
                        .requestMatchers("/api/depenses/**").hasAnyRole("COMPTABLE")

                        // SUPERVISEUR : accès en lecture seulement, à gérer dans les méthodes @PreAuthorize si besoin
                        .requestMatchers("/api/eleves/**").hasRole("SUPERVISEUR")
                        .requestMatchers("/api/donateurs/**").hasRole("SUPERVISEUR")
                        .requestMatchers("/api/dons/**").hasRole("SUPERVISEUR")
                        .requestMatchers("/api/factures/**").hasRole("SUPERVISEUR")
                        .requestMatchers("/api/depenses/**").hasRole("SUPERVISEUR")

                        // DONATEUR : uniquement ses propres données
                        .requestMatchers("/api/dons/mes-dons").hasRole("DONATEUR")
                        .requestMatchers("/api/factures/mes-factures").hasRole("DONATEUR")

                        // Les autres requêtes doivent être authentifiées
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()); // ou formLogin() ou JWT selon ton projet

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // À adapter si tu utilises JWT ou un UserDetailsService personnalisé
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
