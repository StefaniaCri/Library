package Book;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private final ISBN isbn;
    private String title;
    private Integer pages;
    private Author author;
    private BookType bookType;
    private List<Review> reviews = new ArrayList<>();
    private String publishingHouse;
    private List<CopyOfBooks> copyOfBooks;

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }


    public List<CopyOfBooks> getCopyOfBooks() {
        return copyOfBooks;
    }

    public void setCopyOfBooks(List<CopyOfBooks> copyOfBooks) {
        this.copyOfBooks = copyOfBooks;
    }

    public Book(ISBN isbn, String title, int pages, Author author, BookType bookType, String publishingHouse) {
        this.isbn = isbn;
        this.title = title;
        this.pages = pages;
        this.author = author;
        this.bookType = bookType;
        this.publishingHouse = publishingHouse;
    }

    public ISBN getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPages() {
        return pages;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", pages=" + pages +
                ", author=" + author +
                ", bookType=" + bookType +
                ", reviews=" + reviews +
                ", publishingHouse='" + publishingHouse + '\'' +
                ", copyOfBooks=" + copyOfBooks +
                '}';
    }
}