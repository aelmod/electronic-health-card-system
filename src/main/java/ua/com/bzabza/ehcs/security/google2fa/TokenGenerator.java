package ua.com.bzabza.ehcs.security.google2fa;

import ua.com.bzabza.ehcs.UserEntity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TokenGenerator<Entity extends UserEntity> {

    private final static String QR_PREFIX =
            "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=";

    private final static String APP_NAME = "ElectronicHealthCardSystem";

    public User2faToken generateQRUrl(Entity entity) {
        try {
            String qr = QR_PREFIX + URLEncoder.encode(String.format("otpauth://totp/%s:%s?secret=%s&issuer=%s",
                    APP_NAME, entity.getEmail(), entity.getSecret(), APP_NAME), "UTF-8");
            return new User2faToken(qr, entity.getSecret());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
