package ua.com.bzabza.ehcs.card.record;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.bzabza.ehcs.card.Card;
import ua.com.bzabza.ehcs.security.CurrentUser;
import ua.com.bzabza.ehcs.user.User;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/card/{id}/records")
public class RecordController {

    private final RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping
    @JsonView(Record.FullView.class)
    public void create(@CurrentUser User user,
                       @RequestBody @Valid RecordForm recordForm,
                       @PathVariable Integer id) {
//        recordForm.setUser(user);
        Record record = recordForm.toMicroblog();
        Card card = new Card();
        card.setId(id);
        record.setCard(card);
        record.getUsers().add(user);
        recordService.create(record);
        recordService.getByPk(record.getId());
    }

    @GetMapping("{cardRecordId}")
    @JsonView(Record.FullView.class)
    public Record getById(@PathVariable int cardRecordId) {
        return recordService.getByPk(cardRecordId);
    }
}
