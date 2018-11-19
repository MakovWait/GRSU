package by.mkwt.senla.training.carservice.ui.actions.manage.garage;

import by.mkwt.senla.training.carservice.api.CarService;
import by.mkwt.senla.training.carservice.logic.exceptions.IllegalIdException;
import by.mkwt.senla.training.carservice.logic.exceptions.IllegalItemLineImplException;
import by.mkwt.senla.training.carservice.logic.exceptions.ItemIsAlreadyExistException;
import by.mkwt.senla.training.carservice.ui.actions.Action;
import by.mkwt.senla.training.carservice.ui.utils.InputReader;

public class AddGarageAction implements Action {

    private final String INFO_MESSAGE = "Specify garage description ";

    @Override
    public void execute() {
        try {
            CarService.getInstance().getManageMaster().addGarage(InputReader.getInstance().listenInput(INFO_MESSAGE));
        } catch (IllegalItemLineImplException | IllegalIdException | ItemIsAlreadyExistException e) {
            e.printStackTrace();
        }
    }
}
