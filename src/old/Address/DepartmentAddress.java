package Address;

public class DepartmentAddress  extends  Address{
    private Integer floor;
    private Integer roomNumber;

    public DepartmentAddress(String sector, String street, Integer number, Integer postalCode, Integer floor, Integer roomNumber) {
        super(sector, street, number, postalCode);
        this.floor = floor;
        this.roomNumber = roomNumber;
    }

    public DepartmentAddress(Address a, Integer floor, Integer roomNumber) {
        super(a.getSector(), a.getStreet(), a.getNumber(), a.getPostalCode());
        this.floor = floor;
        this.roomNumber = roomNumber;
    }
    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "DepartmentAddress{" +
                "sector='" + sector + '\'' +
                ", street='" + street + '\'' +
                ", number=" + number +
                ", postalCode=" + postalCode +
                ", floor=" + floor +
                ", roomNumber=" + roomNumber +
                '}';
    }
}