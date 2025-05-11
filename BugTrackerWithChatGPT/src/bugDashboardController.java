import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;

public class bugDashboardController {

    // For displaying the bug dashboard I created a Table View with Table Columns to contain each data cell and its respective values
    @FXML
    private TableView<Bug> bugsTable;
    @FXML
    private TableColumn<Bug,String> bugIDColumn;
    @FXML
    private TableColumn<Bug,String> bugTitleColumn;
    @FXML
    private TableColumn<Bug,String> bugDescriptionColumn;
    @FXML
    private TableColumn<Bug,Integer> bugPriorityColumn;
    @FXML
    private TableColumn<Bug,String> bugTypeColumn;
    @FXML
    private TableColumn<Bug,String> bugStatusColumn;
    @FXML
    private TableColumn<Bug,String> bugDateColumn;
    @FXML
    private TableColumn<Bug,String> bugAssignedToColumn;
    @FXML
    private TableColumn<Bug,String> bugReportedToColumn;
    @FXML
    private TableColumn<Bug, String> debugColumn;

    // This is perhaps the most interesting method in that the initialize() method runs automatically as soon as the scene loads
    // What this method does is setting the values of the table, getting its data from the database
    @FXML
    private void initialize() {
        List<Bug> bugs = new ArrayList<>();
        String sql = "SELECT * FROM bugs";

        bugsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        bugIDColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBugID()));
        bugTitleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBugTitle()));
        bugDescriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBugDescription()));
        bugPriorityColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getBugPriority()).asObject());
        bugTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBugType()));
        bugStatusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBugStatus()));
        bugDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBugDate()));
        bugAssignedToColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBugAssignedTo()));
        bugReportedToColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBugReportedTo()));

        // This is where the cell data sets its information to the response that is gained from ChatGPT API that gets its input from the description of the bug
        debugColumn.setCellValueFactory(cellData -> {Bug bug = cellData.getValue();
            String debugResponse = null;
            try {
                debugResponse = chatGPTAPI.chatGPTCall(bug.getBugDescription());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new SimpleStringProperty(debugResponse);
        });

        // Here are a set of set.cellFactory() calls that wraps the text properly
        bugTitleColumn.setCellFactory(cellData -> {
            TableCell<Bug, String> cell = new TableCell<>();
            Text wrappedText = new Text();
            cell.setGraphic(wrappedText);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            wrappedText.wrappingWidthProperty().bind(bugDescriptionColumn.widthProperty());
            wrappedText.textProperty().bind(cell.itemProperty());
            return cell;
        });

        bugDescriptionColumn.setCellFactory(cellData -> {
            TableCell<Bug, String> cell = new TableCell<>();
            Text wrappedText = new Text();
            cell.setGraphic(wrappedText);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            wrappedText.wrappingWidthProperty().bind(bugDescriptionColumn.widthProperty());
            wrappedText.textProperty().bind(cell.itemProperty());
            return cell;
        });

        debugColumn.setCellFactory(cellData -> {
            TableCell<Bug, String> cell = new TableCell<>();
            Text wrappedText = new Text();
            cell.setGraphic(wrappedText);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            wrappedText.textProperty().bind(cell.itemProperty().asString().map(text -> text.replace("\n", " ")));
            wrappedText.wrappingWidthProperty().bind(bugDescriptionColumn.widthProperty());
            wrappedText.textProperty().bind(cell.itemProperty());
            return cell;
        });

        try (Connection sqlConnection = ConnectionJDBC.getConnection(); Statement sqlStatement = sqlConnection.createStatement(); ResultSet sqlResultSet = sqlStatement.executeQuery(sql)) {

            while (sqlResultSet.next()) {
                String bugID = sqlResultSet.getString("bugID");
                String bugTitle = sqlResultSet.getString("bugTitle");
                String bugDescription = sqlResultSet.getString("bugDescription");
                int bugPriority = sqlResultSet.getInt("bugPriority");
                String bugType = sqlResultSet.getString("bugType");
                String bugStatus = sqlResultSet.getString("bugStatus");
                String bugDate = sqlResultSet.getString("bugDate");
                String bugAssignedTo = sqlResultSet.getString("bugAssignedTo");
                String bugReportedTo = sqlResultSet.getString("bugReportedTo");

                bugs.add(new Bug(bugID, bugTitle, bugDescription, bugPriority, bugType, bugStatus, bugDate, bugAssignedTo, bugReportedTo));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Lastly, we have the observable list that turns the whole array into an list that the user can see and the items are set from the already made bug object
        ObservableList<Bug> data = FXCollections.observableArrayList(bugs);
        bugsTable.setItems(data);
    }

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

    @FXML
    private void switchScene2(javafx.event.ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("createBugReport.fxml"));

            Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root, 1440, 900);

            currentStage.setScene(scene);
            currentStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
