package com.example.happy.hr.domain.entities;

import com.example.happy.hr.domain.enums.LocationType;
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

    @Length(max = 1024)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "location_type")
    private LocationType locationType;

    public Location(String address, LocationType locationType) {
        this.address = address;
        this.locationType = locationType;
    }
}
