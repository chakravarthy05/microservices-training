package com.slokam.healthcare.dpi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.slokam.healthcare.dpi.entity.Treatment_Item;

@Repository
public interface ITreatment_ItemRepo extends JpaRepository<Treatment_Item, Long>{

}
