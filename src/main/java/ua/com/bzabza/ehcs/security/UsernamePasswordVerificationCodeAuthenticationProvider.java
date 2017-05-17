package ua.com.bzabza.ehcs.security;

import org.jboss.aerogear.security.otp.Totp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ua.com.bzabza.ehcs.role.Role;
import ua.com.bzabza.ehcs.user.User;
import ua.com.bzabza.ehcs.user.UserService;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UsernamePasswordVerificationCodeAuthenticationProvider implements AuthenticationProvider {

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private final UserService userService;

    @Autowired
    public UsernamePasswordVerificationCodeAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User userByUsername;
        try {
            userByUsername = userService.getUserByUsername(authentication.getPrincipal().toString());
        } catch (EntityNotFoundException e) {
            throw new BadCredentialsException("auth error");
        }

        UsernamePasswordVerificationCodeAuthentication usernamePasswordVerificationCodeAuthentication =
                (UsernamePasswordVerificationCodeAuthentication) authentication;

        if (Objects.equals(authentication.getPrincipal(), userByUsername.getUsername())
                && bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), userByUsername.getPassword())
                && new Totp(userByUsername.getSecret()).verify(usernamePasswordVerificationCodeAuthentication.getVerificationCode())) {

            Set<String> roles = userByUsername.getRoles().stream().map(Role::getAuthority).collect(Collectors.toSet());
            String token = JwtAuthHelper.createJwt(userByUsername.getId(), roles);
            return new SsnJwtAuthentication(token);
        }
        throw new BadCredentialsException("auth error");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordVerificationCodeAuthentication.class);
    }
}
