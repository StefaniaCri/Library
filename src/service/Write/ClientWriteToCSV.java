package service.Write;

import entity.Users.Client;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientWriteToCSV implements IWrite<Client> {
    private static ClientWriteToCSV writeInstance = null;

    private ClientWriteToCSV() {
    }

    public static ClientWriteToCSV getInstance() {
        if (writeInstance == null)
            writeInstance = new ClientWriteToCSV();
        return writeInstance;
    }

    public void writeCSV(Client a) {
        List<String> toWrite = new ArrayList<>(Arrays.asList(a.getName(), a.getSurname(), a.getUsername(), a.getEmail(), a.getPassword(), a.getPhoneNumber()));
        writeToCSV(".\\src\\data\\client.csv", toWrite);
    }

    @Override
    public void updateCSV(List<Client> whatTo) {
        try {
            new FileWriter(".\\src\\data\\client.csv");

            for (var client : whatTo) {
                writeCSV(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

