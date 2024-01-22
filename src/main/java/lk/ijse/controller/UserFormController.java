package lk.ijse.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.dto.UserDto;
import lk.ijse.service.custom.UserService;
import lk.ijse.service.factory.ServiceFactory;
import lk.ijse.service.factory.ServiceType;


public class UserFormController {



    @FXML
    private TextField txtFName;

    @FXML
    private TextField txtLName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtRole;

    @FXML
    private TextField txtUName;

    @FXML
    private TextField txtUserId;

    private UserService userService= ServiceFactory.getService(ServiceType.USER);

    public void clear(){
        txtUserId.setText("");
        txtFName.setText("");
        txtLName.setText("");
        txtUName.setText("");
        txtRole.setText("");
        txtPassword.setText("");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clear();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String userId= txtUserId.getText();
        String fName=txtFName.getText();
        String lName=txtLName.getText();
        String role=txtRole.getText();
        String userName=txtUName.getText();
        String password=txtPassword.getText();

        UserDto userDto = new UserDto(userId,fName,lName,role,userName,password);

        try {
            userService.delete(userDto);
            new Alert(Alert.AlertType.CONFIRMATION, "User Deleted!").show();

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();


        }


    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        String userId= txtUserId.getText();
        String fName=txtFName.getText();
        String lName=txtLName.getText();
        String role=txtRole.getText();
        String userName=txtUName.getText();
        String password=txtPassword.getText();

        UserDto userDto = new UserDto(userId,fName,lName,role,userName,password);

        try {
            userService.save(userDto);
            new Alert(Alert.AlertType.CONFIRMATION, "User Saved!").show();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }




    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String userId= txtUserId.getText();
        String fName=txtFName.getText();
        String lName=txtLName.getText();
        String role=txtRole.getText();
        String userName=txtUName.getText();
        String password=txtPassword.getText();

        UserDto userDto = new UserDto(userId,fName,lName,role,userName,password);

        try {
            userService.update(userDto);
            new Alert(Alert.AlertType.CONFIRMATION, "User Updated!").show();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }




    }

    @FXML
    void btnSearchButtonOnAction(ActionEvent event) {
        String userId= txtUserId.getText();
        UserDto userDto=userService.searchUser(userId);

        if (userDto!=null){
            txtUserId.setText(userDto.getUserId());
            txtFName.setText(userDto.getFName());
            txtLName.setText(userDto.getLName());
            txtUName.setText(userDto.getUserName());
            txtRole.setText(userDto.getRole());
            txtPassword.setText(userDto.getPassword());
        }

    }

}
