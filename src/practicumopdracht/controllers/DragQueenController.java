package practicumopdracht.controllers;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import practicumopdracht.models.DragQueen;
import practicumopdracht.models.Show;
import practicumopdracht.views.DragQueenView;
import practicumopdracht.views.View;

import java.time.LocalDate;

import static practicumopdracht.MainApplication.switchController;

public class DragQueenController extends Controller {

    private DragQueenView view;

    private Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);

    public DragQueenController() {
        view = new DragQueenView();

        view.getBackButton().setOnAction(actionEvent -> handleSwitchScreen());
        view.getEditButton().setOnAction(actionEvent -> handleEdit());
        view.getNewButton().setOnAction(actionEvent -> handleNew());
        view.getDeleteButton().setOnAction(actionEvent -> handleDelete());
        view.getSaveButton().setOnAction(actionEvent -> handleSave());
    }

    private void handleSwitchScreen() {
        switchController(new ShowController());
    }

    private void handleEdit() {
        confirmationAlert.setContentText("Edit clicked");
        confirmationAlert.show();
    }

    private void handleNew() {
        confirmationAlert.setContentText("New clicked");
        confirmationAlert.show();
    }

    private void handleDelete() {
        confirmationAlert.setContentText("Delete clicked");
        confirmationAlert.show();
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
        Show show = (Show) view.getPartOfComboBox().getValue();

//        Boolean kidsFriendly = view.getCheckbox().isSelected();

        if (dragName == null || dragName.replaceAll("\\s+","").length() == 0) {
            alertString.append("- Drag name is requires \n");
            valid = false;
        }

        if (realName == null || realName.replaceAll("\\s+","").length() == 0) {
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
            Alert validationAlert = new Alert(Alert.AlertType.WARNING);
            validationAlert.setContentText(alertString.toString());
            validationAlert.show();
        } else {
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);

            DragQueen dragQueen = new DragQueen(show, dragName, realName, Integer.parseInt(age), gender, homeTown, Double.parseDouble(salary), bio);

            successAlert.setContentText(dragQueen.toString());
            successAlert.show();

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
        view.getPartOfComboBox().setValue(null);
    }

    @Override
    public View getView() {
        return view;
    }
}
