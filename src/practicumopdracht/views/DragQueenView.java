package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.models.Show;

import java.time.LocalDate;

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
    private Button editButton;
    private Button deleteButton;
    private Button backButton;
    private Button saveButton;

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
        partOfComboBox.getItems().addAll(new Show("Show 1", "Amsterdam", LocalDate.now(), true));

        newButton = new Button("New");
        editButton = new Button("Edit");
        deleteButton = new Button("Delete");
        backButton = new Button("Back to overview");
        saveButton = new Button("Save");

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
                editButton,
                deleteButton,
                backButton,
                saveButton
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

    // Button getters
    public Button getNewButton() {
        return newButton;
    }

    public Button getEditButton() {
        return editButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getBackButton() {
        return backButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    // Form field getters

    public TextField getDragNameTextField() {
        return dragNameTextField;
    }

    public TextField getNameTextField() {
        return nameTextField;
    }

    public TextField getAgeTextField() {
        return ageTextField;
    }

    public TextField getGenderTextField() {
        return genderTextField;
    }

    public TextField getHomeTownTextField() {
        return homeTownTextField;
    }

    public TextField getSalaryTextField() {
        return salaryTextField;
    }

    public TextArea getBioTextArea() {
        return bioTextArea;
    }

    public ComboBox getPartOfComboBox() {
        return partOfComboBox;
    }

}