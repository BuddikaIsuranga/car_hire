package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dto.CustomerDto;
import lk.ijse.service.custom.CatergoryService;
import lk.ijse.service.custom.CustomerService;
import lk.ijse.service.factory.ServiceFactory;
import lk.ijse.service.factory.ServiceType;
import lk.ijse.service.impl.CustomerServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    @FXML
    private TableView custTable;

    @FXML
    private TableColumn tbCity;

    @FXML
    private TableColumn tbContact;

    @FXML
    private TableColumn tbCustId;

    @FXML
    private TableColumn tbEMail;

    @FXML
    private TableColumn tbFName;

    @FXML
    private TableColumn tbDistrict;

    @FXML
    private TableColumn tbLName;

    @FXML
    private TableColumn tbPCode;

    @FXML
    private TableColumn tbStreet;

    @FXML
    private TableColumn tbUName;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtCustId;

    @FXML
    private TextField txtDistrict;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFName;

    @FXML
    private TextField txtLName;

    @FXML
    private TextField txtPostCode;

    @FXML
    private TextField txtStreet;

    @FXML
    private TextField txtUserName;

    private CustomerService customerService= ServiceFactory.getService(ServiceType.CUSTOMER);

    public void btnCustSaveOnAction(ActionEvent actionEvent) {
        try {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setCustomerId(txtCustId.getText());
            customerDto.setUserName(txtUserName.getText());
            customerDto.setFName(txtFName.getText());
            customerDto.setLName(txtLName.getText());
            customerDto.setStreet(txtStreet.getText());
            customerDto.setCity(txtCity.getText());
            customerDto.setDistrict(txtDistrict.getText());
            customerDto.setPostCode(txtPostCode.getText());
            customerDto.setEmail(txtEmail.getText());
            customerDto.setContact(txtContact.getText());
            customerService.save(customerDto);
            clear();
            loadCustomerTable();
            new Alert(Alert.AlertType.CONFIRMATION, "Customer saved successfully").show();
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }



    }

    public void btnCustUpdateOnAction(ActionEvent actionEvent) {

        try {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setCustomerId(txtCustId.getText());
            customerDto.setUserName(txtUserName.getText());
            customerDto.setFName(txtFName.getText());
            customerDto.setLName(txtLName.getText());
            customerDto.setStreet(txtStreet.getText());
            customerDto.setCity(txtCity.getText());
            customerDto.setDistrict(txtDistrict.getText());
            customerDto.setPostCode(txtPostCode.getText());
            customerDto.setEmail(txtEmail.getText());
            customerDto.setContact(txtContact.getText());
            customerService.save(customerDto);
            clear();
            loadCustomerTable();
            new Alert(Alert.AlertType.CONFIRMATION, "Customer updated successfully").show();
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }



    }

    public void clear(){
        txtCustId.setText("");
        txtUserName.setText("");
        txtFName.setText("");
        txtLName.setText("");
        txtStreet.setText("");
        txtCity.setText("");
        txtDistrict.setText("");
        txtPostCode.setText("");
        txtEmail.setText("");
        txtContact.setText("");
    }


    public void btnCustClearOnAction(ActionEvent event) {
        clear();

    }

    public void btnCustDeleteOnAction(ActionEvent actionEvent) {
        try {
            String text = txtCustId.getText();
            CustomerDto customerDto = new CustomerDto();
            customerDto.setCustomerId(text);
            customerService.delete(customerDto);
            new Alert(Alert.AlertType.CONFIRMATION, "Customer deleted successfully").show();
            clear();
            loadCustomerTable();
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        }





    public void btnCustSearchOnAction(ActionEvent event) {

        String customerId=txtCustId.getText();
        CustomerDto customerDto=customerService.serach(customerId);
        if(customerDto!=null){
            txtCustId.setText(customerDto.getCustomerId());
            txtUserName.setText(customerDto.getUserName());
            txtFName.setText(customerDto.getFName());
            txtLName.setText(customerDto.getLName());
            txtStreet.setText(customerDto.getStreet());
            txtCity.setText(customerDto.getCity());
            txtDistrict.setText(customerDto.getDistrict());
            txtPostCode.setText(customerDto.getPostCode());
            txtEmail.setText(customerDto.getEmail());
            txtContact.setText(customerDto.getContact());
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tbCustId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tbUName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        tbFName.setCellValueFactory(new PropertyValueFactory<>("fName"));
        tbStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
        tbCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        tbDistrict.setCellValueFactory(new PropertyValueFactory<>("district"));
        tbPCode.setCellValueFactory(new PropertyValueFactory<>("postCode"));
        tbEMail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tbContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        tbLName.setCellValueFactory(new PropertyValueFactory<>("lName"));

        loadCustomerTable();



    }

    private void loadCustomerTable() {
        custTable.setItems(FXCollections.observableList(customerService.getAll()));
    }


}



