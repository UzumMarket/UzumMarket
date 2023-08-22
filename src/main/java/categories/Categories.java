package categories;

import common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@NoArgsConstructor
@Data
@ToString(callSuper = true)
public class Categories extends BaseEntity<UUID> {
    private String name;

    public Categories(UUID uuid, String name) {
        super(uuid);
        this.name = name;
    }
}
