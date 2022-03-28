package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.models.Show;

import java.time.LocalDate;

public class ShowView extends View {

    private Label nameLabel;
    private TextField nameTextField;

    private Label locationLabel;
    private TextField locationTextField;

    private Label dateLabel;
    private DatePicker datePicker;

    private Label kidsFriendlyLabel;
    private CheckBox checkbox;

    private Button newButton;
    private Button deleteButton;
    private Button saveButton;
    private Button seeDetailButton;

    private BorderPane borderPane;
    private ToolBar toolBar;
    private Menu menu;
    private MenuBar menuBar;
    private MenuItem loadMenuButton;
    private MenuItem saveMenuButton;
    private MenuItem closeMenuButton;

    private ListView<Show> showList;

    @Override
    protected void initializeView() {
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        GridPane gridpane = new GridPane();

        vbox.setSpacing(10);
        vbox.setPadding(new Insets(5, 5, 5, 15));
        hbox.setSpacing(10);
        gridpane.setVgap(10);

        nameLabel = new Label("Name*");
        nameTextField=new TextField();

        locationLabel = new Label("Location*");
        locationTextField=new TextField();

        dateLabel = new Label("Date*");
        datePicker = new DatePicker();

        kidsFriendlyLabel = new Label("Kids friendly?");
        checkbox = new CheckBox();

        newButton = new Button("New");
        deleteButton = new Button("Delete");
        seeDetailButton = new Button("See detail");
        saveButton = new Button("Save");

        showList = new ListView();

        // File menu
        borderPane = new BorderPane();
        toolBar = new ToolBar();
        menuBar = new MenuBar();
        menu = new Menu("File");
        loadMenuButton = new MenuItem("Load");
        saveMenuButton = new MenuItem("Save");
        closeMenuButton = new MenuItem("Close");

        menu.getItems().addAll(
                loadMenuButton,
                saveMenuButton,
                closeMenuButton
        );

        menuBar.getMenus().add(menu);
        toolBar.getItems().add(menuBar);
        borderPane.setTop(toolBar);

        gridpane.add(nameLabel, 1, 1);
        gridpane.add(nameTextField, 2, 1);
        gridpane.add(locationLabel, 1, 2);
        gridpane.add(locationTextField, 2, 2);
        gridpane.add(dateLabel, 1, 3);
        gridpane.add(datePicker, 2, 3);
        gridpane.add(kidsFriendlyLabel, 1, 4);
        gridpane.add(checkbox, 2, 4);

        hbox.getChildren().addAll(
                newButton,
                deleteButton,
                saveButton,
                seeDetailButton
        );

        vbox.getChildren().addAll(
                borderPane,
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

    public ListView<Show> getShowList() {
        return showList;
    }

    // file menu getters

    public MenuItem getLoadMenuButton() {
        return loadMenuButton;
    }

    public MenuItem getSaveMenuButton() {
        return saveMenuButton;
    }

    public MenuItem getCloseMenuButton() {
        return closeMenuButton;
    }

    // Form fields setters
    public void setNameTextField(String name) {
        this.nameTextField.setText(name);
    }

    public void setLocationTextField(String location) {
        this.locationTextField.setText(location);
    }

    public void setDatePicker(LocalDate date) {
        this.datePicker.setValue(date);
    }

    public void setCheckbox(Boolean kidsFriendly) {
        this.checkbox.setSelected(kidsFriendly);
    }

}
