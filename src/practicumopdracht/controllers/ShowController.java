package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import practicumopdracht.MainApplication;
import practicumopdracht.models.Show;
import practicumopdracht.views.ShowView;
import practicumopdracht.views.View;

import java.time.LocalDate;

import static practicumopdracht.MainApplication.*;
import static practicumopdracht.MainApplication.selectedShow;

public class ShowController extends Controller {

    private ShowView view;
    private ObservableList<Show> showObservableList;

    public ShowController() {
    view = new ShowView();

        view.getSeeDetailButton().setOnAction(actionEvent -> handleSwitchScreen());

        view.getEditButton().setOnAction(actionEvent -> handleEdit());
        view.getNewButton().setOnAction(actionEvent -> handleNew());
        view.getDeleteButton().setOnAction(actionEvent -> handleDelete());
        view.getSaveButton().setOnAction(actionEvent -> handleSave());
        view.getShowList().setOnMouseClicked(onMouseClickedProperty -> handleListClick());

        setListView();
    }

    private void handleSwitchScreen() {
        Show selectedShow = view.getShowList().getSelectionModel().getSelectedItem();
        System.out.println(selectedShow);
        if (selectedShow != null) {
            MainApplication.selectedShow = selectedShow;
            switchController(new DragQueenController());
        }
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

            Show show = view.getShowList().getSelectionModel().getSelectedItem();

            show.setName(name);
            show.setLocation(location);
            show.setDate(date);
            show.setKidsFriendly(kidsFriendly);

            view.getShowList().refresh();

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

    private void handleListClick() {
        Show selectedShow = view.getShowList().getSelectionModel().getSelectedItem();
        MainApplication.selectedShow = selectedShow;
        if(selectedShow != null) {

            view.setNameTextField(selectedShow.getName());
            view.setLocationTextField(selectedShow.getLocation());
            view.setDatePicker(selectedShow.getDate());
            view.setCheckbox(selectedShow.getIsKidsFriendly());
        }
    }

    /**
     * Set de listview
     */
    private void setListView(){
        showObservableList = FXCollections.observableArrayList(getShowDAO().getAll());
        view.getShowList().setItems(showObservableList);
    }

    @Override
    public View getView() {
        return view;
    }
}
