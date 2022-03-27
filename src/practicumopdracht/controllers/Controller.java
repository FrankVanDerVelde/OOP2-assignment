package practicumopdracht.controllers;

import javafx.scene.control.Alert;
import practicumopdracht.views.View;

public abstract class Controller {

    public abstract View getView();

    // Return the alert for other usage like confirmation results
    public Alert useAlert(String type, String message) {
        Alert alert;
        switch(type) {
            case "confirm":
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                break;
            case "error":
                alert =  new Alert(Alert.AlertType.ERROR);
                break;
            case "inform":
                alert = new Alert(Alert.AlertType.INFORMATION);
                break;
            case "none":
                alert = new Alert(Alert.AlertType.NONE);
                break;
            case "warn":
                alert = new Alert(Alert.AlertType.WARNING);
                break;
            default:
                alert = new Alert(Alert.AlertType.NONE);
        }

        alert.setContentText(message);
        alert.show();
        return alert;
    }

}
