package com.project.OMS.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "Order_List")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	private Long customerId;
	
	@CreationTimestamp
	private LocalDate dateOfOrder;
	
	private String adressOfDelivery;
	
	@ManyToMany
	@JoinTable(name = "Order_Item",joinColumns = @JoinColumn(name ="Order_Id"),
						inverseJoinColumns = @JoinColumn(name ="Item_Id"))
	private List<Item> items = new ArrayList<>();
	
	public Order() {

	}

	public Order(Long customerId, LocalDate dateOfOrder, String adressOfDelivery) {
		super();
		this.customerId = customerId;
		this.dateOfOrder = dateOfOrder;
		this.adressOfDelivery = adressOfDelivery;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public LocalDate getDateOfOrder() {
		return dateOfOrder;
	}

	public void setDateOfOrder(LocalDate dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}

	public String getAdressOfDelivery() {
		return adressOfDelivery;
	}

	public void setAdressOfDelivery(String adressOfDelivery) {
		this.adressOfDelivery = adressOfDelivery;
	}

	public List<Item> getItems() {
		return items;
	}

	public void addItems(Item items) {
		this.items.add(items);
	}

}
