package project;



import java.util.*;

import java.util.Scanner;

import weka.classifiers.evaluation.*;
import weka.classifiers.functions.*;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.*;

public class forecasting extends Application {
    ArrayList<String> methods;
    ArrayList<HousingPrice> data1;

    dataEntry data;

    public static String methodOption;

    public forecasting(ArrayList<HousingPrice> data1) {
        this.data1 = data1;
        this.methods = new ArrayList<String>();
        this.methods.add("Linear reggression");
        this.methods.add("SMOreg");
        this.methods.add("MultilayePercepton");


    }
    public static void main(String[] args) throws Exception {
        // create an instance of your class
        ArrayList<HousingPrice> data = CSVReader.readCSVFile("18100205.csv");
        forecasting forecast = new forecasting(data);

        // call the pickMethod and getData methods to perform your desired operations
        forecast.pickMethod();
        if(methodOption.equals("Linear reggression")){
            forecast.makeLinearRegressionPrediction();
        }
    }


    public void pickMethod() {
        System.out.println("pick a method");
        for (int i = 0; i < this.methods.size(); i++) {
            System.out.println(methods.get(i));
        }
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter your method: ");
        methodOption = reader.next();
        System.out.println("You picked "+ methodOption);
        reader.close();
        this.data = new MainWin().showWindow();

    }

    public DenseInstance getData() {


        HousingPrice housingPrice = data1.get(data.ts); // retrieve the element at index wanted
        DenseInstance instance = new DenseInstance(17);
        // Create empty instances object

        // Add instances from data
        instance.setValue(0, housingPrice.getRefDate());
        instance.setValue(1, housingPrice.getGeo());
        instance.setValue(2, housingPrice.getDguid());
        instance.setValue(3, housingPrice.getIndex());
        instance.setValue(4, housingPrice.getUom());
        instance.setValue(5, housingPrice.getUomId());
        instance.setValue(6, housingPrice.getScalarFactor());
        instance.setValue(7, housingPrice.getScalarId());
        instance.setValue(8, housingPrice.getVector());
        instance.setValue(9, housingPrice.getCoordinate());
        instance.setValue(10, housingPrice.getValue());
        instance.setValue(11, housingPrice.getStatus());
        instance.setValue(12, housingPrice.getSymbol());
        instance.setValue(13, housingPrice.getTerminated());
        instance.setValue(15, data.iterations);
        instance.setValue(14, data.months);


        return instance;




    }

    public void makeLinearRegressionPrediction() throws Exception {

        // Get the instance from the data
        DenseInstance instance = getData();

        // Create attribute vector
        FastVector attributes = new FastVector(instance.numAttributes());
        for (int i = 0; i < instance.numAttributes(); i++) {
            Attribute attr = new Attribute(instance.attribute(i).name());
            attributes.addElement(attr);
        }

        // Create the dataset
        Instances dataset = new Instances("dataset", attributes, 1);
        dataset.add(instance);
        dataset.setClassIndex(instance.numAttributes() - 1);

        // Create the linear regression model
        LinearRegression model = new LinearRegression();
        model.buildClassifier(dataset);

        // Make the prediction
        double prediction = model.classifyInstance(instance);
        System.out.println("Linear regression prediction: " + prediction);
    }







}






