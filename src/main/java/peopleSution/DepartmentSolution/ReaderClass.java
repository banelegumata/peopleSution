package peopleSution.DepartmentSolution;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ReaderClass {
    public void letReadAfile(){

        //read from file
        try {

            Gson gson = new Gson();

            FileReader reader = new FileReader( "FileName here");
//            gson.fromJson(reader, )
//            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
