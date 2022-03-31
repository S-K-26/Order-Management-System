package com.project.OMS.Repositpry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.OMS.Entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>{

}
