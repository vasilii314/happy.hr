package com.example.happy.hr.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "location")
@NoArgsConstructor
@Data
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Integer id;

    @Length(max = 150)
    @Column(name = "location_type")
    private String locationType;

    @Length(max = 1024)
    @Column(name = "office_address")
    private String officeAddress;

    public Location(String locationType, String officeAddress) {
        this.locationType = locationType;
        this.officeAddress = officeAddress;
    }
}
