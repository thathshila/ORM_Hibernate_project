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
import lk.ijse.Bo.Custom.ItemBo;
import lk.ijse.Dto.ItemDto;
import lk.ijse.Entity.Tm.ItemTm;

import java.io.IOException;
import java.util.List;

public class ItemFormController {

    @FXML
    private AnchorPane anpItem;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableView<ItemTm> tableItem;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    ItemBo itemBo = (ItemBo) BoFactory.getBoFactory().getBoType(BoFactory.BoType.ITEM);


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
        anpItem.getScene().getWindow().hide();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
       String item_name = txtName.getText();
      double price = Double.parseDouble(txtPrice.getText());
      int qty = Integer.parseInt(txtQty.getText());

      ItemDto itemDto = new ItemDto(item_name, price, qty);
      itemBo.save(itemDto);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
