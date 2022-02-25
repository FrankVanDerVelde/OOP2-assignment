package practicumopdracht.controllers;

import practicumopdracht.models.Show;
import practicumopdracht.views.ShowView;
import practicumopdracht.views.View;

public class ShowController extends Controller {

    private Show show;
    private ShowView view;

    public ShowController() {
    view = new ShowView();

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
