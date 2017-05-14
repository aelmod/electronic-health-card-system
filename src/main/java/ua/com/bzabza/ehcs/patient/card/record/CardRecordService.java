package ua.com.bzabza.ehcs.patient.card.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CardRecordService {

    private final CardRecordRepository cardRecordRepository;

    @Autowired
    public CardRecordService(CardRecordRepository cardRecordRepository) {
        this.cardRecordRepository = cardRecordRepository;
    }

    @Transactional(readOnly = true)
    public List<CardRecord> findBy(CardRecordSpecification cardRecordSpecification) {
        return cardRecordRepository.findBy(cardRecordSpecification);
    }

    @Transactional(readOnly = true)
    public CardRecord getByPk(Integer id) {
        return cardRecordRepository.findOneByPk(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void create(CardRecord cardRecord) {
        cardRecordRepository.persist(cardRecord);
    }
}
