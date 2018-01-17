package entity.user;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author a.shestovsky
 */
@Getter
@Setter
//@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User {
    private Long id;
    private String firstName;
    private String lastName;
//    private String email;
//    private String password;
//    private String phone;
//    private String address;
//    private LocalDate registrationDate;
//    private Role role;

//    public User(String firstName, String lastName, String email, String password,
//                String phone, String address, LocalDate registrationDate, Role role) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
//        this.phone = phone;
//        this.address = address;
//        this.registrationDate = registrationDate;
//        this.role = role;
//    }

    public User(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}