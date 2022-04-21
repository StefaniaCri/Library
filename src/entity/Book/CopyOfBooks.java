package entity.Book;

public class CopyOfBooks {
    private Boolean rented;
    private Integer shelveNumber;
    private Department department;

    public CopyOfBooks(Boolean rented, Integer shelveNumber, Department department) {
        this.rented = rented;
        this.shelveNumber = shelveNumber;
        this.department = department;
    }

    public Boolean getRented() {
        return rented;
    }

    public void setRented(Boolean rented) {
        this.rented = rented;
    }

    public Integer getShelveNumber() {
        return shelveNumber;
    }

    public void setShelveNumber(Integer shelveNumber) {
        this.shelveNumber = shelveNumber;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "CopyOfBooks{" +
                "rented=" + rented +
                ", shelveNumber=" + shelveNumber +
                ", department=" + department +
                '}';
    }
}