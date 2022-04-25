package com.java.OS.Entity;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/** 
* @author Saurabh Kuthe
* Since we named the class Order, by default the table named ORDER should be created.
 But because that is a reserved SQL word, added @Table(name = “Order_List”) to avoid conflicts.
 
* @Entity - Used to map order class to table.
  Assigned annotation @Id to OrderId to set it as Primary Key.
* @GeneratedValue - Auto generates the order Id for each order added
* @CreationTimestamp - Annotated dateOfOrder with this because when new order is added it will
  					   create new date & time when order is added.
* @ManyToMany - Since each order can have many items & Each Item can have many orders we have used
  			    @ManyToMany to map it in the database.
 */
@Entity
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer", "FieldHandler" })
@Table(name = "Order_List")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;

	private int customerId;

	@CreationTimestamp
	private LocalDate dateOfOrder;

	private String adressOfDelivery;

	@ManyToMany
	@JoinTable(name = "Order_Item", joinColumns = @JoinColumn(name = "Order_Id"), inverseJoinColumns = @JoinColumn(name = "Item_Id"))
	private List<Item> items = new ArrayList<>();

	
	/**
	 * Default No-Argument Constructor.
	 */
	public Order() {

	}
	
	/**
	 * Constructor to accept :-
	 * @param customerId
	 * @param dateOfOrder
	 * @param adressOfDelivery
	 */
	public Order(int customerId, LocalDate dateOfOrder, String adressOfDelivery) {
		super();
		this.customerId = customerId;
		this.dateOfOrder = dateOfOrder;
		this.adressOfDelivery = adressOfDelivery;
	}
	
	/**
	 * Getter & Setter Methods.
	 * @return orderId 
	 */
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
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
	
	/**
	 *We have removed the default setter method for item list to have control over
	  adding orders we don't want to add list of items instead set item  at a time.
	 * @param items to add items in Our orders.
	 */
	public void addItems(Item items) {
		this.items.add(items);
	}
	
	/**
	 * 
	 * @param items - removes items from order.
	 */
	public void removeItems(Item items) {
		this.items.remove(items);
	}

}
