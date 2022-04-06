package com.project.OMS.Repositpry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.OMS.Entity.Item;
/* 
* Created ItemRepository Class to provide the mechanism for storage, retrieval, search,
update and delete operation on Items.

* Since we extended ItemRepository to @Interface JpaRepository we don't need to add @Repository 
  & @Transaction annotation.It has default implementation class SimpleJpaRepository &
  it is already annotated with @Repository & @Transaction. It has two parameters <JPA Entity, Primary Key>.
 */

public interface ItemRepository extends JpaRepository<Item,Integer> {
	

}
