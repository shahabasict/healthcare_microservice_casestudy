package com.healthcare.patient_service.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "patients",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
        },
indexes = {
        @Index(columnList = "email")
})
@EntityListeners(AuditingEntityListener.class)
@TableGenerator(name = "idgen",initialValue = 100)
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "idgen")
    private long id;
    private String fullName;
    private String email;
    private String address;
    private String phone;
    private LocalDate dob;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PreExistingIllness> preExistingIllnesses;

}
