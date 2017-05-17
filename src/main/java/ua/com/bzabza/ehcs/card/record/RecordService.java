package ua.com.bzabza.ehcs.card.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RecordService {

    private final RecordRepository recordRepository;

    @Autowired
    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Transactional(readOnly = true)
    public List<Record> findBy(RecordSpecification recordSpecification) {
        return recordRepository.findBy(recordSpecification);
    }

    @Transactional(readOnly = true)
    public Record getByPk(Integer id) {
        return recordRepository.findOneByPk(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void create(Record record) {
        recordRepository.persist(record);
    }
}
