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
import lk.ijse.Dao.Custom.OrderDao;
import lk.ijse.Dao.DaoFactory;
import lk.ijse.Dto.CustomerDto;
import lk.ijse.Dto.ItemDto;
import lk.ijse.Dto.OrderDto;
import lk.ijse.Entity.Customer;
import lk.ijse.Entity.Item;
import lk.ijse.Entity.Order;
import lk.ijse.Entity.OrderDetail;
import lk.ijse.Entity.Tm.OrderTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private Label txmId;

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
    OrderDao orderDao = (OrderDao) DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.ORDER);
    OrderBo orderBo = (OrderBo) BoFactory.getBoFactory().getBoType(BoFactory.BoType.ORDER);
    ItemBo itemBo = (ItemBo) BoFactory.getBoFactory().getBoType(BoFactory.BoType.ITEM);
    CustomerBo customerBo = (CustomerBo) BoFactory.getBoFactory().getBoType(BoFactory.BoType.CUSTOMER);

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        setItemCode();
        setCustomerId();
        generateNewOrderId();
    }

    private void setCellValueFactory(){
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
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
        Long id = comboItemCode.getValue();
        String name = txtItemName.getText();
        int quantity = Integer.parseInt(txtQuantity.getText());
        double price = Double.parseDouble(txtPrice.getText());
        double total = quantity * price;
        Button btnRemove = new Button("remove");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int selectedIndex = tblOrder.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0) {
                    obList.remove(selectedIndex);
                    tblOrder.refresh();
                    calculateNetTotal();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Please select an item to remove").show();
                }
            }
        });

        // Check if the item already exists in the table
        for (int i = 0; i < tblOrder.getItems().size(); i++) {
            OrderTm tm = obList.get(i);
            if (tm.getItemName().equals(name)) {
                // If the item exists, update its quantity and total
                quantity += tm.getQuantity();
                total = quantity * price;

                tm.setQuantity(quantity);
                tm.setTotal(total);

                tblOrder.refresh();
                calculateNetTotal();
                return; // Exit the method since the item was found and updated
            }
        }

        // If the item does not exist, add it to the order list
        OrderTm tm = new OrderTm(id,name, quantity, price, total, btnRemove);
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
        //Todo get customer by customer_id
        Customer customer = customerDao.getCustomerById(comboCustomerId.getValue());

        //Todo save order
        OrderDto orderDto = new OrderDto();
        orderDto.setTotal(Double.parseDouble(lblBalance.getText()));
        orderDto.setCustomer(customer);
        orderBo.save(orderDto);

        //Todo save orderDetails
        Order order = orderDao.getOrderById(Long.valueOf(txmId.getText()));
        System.out.println(order);

        List<OrderDetail> orderDetails = new ArrayList<>();
        List<OrderTm> items = tblOrder.getItems();

        for (OrderTm orderTm : items) {
            Item item = itemDao.getItemById(orderTm.getItemId());
            int quantity = orderTm.getQuantity();
            double total = orderTm.getTotal();

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setQuantity(quantity);
            orderDetail.setOrder(order);
            orderDetail.setItem(item);
            orderDetail.setTotal_price(total);
            orderDetails.add(orderDetail);
            System.out.println(orderDetail);
        }
        orderDao.saveOrderDetails(orderDetails);

        //Todo update item
//       for (OrderTm orderTm : items){
//            itemDao.updateQty(orderTm.getItemId(),orderTm.getQuantity());
//       }
        for (OrderTm orderTm : items) {
            boolean success = itemDao.updateQty(orderTm.getItemId(), orderTm.getQuantity());
            System.out.println(orderTm.getItemId());
            System.out.println(orderTm.getQuantity());
            if (!success) {
                System.out.println("Failed to update quantity for item ID: " + orderTm.getItemId());
                // Optional: Handle the failure, such as logging or reattempting the update
            }
        }
    }

    public void comboCustomerIdOnAction(ActionEvent actionEvent) {
        Long customerId = comboCustomerId.getValue();
        CustomerDto customerDto = customerBo.getCustomer(customerId);
        if (customerDto != null) {
            txtCustomerName.setText(customerDto.getName());
        }
    }

    public void comboItemCodeOnAction(ActionEvent actionEvent) {
        Long itemCode = comboItemCode.getValue();
        ItemDto item = itemBo.findById(itemCode);
        if (item != null) {
            txtItemName.setText(item.getName());
            lblAvailableQty.setText(String.valueOf(item.getQty()));
            txtPrice.setText(String.valueOf(item.getPrice()));
        }
    }

    public void generateNewOrderId(){
        //get current id from database
        //get current id+1
        //return new id
       Long nextId = orderDao.getCurrentId();
        txmId.setText(String.valueOf(nextId));
    }
}

