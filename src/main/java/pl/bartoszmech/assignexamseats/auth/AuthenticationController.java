package pl.bartoszmech.assignexamseats.auth;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bartoszmech.assignexamseats.auth.dto.LoginResponse;
import pl.bartoszmech.assignexamseats.auth.dto.LoginRequest;
import pl.bartoszmech.assignexamseats.auth.dto.RegisterRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @PostMapping(value = "/register")
    public LoginResponse register(@Valid @RequestBody RegisterRequest request){
        return authenticationService.register(request);
    }
    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        return ResponseEntity.ok(authenticationService.login(request));
    }
}
