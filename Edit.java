public class Edit {

    // Attributes
    private int projectNumber;
    private String projectName;
    private String building;
    private String physicalAddress;
    private String erf;
    private float totalFee;
    private float paidToDate;
    private String deadline;
    private String completeDate;
    private String complete;
    private int engineerId;
    private int architectId;
    private int contractorId;
    private int customerId;


    // Edit Constructor
    public Edit (int projectNumber, String projectName, String building,
                 String physicalAddress, String erf,
                 Float totalFee,Float paidToDate, String deadline,
                 String completeDate, String complete, int engineerId,
                 int architectId, int contractorId, int customerId) {
        this.setProjectNumber(projectNumber);
        this.setProjectName(projectName);
        this.setBuilding(building);
        this.setPhysicalAddress(physicalAddress);
        this.setERF(erf);
        this.setTotalFee(totalFee);
        this.setPaidToDate(paidToDate);
        this.setDeadline(deadline);
        this.setCompleteDate(completeDate);
        this.setComplete(complete);
        this.setEngineerId(engineerId);
        this.setArchitectId(architectId);
        this.setContractorId(contractorId);
        this.setCustomerId(customerId);


    }

    // Setters and getters for each attribute

    /* Project Building Setters and Getters */
    // Getter method for the ProjectName
    public int getProjectNumber() {
        return projectNumber;
    }

    // Setter method for the ProjectName
    public void setProjectNumber(int projectNumber) {
        this.projectNumber = projectNumber;
    }

    // Getter method for the ProjectNumber
    public String getProjectName() {
        return projectName;
    }

    // Setter method for the ProjectNumber
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    // Getter method for the Building
    public String getBuilding() {
        return building;
    }

    // Setter method for the Building
    public void setBuilding(String building) {
        this.building = building;
    }

    // Getter method for the PhysicalAddress
    public String getPhysicalAddress() {
        return physicalAddress;
    }

    // Setter method for the PhysicalAddress
    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    // Getter method for the ERF
    public String getERF() {
        return erf;
    }

    // Setter method for the ERF
    public void setERF(String erf) {
        this.erf = erf;
    }

    // Getter method for the TotalFee
    public float getTotalFee() {
        return totalFee;
    }

    // Setter method for the TotalFee
    public void setTotalFee(float totalFee) {
        this.totalFee = totalFee;
    }

    // Getter method for the Paid_to_date
    public float getPaidToDate() {
        return paidToDate;
    }

    // Setter method for the Paid_to_date
    public void setPaidToDate(float paidToDate) {
        this.paidToDate = paidToDate;
    }

    // Getter method for the Deadline
    public String getDeadline() {
        return deadline;
    }

    // Setter method for the Deadline
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    // Getter method for the Complete date
    public String getCompletedDate() {
        return completeDate;
    }

    // Setter method for the Complete Date
    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
    }

    // Getter method for the Project status
    public String getComplete() {
        return complete;
    }

    // Setter method for the Project status
    public void setComplete(String complete) {
        this.complete = complete;
    }


    /*Engineer Setters and Getters*/

    // Getter method for the ID
    public int getEngineerId() {
        return engineerId;
    }


    // Setter method for the ID
    public void setEngineerId(int engineerId) {
        this.engineerId = engineerId;
    }



    /* Architect Setters and Getters */

    // Getter method for the Name
    public int getArchitectId() {
        return architectId;
    }

    // Setter method for the Name
    public void setArchitectId(int architectId) {
        this.architectId = architectId;
    }


    /* Contractor Setters and Getters */

    // Getter method for the Name
    public int getContractorId() {
        return contractorId;
    }

    // Setter method for the Name
    public void setContractorId(int contractorId) {
        this.contractorId = contractorId;
    }


    /* Customer Setters and Getters */

    // Getter method for the Name
    public int getCustomerId() {
        return customerId;
    }

    // Setter method for the Name
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }


    // toString
    public String toString(){
        return getProjectName() + ", " + getProjectNumber() +
                ", " + getBuilding() + ", " + getPhysicalAddress() +
                ", " + getERF() + ", " + getTotalFee() +
                ", " + getPaidToDate() + ", " + getDeadline() +
                ", " + getCompletedDate() + ", " + getComplete() +
                ", " + getEngineerId() + ", " + getEngineerId() +
                ", " + getArchitectId() + ", " + getContractorId() +
                ", " + getCustomerId();
    }
}
