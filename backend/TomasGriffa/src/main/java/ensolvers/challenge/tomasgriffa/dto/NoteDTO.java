package ensolvers.challenge.tomasgriffa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteDTO {

    private String title;
    private String note;
    private Boolean isActive;
    private Integer userId;
}
