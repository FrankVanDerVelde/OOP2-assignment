package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicumopdracht.controllers.Controller;
import practicumopdracht.controllers.DragQueenController;
import practicumopdracht.controllers.ShowController;
import practicumopdracht.views.ShowView;
import practicumopdracht.views.View;

public class MainApplication extends Application {
    private final String TITLE = String.format("Practicumopdracht OOP2 - %s", Main.studentNaam);
    private final int WIDTH = 640;
    private final int HEIGHT = 480;

    @Override
    public void start(Stage stage) {

        if(!Main.launchedFromMain) {
            System.err.println("Je moet deze applicatie opstarten vanuit de Main-class, niet de MainApplication-class!");
            System.exit(1337);

            return;
        }

        stage.setTitle(TITLE);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

//        Controller controller = new ShowController();
        Controller controller = new DragQueenController();

        View view = new ShowView();
        Scene scene = new Scene(controller.getView().getRoot());
        stage.setScene(scene);
        stage.show();
    }
}
