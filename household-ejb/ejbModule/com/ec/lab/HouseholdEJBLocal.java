package com.ec.lab;

import javax.ejb.Local;

import com.ec.lab.model.Household;
/**
 * Session Bean LOcal interface HouseholdEJB
 */
@Local
public interface HouseholdEJBLocal {
public String PredictData();
public Boolean Load();
public Boolean SaveInputData(Household _home);
}
