import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.Random;


public class createBugReportController {
    // Here are the textfields associated with inserting the data and getting it from the fxml file that the user inputs on the frontend
    @FXML
    private TextField bugTitle;

    @FXML
    private TextField bugDescription;

    @FXML
    private TextField bugPriority;

    @FXML
    private TextField bugType;

    @FXML
    private TextField bugStatus;

    @FXML
    private TextField bugDate;

    @FXML
    private TextField bugAssigned;

    @FXML
    private TextField bugReported;

    // Essentially the createBugReport Method creates a bug and inserts it into the database for completion along with sending out an alert
    @FXML
    private void createBugReport(javafx.event.ActionEvent actionEvent) {
        Random rand = new Random();

        Integer bugIDInteger = rand.nextInt(99999);
        String bugID = String.valueOf(bugIDInteger);
        String title = bugTitle.getText();
        String description = bugDescription.getText();
        Integer priority = Integer.valueOf(bugPriority.getText());
        String type = bugType.getText();
        String status = bugStatus.getText();
        String date = bugDate.getText();
        String assigned = bugAssigned.getText();
        String reported = bugReported.getText();

        Bug bug = new Bug(bugID, title, description, priority, type, status, date, assigned, reported);
        bugDatabase db = new bugDatabase();

        db.addBug(bug);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Bug Report");
        alert.setHeaderText(null);
        alert.setContentText("Bug Report Successful!");
        alert.showAndWait();
    }

    // This essentially switches the scene when a button is pressed which essentially goes to the login
    @FXML
    private void switchScene(javafx.event.ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

            Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root, 1440, 900);

            currentStage.setScene(scene);
            currentStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Similar to the previous function we are basically switching the scene to the bugDashboard
    @FXML
    private void switchScene2(javafx.event.ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("bugDashboard.fxml"));

            Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root, 1440, 900);

            currentStage.setScene(scene);
            currentStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
