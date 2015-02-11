import java.util.*;

public class ParkingLot {

    private int capacity;
    private int threshold;
    private ParkingLotType type;

    private List<Car> parkedCars = new ArrayList<Car>();
    private List<ICapacityFullListener> parkingLotListeners = new ArrayList<ICapacityFullListener>();

    private HashMap<IThresholdListener, Integer> listenersThreshold = new HashMap<IThresholdListener, Integer>();
    private HashMap<String, List<INotification>> eventNotifications = new HashMap<String, List<INotification>>();
    Set<String> events = new HashSet<String>(Arrays.asList("CAPACITY_FULL", "THRESHOLD_REACHED", "THRESHOLD_RELAXED"));



    public ParkingLot(int capacity, ParkingLotType type,int threshold) {
        this.capacity = capacity;
        this.type = type;
        this.threshold = threshold;
    }

    public void registerListener(String event, List<INotification> listener) {
        if (events.contains(event))
            this.eventNotifications.put(event, listener);
    }

    public int park(Car carInstance) {
        parkedCars.add(carInstance);
        if (parkedCars.size() == capacity) {
            for (INotification notification : eventNotifications.get("CAPACITY_FULL"))
                notification.notifyEvent();
        }

        if (parkedCars.size() == threshold) {
            for (INotification notification : eventNotifications.get("THRESHOLD_REACHED"))
                notification.notifyEvent();
        }

        return parkedCars.size();
    }

    public Car unpark(int parkingSlot) {
        Car carBeingUnparked = parkedCars.get(parkingSlot - 1);
        parkedCars.remove(parkingSlot - 1);
        if (parkedCars.size() == threshold) {
            for (INotification notification : eventNotifications.get("THRESHOLD_RELAXED"))
                notification.notifyEvent();
        }
        return carBeingUnparked;
    }
}
