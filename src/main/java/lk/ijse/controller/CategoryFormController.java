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
import lk.ijse.dto.CategoryDto;
import lk.ijse.service.custom.CatergoryService;
import lk.ijse.service.factory.ServiceFactory;
import lk.ijse.service.factory.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoryFormController implements Initializable {


    public TableView catTable;
    public TableColumn tbCatId;
    public TableColumn tbCatName;
    @FXML
    private TextField txtCatId;

    @FXML
    private TextField txtCatName;

    private final CatergoryService catergoryService= ServiceFactory.getService(ServiceType.CATERGORY);

    @FXML

    public void clear(){
        txtCatId.setText("");
        txtCatName.setText("");
    }

    public void btnCarCatClearOnAction(ActionEvent event) {

      clear();

    }

    @FXML
    void btnCarCatDeleteOnAction(ActionEvent event) {

        String categoryId = txtCatId.getText();
        String categoryName = txtCatName.getText();

        CategoryDto categoryDto = new CategoryDto(categoryId, categoryName);

        try {
            catergoryService.delete(categoryDto);
            new Alert(Alert.AlertType.CONFIRMATION, "Category Deleted!").show();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }


    }

    @FXML
    public void btnCarCatSaveOnAction(ActionEvent event) {

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryid(txtCatId.getText());
        categoryDto.setCategoryname(txtCatName.getText());
        try {
            catergoryService.save(categoryDto);
            new Alert(Alert.AlertType.CONFIRMATION, "Category successfully saved!").show();
            loadCategoryTable();
            clear();
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }

    }

    @FXML
    void btnCarCatSearchOnAction(ActionEvent event) {
        CategoryDto categoryDto = catergoryService.searchCategory(txtCatId.getText());
        if (categoryDto != null) {
            new Alert(Alert.AlertType.CONFIRMATION, "Category found!").show();
            txtCatId.setText(categoryDto.getCategoryid());
            txtCatName.setText(categoryDto.getCategoryname());
        } else {
            new Alert(Alert.AlertType.ERROR, "No Category found!").show();
        }


    }

    @FXML
    void btnCarCatUpdateOnAction(ActionEvent event) {

        String categoryId=txtCatId.getText();
        String categoryName= txtCatName.getText();

        CategoryDto categoryDto = new CategoryDto(categoryId, categoryName);


        try {
            catergoryService.update(categoryDto);
            new Alert(Alert.AlertType.CONFIRMATION, "Category Updated!!").show();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tbCatId.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        tbCatName.setCellValueFactory(new PropertyValueFactory<>("categoryName"));




        loadCategoryTable();
    }

    private void loadCategoryTable() {
        catTable.setItems(FXCollections.observableList(catergoryService.getAll()));
    }
}
