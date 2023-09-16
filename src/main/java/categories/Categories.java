package categories;

import common.BaseEntity;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@ToString(callSuper = true)
public class Categories extends BaseEntity<UUID> implements Serializable {
    private String name;

    public Categories(UUID uuid, String name) {
        super(uuid);
        this.name = name;
    }

}
