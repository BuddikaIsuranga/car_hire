package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import lk.ijse.dao.DaoFactory.FactoryDao;
import lk.ijse.dto.CarDto;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.RentDto;
import lk.ijse.service.custom.CarService;
import lk.ijse.service.custom.CustomerService;
import lk.ijse.service.custom.RentService;
import lk.ijse.service.factory.ServiceFactory;
import lk.ijse.service.factory.ServiceType;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;


public class RentFormController implements Initializable {

    private  RentService rentService = ServiceFactory.getService(ServiceType.RENT);
    private final CustomerService customerService = ServiceFactory.getService(ServiceType.CUSTOMER);
    private final CarService carService = ServiceFactory.getService(ServiceType.CAR);




    public TextField txtRentRatePerDay;
    public DatePicker datePickerStartDate;
    public DatePicker datePickerEndDate;
    @FXML
    private TableColumn colAddPay;

    @FXML
    private TableColumn colEDate;

    @FXML
    private TableColumn colRentId;

    @FXML
    private TableColumn colSDate;

    @FXML
    private TableColumn colTotal;

    @FXML
    private ComboBox comboCarId;

    @FXML
    private ComboBox comboCustId;



    @FXML
    private Label lblDueAmount;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView rentTable;

    @FXML
    private AnchorPane rootRent;

    @FXML
    private TextField txtAdavancePay;

    @FXML
    private TextField txtRentId;







    public void datePickerEndDateOnAction(ActionEvent actionEvent) {

        LocalDate startDate = datePickerStartDate.getValue();
        LocalDate endDate = datePickerEndDate.getValue();
        if (startDate != null && endDate != null) {
            if (startDate.isBefore(endDate)) {
                lblTotal.setText(
                        String.valueOf(
                                Double.parseDouble(
                                        txtRentRatePerDay.getText()) * (endDate.toEpochDay() - startDate.toEpochDay())
                        )
                );
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Date").show();
            }
        }
    }

    public void btnComboCustId(ActionEvent actionEvent) {
    }

    public void cmbCarIdOnAction(ActionEvent actionEvent) {
        String carId = comboCarId.getSelectionModel().getSelectedItem().toString();
        CarDto carDto = carService.searchCar(carId);
        txtRentRatePerDay.setText(String.valueOf(carDto.getCarRate()));
    }


    public void txtRentIdOnActoin(ActionEvent actionEvent) {
    }

    public void advancedPaymentOnKeyReleased(KeyEvent keyEvent) {
        if (!txtAdavancePay.getText().isEmpty()) {
            double total = Double.parseDouble(lblTotal.getText());
            double advance = Double.parseDouble(txtAdavancePay.getText());
            lblDueAmount.setText(String.valueOf(total - advance));
        } else {
            lblDueAmount.setText(lblTotal.getText());
        }
    }


    public void btnPlaceOrder(ActionEvent actionEvent) {
    }

    public void btnAddRentOnAction(ActionEvent actionEvent) {

        try{
        String rentId = txtRentId.getText();
        String carId = comboCarId.getSelectionModel().getSelectedItem().toString();
        String custId = comboCustId.getSelectionModel().getSelectedItem().toString();
        LocalDate startDate = datePickerStartDate.getValue();
        LocalDate endDate = datePickerEndDate.getValue();
        double total = Double.parseDouble(lblTotal.getText());
        double advance = Double.parseDouble(txtAdavancePay.getText());

        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(custId);

        CarDto carDto = new CarDto();
        carDto.setCarId(carId);

        RentDto rentDto = new RentDto(
                rentId,
                Date.valueOf(startDate),
                Date.valueOf(endDate),
                advance,
                total,
                advance < total ? "pending" : "complete",
                customerDto,
                carDto
        );


        rentService.save(rentDto);
        new Alert(Alert.AlertType.CONFIRMATION, "Rent Added Successfully").show();
    }catch (RuntimeException e){
        e.printStackTrace();
        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
    }



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colRentId.setCellValueFactory(new PropertyValueFactory<>("rentId"));
        colSDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colEDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colAddPay.setCellValueFactory(new PropertyValueFactory<>("payment"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        rentTableLoad();
        loadCarIdsList();
        loadCustIdsList();
    }

    private void loadCustIdsList() {
        List<CustomerDto> customerDtoList = customerService.getAll();
        if (customerDtoList != null) {
            ObservableList<String> observableArrayList = FXCollections.observableArrayList();
            customerDtoList.forEach(customerDto -> observableArrayList.add(customerDto.getCustomerId()));
            comboCustId.setItems(observableArrayList);
        }
    }

    private void loadCarIdsList() {
        List<CarDto> carDtoList = carService.getAll();
        if (carDtoList != null) {
            ObservableList<String> observableArrayList = FXCollections.observableArrayList();
            carDtoList.stream().filter(
                    (carDto -> carDto.getIsCarAvailable())
            ).toList().forEach(
                    (carDto -> observableArrayList.add(carDto.getCarId()))
            );
            comboCarId.setItems(observableArrayList);
        }
    }

    private void rentTableLoad() {
        List<RentDto> rentDtoList = rentService.getAll();
        ObservableList observableList = FXCollections.observableArrayList();
        if (rentDtoList != null) {
            rentDtoList.forEach(rentDto -> observableList.add(
                    new RentDto(
                            rentDto.getRentId(),
                            rentDto.getStartDate(),
                            rentDto.getEndDate(),
                            rentDto.getPayment(),
                            rentDto.getTotal()
                    )
            ));
            rentTable.setItems(observableList);
        }
    }

}
