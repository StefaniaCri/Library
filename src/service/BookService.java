package service;

import entity.Book.*;
import service.Read.BookReadFromCSV;
import service.Write.BookWriteToCSV;

public class BookService extends MainService {
    protected static BookService instance = null;
    AuthorService authorService = AuthorService.getInstance();
    ISBNService isbnService = ISBNService.getInstance();
    BookWriteToCSV write = BookWriteToCSV.getInstance();
    BookReadFromCSV reader = BookReadFromCSV.getInstance();
    AuditService auditService = AuditService.getInstance();

    private BookService() {
        books = reader.readCSV();
    }

    public static BookService getInstance() {
        if (instance == null) {
            return instance = new BookService();
        }
        return instance;
    }

    Book readBook() {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());

        ISBN isbn = null;
        try {
            isbn = isbnService.readISBN();
        } catch (ISBNFormatException e) {
            e.printStackTrace();
        }

        System.out.println("Enter title");
        String title = read.next();
        System.out.println("Enter pages");
        Integer pages = read.nextInt();
        System.out.println("Enter author");
        Author author = authorService.readAuthor();
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


        Book a = new Book(isbn, title, pages, author, bookType, publishingHouse);
        write.writeCSV(a);
        return a;
    }

    public Book getBookByTitle() {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());
        System.out.println("Title of the book you are looking for");
        String title = read.next();
        for (Book book1 : instance.books) {

            if (book1.getTitle().equals(title)) {
                return book1;
            }
        }
        return null;
    }
    public void updateCSV()
    {
        write.updateCSV(books);
    }
}
