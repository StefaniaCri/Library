package service.Read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface IRead <T>{
    default List<String[]> getCSVcolumns(String fileName) {
        try {
            List<String[]> columns = new ArrayList<>();
            var in = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = in.readLine()) != null) {
                String[] fields = line.replaceAll(" ", "").split(",");
                columns.add(fields);
            }
            return columns;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    List<T> readCSV();
}
