package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import practicumopdracht.MainApplication;
import practicumopdracht.comparators.KidsFriendlyComparator;
import practicumopdracht.comparators.ShowDateComparator;
import practicumopdracht.data.DragQueenDAO;
import practicumopdracht.data.ShowDAO;
import practicumopdracht.models.Show;
import practicumopdracht.views.ShowView;
import practicumopdracht.views.View;

import java.time.LocalDate;
import java.util.Optional;

import static javafx.scene.control.Alert.AlertType.*;
import static practicumopdracht.MainApplication.getShowDAO;
import static practicumopdracht.MainApplication.switchController;


/**
 *  The controller for dragqueens it has access to the accompanying view and manages its interactive components
 *
 * @ Author Frank van der Velde
 */
public class ShowController extends Controller {

    private final ShowView view;
    private ObservableList<Show> showObservableList;
    private Show selectedShow;
    private boolean newClicked;
    private final ShowDAO showDAO;
    private final DragQueenDAO dragQueenDAO;

    private final Button newButton;
    private final Button deleteButton;
    private final Button saveButton;
    private final Button detailButton;

    public ShowController() {
        view = new ShowView();
        showDAO = MainApplication.getShowDAO();
        dragQueenDAO = MainApplication.getDragQueenDAO();
        newClicked = true;

        newButton = view.getNewButton();
        deleteButton = view.getDeleteButton();
        saveButton = view.getSaveButton();
        detailButton = view.getSeeDetailButton();

        newButton.setOnAction(actionEvent -> handleNew());
        deleteButton.setOnAction(actionEvent -> handleDelete());
        saveButton.setOnAction(actionEvent -> handleSave());
        detailButton.setOnAction(actionEvent -> handleSwitchScreen());

        view.getLoadMenuButton().setOnAction(actionEvent -> handleMenuLoadButtonClick());
        view.getSaveMenuButton().setOnAction(actionEvent -> handleMenuSaveButtonClick());
        view.getCloseMenuButton().setOnAction(actionEvent -> handleMenuCloseButtonClick());
        view.getSortAsc().setOnAction(actionEvent -> sortKidsFriendlyAscending());
        view.getSortDesc().setOnAction(actionEvent -> sortKidsFriendlyDescending());

        view.getShowList().setOnMouseClicked(onMouseClickedProperty -> handleListClick());

        setListView();
        toggleButtonStates();
        sortDateAscending();
    }

    private void sortDateAscending() {
        FXCollections.sort(view.getShowList().getItems(), new ShowDateComparator());
    }

    private void sortKidsFriendlyAscending() {
        FXCollections.sort(view.getShowList().getItems(), new KidsFriendlyComparator());
    }

    private void sortKidsFriendlyDescending() {
        FXCollections.sort(view.getShowList().getItems(), new KidsFriendlyComparator().reversed());
    }

    private void handleSwitchScreen() {
        Show selectedShow = view.getShowList().getSelectionModel().getSelectedItem();
        if (selectedShow != null) {
            switchController(new DragQueenController(selectedShow));
        }
    }

    private void handleNew() {
        newClicked = true;
        view.getShowList().getSelectionModel().clearSelection();
        clearFields();
        toggleButtonStates();
    }

    private void handleDelete() {
        Optional<ButtonType> result = useAlert(CONFIRMATION, "Are you sure you want to delete this show?").showAndWait();
        if (result.get() == ButtonType.OK) {
            dragQueenDAO.getAllFor(selectedShow).forEach(dragQueenDAO::remove);
            showObservableList.remove(selectedShow);
            showDAO.remove(selectedShow);
            useAlert(INFORMATION, "Succesfully deleted show");
        }
        view.getShowList().getSelectionModel().clearSelection();
        newClicked = true;
        clearFields();
        toggleButtonStates();
    }

    /**
     * Handles the validating of form values and then either updates an existing or adds a new dragqueen
     */
    private void handleSave() {
        Boolean inputFieldsValid = true;
        StringBuilder alertString = new StringBuilder();

        String name = view.getNameTextField().getText();
        String location = view.getLocationTextField().getText();
        LocalDate date = view.getDatePicker().getValue();
        Boolean kidsFriendly = view.getCheckbox().isSelected();

        if (name.replaceAll("\\s+", "").length() == 0) {
            alertString.append("- Name is required \n");
            inputFieldsValid = false;
        }

        if (location.replaceAll("\\s+", "").length() == 0) {
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
            useAlert(WARNING, alertString.toString());
        } else {
            if (newClicked) {
                Show showToAdd = new Show(name, location, date, kidsFriendly);
                showDAO.addOrUpdate(showToAdd);
                setListView();
                clearFields();
                useAlert(INFORMATION, "Added a new show with the values: \n" + showToAdd);
            } else {
                selectedShow.setName(name);
                selectedShow.setLocation(location);
                selectedShow.setDate(date);
                selectedShow.setKidsFriendly(kidsFriendly);
                view.getShowList().refresh();
                useAlert(INFORMATION, "Updated show");
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
        if (selectedShow != null) {

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
    private void setListView() {
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


    /**
     * Handles the click event of the Menu load button and then calls for the data to be loaded
     */
    private void handleMenuLoadButtonClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to load the show and dragqueen data?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            try {
                showDAO.load();
                dragQueenDAO.load();
                setListView();
                useAlert(INFORMATION, "Loading succesful");
            } catch (Exception e) {
                useAlert(INFORMATION, "Loading failed");
            }
        }
    }


    /**
     * Handles the click event of the Menu save button and then calls for the data to be saved
     */
    private void handleMenuSaveButtonClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Would you like to save the show and dragqueen data?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            try {
                showDAO.save();
                dragQueenDAO.save();
                setListView();
                useAlert(INFORMATION, "Saving succesful");
            } catch (Exception e) {
                useAlert(INFORMATION, "Saving failed");
            }
        }
    }

    /**
     * Handles the click event of the Menu close button checks if the user wants to save the data then closes the app
     */
    private void handleMenuCloseButtonClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to close the app?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                handleMenuSaveButtonClick();
            } catch (Exception e) {
                useAlert(INFORMATION, "Saving failed");
            } finally {
                MainApplication.getStage().close();
            }
        }
    }

    @Override
    public View getView() {
        return view;
    }
}
