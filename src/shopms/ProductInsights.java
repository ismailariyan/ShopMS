package shopms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ProductInsights implements Initializable {

    @FXML
    private TableColumn<productData, String> category;

    @FXML
    private AnchorPane empSales_form;

    @FXML
    private TableView<productData> proInsight_tableView;

    @FXML
    private TableColumn<productData, String> productID;

    @FXML
    private TableColumn<productData, String> productName;

    @FXML
    private TableColumn<productData, String> unitSold;
    private Alert alert;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private Image image;
    public ObservableList<productData> MostSoldList() {

        ObservableList<productData> listData = FXCollections.observableArrayList();

        String sql = "SELECT prod_id,prod_name,customer.type, SUM(CAST(quantity AS SIGNED)) AS total_quantity FROM customer GROUP BY prod_name ORDER BY total_quantity DESC;";

        connect = database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            productData prodData;

            while (result.next()) {

                prodData = new productData(result.getString("prod_id"),
                        result.getString("prod_name"),
                        result.getString("type"),
                        result.getInt("total_quantity"));

                listData.add(prodData);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    // TO SHOW DATA ON OUR TABLE
    private ObservableList<productData> inventoryListData;

    public void inventoryShowData() {
        inventoryListData = MostSoldList();

        productID.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        category.setCellValueFactory(new PropertyValueFactory<>("type"));
        unitSold.setCellValueFactory(new PropertyValueFactory<>("stock"));

        proInsight_tableView.setItems(inventoryListData);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
inventoryShowData();
    }
}
