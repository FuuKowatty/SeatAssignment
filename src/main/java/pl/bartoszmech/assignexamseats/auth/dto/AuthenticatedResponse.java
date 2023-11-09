package pl.bartoszmech.assignexamseats.auth.dto;

import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@ResponseBody
public class AuthenticatedResponse {
    private String token;


    public AuthenticatedResponse() {
    }

    public AuthenticatedResponse(String token) {
        this.token = token;
    }

    private AuthenticatedResponse(Builder builder) {
        this.token = builder.token;
    }

    public String getToken() {
        return token;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String token;

        public Builder token(String token) {
            this.token = token;
            return this;
        }
        public AuthenticatedResponse build() {
            return new AuthenticatedResponse(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticatedResponse that = (AuthenticatedResponse) o;
        return Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "token='" + token + '\'' +
                '}';
    }
}