package ua.com.bzabza.ehcs.card.record.commentary;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.bzabza.ehcs.security.CurrentUser;
import ua.com.bzabza.ehcs.user.User;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/records/{recordId}/commentaries")
public class CommentaryController {

    private final CommentaryService commentaryService;

    @Autowired
    public CommentaryController(CommentaryService commentaryService) {
        this.commentaryService = commentaryService;
    }

    @GetMapping
    @JsonView(Commentary.WithUser.class)
    public List<Commentary> getAllCommentaries(CommentarySpecification commentarySpecification,
                                               @PathVariable int microblogId) {
        commentarySpecification.setMicroblogId(microblogId);
        return commentaryService.findBy(commentarySpecification);
    }

    @PostMapping
    @JsonView(Commentary.WithUser.class)
    public Commentary add(@CurrentUser User user, @RequestBody @Valid CommentaryForm commentaryForm,
                          @PathVariable int microblogId) {
        commentaryForm.setUser(user);
        commentaryForm.setMicroblogId(microblogId);
        Commentary commentary = commentaryForm.toCommentary();
        commentaryService.save(commentary);
        return commentaryService.findById(commentary.getId());
    }
}
