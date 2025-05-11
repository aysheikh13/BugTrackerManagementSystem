import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import java.util.Random;

public class signUpController {
    // Text Fields and Password Fields for the inputs and fields shown within the fxml file that we gain
    @FXML
    private TextField email;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField passwordVerify;

    // Here we call multiple functions for when the button is clicked instead of just calling simply one
    // The methods called will check if the the signUp is valid and switch scenes as well as give an alert when its valid
    @FXML
    private void callFunctions(ActionEvent actionEvent) {
        if(signUp(actionEvent))
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sign Up");
            alert.setHeaderText(null);
            alert.setContentText("Sign Up Successful!");
            alert.showAndWait();
            switchScene(actionEvent);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sign Up");
            alert.setHeaderText(null);
            alert.setContentText("Passwords do not match!");
            alert.showAndWait();
        }
    }

    @FXML
    private boolean signUp(ActionEvent actionEvent) {
        String userEmail = email.getText();
        String userFirstName = firstName.getText();
        String userLastName = lastName.getText();
        String userPassword = password.getText();
        String userPasswordVerify = passwordVerify.getText();

        Random rand = new Random();

        Integer userIDInteger = rand.nextInt(99999);

        if(userPassword.equals(userPasswordVerify))
        {
            User user = new User(userIDInteger, userEmail, userPassword, userFirstName, userLastName);
            userDatabase db = new userDatabase();
            db.addUser(user);
            return true;
        }

        return false;

    }


    // Like the previous functions swtichScene
    @FXML
    private void switchScene(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root, 1440, 900);

            currentStage.setScene(scene);
            currentStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
