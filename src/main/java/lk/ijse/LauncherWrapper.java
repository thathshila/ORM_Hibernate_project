package lk.ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LauncherWrapper extends Application {
public static void main(String[] args) {
    launch(args);
}
    @Override
    public void start(Stage stage) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"));
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.setTitle("Customer Form");
        stage.show();
    }
}
