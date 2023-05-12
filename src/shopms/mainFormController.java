
package shopms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

public class mainFormController implements Initializable {
    
    @FXML
    private AnchorPane main_form;
    
    @FXML
    private Label username;
    
    @FXML
    private Button dashboard_btn;
    
    @FXML
    private Button inventory_btn;
    
    @FXML
    private Button menu_btn;
    
    @FXML
    private Button customers_btn;
    
    @FXML
    private Button logout_btn;
    
    @FXML
    private AnchorPane dashboard_form;
    
    @FXML
    private AnchorPane customers_form;
    
    @FXML
    private TableView<shopms.customersData> customers_tableView;
    
    @FXML
    private TableColumn<shopms.customersData, String> customers_col_customerID;
    
    @FXML
    private TableColumn<shopms.customersData, String> customers_col_total;
    
    @FXML
    private TableColumn<shopms.customersData, String> customers_col_date;
    
    @FXML
    private TableColumn<shopms.customersData, String> customers_col_cashier;


    private Alert alert;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private Image image;

    
    public ObservableList<shopms.customersData> customersDataList() {
        
        ObservableList<shopms.customersData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM receipt";
        connect = database.connectDB();
        
        try {
            
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            shopms.customersData cData;
            
            while (result.next()) {
                cData = new shopms.customersData(result.getInt("id"),
                        result.getInt("customer_id"),
                        result.getDouble("total"),
                        result.getDate("date"),
                        result.getString("em_username"));
                
                listData.add(cData);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    
    private ObservableList<customersData> customersListData;
    
    public void customersShowData() {
        customersListData = customersDataList();
        
        customers_col_customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customers_col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        customers_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        customers_col_cashier.setCellValueFactory(new PropertyValueFactory<>("emUsername"));
        
        customers_tableView.setItems(customersListData);
    }
    
    public void switchForm(ActionEvent event) {
        
        if (event.getSource() == dashboard_btn) {
//            dashboard_form.setVisible(true);
//            inventory_form.setVisible(false);
//            menu_form.setVisible(false);
//            customers_form.setVisible(false);
            
//            dashboardDisplayNC();
//            dashboardDisplayTI();
//            dashboardTotalI();
//            dashboardNSP();
//            dashboardIncomeChart();
//            dashboardCustomerChart();
            
        } else if (event.getSource() == inventory_btn) {
//            dashboard_form.setVisible(false);
//            inventory_form.setVisible(true);
//            menu_form.setVisible(false);
//            customers_form.setVisible(false);
//
//            inventoryTypeList();
//            inventoryStatusList();
//            inventoryShowData();
        } else if (event.getSource() == menu_btn) {
//            dashboard_form.setVisible(false);
//            inventory_form.setVisible(false);
//            menu_form.setVisible(true);
//            customers_form.setVisible(false);
//
//            menuDisplayCard();
//            menuDisplayTotal();
//            menuShowOrderData();
        } else if (event.getSource() == customers_btn) {
//            dashboard_form.setVisible(false);
//            inventory_form.setVisible(false);
//            menu_form.setVisible(false);
//            customers_form.setVisible(true);
//
//            customersShowData();
        }
        
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
        customersShowData();
        
    }
    
}
