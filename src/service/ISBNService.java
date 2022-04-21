package service;

import entity.Book.ISBN;
import entity.Book.ISBNFormatException;

public class ISBNService extends MainService {
    protected static ISBNService instance = null;

    public ISBNService() {
    }

    public static ISBNService getInstance() {
        if (instance == null) {
            return instance = new ISBNService();
        }
        return instance;
    }

    ISBN readISBN() throws ISBNFormatException {
        System.out.println("Enter ISBN");
        String isbn = read.next();

        return new ISBN(isbn);
    }


}
