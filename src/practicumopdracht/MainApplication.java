package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicumopdracht.controllers.Controller;
import practicumopdracht.controllers.ShowController;
import practicumopdracht.data.*;
import practicumopdracht.models.Show;

public class MainApplication extends Application {
    private final String TITLE = String.format("Practicumopdracht OOP2 - %s", Main.studentNaam);
    private final int WIDTH = 640;
    private final int HEIGHT = 600;
    private static final Stage stage = new Stage();
    private static ShowDAO showDAO;
    private static DragQueenDAO dragQueenDAO;

    public static Show selectedShow;

    @Override
    public void start(Stage stage) {

        if(!Main.launchedFromMain) {
            System.err.println("Je moet deze applicatie opstarten vanuit de Main-class, niet de MainApplication-class!");
            System.exit(1337);

            return;
        }

//        MainApplication.showDAO = new DummyShowDAO();
//        MainApplication.dragQueenDAO = new DummyDragQueenDAO();
//        showDAO.load();
//        dragQueenDAO.load();

        MainApplication.showDAO = new TextShowDAO();
        MainApplication.dragQueenDAO = new TextDragQueenDAO();

        MainApplication.stage.setTitle(TITLE);
        MainApplication.stage.setWidth(WIDTH);
        MainApplication.stage.setHeight(HEIGHT);
        switchController(new ShowController());
        MainApplication.stage.show();
    }

    public static final void switchController(Controller controller) {
        stage.setScene(new Scene(controller.getView().getRoot()));
    }

    public static ShowDAO getShowDAO() {
        return showDAO;
    }

    public static DragQueenDAO getDragQueenDAO() {
        return dragQueenDAO;
    }

    public static Stage getStage() {
        return stage;
    }


}
