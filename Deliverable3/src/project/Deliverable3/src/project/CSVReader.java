package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {
    
	public static ArrayList<HousingPrice> readCSVFile(String filename) {
	    ArrayList<HousingPrice> housingPrices = new ArrayList<>();

	    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
	        String line;
	        // Read header line
	        br.readLine();
	        while ((line = br.readLine()) != null) {
	        	//read and get values from CSV file
	            String[] values = line.split(",\"");
	            String refDate = values[0];
	            String geo = values[1];
	            String dguid = values[2];
	            String index = values[3];
	            String uom = values[4].substring(6,values[4].length());
	            String uomId = values[5];
	            String scalarFactor = values[6];
	            String scalarId = values[7];
	            String vector = values[8];
	            String coordinate = values[9];
	            
	            String valueStr = values[10].substring(0,values[10].length()-1);
	            
	            String status = values[11];
	            String symbol = values[12];
	            String terminated = values[13];
	            int decimals = 1;

	            HousingPrice housingPrice = new HousingPrice(refDate, geo, dguid, index, uom, uomId, scalarFactor, scalarId, vector, coordinate, valueStr, status, symbol, terminated, decimals);
	        //    System.out.println(refDate + "|"+geo);  

	            housingPrices.add(housingPrice);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return housingPrices;
	}


}

