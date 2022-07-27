public class CustomerDetails {

    // Attributes
    private int customerId;
    private String name;
    private String surname;
    private String telephoneNumber;
    private String email;
    private String physicalAddress;


    // Edit Constructor
    public CustomerDetails (int customerId, String name, String surname,
                            String telephoneNumber, String email,
                            String physicalAddress) {
        this.setCustomerId(customerId);
        this.setName(name);
        this.setSurname(surname);
        this.setTelephoneNumber(telephoneNumber);
        this.setEmail(email);
        this.setPhysicalAddress(physicalAddress);


    }

    // Setters and getters for each attribute

    /* Project Building Setters and Getters */
    // Getter method for the ProjectName
    public int getCustomerId() {
        return customerId;
    }

    // Setter method for the ProjectName
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    // Getter method for the ProjectNumber
    public String getName() {
        return name;
    }

    // Setter method for the ProjectNumber
    public void setName(String name) {
        this.name = name;
    }

    // Getter method for the Building
    public String getSurname() {
        return surname;
    }

    // Setter method for the Building
    public void setSurname(String surname) {
        this.surname = surname;
    }

    // Getter method for the ERF
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    // Setter method for the ERF
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    // Getter method for the TotalFee
    public String getEmail() {
        return email;
    }

    // Setter method for the TotalFee
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter method for the PhysicalAddress
    public String getPhysicalAddress() {
        return physicalAddress;
    }

    // Setter method for the PhysicalAddress
    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }


    // toString
    public String toString(){
        String output = "Customer Details -";
        output += "\nCustomerId: " + getCustomerId();
        output += "\nName: " + getName();
        output += "\nSurname: " + getSurname();
        output += "\nTelephone Number: " +  getTelephoneNumber();
        output += "\nEmail: " + getEmail();
        output += "\nPhysical Address: " + getPhysicalAddress();
        return output;
    }
}
