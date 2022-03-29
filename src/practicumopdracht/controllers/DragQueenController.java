package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import practicumopdracht.MainApplication;
import practicumopdracht.comparators.DragQueenAgeAndGenderComparator;
import practicumopdracht.comparators.SalaryComparator;
import practicumopdracht.data.DragQueenDAO;
import practicumopdracht.models.DragQueen;
import practicumopdracht.models.Show;
import practicumopdracht.views.DragQueenView;
import practicumopdracht.views.View;

import java.util.Optional;

import static javafx.scene.control.Alert.AlertType.*;
import static practicumopdracht.MainApplication.getDragQueenDAO;
import static practicumopdracht.MainApplication.switchController;

/**
 *  The controller for dragqueens it has access to the accompanying view and manages its interactive components
 *
 * @ Author Frank van der Velde
 */
public class DragQueenController extends Controller {

    private final DragQueenView view;
    private final ComboBox comboBox;
    private final DragQueenDAO dragQueenDAO;
    private final Button newButton;
    private final Button deleteButton;
    private final Button saveButton;
    private final Button backButton;
    private final Show selectedShow;
    private ObservableList<DragQueen> dragQueenObservableList;
    private boolean newClicked;
    private DragQueen selectedQueen;

    public DragQueenController(Show show) {
        view = new DragQueenView();
        comboBox = view.getComboBox();
        dragQueenDAO = MainApplication.getDragQueenDAO();
        newClicked = true;
        selectedShow = show;

        newButton = view.getNewButton();
        deleteButton = view.getDeleteButton();
        saveButton = view.getSaveButton();
        backButton = view.getBackButton();

        newButton.setOnAction(actionEvent -> handleNew());
        deleteButton.setOnAction(actionEvent -> handleDelete());
        saveButton.setOnAction(actionEvent -> handleSave());
        backButton.setOnAction(actionEvent -> handleSwitchScreen());

        view.getQueenList().setOnMouseClicked(onMouseClickedProperty -> handleListClick());

        view.getSortSalaryAsc().setOnAction(actionEvent -> sortSalaryAscending());
        view.getSortSalaryDesc().setOnAction(actionEvent -> sortSalaryDescending());
        view.getSortAgeAndGenderAsc().setOnAction(actionEvent -> sortAgeAndGenderAscending());
        view.getGetSortAgeAndGenderDesc().setOnAction(actionEvent -> sortAgeAndGenderDescending());

        setComboBox();
        setListView();
        toggleButtonStates();
        sortAgeAndGenderAscending();
    }

    private void sortSalaryAscending() {
        FXCollections.sort(view.getQueenList().getItems(), new SalaryComparator());
    }

    private void sortSalaryDescending() {
        FXCollections.sort(view.getQueenList().getItems(), new SalaryComparator().reversed());
    }

    private void sortAgeAndGenderAscending() {
        FXCollections.sort(view.getQueenList().getItems(), new DragQueenAgeAndGenderComparator());
    }

    private void sortAgeAndGenderDescending() {
        FXCollections.sort(view.getQueenList().getItems(), new DragQueenAgeAndGenderComparator().reversed());
    }

    private void handleSwitchScreen() {
        switchController(new ShowController());
    }

    private void handleNew() {
        view.getQueenList().getSelectionModel().clearSelection();
        clearFields();
        newClicked = true;
        toggleButtonStates();
    }

    private void handleDelete() {
        Optional<ButtonType> result = useAlert(CONFIRMATION, "confirm selection").showAndWait();

        if (result.get() == ButtonType.OK) {
            dragQueenDAO.remove(selectedQueen);
            dragQueenObservableList.remove(selectedShow);
            dragQueenDAO.remove(selectedQueen);
            useAlert(INFORMATION, "Succesfully deleted queen");
        }
        view.getQueenList().getSelectionModel().clearSelection();
        newClicked = true;
        clearFields();

        toggleButtonStates();
    }


    /**
     * Handles the validating of form values and then either updates an existing or adds a new dragqueen
     */
    private void handleSave() {
        Boolean valid = true;
        StringBuilder alertString = new StringBuilder();

        String dragName = view.getDragNameTextField().getText();
        String realName = view.getNameTextField().getText();
        String age = view.getAgeTextField().getText();
        String gender = view.getGenderTextField().getText();
        String homeTown = view.getHomeTownTextField().getText();
        String salary = view.getSalaryTextField().getText();
        String bio = view.getBioTextArea().getText();
        Show show = (Show) comboBox.getValue();

        int castAge = 0;
        double castSalary = 0;

        if (dragName.replaceAll("\\s+", "").length() == 0) {
            alertString.append("- Drag name is requires \n");
            valid = false;
        }

        if (realName.replaceAll("\\s+", "").length() == 0) {
            alertString.append("- Location is required \n");
            valid = false;
        }

        if (show == null) {
            alertString.append("- Setting a show is required \n");
            valid = false;
        }

        try {
            castAge = Integer.parseInt(age);
        } catch (NumberFormatException nfe) {
            alertString.append("- Age has to be a non decimal number \n");
            valid = false;
        }
        if (castAge < 0) {
            alertString.append("- Age has to be higher than 0 \n");
            valid = false;
        }

        try {
            castSalary = Double.parseDouble(salary);
        } catch (NumberFormatException nfe) {
            alertString.append("- Salary has to be a decimal number \n");
            valid = false;
        }

        if (castSalary < 0) {
            alertString.append("- Salary has to be higher than 0 \n");
            valid = false;
        }

        if (valid == false) {
            useAlert(WARNING, alertString.toString());
        } else {
            if (newClicked) {
                DragQueen queenToAdd = new DragQueen(show, dragName, realName, Integer.parseInt(age), gender, homeTown, Double.parseDouble(salary), bio);
                dragQueenDAO.addOrUpdate(queenToAdd);
                useAlert(INFORMATION, "Added a new show with the values: \n" + queenToAdd);
                setListView();
                clearFields();
            } else {
                selectedQueen.setDragName(dragName);
                selectedQueen.setRealName(realName);
                selectedQueen.setAge(castAge);
                selectedQueen.setGender(gender);
                selectedQueen.setHomeTown(homeTown);
                selectedQueen.setSalary(castSalary);
                selectedQueen.setBio(bio);
                selectedQueen.setBelongsTo(show);
                view.getQueenList().refresh();
                useAlert(INFORMATION, "Updated show");
            }
        }
    }

    private void clearFields() {
        view.getNameTextField().clear();
        view.getDragNameTextField().clear();
        view.getAgeTextField().clear();
        view.getGenderTextField().clear();
        view.getHomeTownTextField().clear();
        view.getSalaryTextField().clear();
        view.getBioTextArea().clear();
        comboBox.setValue(selectedShow);
    }

    private void handleListClick() {
        selectedQueen = view.getQueenList().getSelectionModel().getSelectedItem();
        if (selectedQueen != null) {
            newClicked = false;
            view.setDragNameTextField(selectedQueen.getDragName());
            view.setNameTextField(selectedQueen.getRealName());
            view.setAgeTextField(String.valueOf(selectedQueen.getAge()));
            view.setGenderTextField(selectedQueen.getGender());
            view.setHomeTownTextField(selectedQueen.getHomeTown());
            view.setSalaryTextField(String.valueOf(selectedQueen.getSalary()));
            view.setBioTextArea(selectedQueen.getBio());
        }
        toggleButtonStates();
    }

    private void setListView() {
        dragQueenObservableList = FXCollections.observableArrayList(getDragQueenDAO().getAllFor(selectedShow));
        view.getQueenList().setItems(dragQueenObservableList);
    }

    private void setComboBox() {
        comboBox.getItems().addAll(MainApplication.getShowDAO().getAll());
        comboBox.setValue(selectedShow);
    }

    private void toggleButtonStates() {
        if (view.getQueenList().getSelectionModel().getSelectedItem() != null) {
            newButton.setDisable(false);
            deleteButton.setDisable(false);
        } else {
            newButton.setDisable(true);
            deleteButton.setDisable(true);
        }
    }

    @Override
    public View getView() {
        return view;
    }
}
