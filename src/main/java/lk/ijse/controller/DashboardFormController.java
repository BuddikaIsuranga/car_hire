package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {


    public AnchorPane formAnchorPane;

    public void categoryButtonOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/category_form.fxml"));

        this.formAnchorPane.getChildren().clear();
        this.formAnchorPane.getChildren().add(root);

        

    }

    public void carsButtonOnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/view/cars_form.fxml"));

        this.formAnchorPane.getChildren().clear();
        this.formAnchorPane.getChildren().add(root);

    }

    public void customerButtonOnAction(ActionEvent actionEvent) throws IOException {


        Parent root = FXMLLoader.load(this.getClass().getResource("/view/customer_form.fxml"));

        this.formAnchorPane.getChildren().clear();
        this.formAnchorPane.getChildren().add(root);
    }

    public void userButtonOnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/view/user_form.fxml"));

        this.formAnchorPane.getChildren().clear();
        this.formAnchorPane.getChildren().add(root);
    }

    public void rentButtonOnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/view/rent_form.fxml"));

        this.formAnchorPane.getChildren().clear();
        this.formAnchorPane.getChildren().add(root);
    }

    public void activeRentsButtonOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/ActiveRent_form.fxml"));

        this.formAnchorPane.getChildren().clear();
        this.formAnchorPane.getChildren().add(root);
    }

    public void logoutButtonOnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));

        Scene scene = new Scene(root);

        Stage stage = (Stage) formAnchorPane.getScene().getWindow();

        stage.setScene(scene);
        stage.centerOnScreen();
    }
}
