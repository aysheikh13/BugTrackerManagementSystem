import java.util.ArrayList;
import java.sql.Connection;
import java.sql.*;

public class bugDatabase {

    private String sqlQuery;
    private ArrayList<Bug> bugs;
    private PreparedStatement sqlStatement;

    // Constructor that initializes the list and sets up a normal query statement
    bugDatabase() {
        bugs = new ArrayList<>();
        sqlQuery = "SELECT * FROM bugs";
    }

    // Here our first method adds a bug to the database and the list whilst attempting to establish connection to the database
    public void addBug(Bug bug) {
        bugs.add(bug);
        sqlQuery = "Insert into bugs (bugID, bugTitle, bugDescription, bugPriority, bugType, bugStatus, bugDate, bugAssignedTo, bugReportedTo) values(?,?,?,?,?,?,?,?,?)";

        try(Connection databaseConnection = ConnectionJDBC.getConnection(); PreparedStatement sqlStatement = databaseConnection.prepareStatement(sqlQuery);) {

            sqlStatement.setString(1, bug.getBugID());
            sqlStatement.setString(2, bug.getBugTitle());
            sqlStatement.setString(3, bug.getBugDescription());
            sqlStatement.setInt(4, bug.getBugPriority());
            sqlStatement.setString(5, bug.getBugType());
            sqlStatement.setString(6, bug.getBugStatus());
            java.sql.Date bugDate = java.sql.Date.valueOf(bug.getBugDate());
            sqlStatement.setDate(7, bugDate);
            sqlStatement.setString(8, bug.getBugAssignedTo());
            sqlStatement.setString(9, bug.getBugReportedTo());

            sqlStatement.executeUpdate();
            databaseConnection.close();

            System.out.println("Bug added successfully");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    // This method deletes the bug from the database
    public void deleteBug(Bug bug) {
        sqlQuery = "DELETE FROM bugs WHERE bugID = ?";

        try(Connection databaseConnection = ConnectionJDBC.getConnection(); PreparedStatement sqlStatement = databaseConnection.prepareStatement(sqlQuery);) {


            sqlStatement.setString(1, bug.getBugID());

            sqlStatement.executeUpdate();
            databaseConnection.close();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    // Here we can update the status of the bug by passing a bug as a paremeter
    public void updateBugStatus(Bug bug) {
        sqlQuery = "UPDATE bugs SET bugStatus = ? WHERE bugID = ?";

        try(Connection databaseConnection = ConnectionJDBC.getConnection(); PreparedStatement sqlStatement = databaseConnection.prepareStatement(sqlQuery);) {

            sqlStatement.setString(1, bug.getBugStatus());
            sqlStatement.setString(2, bug.getBugID());

            sqlStatement.executeUpdate();
            databaseConnection.close();

        } catch(SQLException e) {
            e.printStackTrace();
        }

    }

    // This method returns the bugs list and also returns the table of bugs that is already within the database
    public ArrayList<Bug> getBugs() {
        sqlQuery = "SELECT * FROM bugs";

        try(Connection databaseConnection = ConnectionJDBC.getConnection(); PreparedStatement sqlStatement = databaseConnection.prepareStatement(sqlQuery);) {
            sqlStatement.executeUpdate();
            databaseConnection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return bugs;
    }

    // Lastly, we get the bug from a bugID that is passed as a paremeter that can return the bug associated with that id or if not a null is returned if the bug cannot be found
    public Bug getBug(String bugid) {
        for(Bug bug : bugs) {
            if(bugid.equals(bug.getBugID())) {
                return bug;
            }
        }
        return null;
    }

}
