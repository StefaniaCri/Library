package service;

import entity.Book.Author;

public class AuthorService extends MainService {
    protected static AuthorService instance = null;

    public AuthorService() {
    }

    public static AuthorService getInstance() {
        if (instance == null) {
            return instance = new AuthorService();
        }
        return instance;
    }

    Author readAuthor() {
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


    public Author getAuthorByFullName() {
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

}
