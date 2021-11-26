package com.example.happy.hr.repositories;


import com.example.happy.hr.domain.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    List<Location> findByAddress(String address);
    List<Location> findByLocationType(String locationType);
}
