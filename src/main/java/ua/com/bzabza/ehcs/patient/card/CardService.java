package ua.com.bzabza.ehcs.patient.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Transactional
    public void save(Card card) {
        cardRepository.persist(card);
    }

    public Card getByPk(Integer id) {
        return cardRepository.findOneByPk(id).orElseThrow(EntityNotFoundException::new);
    }
}
