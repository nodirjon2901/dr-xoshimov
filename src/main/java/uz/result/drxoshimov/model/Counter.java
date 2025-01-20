package uz.result.drxoshimov.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;
import uz.result.drxoshimov.enumurations.Button;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "counter")
public class Counter extends BaseEntity {

    @Enumerated(EnumType.STRING)
    Button section;

    Long countCall;

}
