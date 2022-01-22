package application.model.dto;

public class AddClientDto {
    private String fullName;
    private String mainPhoneNumber;

    public AddClientDto(String fullName, String mainPhoneNumber) {
        this.fullName = fullName;
        this.mainPhoneNumber = mainPhoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMainPhoneNumber() {
        return mainPhoneNumber;
    }

    public void setMainPhoneNumber(String mainPhoneNumber) {
        this.mainPhoneNumber = mainPhoneNumber;
    }

    @Override
    public String toString() {
        return "AddClientDto{"
                + "fullName='" + fullName + '\''
                + ", mainPhoneNumber='" + mainPhoneNumber + '\''
                + '}';
    }
}
