package practicumopdracht.views;

import javafx.scene.Parent;

/**
 * This abstract view contains the default methods used for views
 *
 * @author Frank van der Velde
 */
public abstract class View {
    protected Parent root;

    public View() {
        initializeView();
    }

    protected abstract void initializeView();

    public Parent getRoot() {
        return root;
    }
}
