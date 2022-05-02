package service;


import java.io.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ReadFromCSV {
    private static ReadFromCSV readInstance = null;

    public static ReadFromCSV getInstance() {
        if (readInstance == null)
            readInstance = new ReadFromCSV();
        return readInstance;
    }

    public List<String[]> getCSVcolumns(String fileName) {
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
}
