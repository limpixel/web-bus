package connector;

public class User {
    private int users_id;
    private String username;
    private String email;

    // Constructor
    public User(int users_id, String username, String email) {
        this.users_id = users_id;
        this.username = username;
        this.email = email;
    }

    // Getters
    public int getUsers_id() {
        return users_id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    // Setters (if needed)
    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
