package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ShowView extends View {

    private TextField nameTextField;
    private TextField locationTextField;
    private DatePicker datePicker;
    private CheckBox checkbox;

    private Button newButton;
    private Button deleteButton;
    private Button seeDetailButton;

    @Override
    protected void initializeView() {
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        GridPane gridpane = new GridPane();

        vbox.setSpacing(10);
        vbox.setPadding(new Insets(5, 5, 5, 15));
        hbox.setSpacing(10);
        gridpane.setVgap(10);

        Label nameLabel = new Label("Name");
        nameTextField=new TextField();

        Label locationLabel = new Label("Location");
        locationTextField=new TextField();

        Label dateLabel = new Label("Date");
        datePicker = new DatePicker();

        Label labelLocation = new Label("Kids friendly?");
        checkbox = new CheckBox();

        newButton = new Button("New");
        deleteButton = new Button("Delete");
        seeDetailButton = new Button("See detail");

        gridpane.add(nameLabel, 1, 1);
        gridpane.add(nameTextField, 2, 1);
        gridpane.add(locationLabel, 1, 2);
        gridpane.add(locationTextField, 2, 2);
        gridpane.add(dateLabel, 1, 3);
        gridpane.add(datePicker, 2, 3);
        gridpane.add(labelLocation, 1, 4);
        gridpane.add(checkbox, 2, 4);

        ListView showList = new ListView();

        showList.getItems().add("Item 1");
        showList.getItems().add("Item 2");
        showList.getItems().add("Item 3");

        hbox.getChildren().addAll(
                newButton,
                deleteButton,
                seeDetailButton
        );

        vbox.getChildren().addAll(
                gridpane,
                showList,
                hbox
        );

        root = vbox;
    }

//    public Label getLabel() {
//        return label;
//    }
//
//    public Button getButton() {
//        return button;
//    }

}
