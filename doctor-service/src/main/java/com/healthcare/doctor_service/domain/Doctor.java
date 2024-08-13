package com.healthcare.doctor_service.domain;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(collection = "doctors")
public class Doctor {

    @Id
    private String doctorId;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String[] specialization;

    @Field("op_days")
    private String[] opDays;

    public Doctor(String fullName,String email, String phone, String address, String[] specialization, String[] opDays){
        this.fullName=fullName;
        this.email=email;
        this.address=address;
        this.phone=phone;
        this.specialization=specialization;
        this.opDays=opDays;

    }

}
