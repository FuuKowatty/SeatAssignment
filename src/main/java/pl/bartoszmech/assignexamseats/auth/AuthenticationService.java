package pl.bartoszmech.assignexamseats.auth;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.bartoszmech.assignexamseats.auth.dto.LoginRequest;
import pl.bartoszmech.assignexamseats.auth.dto.RegisterRequest;
import pl.bartoszmech.assignexamseats.auth.token.JwtService;
import pl.bartoszmech.assignexamseats.auth.dto.LoginResponse;
import pl.bartoszmech.assignexamseats.auth.token.Token;
import pl.bartoszmech.assignexamseats.auth.token.TokenRepository;
import pl.bartoszmech.assignexamseats.exception.AuthenticationErrorException;
import pl.bartoszmech.assignexamseats.exception.UserEmailTakenException;
import pl.bartoszmech.assignexamseats.model.User;
import pl.bartoszmech.assignexamseats.repository.UserRepository;

import java.util.Optional;

import static pl.bartoszmech.assignexamseats.auth.token.TokenType.BEARER;
import static pl.bartoszmech.assignexamseats.exception.AuthenticationError.EMAIL;
import static pl.bartoszmech.assignexamseats.exception.AuthenticationError.PASSWORD;

@Service
public class AuthenticationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;




    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.tokenRepository = tokenRepository;
    }

    public LoginResponse register(RegisterRequest request) {
        emailCheck(request.getEmail());
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        var savedUser = userRepository.save(user);
        LOGGER.info("User successfully created with id " + user.getId());
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return LoginResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void removeUserTokenIfExists(User user) {
        Optional<Token> tokenToDelete = tokenRepository.findByUser(user);
        if (tokenToDelete.isPresent()) {
            Token deletedToken = tokenToDelete.get();
            tokenRepository.delete(deletedToken);
        }
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(BEARER)
                .build();
        tokenRepository.save(token);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthenticationErrorException(EMAIL, "Invalid email"));
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword())
            );
            removeUserTokenIfExists(user);
            var jwtToken = jwtService.generateToken(user);
            saveUserToken(user, jwtToken);
            return LoginResponse.builder()
                    .token(jwtToken)
                    .build();
        } else {
            throw new AuthenticationErrorException(PASSWORD, "Invalid password");
        }
    }

    public void emailCheck(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new UserEmailTakenException("email has already taken");
        }
    }

}
