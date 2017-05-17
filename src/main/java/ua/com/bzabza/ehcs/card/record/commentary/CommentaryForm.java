package ua.com.bzabza.ehcs.card.record.commentary;

import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import ua.com.bzabza.ehcs.card.record.Record;
import ua.com.bzabza.ehcs.user.User;

import java.util.Date;

@Setter
public class CommentaryForm {

    @NotEmpty
    private String text;

    private Integer microblogId;

    private User user;

    public Commentary toCommentary() {
        Commentary commentary = new Commentary();
        Record record = new Record();
        record.setId(microblogId);
        commentary.setUser(user);
        commentary.setCreationTime(new Date());
        commentary.setText(text);
        commentary.setRecord(record);
        return commentary;
    }
}
