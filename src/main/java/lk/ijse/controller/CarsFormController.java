package lk.ijse.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dto.CarDto;
import lk.ijse.dto.CategoryDto;
import lk.ijse.service.custom.CarService;
import lk.ijse.service.custom.CatergoryService;
import lk.ijse.service.factory.ServiceFactory;
import lk.ijse.service.factory.ServiceType;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;

public class CarsFormController implements Initializable {

    @FXML
    private ToggleGroup availability;

    @FXML
    private TableView carTabel;

    @FXML
    private ComboBox cmbCategory;

    @FXML
    private RadioButton rBtnAvailable;

    @FXML
    private RadioButton rBtnNotAvailable;

    @FXML
    private TableColumn tbBrand;

    @FXML
    private TableColumn tbCarId;

    @FXML
    private TableColumn tbModel;

    @FXML
    private TableColumn tbNumber;

    @FXML
    private TableColumn tbRate;

    @FXML
    private TableColumn tbYear;



    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtCarId;

    @FXML
    private TextField txtCarNum;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtRate;

    @FXML
    private TextField txtYear;

    private CarService carService= ServiceFactory.getService(ServiceType.CAR);
    private final CatergoryService categoryService = ServiceFactory.getService(ServiceType.CATERGORY);


    @FXML
    void btnCarIdOnAction(ActionEvent event) {

    }

    public void clear(){
        txtYear.setText("");
        txtRate.setText("");
        txtModel.setText("");
        txtBrand.setText("");
        txtCarNum.setText("");
        txtCarId.setText("");
    }

    @FXML
    void btnCarsClearOnAction(ActionEvent event) {
        clear();

    }

    @FXML
    void btnCarsDeleteOnAction(ActionEvent event) {

        try{
            String idText = txtCarId.getText();
            CarDto carDto = new CarDto();
            carDto.setCarId(idText);
            carService.delete(carDto);
            new Alert(Alert.AlertType.CONFIRMATION,"Car deleted successfully!").show();
            loadCarTable();
            clear();
        }catch (RuntimeException e){
            new Alert(Alert.AlertType.ERROR,"Error : "+e.getMessage()).show();
        }



    }

    @FXML
    void btnSesrchOnAction(ActionEvent event) {
        String carId=txtCarId.getText();
        CarDto carDto=carService.searchCar(carId);
        if(carDto!=null){
            txtCarId.setText(carDto.getCarId());
            txtBrand.setText(carDto.getCarBrand());
            txtCarNum.setText(carDto.getCarNumber());
            txtModel.setText(carDto.getCarModel());
            txtRate.setText(String.valueOf(carDto.getCarRate()));
            txtYear.setText(String.valueOf(carDto.getCarYear()));
        }

    }

    @FXML
    void btnCarsSaveOnAction(ActionEvent event) {

        try{
            CarDto carDto = new CarDto();
            carDto.setCarId(txtCarId.getText());
            carDto.setCarNumber(txtCarNum.getText());
            carDto.setCarBrand(txtBrand.getText());
            carDto.setCarModel(txtModel.getText());
            carDto.setCarYear(Long.parseLong(txtYear.getText()));
            carDto.setCarRate(Long.parseLong(txtRate.getText()));
            carDto.setIsCarAvailable(rBtnAvailable.isSelected());
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setCategoryid(cmbCategory.getSelectionModel().getSelectedItem().toString());
            carDto.setCategoryDto(categoryDto);
            carService.save(carDto);
            new Alert(Alert.AlertType.CONFIRMATION,"Car saved successfully!").show();
            loadCarTable();
            clear();
        }catch (RuntimeException e){
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        }




    }

    @FXML
    void btnCarsUpdateOnAction(ActionEvent event) {

       /* String carId = txtCarId.getText();
        String carNumber = txtCarNum.getText();
        String carBrand = txtBrand.getText();
        String carModel = txtModel.getText();
        Long carYear = Long.valueOf(txtYear.getText());
        Long carRate = Long.valueOf(txtRate.getText());


        CarDto carDto = new CarDto(carId, carNumber, carBrand, carModel, carYear, carRate );

        try {
            carService.update(carDto);
            new Alert(Alert.AlertType.CONFIRMATION, "Car Updated!").show();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }*/






    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tbCarId.setCellValueFactory(new PropertyValueFactory<>("carId"));
        tbNumber.setCellValueFactory(new PropertyValueFactory<>("carNumber"));
        tbBrand.setCellValueFactory(new PropertyValueFactory<>("carBrand"));
        tbModel.setCellValueFactory(new PropertyValueFactory<>("carModel"));
        tbYear.setCellValueFactory(new PropertyValueFactory<>("carYear"));
        tbRate.setCellValueFactory(new PropertyValueFactory<>("carRate"));

        loadCarTable();
        loadCarCategories();

    }

    private void loadCarCategories() {
        List<CategoryDto> all = categoryService.getAll();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        all.forEach(categoryDto -> {
            observableList.add(categoryDto.getCategoryid());
        });
        cmbCategory.setItems(observableList);
    }

    private void loadCarTable() {
        carTabel.setItems(FXCollections.observableList(carService.getAll()));
    }
}


