package service;

import entity.Book.Author;
import entity.Book.Book;
import entity.Book.Review;
import entity.Users.Client;
import entity.Users.User;

import java.util.ArrayList;
import java.util.List;

public class ClientService extends MainService {
    protected static ClientService instance = null;

    public ClientService() {
    }

    public static ClientService getInstance() {
        if (instance == null) {
            return instance = new ClientService();
        }
        return instance;
    }

    public Client createClient() {

        User a = addUser();

        System.out.println("Enter your phone number");
        String phoneNumber = read.next();

        loggedIn = new Client(a.getName(), a.getSurname(), a.getUsername(), a.getEmail(), a.getPassword(), phoneNumber);
        usersList.add(loggedIn);
        return (Client) loggedIn;
    }

    public void addBook(Book b) {
        if (loggedIn instanceof Client) {
            var fav = ((Client) loggedIn).getFavouriteBooks();
            if (fav.contains(b))
                System.out.println("entities.Book already in list");
            else
                fav.add(b);
            ((Client) loggedIn).setFavouriteBooks(fav);
        } else System.out.println("You are not logged in an account");
    }

    public void removeBook(Book b) {
        if (loggedIn instanceof Client) {
            var fav = ((Client) loggedIn).getFavouriteBooks();
            if (fav.contains(b)) {
                fav.remove(b);
            } else {
                System.out.println("the book is not in list");
            }
            ((Client) loggedIn).setFavouriteBooks(fav);
        } else System.out.println("You are not logged in an account");
    }

    public void addAuthor(Author b) {
        if (loggedIn instanceof Client) {
            var fav = ((Client) loggedIn).getFavouriteAuthors();
            if (fav.contains(b))
                System.out.println("Author already in list");
            else
                fav.add(b);
            ((Client) loggedIn).setFavouriteAuthors(fav);
        } else System.out.println("You are not logged in an account");
    }

    public void removeAutor(Author b) {
        if (loggedIn instanceof Client) {
            var fav = ((Client) loggedIn).getFavouriteAuthors();
            if (fav.contains(b)) {
                fav.remove(b);
            } else {
                System.out.println("the author is not in list");
            }
            ((Client) loggedIn).setFavouriteAuthors(fav);
        } else System.out.println("You are not logged in an account");
    }

    public void leaveReview(Integer numberOfStars, Book reviewed) {
        if (loggedIn instanceof Client) {
            Review review = new Review(loggedIn.getUsername(), numberOfStars);
            var reviews = reviewed.getReviews();
            reviews.add(review);
            reviewed.setReviews(reviews);
        } else System.out.println("You are not logged in an account");
    }

    public void leaveReview(Integer numberOfStars, Book reviewed, String text) {
        if (loggedIn instanceof Client) {
            Review review = new Review(loggedIn.getUsername(), text, numberOfStars);
            var reviews = reviewed.getReviews();
            //System.out.println(reviews);
            reviews.add(review);
            //System.out.println(reviews);
            reviewed.setReviews(reviews);
            //System.out.println(reviewed.getReviews());
        } else System.out.println("You are not logged in an account");
    }

    public void rentBooks(String newDate, Book... a) {
        if (loggedIn instanceof Client) {
            if (a.length <= 4) {

                var alreadyRented = ((Client) loggedIn).getRentedBooks();

                if (alreadyRented.containsKey(newDate)) {
                    var scanned = alreadyRented.get(newDate);
                    if (scanned.size() == 4 || scanned.size() + a.length > 4) {
                        System.out.println("Cannot rent another book today");
                    } else {
                        List<Book> books = alreadyRented.get(newDate);
                        for (Book list : a)
                            books.add(list);
                        alreadyRented.put(newDate, books);
                    }
                } else {
                    List<Book> books = new ArrayList<>();
                    for (Book list : a)
                        books.add(list);
                    alreadyRented.put(newDate, books);

                    System.out.println(alreadyRented);
                    ((Client) loggedIn).setRentedBooks(alreadyRented);

                }
            } else {
                System.out.println("You can rent only 4 books");
            }
        }
    }
}
