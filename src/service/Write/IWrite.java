package service.Write;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public interface IWrite<T> {

    default void writeToCSV(String fileName, List<String> whatTo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            whatTo.stream().map(x -> {
                if (whatTo.get(whatTo.size() - 1) != x) return x + ",";
                return x + "\n";
            }).forEach(y -> {
                try {
                    writer.append(y);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
            writer.flush();
            //System.out.println("Succesfully appended to CSV " + fileName);
        } catch (IOException e) {
            System.out.println("Something went wrong in writeCSV");
        }
    }
    void updateCSV(List<T> whatTo);


}

