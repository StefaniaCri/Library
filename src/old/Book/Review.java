package Book;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Review {
    private String client;
    private String text;
    private LocalDateTime date;
    private Integer numberOfStars;

    public Review(String client, Integer numberOfStars) {
        this.client = client;
        this.numberOfStars = numberOfStars;
        this.date = LocalDateTime.now();
    }

    public Review(String  client, String text, Integer numberOfStars) {
        this.client = client;
        this.text = text;
        this.numberOfStars = numberOfStars;
        this.date = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Review{" +
                "client='" + client + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", numberOfStars=" + numberOfStars +
                '}';
    }
}