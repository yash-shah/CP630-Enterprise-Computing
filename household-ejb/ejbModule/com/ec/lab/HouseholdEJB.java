package com.ec.lab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;

import com.ec.lab.model.Household;

/**
 * Session Bean implementation class HouseholdEJB
 */
@Stateless
public class HouseholdEJB implements HouseholdEJBRemote, HouseholdEJBLocal {

    /**
     * Default constructor. 
     */
    public HouseholdEJB() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Saves data in inputs file
     * @param _home is the household object that would be saved in the file
     */
	@Override
	public Boolean SaveInputData(Household _home) {
		try {
			List<List<String>> rows = Arrays.asList(
				    Arrays.asList(
				    		Double.toString(_home.getGroceryBill()),
				    		Double.toString(_home.getAdults()),
				    		Double.toString(_home.getFamilyIncome()),
				    		Double.toString(_home.getVehicles()),
				    		Double.toString(_home.getDistanceToStore()),
				    		Double.toString(_home.getVegetarian()),
				    		Double.toString(_home.getChildren())
				    		)
				);
			
				FileWriter csvWriter = new FileWriter("/pythonprojects/new.csv");
				csvWriter.append("Grocery_Bill");
				csvWriter.append(",");
				csvWriter.append("N_Adults");
				csvWriter.append(",");
				csvWriter.append("Family_Income");
				csvWriter.append(",");
				csvWriter.append("N_Vehicles");
				csvWriter.append(",");
				csvWriter.append("Distance_to_Store");
				csvWriter.append(",");
				csvWriter.append("Vegetarian");
				csvWriter.append(",");
				csvWriter.append("N_Children");
				csvWriter.append("\n");

				for (List<String> rowData : rows) {
				    csvWriter.append(String.join(",", rowData));
				    csvWriter.append("\n");
				}

				csvWriter.flush();
				csvWriter.close();
				return true;
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

    /**
     * predicts based on data saved in the finputs file data in inputs file
     * 
     */
	@Override
	public String PredictData() {
		if(SendPrediction())
		{
			return ExtractPredictionFromCSV();
		}
		return null;
		
	}
	
    /**
     * Loads and trains the model and saves it to a BIn file
     * 
     */
	@Override
	public Boolean Load() {
		
		ProcessBuilder processBuilder = new ProcessBuilder();

        // Run this on Windows, cmd, /c = terminate after this run
        processBuilder.command("cmd.exe", "/c", "spark-submit --master spark://192.168.0.14:7077 /pythonprojects/LoadFile.py");

        try {

            Process process = processBuilder.start();

			// blocked :(
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);
            if(exitCode == 0)
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        	return false;
	}
	
    /**
     * calls the predict function inl python to execute prediction on the saved inputs file
     * 
     */
	
	private Boolean SendPrediction()
	{
		Boolean result = false;
		ProcessBuilder processBuilder = new ProcessBuilder();

		// Run this on Windows, cmd, /c = terminate after this run
		processBuilder.command("cmd.exe", "/c", "spark-submit --master spark://192.168.0.14:7077 /pythonprojects/Predict.py");

		try {

		    Process process = processBuilder.start();

			// blocked :(
		    //Future Enhancement : move it to another thread to release the Main thread.
		    BufferedReader reader =
		            new BufferedReader(new InputStreamReader(process.getInputStream()));

		    String line;
		    while ((line = reader.readLine()) != null) {
		        System.out.println(line);
		    }

		    int exitCode = process.waitFor();
		    System.out.println("\nExited with error code : " + exitCode);
		    
		    if(exitCode == 0)
		    {
		    	return true;
		    }

		} catch (IOException e) {
		    e.printStackTrace();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		return result;
	}
	
    /**
     * extracts prediction result saved in the output file
     * 
     */
	private String ExtractPredictionFromCSV()
	{
		String resultPrediction = "";
		try
		{
			File csvFile = new File("/pythonprojects/result.csv");
	    	if (csvFile.isFile()) {
	    		BufferedReader csvReader = new BufferedReader(new FileReader("/pythonprojects/result.csv"));
	    		 String row;
	    		 String[] data = new String[5];
	    		while ((row = csvReader.readLine()) != null) {
	    		    data = row.split(",|\n"); 
	    		}
	    		csvReader.close();
	    		return data[1];	
		    }
		}

		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return resultPrediction;
	}


	
	

}
