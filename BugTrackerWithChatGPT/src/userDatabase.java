import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

class userDatabase {
    private String sqlQuery;
    private ArrayList<User> users;

    private PreparedStatement sqlStatement;

    public userDatabase() {
        users = new ArrayList<>();
        sqlQuery = "SELECT * FROM users";
    }
    // Method adds user to the database with a sql query and also establishing connection to the database
    public void addUser(User user) {

        users.add(user);
        sqlQuery = "Insert into users (userID, email, password, firstName, lastName) values(?,?,?,?,?)";

        try(Connection databaseConnection = ConnectionJDBC.getConnection(); PreparedStatement sqlStatement = databaseConnection.prepareStatement(sqlQuery);) {

            sqlStatement.setInt(1, user.getUserID());
            sqlStatement.setString(2, user.getEmail());
            sqlStatement.setString(3, user.getPassword());
            sqlStatement.setString(4, user.getFirstName());
            sqlStatement.setString(5, user.getLastName());


            sqlStatement.executeUpdate();
            databaseConnection.close();

            System.out.println("User added successfully");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    // This method performs the delete which deletes the user from the sql database
    public void deleteUser(User user) {
        sqlQuery = "DELETE FROM users WHERE userID = ?";

        try(Connection databaseConnection = ConnectionJDBC.getConnection(); PreparedStatement sqlStatement = databaseConnection.prepareStatement(sqlQuery);) {


            sqlStatement.setInt(1, user.getUserID());

            sqlStatement.executeUpdate();
            databaseConnection.close();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    // Next, we have the getUsers method which basically gets all the users within the database
    public ArrayList<User> getUsers() {
        sqlQuery = "SELECT * FROM users";

        try(Connection databaseConnection = ConnectionJDBC.getConnection(); PreparedStatement sqlStatement = databaseConnection.prepareStatement(sqlQuery);) {
            sqlStatement.executeUpdate();
            databaseConnection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    // I made this method in case the user would forget their password and wanted to reset it based on their userID
    public void updatePassword(User user) {
        sqlQuery = "UPDATE users SET password = ? WHERE userID = ?";

        try(Connection databaseConnection = ConnectionJDBC.getConnection(); PreparedStatement sqlStatement = databaseConnection.prepareStatement(sqlQuery);) {

            sqlStatement.setString(1, user.getPassword());
            sqlStatement.setInt(2, user.getUserID());

            sqlStatement.executeUpdate();
            databaseConnection.close();

        } catch(SQLException e) {
            e.printStackTrace();
        }

    }
    // This method basically gets the user based on their userID
    public User getUser(String userID) {
        for(User user : users) {
            if(userID.equals(user.getUserID())) {
                return user;
            }
        }
        return null;
    }


}
