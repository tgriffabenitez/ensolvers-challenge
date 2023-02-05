package ensolvers.challenge.tomasgriffa.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "note")
    private String note;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    public Note() {
        this.created = LocalDateTime.now();
        this.isActive = true;
    }

    public Note(String title, String note) {
        this.title = title;
        this.note = note;
        this.created = LocalDateTime.now();
        this.isActive = true;
    }

}
