package peopleSution.DepartmentSolution;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;

public class writerClass {
    public void writeToFile(String receivedStringToWrite){
        //working with json
        Gson gson = new Gson();

        //write to file
        FileWriter writer = null;
        try {
            writer = new FileWriter("myJsonfiles\\testFirstSample.json");
            gson.toJson(receivedStringToWrite, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
