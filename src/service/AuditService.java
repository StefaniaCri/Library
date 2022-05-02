package service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;


public class AuditService {
    private static AuditService instance = null;

    public static AuditService getInstance() {
        if (instance == null) {
            return instance = new AuditService();
        }
        return instance;
    }

    public void write(String operation) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(".\\src\\data\\audit.csv", true))) {
            LocalDateTime now = LocalDateTime.now();
            writer.append(operation + ", " + now + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
