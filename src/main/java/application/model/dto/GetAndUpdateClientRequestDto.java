package application.model.dto;

public class GetAndUpdateClientRequestDto {
    private Long clientId;
    private String fullName;
    private String passport;
    private String dateOfBirth;
    private String additionalPhoneNumber;

    public GetAndUpdateClientRequestDto(Long clientId, String fullName,
                                        String passport, String dateOfBirth,
                                        String additionalPhoneNumber) {
        this.clientId = clientId;
        this.fullName = fullName;
        this.passport = passport;
        this.dateOfBirth = dateOfBirth;
        this.additionalPhoneNumber = additionalPhoneNumber;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAdditionalPhoneNumber() {
        return additionalPhoneNumber;
    }

    public void setAdditionalPhoneNumber(String additionalPhoneNumber) {
        this.additionalPhoneNumber = additionalPhoneNumber;
    }

    @Override
    public String toString() {
        return "GetAndUpdateClientRequestDto{"
                + "clientId=" + clientId
                + ", fullName='" + fullName + '\''
                + ", passport='" + passport + '\''
                + ", dateOfBirth='" + dateOfBirth + '\''
                + ", additionalPhoneNumber='" + additionalPhoneNumber + '\''
                + '}';
    }
}
