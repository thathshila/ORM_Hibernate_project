package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Bo.BoFactory;
import lk.ijse.Bo.Custom.CustomerBo;
import lk.ijse.Dto.CustomerDto;
import lk.ijse.Entity.Tm.CustomerTm;

import java.io.IOException;
import java.util.List;

public class CustomerFormController {

    @FXML
    private AnchorPane anpCustomer;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    CustomerBo customerBo = (CustomerBo) BoFactory.getBoFactory().getBoType(BoFactory.BoType.CUSTOMER);




    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/DashboardForm.fxml"));
        AnchorPane anchorPane = loader.load();

        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setScene(anchorPane.getScene());
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
        stage.show();
        anpCustomer.getScene().getWindow().hide();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String cus_name = txtName.getText();
        String cus_address = txtAddress.getText();
        String cus_contact = txtContact.getText();

        CustomerDto customerDto = new CustomerDto(cus_name,cus_address,cus_contact);
        customerBo.save(customerDto);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
