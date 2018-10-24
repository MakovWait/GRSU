package by.mkwt.senla.training.list4.task1.carservice.utils;

import by.mkwt.senla.training.list4.task1.carservice.models.managers.GarageManager;
import by.mkwt.senla.training.list4.task1.carservice.models.managers.MechanicManager;
import by.mkwt.senla.training.list4.task1.carservice.models.managers.OrderManager;
import by.mkwt.senla.training.list4.task1.carservice.models.managers.ScheduleManager;
import by.mkwt.senla.training.list4.task1.carservice.utils.sorters.MechanicSorter;
import by.mkwt.senla.training.list4.task1.carservice.utils.sorters.OrderSorter;
import by.mkwt.senla.training.list4.task1.carservice.utils.sorters.comparators.mechanic.MechanicCompareTypes;
import by.mkwt.senla.training.list4.task1.carservice.utils.sorters.comparators.order.OrderCompareTypes;
import by.mkwt.senla.training.list4.task1.carservice.models.items.Garage;
import by.mkwt.senla.training.list4.task1.carservice.models.items.Mechanic;
import by.mkwt.senla.training.list4.task1.carservice.models.items.Order;
import by.mkwt.senla.training.list4.task1.carservice.models.items.OrderState;
import by.mkwt.senla.training.list4.task1.carservice.models.items.Schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class RequestManager {

    private static GarageManager garageManager = new GarageManager();
    private static OrderManager orderManager = new OrderManager();
    private static ScheduleManager scheduleManager = new ScheduleManager();
    private static MechanicManager mechanicManager = new MechanicManager();

    public static ArrayList<Garage> getEmptyGarages() {
        garageManager.buildItemListFromFile();
        ArrayList<Garage> result = new ArrayList<>();

        scheduleManager.buildItemListFromFile();
        ArrayList<Order> activeOrders = getActiveOrders();

        for (Schedule schedule : scheduleManager.getSchedules()) {
            for (Order order : activeOrders) {
                if (schedule.getOrderID().equals(order.getId())) {
                    result.add(garageManager.getGarageByID(schedule.getMechanicID()));
                }
            }
        }
        garageManager.getGarages().removeAll(result);
        result = garageManager.getGarages();
        return result;
    }

    public static ArrayList<Order> getActiveOrders() {
        ArrayList<Order> result = new ArrayList<>();
        orderManager.buildItemListFromFile();

        for (Order order : orderManager.getOrders()) {
            if (order.getState() == OrderState.inProcess) {
                result.add(order);
            }
        }
        return result;
    }

    public static ArrayList<Order> getActiveOrdersSortedBy(OrderCompareTypes type) {
        OrderSorter orderSorter = new OrderSorter();

        ArrayList<Order> result = getActiveOrders();
        result.sort(orderSorter.orderBy(type));

        return result;
    }

    public static ArrayList<Order> getOrderList() {
        orderManager.buildItemListFromFile();
        return orderManager.getOrders();
    }

    public static ArrayList<Order> getOrderListSortedBy(OrderCompareTypes type) {
        OrderSorter orderSorter = new OrderSorter();

        orderManager.buildItemListFromFile();
        orderManager.getOrders().sort(orderSorter.orderBy(type));
        return orderManager.getOrders();
    }

    public static ArrayList<Mechanic> getMechanicList() {
        mechanicManager.buildItemListFromFile();
        return mechanicManager.getMechanics();
    }

    public static ArrayList<Mechanic> getMechanicListSortedBy(MechanicCompareTypes type) {
        MechanicSorter mechanicSorter = new MechanicSorter();

        mechanicManager.buildItemListFromFile();
        mechanicManager.getMechanics().sort(mechanicSorter.orderBy(type));
        return mechanicManager.getMechanics();
    }

    public static Order getOrderByMechanic(Mechanic mechanic) {
        scheduleManager.buildItemListFromFile();
        orderManager.buildItemListFromFile();
        for (Schedule schedule : scheduleManager.getSchedules()) {
            if(schedule.getMechanicID().equals(mechanic.getId())) {
                return orderManager.getOrderByID(schedule.getOrderID());
            }
        }
        return null;
    }

    public static ArrayList<Mechanic> getMechanicsByOrder(Order order) {
        scheduleManager.buildItemListFromFile();
        mechanicManager.buildItemListFromFile();
        ArrayList<Mechanic> mechanics = new ArrayList<>();

        for (Schedule schedule : scheduleManager.getSchedules()) {
            if(schedule.getOrderID().equals(order.getId())) {
                mechanics.add(mechanicManager.getMechanicByID(schedule.getMechanicID()));
            }
        }
        return mechanics;
    }

    public static Date getNearestFreeDate() {
        scheduleManager.buildItemListFromFile();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(scheduleManager.getSchedules().get(scheduleManager.getSchedules().size() - 1).getDate());
        calendar.add(Calendar.DAY_OF_WEEK, 1);

        return calendar.getTime();
    }

}
