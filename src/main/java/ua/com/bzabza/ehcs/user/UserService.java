package ua.com.bzabza.ehcs.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.bzabza.ehcs.card.Card;
import ua.com.bzabza.ehcs.card.CardService;
import ua.com.bzabza.ehcs.exception.EntityAlreadyExistsException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final CardService cardService;

    @Autowired
    public UserService(UserRepository userRepository, CardService cardService) {
        this.userRepository = userRepository;
        this.cardService = cardService;
    }

    @Transactional(readOnly = true)
    public List<User> findBy(UserSpecification userSpecification) {
        return userRepository.findBy(userSpecification);
    }

    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @Transactional(readOnly = true)
    public User getByPk(Integer id) {
        return userRepository.findOneByPk(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return userRepository.findOneByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @Transactional
    public User save(User user) {
        if (!isUsernameExists(user.getUsername())) {
            String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.persist(user);
            return user;
        } else {
            throw new EntityAlreadyExistsException("Username already exists");
        }
    }

    @Transactional
    public User savePatient(User user) {
        saveCard(user);
        return save(user);
    }

    private void saveCard(User user) {
        Card card = new Card();
        card.setPatient(user);
        cardService.save(card);
    }

    private boolean isUsernameExists(String username) {
        try {
            User userByUsername = getUserByUsername(username);
            return Objects.equals(username, userByUsername.getUsername());
        } catch (EntityNotFoundException ignored) {/* Exception handling not need for this situation */}
        return false;
    }
}
