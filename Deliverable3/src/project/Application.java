package project;

import java.util.ArrayList;


public class Application {
    public static void main(String[] args) throws Exception {
        // Read data from Excel file
        ArrayList<HousingPrice> data = CSVReader.readCSVFile("18100205.csv");

        // Create UI and display chart
        HousingPriceChartUI ui = new HousingPriceChartUI(data);
        ui.displayChart();
    }
}