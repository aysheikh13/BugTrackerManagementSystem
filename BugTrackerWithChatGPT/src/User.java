import java.util.ArrayList;

class User {
    // First, we have the variables that describe our user and what consists of them in this context
    private int userID;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    // Next, we have the constructor that will initialize our object of this class
    public User(int userID, String email, String password, String firstName, String lastName) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Lastly, we have some getter/setter functions to obtain or set the variables should it ever be necessary
    public int getUserID() {
        return userID;
    }

    public String getEmail() { return email; }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
