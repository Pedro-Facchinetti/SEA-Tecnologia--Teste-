package com.pedrofacchinetti.TesteBackEnd;

import com.pedrofacchinetti.TesteBackEnd.enums.UserRole;
import com.pedrofacchinetti.TesteBackEnd.model.User;
import com.pedrofacchinetti.TesteBackEnd.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class TesteBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(TesteBackEndApplication.class, args);
    }

    @Bean
public CommandLineRunner initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    return args -> {
        // Verifica se o usuário Admin já existe
        if (userRepository.findByUsername("Admin") == null) {
            User admin = new User();
            admin.setUsername("Admin");
            admin.setPassword(passwordEncoder.encode("123qwel@#"));
            admin.setRole(UserRole.ADMIN); // Use o enum aqui
            userRepository.save(admin);
        }

        // Verifica se o usuário Padrão já existe
        if (userRepository.findByUsername("Padrão") == null) {
            User user = new User();
            user.setUsername("Padrão");
            user.setPassword(passwordEncoder.encode("123qwe123"));
            user.setRole(UserRole.PADRAO); // Use o enum aqui
            userRepository.save(user);
        }
    };
}

}
