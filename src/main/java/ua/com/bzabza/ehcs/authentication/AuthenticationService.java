package ua.com.bzabza.ehcs.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ua.com.bzabza.ehcs.security.SsnJwtAuthentication;
import ua.com.bzabza.ehcs.security.UsernamePasswordVerificationCodeAuthentication;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public String authenticate(LoginPasswordVerificationCodeForm loginPasswordVerificationCodeForm) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordVerificationCodeAuthentication(
                loginPasswordVerificationCodeForm.getLogin(),
                loginPasswordVerificationCodeForm.getPassword(),
                loginPasswordVerificationCodeForm.getVerificationCode()
        ));

        return ((SsnJwtAuthentication) authenticate).getToken();
    }
}
