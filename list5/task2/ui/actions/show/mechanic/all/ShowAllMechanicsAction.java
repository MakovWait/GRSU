package by.mkwt.senla.training.carservice.ui.actions.show.mechanic.all;

import by.mkwt.senla.training.carservice.api.CarService;
import by.mkwt.senla.training.carservice.ui.actions.Action;

public class ShowAllMechanicsAction implements Action {

    private final String MESSAGE = "All mechanics ";


    @Override
    public void execute() {
        System.out.println(MESSAGE + CarService.getInstance().getRequestMaster().getAllMechanics());
    }
}
