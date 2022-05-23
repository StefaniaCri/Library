package service;

import entity.Book.Author;
import entity.Book.Book;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        //a valid ISBN for testing: 0569934052
        MainService service = MainService.getInstance();
        ClientService client = ClientService.getInstance();
        LibrarianService librarian = LibrarianService.getInstance();
        BookService bookService = BookService.getInstance();
        AuthorService authorService = AuthorService.getInstance();
        Scanner read = new Scanner(System.in);
        Boolean authorNeedUpdate = false;
        Boolean bookNeedUpdate = false;
        Boolean clientNeedUpdate = false;
        Boolean librarianNeedUpdate = false;
        int option;
        do {
            int option1;
            System.out.println("The type of account you have:");
            System.out.println("1 - Librarian");
            System.out.println("2 - Client");
            option = read.nextInt();
            switch (option) {
                case (1): {
                    do {
                        System.out.println("1 - Create a new account");
                        System.out.println("2 - Log in");
                        System.out.println("3 - Add a new book");
                        System.out.println("4 - Edit a book");
                        System.out.println("5 - Remove a book");
                        System.out.println("6 - See the users sorted by email");
                        System.out.println("7 - Change the password");
                        System.out.println("8 - Log out");
                        System.out.println("9 - Add a new author");
                        System.out.println("10 - Remove an author");
                        System.out.println("11 - Exit");
                        option1 = read.nextInt();
                        switch (option1) {
                            case 1:
                                librarian.createLibrarian();
                                break;
                            case 2:
                                System.out.println("Your email adress");
                                String email = read.next();
                                System.out.println("Your password");
                                String password = read.next();
                                service.logInAccount(email, password);
                                break;
                            case 3:
                                librarian.addBook();
                                break;
                            case 4:
                                librarian.editBook();
                                bookNeedUpdate = true;
                                break;
                            case 5:
                                librarian.removeBook();
                                bookNeedUpdate = true;
                                break;
                            case 6:
                                librarian.seeUsers();
                                break;
                            case 7:
                                service.changePassword();
                                librarianNeedUpdate = true;
                                break;
                            case 8:
                                service.logOut();
                                System.out.println("You are succesfully logged out");
                                break;

                            case 9:
                                librarian.addAuthor();
                                break;
                            case 10:
                                librarian.removeAuthor();
                                authorNeedUpdate = true;
                                break;
                        }
                    } while (option1 < 11);
                    break;

                }
                case 2: {
                    do {
                        System.out.println("1 - Create a new account");
                        System.out.println("2 - Log in");
                        System.out.println("3 - Rent books");
                        System.out.println("4 - Add a book to favourites");
                        System.out.println("5 - Remove a book from favourites");
                        System.out.println("6 -  Add an author to favourites");
                        System.out.println("7 - Remove an author from favourites");
                        System.out.println("8 - Leave a review to a book");
                        System.out.println("9 - Change the password");
                        System.out.println("10 - Log out");
                        System.out.println("11 - Exit");
                        option1 = read.nextInt();
                        switch (option1) {
                            case 1:
                                client.createClient();
                                break;
                            case 2:
                                System.out.println("Your email adress");
                                String email = read.next();
                                System.out.println("Your password");
                                String password = read.next();
                                service.logInAccount(email, password);
                                break;
                            case 3:
                                System.out.println("The number of books (<=4):");
                                int number = read.nextInt();
                                while (number != 0) {
                                    LocalDateTime myDateObj = LocalDateTime.now();
                                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                    String formattedDate = myDateObj.format(myFormatObj);
                                    System.out.println(service.getBooks());
                                    Book book = bookService.getBookByTitle();
                                    client.rentBooks(formattedDate, book);
                                    number--;
                                }
                                break;
                            case 4:
                                System.out.println(service.getBooks());
                                Book book = bookService.getBookByTitle();
                                client.addBook(book);
                                break;
                            case 5:
                                System.out.println(service.getBooks());
                                book = bookService.getBookByTitle();
                                client.removeBook(book);
                                break;
                            case 6:
                                System.out.println(service.getAuthors());
                                Author author = authorService.getAuthorByFullName();
                                client.addAuthor(author);
                                break;
                            case 7:
                                System.out.println(service.getAuthors());
                                author = authorService.getAuthorByFullName();
                                client.removeAutor(author);
                                break;
                            case 8:
                                System.out.println("Review text (1) sau stele (2)?");
                                int caz = read.nextInt();
                                if (caz == 1) {
                                    System.out.println(service.getBooks());
                                    book = bookService.getBookByTitle();
                                    System.out.println("Number of stars:");
                                    int noStars = read.nextInt();
                                    System.out.println("Text");
                                    String text = read.next();
                                    client.leaveReview(noStars, book, text);
                                } else {
                                    System.out.println(service.getBooks());
                                    book = bookService.getBookByTitle();
                                    System.out.println("Number of stars:");
                                    int noStars = read.nextInt();
                                    client.leaveReview(noStars, book);
                                }
                                break;
                            case 9:
                                service.changePassword();
                                clientNeedUpdate = true;
                                break;
                            case 10:
                                service.logOut();
                                System.out.println("You are succesfully logged out");
                                break;
                        }
                    } while (option1 < 10);
                    break;
                }
            }


        } while (option < 3);
        //System.out.println(service.getBooks());
        //la final updatam toate CSV-uril in caz ca a aparut o modificare facuta de user
        if (authorNeedUpdate)
            authorService.updateCSV();
        if(librarianNeedUpdate)
            librarian.updateCSV();
        if(clientNeedUpdate)
            client.updateCSV();
        if(bookNeedUpdate)
            bookService.updateCSV();
    }
}