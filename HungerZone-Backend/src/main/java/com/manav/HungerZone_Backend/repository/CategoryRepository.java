package com.manav.HungerZone_Backend.repository;

import com.manav.HungerZone_Backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    public List<Category> findByRestaurantId(Long id);

}
