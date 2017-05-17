package ua.com.bzabza.ehcs.card.record;

import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
public class RecordForm {

    @NotNull
    private String text;

    public Record toMicroblog() {
        Record record = new Record();
        record.setText(text);
        record.setCreationTime(new Date());
        return record;
    }
}
