package com.ec.lab.model;

public class Household  implements java.io.Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private double groceryBill;  	/*!< last groceryBill of the family */
	private double adults;	/*!< # of adults */
	private double familyIncome;	/*!< Family income in total */
	private double vehicles;	/*!< # of vehicles */
	private double distanceToStore;	/*!< Distance to the neasert store */
	private double vegetarian = 0;	/*!< is the family a Veg? */
	private double children;	/*!< # of children */
	
    //! A constructor.
    /*!
      Default Constructor
    */
	
	public Household() {
		super();
	}
	
	 //! A constructor.
    /*!
      Parameterized Constructor
    */
	public Household(double groceryBill, double adults, double familyIncome, double vehicles, double distanceToStore,
			double vegetarian, double children) {
		super();
		this.groceryBill = groceryBill;
		this.adults = adults;
		this.familyIncome = familyIncome;
		this.vehicles = vehicles;
		this.distanceToStore = distanceToStore;
		this.vegetarian = vegetarian;
		this.children = children;
	}
	
    //! 
    /*! getters and Setters */
	public double getGroceryBill() {
		return groceryBill;
	}
    //! 
    /*! getters and Setters */
	public void setGroceryBill(double groceryBill) {
		this.groceryBill = groceryBill;
	}
    //! 
    /*! getters and Setters */
	public double getAdults() {
		return adults;
	}
    //! 
    /*! getters and Setters */
	public void setAdults(double adults) {
		this.adults = adults;
	}
    //! 
    /*! getters and Setters */
	public double getFamilyIncome() {
		return familyIncome;
	}
    //! 
    /*! getters and Setters */
	public void setFamilyIncome(double familyIncome) {
		this.familyIncome = familyIncome;
	}
    //! 
    /*! getters and Setters */
	public double getVehicles() {
		return vehicles;
	}
    //! 
    /*! getters and Setters */
	public void setVehicles(double vehicles) {
		this.vehicles = vehicles;
	}
    //! 
    /*! getters and Setters */
	public double getDistanceToStore() {
		return distanceToStore;
	}
    //! 
    /*! getters and Setters */
	public void setDistanceToStore(double distanceToStore) {
		this.distanceToStore = distanceToStore;
	}
    //! 
    /*! getters and Setters */
	public double getVegetarian() {
		return vegetarian;
	}
    //! 
    /*! getters and Setters */
	public void setVegetarian(double vegetarian) {
		this.vegetarian = vegetarian;
	}
    //! 
    /*! getters and Setters */
	public double getChildren() {
		return children;
	}
    //! 
    /*! getters and Setters */
	public void setChildren(double children) {
		this.children = children;
	}
	
}
