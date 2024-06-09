package com.manav.HungerZone_Backend.repository;

import com.manav.HungerZone_Backend.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<UserAddress, Long> {

}
