package entity.Users;

public class Librarian extends User {

    public Librarian(String name, String surname, String username, String email, String password) {
        super(name, surname, username, email, password);
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}