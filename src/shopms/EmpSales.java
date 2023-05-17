package shopms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class EmpSales implements Initializable {
    @FXML
    private TableView<EmpData> emp_tableView;

    @FXML
    private AnchorPane empSales_form;

    @FXML
    private TableColumn<EmpData,String> emp_id;

    @FXML
    private TableColumn<EmpData,String> emp_name;

    @FXML
    private TableColumn<EmpData,String> totalsales;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private ObservableList<EmpData> EmpListData;

    public ObservableList<EmpData> EmpDataList() {
        ObservableList<EmpData> listData = FXCollections.observableArrayList();
        String sql = "SELECT employee.id, receipt.em_username, SUM(receipt.total) AS total_sales\n" +
                "FROM receipt\n" +
                "JOIN employee ON receipt.em_username = employee.username\n" +
                "GROUP BY receipt.em_username order by total_sales desc;";
        connect = database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            shopms.EmpData eData;

            while (result.next()) {
                eData = new EmpData(
                        result.getInt("id"),
                        result.getString("em_username"),
                        result.getDouble("total_sales")
                );
                listData.add(eData);
            }
            System.out.println(listData);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EmpListData = EmpDataList();
        emp_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        emp_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        totalsales.setCellValueFactory(new PropertyValueFactory<>("total"));
        emp_tableView.setItems(EmpListData);

    }
}
