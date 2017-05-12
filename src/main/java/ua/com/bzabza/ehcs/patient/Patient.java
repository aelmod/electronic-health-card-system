package ua.com.bzabza.ehcs.patient;

import lombok.Setter;
import ua.com.bzabza.ehcs.card.Card;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import java.util.Date;

@Setter
@Entity
public class Patient {

    private String fullName;

    private Date birthday;

    public enum Sex {
        MALE, FEMALE;
    }

    @Enumerated(EnumType.STRING)
    private Sex sex;

//    private City city;

//    private User user;

    private String phone;

    private String email;

    private String description;

    @OneToOne(mappedBy = "patient")
    private Card card;

    private String photo;
}
