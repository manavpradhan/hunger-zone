package com.manav.HungerZone_Backend.repository;

import com.manav.HungerZone_Backend.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<UserAddress, Long> {

}
