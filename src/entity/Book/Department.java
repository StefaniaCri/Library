package entity.Book;

import entity.Address.DepartmentAddress;


public class Department {
    private String title;
    private DepartmentAddress location;

    public Department(String title, DepartmentAddress location) {
        this.title = title;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DepartmentAddress getLocation() {
        return location;
    }

    public void setLocation(DepartmentAddress location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Department{" +
                "title='" + title + '\'' +
                ", location=" + location +
                '}';
    }
}
