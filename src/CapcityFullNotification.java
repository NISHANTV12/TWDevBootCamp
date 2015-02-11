/**
 * Created by nishantvishwakarma on 10/02/15.
 */
public class CapcityFullNotification implements INotification {

    ICapacityFullListener capacityFullListener;

    public CapcityFullNotification(ICapacityFullListener capacityFullListener) {
        this.capacityFullListener = capacityFullListener;
    }

    @Override
    public void notifyEvent() {
        capacityFullListener.notifyCapacityFull();
    }
}
