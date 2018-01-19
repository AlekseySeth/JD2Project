package entity.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


/**
 * @author a.shestovsky
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class User {
    private Long id;
    private String firstName;
    private String lastName;

    public User(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
