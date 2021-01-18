package com.ec.lab;

import javax.ejb.Remote;

import com.ec.lab.model.Household;
/**
 * Session Bean Remote interface HouseholdEJB
 */
@Remote
public interface HouseholdEJBRemote {
	public String PredictData();
	public Boolean Load();
	public Boolean SaveInputData(Household _home);
}
