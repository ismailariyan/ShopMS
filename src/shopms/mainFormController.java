
package shopms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class mainFormController implements Initializable {
    
    @FXML
    private AnchorPane main_form;
    
    @FXML
    private Label username;
    
    @FXML
    private Button logout_btn;


    private Alert alert;
    @FXML
    public void dashboard(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        main_form.getChildren().clear();
        main_form.getChildren().add(root);
    }
    @FXML
    public void inventory(ActionEvent event)throws IOException{
        main_form.getChildren().clear();
        Parent root = FXMLLoader.load(getClass().getResource("inventory.fxml"));

        main_form.getChildren().add(root);
    }
    @FXML
    public void menu(ActionEvent event)throws IOException{
        main_form.getChildren().clear();
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));

        main_form.getChildren().add(root);
    }
    @FXML
    public void custData(ActionEvent event) throws  IOException{
        main_form.getChildren().clear();
        Parent root = FXMLLoader.load(getClass().getResource("customer_form.fxml"));

        main_form.getChildren().add(root);
    }
// LETS PROCEED TO OUR DASHBOARD FORM : )

    public void logout() {
        
        try {
            
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();
            
            if (option.get().equals(ButtonType.OK)) {

                // TO HIDE MAIN FORM 
                logout_btn.getScene().getWindow().hide();

                // LINK YOUR LOGIN FORM AND SHOW IT 
                Parent root = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
                
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                
                stage.setTitle(" Shop Management System");
                
                stage.setScene(scene);
                stage.show();
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void displayUsername() {
        
        String user = data.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);
        
        username.setText(user);
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        displayUsername();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        main_form.getChildren().clear();
        main_form.getChildren().add(root);
        
    }
    
}
