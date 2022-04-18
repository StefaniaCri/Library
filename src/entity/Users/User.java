package entity.Users;


import org.jetbrains.annotations.NotNull;

public class User implements Comparable<User> {
    protected String name;
    protected String surname;
    protected String username;
    protected String email;
    protected String password;
    protected static long id = 0;

    public User(String name, String surname, String username, String email, String password) {
        this.id++;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id= " + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public int compareTo(@NotNull User o) {
        if (getEmail() == null || o.getEmail() == null)
            return 0;
        return getEmail().compareTo(o.getEmail());
    }
}