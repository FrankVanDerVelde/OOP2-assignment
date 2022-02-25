package views;

import javafx.scene.Parent;

public abstract class View {
    private Parent root;

    public View() {
//        this.root = initializeView();
        initializeView();
    }

    protected abstract void initializeView();

    public Parent getRoot() {
        return root;
    }
}
