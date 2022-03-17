package practicumopdracht.controllers;

import practicumopdracht.models.Show;
import practicumopdracht.views.ShowView;
import practicumopdracht.views.View;

import java.time.LocalDate;

import static practicumopdracht.MainApplication.switchController;

public class ShowController extends Controller {

    private ShowView view;

    public ShowController() {
    view = new ShowView();

        view.getSeeDetailButton().setOnAction(actionEvent -> handleSwitchScreen());

        view.getEditButton().setOnAction(actionEvent -> handleEdit());
        view.getNewButton().setOnAction(actionEvent -> handleNew());
        view.getDeleteButton().setOnAction(actionEvent -> handleDelete());
        view.getSaveButton().setOnAction(actionEvent -> handleSave());
    }

    private void handleSwitchScreen() {
        switchController(new DragQueenController());
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

        String name = view.getNameTextField().getText();
        String location = view.getLocationTextField().getText();
        LocalDate date = view.getDatePicker().getValue();
        Boolean kidsFriendly = view.getCheckbox().isSelected();

        if (name.replaceAll("\\s+","").length() == 0) {
            alertString.append("- Name is required \n");
            valid = false;
        }

        if (location.replaceAll("\\s+","").length() == 0) {
            alertString.append("- Location is required \n");
            valid = false;
        }

        if (date == null) {
            alertString.append("- Date is required \n");
            valid = false;
        } else {
            if (LocalDate.now().isAfter(date)) {
                alertString.append("- Date has to be later than current date");
                valid = false;
            }
        }

        if (valid == false) {
            useAlert("warn", alertString.toString());
        } else {
            Show dragShow = new Show(name, location, date, kidsFriendly);

            useAlert("inform", dragShow.toString());

            clearFields();
        }
    }

    private void clearFields() {
        view.getNameTextField().clear();
        view.getLocationTextField().clear();
        view.getDatePicker().setValue(null);
        view.getCheckbox().setSelected(false);
    }

    @Override
    public View getView() {
        return view;
    }
}
