
package javafxapplication3;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.sql.*;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.beans.value.ObservableValue;

/**
 * @author user
 */
public class FXMLDocumentController implements Initializable {
    Connection connection;
    private ObservableList<Students> students = FXCollections.observableArrayList();
    @FXML
    private AnchorPane panelWindow;

    public ObservableList<Students> getStudents() {
        return students;
    }

    public void setStudents(ObservableList<Students> students) {
        this.students = students;
    }

    @FXML
    private Label label;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonUpdate;

    @FXML
    private Button buttonDelete;
    @FXML
    private TextField inputName;

    @FXML
    private TextField inputPhone;
    @FXML
    private TableView<Students> tableStudents;
    @FXML
    private TableColumn<Students, String> column_Id;
    @FXML
    private TableColumn<Students, String> column_name;
    @FXML
    private TableColumn<Students, String> column_phone;


    @FXML
    private void handleButtonActionAdd(ActionEvent event) {
        label.setText("You Added: " + inputName.getText() + "  Phone: " + inputPhone.getText());
        to_database_add();
        tableStudents.getItems().clear();
        table_students();
        System.out.println("\nYou clicked Add");
    }

    @FXML
    private void handleButtonActionUpdate(ActionEvent event) {
        update_database();
        label.setText("Student Updated!");
        tableStudents.getItems().clear();
        table_students();
        System.out.println("\nYou clicked Update");

    }

    @FXML
    private void handleButtonActionDelete(ActionEvent event) {
        delete_from_database();
        label.setText("Student Deleted!");
        System.out.println("\nYou clicked Delete");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DBConnection database = new DBConnection();
        database.create_or_connection();
        table_students();
        database.select_database();
    }

    void table_students() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:users.db");
            Statement statement = connection.createStatement();
            String query = "SELECT id, name, phone FROM users ";
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                students.add(new Students(rs.getString("id"), rs.getString("name"), rs.getString("phone")));
            }
            column_Id.setCellValueFactory(cellData -> cellData.getValue().column_IdProperty());
            column_name.setCellValueFactory(cellData -> cellData.getValue().column_nameProperty());
            column_phone.setCellValueFactory(cellData -> cellData.getValue().column_phoneProperty());
            tableStudents.setItems(students);

            column_Id.setStyle("  -fx-text-background-color: gold;-fx-font-size:  14;-fx-background-color:  darkslateblue;  -fx-font-weight: bold;   -fx-text-fill:  gold;");
            column_name.setStyle(" -fx-color:  darkslateblue;  -fx-text-background-color: gold;-fx-font-size:  14;  -fx-font-weight: bold;");
            column_phone.setStyle("-fx-color:  darkslateblue;   -fx-text-background-color: gold;-fx-font-size:  14;  -fx-font-weight: bold;");
            tableStudents.setStyle("-fx-control-inner-background-alt: purple;-fx-control-inner-background:  darkolivegreen;");


            tableStudents.getSelectionModel().selectedItemProperty().addListener((final ObservableValue<? extends Students> observable, final Students oldId, final Students newId) -> {
                label.setText(newId.column_IdProperty().toString().replace("StringProperty [value: ", "").replace(']', ' ').trim());
            });

            tableStudents.getSelectionModel().selectedItemProperty().addListener((final ObservableValue<? extends Students> observable, final Students oldName, final Students newName) -> {
                if (newName != null) {
                    inputName.setText(newName.column_nameProperty().toString().replace("StringProperty [value: ", "").replace(']', ' ').trim());
                }
            });

            tableStudents.getSelectionModel().selectedItemProperty().addListener((final ObservableValue<? extends Students> observable, final Students oldPhone, final Students newPhone) -> {
                if (newPhone != null) {
                    inputPhone.setText(newPhone.column_phoneProperty().toString().replace("StringProperty [value: ", "").replace(']', ' ').trim());
                }
            });
            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void to_database_add() {

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:users.db");
            String name = inputName.getText();
            String phone = inputPhone.getText();

            String query =
                    "INSERT INTO users (name, phone)  VALUES('" + name + "' , '" + phone + "') ";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            System.out.printf("Added = Name: %s \tPhone: %s\n", name, phone);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    void delete_from_database() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:users.db");
            Statement statement = connection.createStatement();
            System.out.println("connected");

            String name = inputName.getText();
            String phone = inputPhone.getText();

            String sqldelete = "DELETE FROM users WHERE name =  '" + name + "'; ";
            statement.executeUpdate(sqldelete);
            statement.close();
            int id = tableStudents.getSelectionModel().getSelectedIndex();
            tableStudents.getItems().remove(id);

            System.out.printf("Deleted = Name: %s\n Phone: %s ", name, phone);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void update_database() {

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:users.db");
            Statement statement = connection.createStatement();
            System.out.println("connected");

            String name = inputName.getText();
            String phone = inputPhone.getText();

            String sqldelete = "UPDATE users SET name =  '" + name + "', phone =  '" + phone + "'  WHERE id =  " + label.getText() + ";";
            statement.executeUpdate(sqldelete);
            statement.close();

            System.out.printf("Updated = Name: %s\n Phone: %s", name, phone);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}


