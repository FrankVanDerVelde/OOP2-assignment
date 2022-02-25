package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DragQueenView extends View {

    // Labels and their inputs
    private TextField dragNameTextField;
    private TextField nameTextField;
    private TextField ageTextField;
    private TextField genderTextField;
    private TextField homeTownTextField;
    private TextField salaryTextField;
    private TextArea bioTextArea;
    private ComboBox partOfComboBox;

    // Controls
    private Button newButton;
    private Button deleteButton;
    private Button backButton;

    @Override
    protected void initializeView() {
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        GridPane gridpane = new GridPane();

        vbox.setSpacing(10);
        vbox.setPadding(new Insets(5, 5, 5, 15));
        hbox.setSpacing(10);
        gridpane.setVgap(10);

        Label partOfLabel = new Label("Part of");
        partOfComboBox = new ComboBox();
        partOfComboBox.getItems().addAll("Show 1", "Show 2", "Show 3", "Show 4", "Show 5");

        newButton = new Button("New");
        deleteButton = new Button("Delete");
        backButton = new Button("Back to overview");

        Label dragNameLabel = new Label("Drag name");
        dragNameTextField = new TextField();

        Label nameLabel = new Label("Real name");
        nameTextField = new TextField();

        Label ageLabel = new Label("Age");
        ageTextField = new TextField();

        Label genderLabel = new Label("Gender");
        genderTextField = new TextField();

        Label homeTownLabel = new Label("Home Town");
        homeTownTextField = new TextField();

        Label salaryLabel = new Label("Salary");
        salaryTextField = new TextField();

        Label bioLabel = new Label("Bio");
        bioTextArea = new TextArea();

        gridpane.add(partOfLabel, 1, 1);
        gridpane.add(partOfComboBox, 2, 1);
        gridpane.add(dragNameLabel, 1, 2);
        gridpane.add(dragNameTextField, 2, 2);
        gridpane.add(nameLabel, 1, 3);
        gridpane.add(nameTextField, 2, 3);
        gridpane.add(ageLabel, 1, 4);
        gridpane.add(ageTextField, 2, 4);
        gridpane.add(genderLabel, 1, 5);
        gridpane.add(genderTextField, 2, 5);
        gridpane.add(homeTownLabel, 1, 6);
        gridpane.add(homeTownTextField, 2, 6);
        gridpane.add(salaryLabel, 1, 7);
        gridpane.add(salaryTextField, 2, 7);
        gridpane.add(bioLabel, 1, 8);
        gridpane.add(bioTextArea, 2, 8);

        ListView queenList = new ListView();

        queenList.getItems().add("Item 1");
        queenList.getItems().add("Item 2");
        queenList.getItems().add("Item 3");

        hbox.getChildren().addAll(
                newButton,
                deleteButton,
                backButton
        );

        vbox.getChildren().addAll(
                gridpane,
                queenList,
                hbox
        );

//        UnaryOperator<Change> filter = change -> {
//            String text = change.getText();
//
//            if (text.matches("[0-9]*")) {
//                return change;
//            }
//
//            return null;
//        };
//        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
//        fieldNport = new TextField();
//        fieldNport.setTextFormatter(textFormatter);

        root = vbox;
    }
}
