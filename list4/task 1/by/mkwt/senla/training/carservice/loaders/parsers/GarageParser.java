package by.mkwt.senla.training.carservice.loaders.parsers;


import by.mkwt.senla.training.carservice.loaders.ItemParser;
import by.mkwt.senla.training.carservice.loaders.LoaderComponent;
import by.mkwt.senla.training.carservice.models.items.Garage;

public class GarageParser implements ItemParser<Garage> {

    @Override
    public Garage getItemFrom(String line) {
        String[] values = line.split(LoaderComponent.DELIMITER);

        Garage tmpGarage = new Garage();

        tmpGarage.setId(Long.parseLong(values[0]));

        return tmpGarage;
    }

    @Override
    public String getLineFrom(Garage item) {

        String[] result = new String[] {
                String.valueOf(item.getId())
        };

        return String.join(LoaderComponent.BORDER, result);
    }

}
