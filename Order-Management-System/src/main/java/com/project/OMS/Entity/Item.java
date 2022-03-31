package com.project.OMS.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Column(name = "Item_Name")
	private String name;

	@Column(name = "Procuct_cost")
	private Double cost;
	
	@ManyToMany(mappedBy = "items",fetch = FetchType.LAZY)
	private List<Order> orders = new ArrayList<>();
	
	public Item() {

	}

	public Item(String name, Double cost) {
		super();
		this.name = name;
		this.cost = cost;
	}

	public Long getItemId() {
		return id;
	}

	public void setItemId(Long itemId) {
		this.id = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void addOrders(Order orders) {
		this.orders.add(orders);
	}
	
	public void removeOrders(Order orders) {
		this.orders.remove(orders);
	}
	

}
