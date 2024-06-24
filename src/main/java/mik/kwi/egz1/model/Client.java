package mik.kwi.egz1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Clients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="client_id", nullable = false)
    private Integer clientId;
    @Column(name="Name")
    private String name;
    @Column(name="Surname")
    private double surname;
    @Column(name="PhoneNumber",length = 300)
    private String phoneNumber;
    @Column(name = "Address")
    private String address;

}
