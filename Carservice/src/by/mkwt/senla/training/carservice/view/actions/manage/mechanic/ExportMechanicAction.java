package by.mkwt.senla.training.carservice.view.actions.manage.mechanic;

import by.mkwt.senla.training.carservice.api.CarService;
import by.mkwt.senla.training.carservice.view.Constants;
import by.mkwt.senla.training.ui.api.Action;
import by.mkwt.senla.training.ui.utils.ActionContentHolder;

public class ExportMechanicAction implements Action {
    @Override
    public void execute() {
        CarService.getInstance().getManageMaster().exportMechanicsToCsv();
        ActionContentHolder.setContent(Constants.SUCCESS_MESSAGE);
    }
}
