package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.MainApplication;
import practicumopdracht.models.Show;

import java.util.ArrayList;
import java.util.List;

public class ShowView extends View {

    private TextField nameTextField;
    private TextField locationTextField;
    private DatePicker datePicker;
    private CheckBox checkbox;

    private Button newButton;
    private Button editButton;
    private Button deleteButton;
    private Button seeDetailButton;
    private Button saveButton;

    List<Show> shows = MainApplication.getShowDAO().getAll();

    @Override
    protected void initializeView() {
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        GridPane gridpane = new GridPane();

        vbox.setSpacing(10);
        vbox.setPadding(new Insets(5, 5, 5, 15));
        hbox.setSpacing(10);
        gridpane.setVgap(10);

        Label nameLabel = new Label("Name*");
        nameTextField=new TextField();

        Label locationLabel = new Label("Location*");
        locationTextField=new TextField();

        Label dateLabel = new Label("Date*");
        datePicker = new DatePicker();

        Label labelLocation = new Label("Kids friendly?");
        checkbox = new CheckBox();

        newButton = new Button("New");
        editButton = new Button("Edit");
        deleteButton = new Button("Delete");
        seeDetailButton = new Button("See detail");
        saveButton = new Button("Save");

        gridpane.add(nameLabel, 1, 1);
        gridpane.add(nameTextField, 2, 1);
        gridpane.add(locationLabel, 1, 2);
        gridpane.add(locationTextField, 2, 2);
        gridpane.add(dateLabel, 1, 3);
        gridpane.add(datePicker, 2, 3);
        gridpane.add(labelLocation, 1, 4);
        gridpane.add(checkbox, 2, 4);

        ListView showList = new ListView();

        hbox.getChildren().addAll(
                newButton,
                editButton,
                deleteButton,
                seeDetailButton,
                saveButton
        );

        vbox.getChildren().addAll(
                gridpane,
                showList,
                hbox
        );

        root = vbox;
    }

    // Button getters
    public Button getNewButton() {
        return newButton;
    }

    public Button getEditButton() {
        return editButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getSeeDetailButton() {
        return seeDetailButton;
    }

    // Field getters
    public TextField getNameTextField() {
        return nameTextField;
    }

    public TextField getLocationTextField() {
        return locationTextField;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public CheckBox getCheckbox() {
        return checkbox;
    }

    // Set combobox

    public void setShows(ListView<Show> shows) {
        this.show = shows;
    }
}
