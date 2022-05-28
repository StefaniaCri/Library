package service;

import entity.Book.Author;
import repository.AuthorRepository;
import service.Read.AuthorReadFromCSV;
import service.Write.AuthorWriteToCSV;


public class AuthorService extends MainService {
    protected static AuthorService instance = null;
    AuthorWriteToCSV writer = AuthorWriteToCSV.getInstance();
    AuditService auditService = AuditService.getInstance();
    AuthorReadFromCSV reader = AuthorReadFromCSV.getInstance();
    AuthorRepository authorRepositoryUsingStatements = new AuthorRepository();
    private AuthorService() {
        //authors = reader.readCSV();
        authors = authorRepositoryUsingStatements.getAllAuthors();
    }

    public static AuthorService getInstance() {
        if (instance == null) {
            return instance = new AuthorService();
        }
        return instance;
    }

    Author readAuthor() {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());

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
        authorRepositoryUsingStatements.addAuthor(author);
        author.setId(authorRepositoryUsingStatements.getAuthorID(author));
        writer.writeCSV(first_name, last_name);
        System.out.println(authors);
        return author;
    }

    public Author getAuthorByFullName() {
        auditService.write(new Throwable().getStackTrace()[0].getMethodName());
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

    public void updateCSV()
    {
        writer.updateCSV(authors);
    }
}
