package Main;

import Address.*;
import Book.*;
import Users.*;
import Users.Client;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) throws ISBN.ISBNFormatException {
        //a valid ISBN for testing: 0569934052
        MainService service = MainService.getInstance();
        Scanner read = new Scanner(System.in);
        int option;
        do {
            int option1;
            System.out.println("1 - Librarian");
            System.out.println("2 - Client");
            option = read.nextInt();
            switch (option) {
                case (1): {
                    do {
                        System.out.println("1 - Creare cont nou");
                        System.out.println("2 - Logare in cont existent");
                        System.out.println("3 - Add a new book");
                        System.out.println("4 - Edit a book");
                        System.out.println("5 - Remove a book");
                        System.out.println("6 - See the users sorted by email");
                        System.out.println("7 - Change the password");
                        System.out.println("8 - Log out");
                        System.out.println("9 - Add a new author");
                        System.out.println("10 - Remove an author");
                        System.out.println("11 - exit");
                        option1 = read.nextInt();
                        switch (option1) {
                            case 1:
                                service.createLibrarian();
                                break;
                            case 2:
                                System.out.println("Your email adress");
                                String email = read.next();
                                System.out.println("Your password");
                                String password = read.next();
                                service.logInAccount(email,password);
                                break;
                            case 3:
                                service.addBook();
                                break;
                            case 4:
                                service.editBook();
                                break;
                            case 5:
                                service.removeBook();
                                break;
                            case 6:
                                service.seeUsers();
                                break;
                            case 7:
                                service.changePassword();
                                break;
                            case 8:
                                service.logOut();
                                System.out.println("You are succesfully logged out");
                                break;

                            case 9:
                                service.addAuthor();
                                break;
                            case 10:
                                service.removeAuthor();
                                break;
                        }
                    } while (option1 < 11);
                    break;

                }
                case 2: {
                    do {
                        System.out.println("1 - Creare cont nou");
                        System.out.println("2 - Logare in cont existent");
                        System.out.println("3 - Rent books");
                        System.out.println("4 - Add a book to favourites");
                        System.out.println("5 - Remove a book from favourites");
                        System.out.println("6 -  Add an author to favourites");
                        System.out.println("7 - Remove an author from favourites");
                        System.out.println("8 - leave a review to a book");
                        System.out.println("9 - change the password");
                        System.out.println("10 - log out");
                        System.out.println("11 - exit");
                        option1 = read.nextInt();
                        switch (option1) {
                            case 1:
                                service.createClient();
                                break;
                            case 2:
                                System.out.println("Your email adress");
                                String email = read.next();
                                System.out.println("Your password");
                                String password = read.next();
                                service.logInAccount(email,password);
                                break;
                            case 3:
                                System.out.println("The number of books (<=4):");
                                int number = read.nextInt();
                                while(number != 0)
                                {
                                    LocalDateTime myDateObj = LocalDateTime.now();
                                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                    String formattedDate = myDateObj.format(myFormatObj);
                                    System.out.println(service.getBooks());
                                    Book book = service.getBookByTitle();
                                    service.rentBooks(formattedDate,book);
                                    number --;
                                }
                                break;
                            case 4:
                                System.out.println(service.getBooks());
                                Book book = service.getBookByTitle();
                                service.addBook(book);
                                break;
                            case 5:
                                System.out.println(service.getBooks());
                                book = service.getBookByTitle();
                                service.removeBook(book);
                                break;
                            case 6:
                                System.out.println(service.getAuthors());
                                Author author = service.getAuthorByFullName();
                                service.addAuthor(author);
                                break;
                            case 7:
                                System.out.println(service.getAuthors());
                                author = service.getAuthorByFullName();
                                service.removeAutor(author);
                                break;
                            case 8:
                                System.out.println("Review text (1) sau stele (2)?");
                                int caz = read.nextInt();
                                if(caz == 1)
                                {
                                    System.out.println(service.getBooks());
                                    book = service.getBookByTitle();
                                    System.out.println("Number of stars:");
                                    int noStars = read.nextInt();
                                    System.out.println("Text");
                                    String text = read.next();
                                    service.leaveReview(noStars,book,text);
                                }
                                else
                                {
                                    System.out.println(service.getBooks());
                                    book = service.getBookByTitle();
                                    System.out.println("Number of stars:");
                                    int noStars = read.nextInt();
                                    service.leaveReview(noStars,book);
                                }
                                break;
                            case 9:
                                service.changePassword();
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


        }while (option < 3) ;
        // System.out.println(service.getBooks());

    }
}