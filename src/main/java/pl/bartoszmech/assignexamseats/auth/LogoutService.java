package pl.bartoszmech.assignexamseats.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import pl.bartoszmech.assignexamseats.auth.token.TokenRepository;

@Service
public class LogoutService implements LogoutHandler {

    private final TokenRepository tokenRepository;
    public LogoutService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        final String authHeader = request.getHeader("Authorization");
        if(StringUtils.isEmpty(authHeader) || !org.apache.commons.lang3.StringUtils.startsWith(authHeader, "Bearer ")){
            return;
        }
        final String jwt = authHeader.substring(7);
        tokenRepository
                .findByToken(jwt)
                .ifPresent(tokenRepository::deleteToken);
    }
}
