package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import modelTesting.TestStructure;

/**
 * The CsvWriter class writes test results to a CSV file.
 */
public class CsvWriter {


    /**
     * This is a private constructor for the CsvWriter class that throws an IllegalStateException.
     * This is done to prevent the class from being instantiated, as it
     * only contains static methods and should not be instantiated.
     */
    private CsvWriter() {
        throw new IllegalStateException("CSV Writer class");
    }

    /**
     * The function writes test results to a CSV file.
     * 
     * @param tests A list of TestStructure objects that contain the results of various tests.
     * @throws IOException if the file is not created.
     */
    public static void writeCSV(List<TestStructure> tests) {

        try(FileWriter csvWriter = new FileWriter("tests.csv");) {
            csvWriter.append("Data Size, Successful Searches KD, Failed searches KD, Successful Searches PR, Failed searches PR, Range Queries KD \n");
            for(TestStructure test : tests)
                csvWriter.append(Integer.toString(test.dataSize())).append(",")
                        .append(Float.toString(test.successKD())).append(",")
                        .append(Float.toString(test.failKD())).append(",")
                        .append(Float.toString(test.successPR())).append(",")
                        .append(Float.toString(test.failPR())+"\n");
        }
        catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("Test results have been written to tests.csv");
    }   

}
