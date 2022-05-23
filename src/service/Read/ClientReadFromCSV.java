package service.Read;

import entity.Users.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientReadFromCSV implements IRead<Client> {

    private static ClientReadFromCSV readInstance = null;

    private ClientReadFromCSV() {
    }

    public static ClientReadFromCSV getInstance() {
        if (readInstance == null)
            readInstance = new ClientReadFromCSV();
        return readInstance;
    }

    @Override
    public List<Client> readCSV() {
       List<Client> clients = new ArrayList<>();
        var columns = getCSVcolumns(".\\src\\data\\client.csv");
        for (var column : columns) {
            clients.add(new Client(column[0], column[1], column[2], column[3], column[4], column[5]));
        }
        return clients;
    }

}
