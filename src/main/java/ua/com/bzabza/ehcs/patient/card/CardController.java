package ua.com.bzabza.ehcs.patient.card;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/card")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @JsonView(Card.FullView.class)
    @GetMapping("{id}")
    public Card getCardById(@PathVariable Integer id) {
        return cardService.getByPk(id);
    }
}
