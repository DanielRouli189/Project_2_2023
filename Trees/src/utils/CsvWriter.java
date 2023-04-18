package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import modelTesting.TestStructure;

public class CsvWriter {

    private CsvWriter() {
        throw new IllegalStateException("CSV Writer class");
    }

    public static void writeCSV(List<TestStructure> tests) throws IOException {
        FileWriter csvWriter = new FileWriter("tests.csv");

        csvWriter.append("Data Size, Successful Searches k-d, Failed searches k-d, Successful Searches PR, Failed searches PR \n");
        for(TestStructure test : tests)
            csvWriter.append(Integer.toString(test.dataSize())).append(",")
                     .append(Float.toString(test.successKD())).append(",")
                     .append(Float.toString(test.failKD())).append(",")
                     .append(Float.toString(test.successPR())).append(",")
                     .append(Float.toString(test.failPR())+"\n");
    
        csvWriter.flush();
        csvWriter.close();
        System.out.println("Test results have been written to tests.csv");
    }   

}
