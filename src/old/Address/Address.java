package Address;

public class Address {
    protected   String sector;
    protected String street;
    protected Integer number;
    protected Integer postalCode;
    private static long id = 0;

    public Address(String sector, String street, Integer number, Integer postalCode) {
        this.id ++;
        this.sector = sector;
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Address{" +
                "sector='" + sector + '\'' +
                ", street='" + street + '\'' +
                ", number=" + number +
                '}';
    }
}