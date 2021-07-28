package pl.lodz.p.it.spjava.e11.huntingBook.dto;

import pl.lodz.p.it.spjava.e11.huntingBook.model.enums.AccountType;

public class AccountDTO {

    private String login;

    private String password;

    private boolean isActive;

    private String name;

    private String surname;

    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    private String email;

    private AccountType type;

    public AccountDTO() {
    }

    public AccountDTO(String login, boolean isActive, String name, String surname, String email, AccountType type) {
        this.login = login;
        this.isActive = isActive;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.type = type;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AccountDTO{" + "login=" + login +  ", isActive=" + isActive + ", name=" + name + ", surname=" + surname + ", email=" + email + ", type=" + type + '}';
    }

}
