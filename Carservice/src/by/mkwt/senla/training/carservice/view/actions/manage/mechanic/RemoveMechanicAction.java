package by.mkwt.senla.training.carservice.ui.actions.manage.mechanic;

import by.mkwt.senla.training.carservice.api.CarService;
import by.mkwt.senla.training.carservice.logic.exceptions.NoSuchItemException;
import by.mkwt.senla.training.ui.api.Action;
import by.mkwt.senla.training.ui.utils.ActionContentHolder;
import by.mkwt.senla.training.ui.utils.InputReader;

public class RemoveMechanicAction implements Action {

    private final String INFO_MESSAGE = "Specify mechanic id ";

    @Override
    public void execute() {
        long currentId = InputReader.getInstance().parseIntoId(InputReader.getInstance().listenInput(INFO_MESSAGE));
        try {
            CarService.getInstance().getManageMaster().removeMechanic(currentId);
        } catch (NoSuchItemException e) {
            ActionContentHolder.setContent(e.getMessage());
            return;
        }

        ActionContentHolder.setContent("success");
    }
}
