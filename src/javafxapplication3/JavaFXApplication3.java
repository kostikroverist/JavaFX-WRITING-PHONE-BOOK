/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;
import java.sql. *;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author user
 */

public class JavaFXApplication3 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
 
        stage.setScene(scene);
        stage.show();

        DBConnection dbConnection = new DBConnection();
        dbConnection.create_or_connection();
        
    }

    /**
     * @param args the command line arguments
     */
     Connection connection; 
     
    public static void main(String[] args) {
        launch(args);
     
    }
    
}
