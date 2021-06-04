package my.fin.project.model.entity;


public class Address extends Entity {

    private String street;
    private String houseNumber;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public static class Builder {

        private String street;
        private String houseNumber;

        public Address.Builder setStreet(String street) {
            this.street = street;

            return this;
        }

        public Address.Builder setHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;

            return this;
        }

        public Address build() {
            Address address = new Address();
            address.street = this.street;
            address.houseNumber = this.houseNumber;
            return address;
        }

    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                '}';
    }
}
