package ua.com.bzabza.ehcs.user;

import lombok.Getter;

@Getter
public class User2faToken {

    private String QR;

    private String secretKey;

    public User2faToken(String QR, String secretKey) {
        this.QR = QR;
        this.secretKey = secretKey;
    }
}
