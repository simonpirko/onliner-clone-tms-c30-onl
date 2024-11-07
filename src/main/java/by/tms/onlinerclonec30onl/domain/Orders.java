package by.tms.onlinerclonec30onl.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Orders {

    private long id;
    private Customer customer;
    private Double totalPrice;
    private String firstName;
    private String lastName;
    private String phone;
    private String deliveryAddress;
    private Status status;


    public enum Status {
        OPEN, CLOSED
    }

}
