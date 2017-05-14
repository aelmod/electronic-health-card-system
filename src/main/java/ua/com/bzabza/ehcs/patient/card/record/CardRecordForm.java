package ua.com.bzabza.ehcs.patient.card.record;

import lombok.Setter;
import ua.com.bzabza.ehcs.user.User;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
public class CardRecordForm {

    @NotNull
    private String text;

    public CardRecord toMicroblog() {
        CardRecord cardRecord = new CardRecord();
        cardRecord.setText(text);
        cardRecord.setCreationTime(new Date());
        return cardRecord;
    }
}
