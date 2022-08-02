package codereview.katrmin.task2.dtos;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class UrlDto implements Serializable {
    @NotEmpty
    private final String original;
}
