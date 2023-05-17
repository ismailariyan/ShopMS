package shopms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CustomerForm implements Initializable {
    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<customersData> customers_tableView;

    @FXML
    private TableColumn<customersData, String> customers_col_customerID;

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
    private ObservableList<customersData> customersListData;

    public ObservableList<customersData> customersDataList() {
        ObservableList<shopms.customersData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM receipt";
        connect = database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            shopms.customersData cData;

            while (result.next()) {
                cData = new shopms.customersData(
                        result.getInt("id"),
                        result.getInt("customer_id"),
                        result.getDouble("total"),
                        result.getDate("date"),
                        result.getString("em_username")
                );
                listData.add(cData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<customersData> filteredCustomersListData;

    public void filterCustomersByDate(LocalDate selectedDate) {
        filteredCustomersListData = FXCollections.observableArrayList();
        for (customersData data : customersListData) {
            if (data.getDate().toLocalDate().equals(selectedDate)) {
                filteredCustomersListData.add(data);
            }
        }

        customers_tableView.setItems(filteredCustomersListData);
    }

    public void resetCustomersList() {
        customers_tableView.setItems(customersListData);
    }

    @FXML
    private void onDateSelected() {
        LocalDate selectedDate = datePicker.getValue();
        if (selectedDate != null) {
            filterCustomersByDate(selectedDate);
        } else {
            resetCustomersList();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customersListData = customersDataList();

        customers_col_customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customers_col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        customers_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        customers_col_cashier.setCellValueFactory(new PropertyValueFactory<>("emUsername"));

        customers_tableView.setItems(customersListData);
    }
}
