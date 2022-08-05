package codereview.katrmin.task2.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String link;
    private String original;
    private Long count;
    private Integer rank;
}
