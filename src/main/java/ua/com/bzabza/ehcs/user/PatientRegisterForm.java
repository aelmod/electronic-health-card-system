package ua.com.bzabza.ehcs.user;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import ua.com.bzabza.ehcs.city.City;
import ua.com.bzabza.ehcs.country.Country;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
public class PatientRegisterForm {

    @NotNull
    private String fullName;

    @NotNull
    @Size(min = 4, max = 12)
    private String login;

    @NotNull
    @Size(min = 8, max = 32)
    private String password;

    private transient String confirmPassword;

    @NotNull
    private Date birthday;

    @NotNull
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String phone;

    @NotNull
    private int countryId;

    @NotNull
    private int cityId;

    @NotNull
    private User.Sex sex;

    @NotNull
    private Integer doctorId;

    @AssertTrue(message = "The passwords you provided do not match. Please correct and resubmit.")
    private boolean isValidConfirmedPassword() {
        return Objects.equals(password, confirmPassword);
    }

    /**
     * In frontend helper - Available phone number formats:
     * +380630000000, 0630000000,
     * 063-000-0000 x1234, 063-000-0000 ext1234,
     * 063-000-0000, (063)-000-0000,
     * 063.000.0000, 063 000 0000
     */
    @AssertTrue(message = "Invalid phone number format")
    private boolean isValidPhoneNumber() {
        if (phone.matches("[0-9*#+() -]{13}")) return true;
        if (phone.matches("\\d{10}")) return true;
        if (phone.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
        if (phone.matches("\\d{3}[-.\\s]\\d{3}[-.\\s]\\d{4}")) return true;
        if (phone.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
        return false;
    }

    public User toPatient() {
        Country country = new Country();
        country.setId(countryId);
        City city = new City();
        city.setId(cityId);
        User doctor = new User();
        doctor.setId(doctorId);
        return new User();
    }
}