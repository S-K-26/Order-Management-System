package com.project.OMS.Repositpry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.OMS.Entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long>{
	

	
}
