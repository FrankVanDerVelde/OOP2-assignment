package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.models.DragQueen;

/**
 * This view contains all of the front-end element for the dragqueen interface and it's getters and setters
 *
 * @author Frank van der Velde
 */
public class DragQueenView extends View {

    // Labels and their inputs
    private Label dragNameLabel;
    private TextField dragNameTextField;

    private Label nameLabel;
    private TextField nameTextField;

    private Label ageLabel;
    private TextField ageTextField;

    private Label genderLabel;
    private TextField genderTextField;

    private Label homeTownLabel;
    private TextField homeTownTextField;

    private Label salaryLabel;
    private TextField salaryTextField;

    private Label bioLabel;
    private TextArea bioTextArea;

    private ComboBox partOfComboBox;

    private ListView<DragQueen> queenList;

    // Controls
    private Button newButton;
    private Button deleteButton;
    private Button saveButton;
    private Button backButton;

    // radio buttons
    private HBox radioButtonContainer;
    private Label radioButtonLabel;
    private ToggleGroup radioGroup;
    private RadioButton sortSalaryAsc;
    private RadioButton sortSalaryDesc;
    private RadioButton sortAgeAndGenderAsc;
    private RadioButton getSortAgeAndGenderDesc;

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

        newButton = new Button("New");
        deleteButton = new Button("Delete");
        backButton = new Button("Back to overview");
        saveButton = new Button("Save");

        dragNameLabel = new Label("Drag name");
        dragNameTextField = new TextField();

        nameLabel = new Label("Real name");
        nameTextField = new TextField();

        ageLabel = new Label("Age");
        ageTextField = new TextField();

        genderLabel = new Label("Gender");
        genderTextField = new TextField();

        homeTownLabel = new Label("Home Town");
        homeTownTextField = new TextField();

        salaryLabel = new Label("Salary");
        salaryTextField = new TextField();

        bioLabel = new Label("Bio");
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

        queenList = new ListView();

        radioButtonContainer = new HBox();
        radioGroup = new ToggleGroup();
        radioButtonLabel = new Label("Sorting: ");

        sortSalaryAsc = new RadioButton("Salary (0-9)");
        sortSalaryAsc.setToggleGroup(radioGroup);

        sortSalaryDesc = new RadioButton("Salary (9-0)");
        sortSalaryDesc.setToggleGroup(radioGroup);

        sortAgeAndGenderAsc = new RadioButton("Age (0-9)");
        sortAgeAndGenderAsc.setToggleGroup(radioGroup);
        sortAgeAndGenderAsc.setSelected(true);

        getSortAgeAndGenderDesc = new RadioButton("Age (9-0)");
        getSortAgeAndGenderDesc.setToggleGroup(radioGroup);

        radioButtonContainer.getChildren().addAll(
                radioButtonLabel,
                sortSalaryAsc,
                sortSalaryDesc,
                sortAgeAndGenderAsc,
                getSortAgeAndGenderDesc);

        radioButtonContainer.setSpacing(10);

        hbox.getChildren().addAll(
                newButton,
                deleteButton,
                saveButton,
                backButton
        );

        vbox.getChildren().addAll(
                gridpane,
                queenList,
                hbox,
                radioButtonContainer
        );

        root = vbox;
    }

    // Button getters
    public Button getNewButton() {
        return newButton;
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

    // Form field setters
    public void setDragNameTextField(String dragNameTextField) {
        this.dragNameTextField.setText(dragNameTextField);
    }

    public TextField getNameTextField() {
        return nameTextField;
    }

    public void setNameTextField(String nameTextField) {
        this.nameTextField.setText(nameTextField);
    }

    public TextField getAgeTextField() {
        return ageTextField;
    }

    public void setAgeTextField(String ageTextField) {
        this.ageTextField.setText(ageTextField);
    }

    public TextField getGenderTextField() {
        return genderTextField;
    }

    public void setGenderTextField(String genderTextField) {
        this.genderTextField.setText(genderTextField);
    }

    public TextField getHomeTownTextField() {
        return homeTownTextField;
    }

    public void setHomeTownTextField(String homeTownTextField) {
        this.homeTownTextField.setText(homeTownTextField);
    }

    public TextField getSalaryTextField() {
        return salaryTextField;
    }

    public void setSalaryTextField(String salaryTextField) {
        this.salaryTextField.setText(salaryTextField);
    }

    public TextArea getBioTextArea() {
        return bioTextArea;
    }

    public void setBioTextArea(String bioTextArea) {
        this.bioTextArea.setText(bioTextArea);
    }

    public ComboBox getComboBox() {
        return partOfComboBox;
    }

    public ListView<DragQueen> getQueenList() {
        return queenList;
    }

    // Radio button getters
    public Label getDragNameLabel() {
        return dragNameLabel;
    }

    public RadioButton getSortSalaryAsc() {
        return sortSalaryAsc;
    }

    public RadioButton getSortSalaryDesc() {
        return sortSalaryDesc;
    }

    public RadioButton getSortAgeAndGenderAsc() {
        return sortAgeAndGenderAsc;
    }

    public RadioButton getGetSortAgeAndGenderDesc() {
        return getSortAgeAndGenderDesc;
    }
}
