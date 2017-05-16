package ua.com.bzabza.ehcs.patient.card.record.commentary;

import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import ua.com.bzabza.ehcs.patient.card.record.CardRecord;
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
        CardRecord cardRecord = new CardRecord();
        cardRecord.setId(microblogId);
        commentary.setUser(user);
        commentary.setCreationTime(new Date());
        commentary.setText(text);
        commentary.setCardRecord(cardRecord);
        return commentary;
    }
}
