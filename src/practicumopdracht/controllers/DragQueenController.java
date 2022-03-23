package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import practicumopdracht.MainApplication;
import practicumopdracht.models.DragQueen;
import practicumopdracht.models.Show;
import practicumopdracht.views.DragQueenView;
import practicumopdracht.views.View;

import java.time.LocalDate;

import static practicumopdracht.MainApplication.*;

public class DragQueenController extends Controller {

    private DragQueenView view;
    private ComboBox comboBox;
    private ObservableList<DragQueen> showObservableList;

    public DragQueenController() {
        view = new DragQueenView();
        comboBox = view.getComboBox();

        view.getBackButton().setOnAction(actionEvent -> handleSwitchScreen());
        view.getEditButton().setOnAction(actionEvent -> handleEdit());
        view.getNewButton().setOnAction(actionEvent -> handleNew());
        view.getDeleteButton().setOnAction(actionEvent -> handleDelete());
        view.getSaveButton().setOnAction(actionEvent -> handleSave());
        view.getQueenList().setOnMouseClicked(onMouseClickedProperty -> handleListClick());

        setComboBox();
        setListView();
    }

    private void handleSwitchScreen() {
        switchController(new ShowController());
    }

    private void handleEdit() {
        useAlert("confirm", "Edit clicked");
    }

    private void handleNew() {
        useAlert("confirm", "New clicked");
    }

    private void handleDelete() {
        useAlert("confirm", "Delete clicked");
    }

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

//        Boolean kidsFriendly = view.getCheckbox().isSelected();

        if (dragName.replaceAll("\\s+","").length() == 0) {
            alertString.append("- Drag name is requires \n");
            valid = false;
        }

        if (realName.replaceAll("\\s+","").length() == 0) {
            alertString.append("- Location is required \n");
            valid = false;
        }

            try {
                int num = Integer.parseInt(age);
            } catch (NumberFormatException nfe) {
                alertString.append("- Age has to be a non decimal number \n");
                valid = false;
            }

        try {
            double num = Double.parseDouble(salary);
        } catch (NumberFormatException nfe) {
            alertString.append("- Salary has to be a decimal number \n");
            valid = false;
        }


        if (valid == false) {
            useAlert("warn", alertString.toString());
        } else {
            DragQueen dragQueen = new DragQueen(show, dragName, realName, Integer.parseInt(age), gender, homeTown, Double.parseDouble(salary), bio);

            useAlert("inform", dragQueen.toString());

            clearFields();
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
        comboBox.setValue(null);
    }

    private void handleListClick() {
        DragQueen selectedQueen = view.getQueenList().getSelectionModel().getSelectedItem();
        if(selectedShow != null) {

            view.setDragNameTextField(selectedQueen.getDragName());
            view.setNameTextField(selectedQueen.getRealName());
            view.setAgeTextField(String.valueOf(selectedQueen.getAge()));
            view.setGenderTextField(selectedQueen.getGender());
            view.setHomeTownTextField(selectedQueen.getHomeTown());
            view.setSalaryTextField(String.valueOf(selectedQueen.getSalary()));
            view.setBioTextArea(selectedQueen.getBio());

        }
    }

    private void setListView(){
        showObservableList = FXCollections.observableArrayList(getDragQueenDAO().getAllFor(selectedShow));
        view.getQueenList().setItems(showObservableList);
    }

    private void setComboBox(){
        comboBox.getItems().addAll(MainApplication.getShowDAO().getAll());
        comboBox.setValue(selectedShow);
}

    @Override
    public View getView() {
        return view;
    }
}
