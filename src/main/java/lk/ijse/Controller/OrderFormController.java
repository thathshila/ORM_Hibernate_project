package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Bo.BoFactory;
import lk.ijse.Bo.Custom.CustomerBo;
import lk.ijse.Bo.Custom.ItemBo;
import lk.ijse.Bo.Custom.OrderBo;
import lk.ijse.Dao.Custom.CustomerDao;
import lk.ijse.Dao.Custom.ItemDao;
import lk.ijse.Dao.DaoFactory;
import lk.ijse.Dto.CustomerDto;
import lk.ijse.Dto.ItemDto;
import lk.ijse.Entity.Tm.OrderTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderFormController {

    @FXML
    private AnchorPane anpOrder;

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colRemove;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private ComboBox<Long> comboCustomerId;

    @FXML
    private ComboBox<Long> comboItemCode;

    @FXML
    private Label lblAvailableQty;

    @FXML
    private TableView<OrderTm> tblOrder;

    @FXML
    private TextField txmId;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQuantity;

    @FXML
            private Label lblBalance;

    ItemDao itemDao = (ItemDao) DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.ITEM);
    CustomerDao customerDao = (CustomerDao) DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.CUSTOMER);
    OrderBo orderBo = (OrderBo) BoFactory.getBoFactory().getBoType(BoFactory.BoType.ORDER);
    ItemBo itemBo = (ItemBo) BoFactory.getBoFactory().getBoType(BoFactory.BoType.ITEM);
    CustomerBo customerBo = (CustomerBo) BoFactory.getBoFactory().getBoType(BoFactory.BoType.CUSTOMER);

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        setItemCode();
        setCustomerId();
    }

    private void setCellValueFactory(){
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("remove"));
    }

    private void setItemCode(){
        List<Long> stringList = itemDao.getItemCode();
        ObservableList<Long> codes = FXCollections.observableArrayList();
        for (Long s : stringList) {
            codes.add(s);
        }
        comboItemCode.setItems(codes);
    }

    private void setCustomerId(){
        List<Long> customerIds = customerDao.getCustomerId();
        ObservableList<Long> id = FXCollections.observableArrayList();
        for (Long customerId : customerIds) {
            id.add(customerId);
        }
        comboCustomerId.setItems(id);
    }

    private ObservableList<OrderTm> obList = FXCollections.observableArrayList();
    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String name = txtItemName.getText();
        int quantity = Integer.parseInt(txtQuantity.getText());
        double price = Double.parseDouble(txtPrice.getText());
        double total = quantity * price;
        Button btnRemove = new Button("remove");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int selectedIndex = tblOrder.getSelectionModel().getSelectedIndex();
                obList.remove(selectedIndex);

                tblOrder.refresh();
                calculateNetTotal();
            }
        });
        for (int i = 0; i < tblOrder.getItems().size(); i++) {
            if (name.equals(txtItemName.getText())) {

                OrderTm tm = obList.get(i);
                quantity += tm.getQuantity();
                total = quantity * price;

                tm.setQuantity(quantity);
                tm.setTotal(total);

                tblOrder.refresh();

                calculateNetTotal();
                return;
            }
        }
        OrderTm tm = new OrderTm(name, quantity, price, total, btnRemove);
        obList.add(tm);

        tblOrder.setItems(obList);
        calculateNetTotal();

    }

    private void calculateNetTotal() {
        int netBalance = 0;
        for (int i = 0; i < tblOrder.getItems().size(); i++) {
            netBalance += (double) colTotal.getCellData(i);
        }
        lblBalance.setText(String.valueOf(netBalance));
    }



    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/OrderForm.fxml"));
        AnchorPane anchorPane = loader.load();

        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setScene(anchorPane.getScene());
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
        stage.show();
        anpOrder.getScene().getWindow().hide();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

    public void comboCustomerIdOnAction(ActionEvent actionEvent) {
        Long customerId = comboCustomerId.getValue();
        CustomerDto customerDto = customerBo.getCustomer(customerId);
        if (customerDto == null) {
            txtCustomerName.setText(customerDto.getName());
        }
    }

    public void comboItemCodeOnAction(ActionEvent actionEvent) {
        Long itemCode = comboItemCode.getValue();
        ItemDto item = itemBo.findById(itemCode);
        if (item != null) {
            lblAvailableQty.setText(String.valueOf(item.getQty()));
            txtPrice.setText(String.valueOf(item.getPrice()));
        }
    }
}
