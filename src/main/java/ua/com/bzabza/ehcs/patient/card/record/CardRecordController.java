package ua.com.bzabza.ehcs.patient.card.record;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.bzabza.ehcs.patient.card.Card;
import ua.com.bzabza.ehcs.security.CurrentUser;
import ua.com.bzabza.ehcs.user.User;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/card/{id}/records")
public class CardRecordController {

    private final CardRecordService cardRecordService;

    @Autowired
    public CardRecordController(CardRecordService cardRecordService) {
        this.cardRecordService = cardRecordService;
    }

    @PostMapping
    @JsonView(CardRecord.FullView.class)
    public void create(@CurrentUser User user,
                       @RequestBody @Valid CardRecordForm cardRecordForm,
                       @PathVariable Integer id) {
//        cardRecordForm.setUser(user);
        CardRecord cardRecord = cardRecordForm.toMicroblog();
        Card card = new Card();
        card.setId(id);
        cardRecord.setCard(card);
        cardRecord.getUsers().add(user);
        cardRecordService.create(cardRecord);
        cardRecordService.getByPk(cardRecord.getId());
    }

    @GetMapping("{cardRecordId}")
    @JsonView(CardRecord.FullView.class)
    public CardRecord getById(@PathVariable int cardRecordId) {
        return cardRecordService.getByPk(cardRecordId);
    }
}
