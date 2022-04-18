package Main;

import Book.*;
import Users.*;

import java.util.*;

public class MainService {

    protected static User loggedIn = null;
    protected static MainService instance = null;

    public MainService() {

    }

    public static MainService getInstance() {
        if(instance == null)
        {
            return instance = new MainService();
        }
        return instance;
    }

    private  List<User> usersList = new ArrayList<User>();
    private  static Scanner read = new Scanner(System.in);
    private  List<Book> books = new ArrayList<Book>();
    private  List<Author> authors = new ArrayList<Author>();

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<User> getUsersList() {
        return usersList;
    }


    private  User addUser() {

        System.out.println("Enter your name: ");
        String name = read.next();

        System.out.println("Enter your surname");
        String surname = read.next();

        System.out.println("Enter your username");
        String username = read.next();

        System.out.println("Enter your email");
        String email = read.next();

        System.out.println("Enter your password");
        String password = read.next();

        return new User(name, surname, username, email, password);

    }

    public  Client createClient() {

        User a = addUser();

        System.out.println("Enter your phone number");
        String phoneNumber = read.next();

        loggedIn = new Client(a.getName(), a.getSurname(), a.getUsername(), a.getEmail(), a.getPassword(), phoneNumber);
        usersList.add(loggedIn);
        return (Client) loggedIn;
    }

    public Librarian createLibrarian() {
        User a = addUser();
        loggedIn = new Librarian(a.getName(), a.getSurname(), a.getUsername(), a.getEmail(), a.getPassword());
        usersList.add(loggedIn);
        return (Librarian) loggedIn;
    }

    private List<User> sortList() {
        Collections.sort(usersList);
        return usersList;
    }

    public static void seeUsers() {
        if(loggedIn instanceof Librarian)
        {
            List<User> users = instance.sortList();
            System.out.println(users);
        }
        else System.out.println("You are not logged in an account");
    }

    public void logInAccount(String email, String password) {
        if (loggedIn == null) {
            User user =isExistingEmail(email, password);
            if (user != null) {
                System.out.println("You have an account");
                loggedIn = user;
            } else {
                System.out.println("Invalid log in");
            }
        } else {
            System.out.println("You are logged in account");
        }
    }

    public void logOut()
    {
        loggedIn = null;
    }

    public  User isExistingEmail(String email, String password) {
        for (User user : instance.usersList) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }


    private  Author readAuthor() {
        System.out.println("Enter first name");
        String first_name = read.next();

        System.out.println("Enter last name");
        String last_name = read.next();
        for (Author author : authors)
            if (author.getFirst_name().equals(first_name) && author.getLast_name().equals(last_name)) {
                System.out.println("Author already exists");
                return author;
            }

        Author author = new Author(first_name, last_name);
        authors.add(author);
        return author;
    }


    private ISBN readISBN() throws ISBN.ISBNFormatException {
        System.out.println("Enter ISBN");
        String isbn = read.next();

        return new ISBN(isbn);
    }

    private  Book readBook() throws ISBN.ISBNFormatException {
        ISBN isbn = readISBN();
        System.out.println("Enter title");
        String title = read.next();
        System.out.println("Enter pages");
        Integer pages = read.nextInt();
        System.out.println("Enter author");
        Author author = readAuthor();
        System.out.println("Pick a genre");
        BookType[] bookTypes = BookType.values();
        for (int i = 0; i < bookTypes.length; i++) {
            System.out.println(i + ": " + bookTypes[i] + "\n");
        }

        System.out.println("Pick the number of booktype");
        Integer i = read.nextInt();
        BookType bookType = bookTypes[i];
        System.out.println("Publishing house");
        String publishingHouse = read.next();
        return new Book(isbn, title, pages, author, bookType,publishingHouse);
    }

    public void addBook() throws ISBN.ISBNFormatException {
        if(loggedIn instanceof Librarian)
        {
            Book book = instance.readBook();
            instance.books.add(book);
        }
        else System.out.println("You are not logged in an account");

    }

    public void editBook()
    {
        if(loggedIn instanceof Librarian)
        {
            System.out.println("Title of the book you are looking for");
            String title = read.next();
            //pentru simplificarea idei voi considera ca bibliotecarul va cauta dupa titlul cartii
            //de asemenea va putea edita doar numarul de pagini
            for (Book book1 : instance.books) {

                if (book1.getTitle().equals(title)) {
                    Integer pages = read.nextInt();
                    book1.setPages(pages);
                }
            }
        }   else System.out.println("You are not logged in an account");

    }
    public void removeBook()
    {
        if(loggedIn instanceof Librarian)
        {
            System.out.println("Title of the book you are looking for");
            String title1 = read.next();
            instance.books.removeIf(book2 -> book2.getTitle().equals(title1));
        }   else System.out.println("You are not logged in an account");

    }

    public void addAuthor()
    {
        if (loggedIn instanceof Librarian) {
            Author a = readAuthor();
            instance.authors.add(a);
        }   else System.out.println("You are not logged in an account");
    }
    public void removeAuthor()
    {
        if (loggedIn instanceof Librarian) {
            System.out.println("First name of the author you are looking for");
            String first_name = read.next();
            System.out.println("Last name of the author you are looking for");
            String lasr_name = read.next();
            instance.authors.removeIf(author -> author.getFirst_name().equals(first_name) && author.getLast_name().equals(lasr_name));
        }   else System.out.println("You are not logged in an account");

    }


    public void addBook(Book b) {
        if (loggedIn instanceof Client) {
            var fav = ((Client) loggedIn).getFavouriteBooks();
            if (fav.contains(b))
                System.out.println("Book already in list");
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
        }
        else System.out.println("You are not logged in an account");
    }

    public void addAuthor(Author b) {
        if (loggedIn instanceof Client) {
            var fav = ((Client) loggedIn).getFavouriteAuthors();
            if (fav.contains(b))
                System.out.println("Author already in list");
            else
                fav.add(b);
            ((Client) loggedIn).setFavouriteAuthors(fav);
        }   else System.out.println("You are not logged in an account");
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
        }   else System.out.println("You are not logged in an account");
    }

    public void leaveReview(Integer numberOfStars, Book reviewed) {
        if (loggedIn instanceof Client)
        {
            Review review = new Review(loggedIn.getUsername(),numberOfStars);
            var reviews = reviewed.getReviews();
            reviews.add(review);
            reviewed.setReviews(reviews);
        }   else System.out.println("You are not logged in an account");
    }

    public void  leaveReview(Integer numberOfStars, Book reviewed, String text) {
        if (loggedIn instanceof Client)
        {
            Review review = new Review(loggedIn.getUsername(),text,numberOfStars);
            var reviews = reviewed.getReviews();
            //System.out.println(reviews);
            reviews.add(review);
            //System.out.println(reviews);
            reviewed.setReviews(reviews);
            //System.out.println(reviewed.getReviews());
        }   else System.out.println("You are not logged in an account");
    }

    public void rentBooks(String  newDate, Book... a) {
        if(loggedIn instanceof Client)
        {
            if(a.length <= 4)
            {

                var alreadyRented = ((Client)loggedIn).getRentedBooks();

                if(alreadyRented.containsKey(newDate))
                {
                    var scanned = alreadyRented.get(newDate);
                    if(scanned.size()==4 || scanned.size() + a.length >4 )

                    {
                        System.out.println("Cannot rent another book today");
                    }
                    else {
                        List<Book> books = alreadyRented.get(newDate);
                        for (Book list : a)
                            books.add(list);
                        alreadyRented.put(newDate, books);
                    }
                }
                else {
                    List<Book> books = new ArrayList<>();
                    for (Book list : a)
                        books.add(list);
                    alreadyRented.put(newDate, books);

                    System.out.println(alreadyRented);
                    ((Client) loggedIn).setRentedBooks(alreadyRented);

                }
            }
            else {
                System.out.println("You can rent only 4 books");
            }
        }
    }

    public void changePassword()
    {
        if(loggedIn != null)
        {
            System.out.println("Current password:");
            String password = read.next();
            if(loggedIn.getPassword().equals(password))
            {
                System.out.println("Your new password?");
                String newpassword = read.next();
                loggedIn.setPassword(newpassword);
                System.out.println("Your password has been changed sucesfully!");
            }
            else{
                System.out.println("Incorrect password");
            }
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book getBookByTitle() {
        System.out.println("Title of the book you are looking for");
        String title = read.next();
        for (Book book1 : instance.books) {

            if (book1.getTitle().equals(title)) {
                return book1;
            }
        }
        return null;
    }
    public Author getAuthorByFullName()
    {
        System.out.println("Enter first name");
        String first_name = read.next();

        System.out.println("Enter last name");
        String last_name = read.next();
        for (Author author : authors)
            if (author.getFirst_name().equals(first_name) && author.getLast_name().equals(last_name)) {
                return author;
            }
        return null;
    }

    public List<Author> getAuthors() {
        return authors;
    }
}
