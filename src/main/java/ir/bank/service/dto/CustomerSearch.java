package ir.bank.service.dto;

public class CustomerSearch {

    private String firstName;
    private String lastName;
    private String username;
    private String code;
    private Long familiarityMethodId;
    private Boolean isActive;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getFamiliarityMethodId() {
        return familiarityMethodId;
    }

    public void setFamiliarityMethodId(Long familiarityMethodId) {
        this.familiarityMethodId = familiarityMethodId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}