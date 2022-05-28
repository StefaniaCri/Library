package entity.Book;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

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

    public Review(String client, String text, Integer numberOfStars) {
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

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(Integer numberOfStars) {
        this.numberOfStars = numberOfStars;
    }
}