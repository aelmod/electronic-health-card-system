package ua.com.bzabza.ehcs.microblog;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.bzabza.ehcs.patient.card.Card;
import ua.com.bzabza.ehcs.security.CurrentUser;
import ua.com.bzabza.ehcs.user.User;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/card/{id}/records")
public class MicroblogController {

    private final MicroblogService microblogService;

    @Autowired
    public MicroblogController(MicroblogService microblogService) {
        this.microblogService = microblogService;
    }

    @PostMapping("create")
    @JsonView(Microblog.FullView.class)
    public Microblog create(@CurrentUser User user,
                            @RequestBody @Valid MicroblogForm microblogForm,
                            @PathVariable Integer id) {
//        microblogForm.setUser(user);
        Microblog microblog = microblogForm.toMicroblog();
        Card card = new Card();
        card.setId(id);
        microblog.setCard(card);
        microblog.getUsers().add(user);
        microblogService.create(microblog);
        return microblogService.getByPk(microblog.getId());
    }

    @GetMapping("{microblogId}")
    @JsonView(Microblog.FullView.class)
    public Microblog getById(@PathVariable int microblogId) {
        return microblogService.getByPk(microblogId);
    }
}
