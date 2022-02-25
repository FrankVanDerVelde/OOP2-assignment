package practicumopdracht.controllers;

import practicumopdracht.models.DragQueen;
import practicumopdracht.views.DragQueenView;
import practicumopdracht.views.View;

public class DragQueenController extends Controller {

    private DragQueen dragQueen;
    private DragQueenView view;

    public DragQueenController() {
        view = new DragQueenView();

//    view.getButton().setOnAction(actionEvent -> handleIets());
    }

    private void handleIets() {
        // iets uit model hier
    }

    @Override
    public View getView() {
        return view;
    }
}
