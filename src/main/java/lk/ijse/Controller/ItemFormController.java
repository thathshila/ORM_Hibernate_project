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
import lk.ijse.Entity.Item;
import lk.ijse.Entity.Tm.CustomerTm;
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

    Long selectedCode;

    public void initialize() {
        setTable();
        setCellValueFactory();
        selectTableRow();
    }

    private void setTable() {
        ObservableList<ItemTm> itemTms = FXCollections.observableArrayList();
        List<Item> all = itemBo.getItems();
        for (Item item : all) {
            ItemTm itemTm = new ItemTm(item.getCode(), item.getName(), item.getPrice(), item.getQty());
            itemTms.add(itemTm);
        }
        tableItem.setItems(itemTms);
    }

    private void setCellValueFactory() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }


    private void selectTableRow() {
        tableItem.setOnMouseClicked(event -> {
            int focusedIndex = tableItem.getFocusModel().getFocusedIndex();
            ItemTm itemTm  = tableItem.getItems().get(focusedIndex);
           txtName.setText(itemTm.getName());
           txtPrice.setText(String.valueOf(itemTm.getQty()));
           txtQty.setText(String.valueOf(itemTm.getQty()));
           selectedCode = itemTm.getCode();
        });
    }

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
        itemBo.delete(selectedCode);
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
        String item_name = txtName.getText();
        double price = Double.parseDouble(txtPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());

        ItemDto itemDto = new ItemDto(item_name, price, qty);
        itemBo.update(selectedCode,itemDto);
    }

}
