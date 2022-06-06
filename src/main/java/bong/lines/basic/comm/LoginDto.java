package bong.lines.basic.comm;

public class LoginDto {

    private String name;
    private String email;
    private String userId;
    private String password;

    public LoginDto() {
    }

    public LoginDto(String name, String email, String userId, String password) {
        this.name = name;
        this.email = email;
        this.userId = userId;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
