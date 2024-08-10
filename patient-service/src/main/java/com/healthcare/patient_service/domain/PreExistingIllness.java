package com.healthcare.patient_service.domain;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "illness")
public class PreExistingIllness {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int illnessId;
    private String illness;

}
