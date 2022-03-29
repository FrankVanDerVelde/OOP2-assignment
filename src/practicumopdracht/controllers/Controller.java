package practicumopdracht.controllers;

import javafx.scene.control.Alert;
import practicumopdracht.views.View;

/**
 *  The abstract class used to make controllers
 *
 * @ Author Frank van der Velde
 */
public abstract class Controller {

    public abstract View getView();
    /**
     * @param type type of alert possible options are: CONFIRMATION, ERROR, INFORMATION, WARNING, NONE
     * @param message The message displayed in the alert
     * @return returns the alert for further use. For example confirmation button usage
     */
    public Alert useAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.show();
        return alert;
    }

}
