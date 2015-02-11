import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

/**
 * Created by nishantvishwakarma on 10/02/15.
 */
public class ParkingLotTest {
    @Test
    public void shouldBeAbleToParkACar()
    {
        ParkingLot parkingLot = new ParkingLot(100,ParkingLotType.PREMIUM,20);

        List<INotification> notificationList =  new ArrayList<INotification>();
        notificationList.add(new CapcityFullNotification(new ParkingLotOwner()));
        notificationList.add(new CapcityFullNotification(new SecurityPersonnel()));
        parkingLot.registerListener("CAPACITY_FULL",notificationList);

        List<INotification> notificationListThresholdReached =  new ArrayList<INotification>();
        notificationListThresholdReached.add(new ThresholdReachedNotification(new ParkingLotOwner()));
        parkingLot.registerListener("THRESHOLD_REACHED",notificationListThresholdReached);

        List<INotification> notificationListThresholdRelaxed =  new ArrayList<INotification>();
        notificationListThresholdRelaxed.add(new ThresholdReachedNotification(new ParkingLotOwner()));
        parkingLot.registerListener("THRESHOLD_RELAXED",notificationListThresholdRelaxed);

        Car carInstance = new Car();
        Assert.assertEquals(1, parkingLot.park(carInstance));
    }

    @Test
    public void shouldGiveUniqueSlotForDifferentCars() throws Exception {
        ParkingLot parkingLot = new ParkingLot(100,ParkingLotType.EXECUTIVE,20);

        List<INotification> notificationList =  new ArrayList<INotification>();
        notificationList.add(new CapcityFullNotification(new ParkingLotOwner()));
        notificationList.add(new CapcityFullNotification(new SecurityPersonnel()));
        parkingLot.registerListener("CAPACITY_FULL",notificationList);

        List<INotification> notificationListThresholdReached =  new ArrayList<INotification>();
        notificationListThresholdReached.add(new ThresholdReachedNotification(new ParkingLotOwner()));
        parkingLot.registerListener("THRESHOLD_REACHED",notificationListThresholdReached);

        List<INotification> notificationListThresholdRelaxed =  new ArrayList<INotification>();
        notificationListThresholdRelaxed.add(new ThresholdReachedNotification(new ParkingLotOwner()));
        parkingLot.registerListener("THRESHOLD_RELAXED",notificationListThresholdRelaxed);

        Car bmwCar = new Car();
        Car audiCar = new Car();
        assertNotEquals(parkingLot.park(bmwCar), parkingLot.park(audiCar));

    }

    @Test
    public void shouldBeAbleToUnparkTheCar()
    {
        ParkingLot parkingLot = new ParkingLot(100,ParkingLotType.EXECUTIVE,20);

        List<INotification> notificationList =  new ArrayList<INotification>();
        notificationList.add(new CapcityFullNotification(new ParkingLotOwner()));
        notificationList.add(new CapcityFullNotification(new SecurityPersonnel()));
        parkingLot.registerListener("CAPACITY_FULL",notificationList);

        List<INotification> notificationListThresholdReached =  new ArrayList<INotification>();
        notificationListThresholdReached.add(new ThresholdReachedNotification(new ParkingLotOwner()));
        parkingLot.registerListener("THRESHOLD_REACHED",notificationListThresholdReached);

        List<INotification> notificationListThresholdRelaxed =  new ArrayList<INotification>();
        notificationListThresholdRelaxed.add(new ThresholdReachedNotification(new ParkingLotOwner()));
        parkingLot.registerListener("THRESHOLD_RELAXED",notificationListThresholdRelaxed);

        Car carInstance = new Car();
        int parkingSlot = parkingLot.park(carInstance);
        Assert.assertEquals(carInstance,parkingLot.unpark(parkingSlot));
    }

    @Test
    public void shouldBeAbleToGetParkingFullNotificationToRegisteredListener()
    {
        ICapacityFullListener listener = mock(ICapacityFullListener.class);

        ParkingLot parkingLot = new ParkingLot(1,ParkingLotType.EXECUTIVE,5);

        List<INotification> notificationList =  new ArrayList<INotification>();
        notificationList.add(new CapcityFullNotification(listener));
        parkingLot.registerListener("CAPACITY_FULL",notificationList);

        parkingLot.park(new Car());

        verify(listener).notifyCapacityFull();
    }

    @Test
    public void shouldBeAbleToGetThresholdReachedNotificationToRegisteredListener()
    {
        IThresholdListener listener = mock(IThresholdListener.class);
        ParkingLot parkingLot = new ParkingLot(10,ParkingLotType.STANDARD,1);

        List<INotification> notificationListThresholdReached =  new ArrayList<INotification>();
        notificationListThresholdReached.add(new ThresholdReachedNotification(listener));
        parkingLot.registerListener("THRESHOLD_REACHED",notificationListThresholdReached);

        parkingLot.park(new Car());

        verify(listener,only()).notifyThresholdReached();
    }

    @Test
    public void shouldBeAbleToGetThresholdRelaxedNotificationToRegisteredListener() {
        IThresholdListener listener = mock(IThresholdListener.class);
        ParkingLot parkingLot = new ParkingLot(10,ParkingLotType.PREMIUM,1);

        List<INotification> notificationListThresholdReached =  new ArrayList<INotification>();
        notificationListThresholdReached.add(new ThresholdReachedNotification(listener));
        parkingLot.registerListener("THRESHOLD_REACHED",notificationListThresholdReached);

        List<INotification> notificationListThresholdRelaxed =  new ArrayList<INotification>();
        notificationListThresholdRelaxed.add(new ThresholdRelaxedNotification(listener));
        parkingLot.registerListener("THRESHOLD_RELAXED",notificationListThresholdRelaxed);

        int parkingSlot = parkingLot.park(new Car());
        int secondParkingSlot = parkingLot.park(new Car());
        parkingLot.unpark(secondParkingSlot);

        verify(listener).notifyThresholdIsControlled();
    }

}
