package com.ec.household;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.ec.lab.HouseholdEJBLocal;
import com.ec.lab.model.Household;

/**Household RESTful Web Service.
 * accessed through the '/' path  api endpoint
 */

@Path("/")
@RequestScoped
public class HouseholdRS {

	//! Get Methods to show the respective Page
    @Inject 
    HouseholdEJBLocal sbsl; /*!< injects the EJB session bean conducting business logic */
    

    /**
     * Predict - api endpoint 
     *  POST method
     *  returns json
     */
    @POST
    @Path("/predict")
    @Produces({ "application/json" })
    public String predict() {
        return "{\"result\":\"" + sbsl.PredictData() + "\"}";
    }
    
    /**
     * load - api endpoint 
     *  POST method
     *  returns json
     */
    
    @POST
    @Path("/load")
    @Produces({ "application/json" })
    public String Load() {
        return "{\"result\":\"" + sbsl.Load() + "\"}";
    }
    
    /**
     * save - api endpoint 
     *  POST method
     *  returns json
     */
    @POST
    @Path("/save")
    @Produces({ "application/json" })
    public String Save(@FormParam("groceryBill") String groceryBill,/*!< query method calls for Household Object */
    		@FormParam("adults") String adults,
    		@FormParam("familyIncome") String familyIncome,
    		@FormParam("vehicles") String vehicles,
    		@FormParam("distanceToStore") String distanceToStore,
    		@FormParam("vegetarian") String vegetarian,
    		@FormParam("children") String children) 
    {
    	System.out.println("REST save service");
    	Household _home;
    	
		try {
			_home = new Household(Double.parseDouble(groceryBill),
					Double.parseDouble(adults),
					Double.parseDouble(familyIncome),
					Double.parseDouble(vehicles),
					Double.parseDouble(distanceToStore),
					Double.parseDouble(vegetarian),
					Double.parseDouble(children));
			
			return "{\"result\":\"" + sbsl.SaveInputData(_home) + "\"}";
			
		} 
		catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "{\"result\":\"" + e.getMessage() + "\"}";
		}
		catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			return "{\"result\":\"" + ex.getMessage() + "\"}";
		}
        
    	
    }
    
    
}
