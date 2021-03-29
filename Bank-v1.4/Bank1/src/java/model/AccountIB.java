package model;

//Class Account InternetBanking
public class AccountIB {

    private long id;
    private String username;
    private String password;
    private int status;

    public AccountIB(String username, String password, int status) {
        this.username = username;
        this.password = password;
        this.status = status;
    }
    public AccountIB(long id,String username, String password) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public AccountIB(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public AccountIB() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
