package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import practicumopdracht.MainApplication;
import practicumopdracht.data.DAO;
import practicumopdracht.data.DragQueenDAO;
import practicumopdracht.models.DragQueen;
import practicumopdracht.models.Show;
import practicumopdracht.views.ShowView;
import practicumopdracht.views.View;

import java.time.LocalDate;
import java.util.Optional;

import static practicumopdracht.MainApplication.*;
import static practicumopdracht.MainApplication.selectedShow;

public class ShowController extends Controller {

    private ShowView view;
    private ObservableList<Show> showObservableList;
    private Show selectedShow;
    private boolean newClicked;
    private DAO<Show> showDAO;

    private Button newButton;
    private Button deleteButton;
    private Button saveButton;
    private Button detailButton;

    public ShowController() {
    view = new ShowView();
    showDAO =  MainApplication.getShowDAO();
        newClicked = true;

        newButton = view.getNewButton();
        deleteButton = view.getDeleteButton();
        saveButton = view.getSaveButton();
        detailButton = view.getSeeDetailButton();

        newButton.setOnAction(actionEvent -> handleNew());
        deleteButton.setOnAction(actionEvent -> handleDelete());
        saveButton.setOnAction(actionEvent -> handleSave());
        detailButton.setOnAction(actionEvent -> handleSwitchScreen());

        view.getShowList().setOnMouseClicked(onMouseClickedProperty -> handleListClick());

        setListView();
        toggleButtonStates();
    }

    private void handleSwitchScreen() {
        Show selectedShow = view.getShowList().getSelectionModel().getSelectedItem();
        System.out.println(selectedShow);
        if (selectedShow != null) {
            MainApplication.selectedShow = selectedShow;
            switchController(new DragQueenController());
        }
    }

    private void handleNew() {
        newClicked = true;
        view.getShowList().getSelectionModel().clearSelection();
        clearFields();
        toggleButtonStates();
    }

    private void handleDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to delete this show?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){
            DragQueenDAO dragQueenDAO = MainApplication.getDragQueenDAO();
            dragQueenDAO.getAllFor(selectedShow).forEach(dragQueenDAO::remove);
            showObservableList.remove(selectedShow);
            showDAO.remove(selectedShow);
            useAlert("inform", "Succesfully deleted show");
        }
        view.getShowList().getSelectionModel().clearSelection();
        newClicked = true;
        clearFields();
        toggleButtonStates();
    }

    private void handleSave() {
        Boolean inputFieldsValid = true;
        StringBuilder alertString = new StringBuilder();

        String name = view.getNameTextField().getText();
        String location = view.getLocationTextField().getText();
        LocalDate date = view.getDatePicker().getValue();
        Boolean kidsFriendly = view.getCheckbox().isSelected();

        if (name.replaceAll("\\s+","").length() == 0) {
            alertString.append("- Name is required \n");
            inputFieldsValid = false;
        }

        if (location.replaceAll("\\s+","").length() == 0) {
            alertString.append("- Location is required \n");
            inputFieldsValid = false;
        }

        if (date == null) {
            alertString.append("- Date is required \n");
            inputFieldsValid = false;
        } else {
            if (LocalDate.now().isAfter(date)) {
                alertString.append("- Date has to be later than current date");
                inputFieldsValid = false;
            }
        }

        if (inputFieldsValid == false) {
            useAlert("warn", alertString.toString());
        } else {
            if (newClicked) {
                Show showToAdd = new Show(name, location, date, kidsFriendly);
                showObservableList.add(showToAdd);

//                view.getShowList().refresh();
                clearFields();
                useAlert("inform", "Added a new show with the values: \n" + showToAdd);

            } else {
                selectedShow.setName(name);
                selectedShow.setLocation(location);
                selectedShow.setDate(date);
                selectedShow.setKidsFriendly(kidsFriendly);
                view.getShowList().refresh();
                useAlert("inform", "Updated show");
            }
        }
    }

    private void clearFields() {
        view.getNameTextField().clear();
        view.getLocationTextField().clear();
        view.getDatePicker().setValue(null);
        view.getCheckbox().setSelected(false);
    }

    private void handleListClick() {
        newClicked = false;
        selectedShow = view.getShowList().getSelectionModel().getSelectedItem();
        MainApplication.selectedShow = selectedShow;
        if(selectedShow != null) {

            view.setNameTextField(selectedShow.getName());
            view.setLocationTextField(selectedShow.getLocation());
            view.setDatePicker(selectedShow.getDate());
            view.setCheckbox(selectedShow.getIsKidsFriendly());
        }
        toggleButtonStates();
    }

    /**
     * Set de listview
     */
    private void setListView(){
        showObservableList = FXCollections.observableArrayList(getShowDAO().getAll());
        view.getShowList().setItems(showObservableList);
    }

    private void toggleButtonStates() {
        if (view.getShowList().getSelectionModel().getSelectedItem() != null) {
            newButton.setDisable(false);
            deleteButton.setDisable(false);
            detailButton.setDisable(false);

        } else {
            newButton.setDisable(true);
            deleteButton.setDisable(true);
            detailButton.setDisable(true);
        }
    }

    @Override
    public View getView() {
        return view;
    }
}
