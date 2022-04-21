package entity.Book;

import org.jetbrains.annotations.NotNull;

public class ISBN {
    private final String isbn;

    //verificam daca ISBN-ul cartii este valid altfel aruncam o exceptie
    public ISBN(String isbn) throws ISBNFormatException {
        if (ISBN.isValid(isbn)) this.isbn = isbn;
        else throw new ISBNFormatException("ISBN invalid");
    }

    public static boolean isValid(@NotNull String s) {

        //Conform wikipedia (https://tinyurl.com/wiki-isbn) un isbn e valid daca are lungimea egala cu 10
        //si daca suma cifrelor * (11 - pozitia in cod) e divizibila cu 11

        if (s.length() != 10)
            return false;

        long number = Long.parseLong(s);
        long sum = 0;

        for (int i = 0; i < s.length(); i++) {
            long cifra = number % 10;
            number = number / 10;
            sum = sum + (i + 1) * cifra;
        }

        return (sum % 11) == 0;
    }

    public String getIsbn() {
        return isbn;
    }


    @Override
    public String toString() {
        return "ISBN{" +
                "isbn='" + isbn + '\'' +
                '}';
    }
}