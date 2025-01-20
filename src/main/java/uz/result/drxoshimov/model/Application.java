package uz.result.drxoshimov.model;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "application")
public class Application extends BaseEntity {

    String name;

    String phone;

    String message;

}
