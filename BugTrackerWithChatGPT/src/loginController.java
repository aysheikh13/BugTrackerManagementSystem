import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import java.sql.*;
import java.sql.SQLException;

// The loginController class controls the login and the backend for ensuring the button works and the user is authenticated
public class loginController {
    @FXML
    private TextField emailText;

    @FXML
    private PasswordField passwordText;

    // Here we call both functions, if the verification passes, otherwise an alert is sent and the scene doesn't change
    @FXML
    private void callFunctions(javafx.event.ActionEvent actionEvent) {

        if(verifyLogin(actionEvent)) {
            switchScene(actionEvent);
        }

    }

    // Here we are verifying the login as soon as the button is clicked, which checks the database if it actually exists
    @FXML
    private boolean verifyLogin(javafx.event.ActionEvent actionEvent) {
        String email = emailText.getText();
        String password = passwordText.getText();
        Boolean isvalid = false;
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";


        try (Connection sqlConnection = ConnectionJDBC.getConnection(); PreparedStatement sqlStatement = sqlConnection.prepareStatement(query);) {
            sqlStatement.setString(1, email);
            sqlStatement.setString(2, password);

            ResultSet tableSet = sqlStatement.executeQuery();
            isvalid =  tableSet.next();
            sqlConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        if (isvalid) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Login");
            alert.setHeaderText(null);
            alert.setContentText("Login Successful!");
            alert.showAndWait();
            return isvalid;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect Email or Password!");
            alert.showAndWait();
            return false;
        }


    }

    // Here we are switching the scene when the button is clicked to the bugDashboard scene
    @FXML
    private void switchScene(javafx.event.ActionEvent actionEvent) {
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
    @FXML
    private void switchScene2(javafx.event.ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));

            Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root, 1440, 900);

            currentStage.setScene(scene);
            currentStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}