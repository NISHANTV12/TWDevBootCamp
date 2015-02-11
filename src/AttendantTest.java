import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class AttendantTest {
    @Test
    public void shouldBeAbleToParkACar(){

        List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        parkingLots.add(new ParkingLot(5,ParkingLotType.EXECUTIVE,3));
        Attendant attendant = new Attendant(parkingLots);
        Car car = new Car();
        assertEquals(1, attendant.park(car));
    }

    @Test(expected = Exception.class)
    public void shouldNotBeAbleToParkACarOnFullParking() {
        Car car = new Car();
        List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        parkingLots.add(new ParkingLot(0,ParkingLotType.EXECUTIVE,1));
        Attendant attendant = new Attendant(parkingLots);
        attendant.park(car);
    }

    @Test
    public void shouldBeAbleToUnparkACar(){
        Car car = new Car();
        List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        parkingLots.add(new ParkingLot(5,ParkingLotType.PREMIUM,3));
        Attendant attendant = new Attendant(parkingLots);
        int parkingSlot = attendant.park(car);
        assertEquals(car, attendant.unpark(parkingSlot));
    }

//    @Test
//    public void shouldBeAbleToFindParkingSlotInSpecificClass(){
//        List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
//        parkingLots.add(new ParkingLot(4,ParkingLotType.EXECUTIVE,2));
//        Attendant attendant = new Attendant(parkingLots);
//        assertEquals(1,attendant.park(ParkingLotType.EXECUTIVE));
//    }

//    @Test
//    public void shouldBeAbleToFindCheapestParkingSlot(){
//        List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
//        parkingLots.add(new ParkingLot(5,ParkingLotType.STANDARD,3));
//        Attendant attendant = new Attendant(parkingLots);
//        assertEquals(ParkingLotType.STANDARD,attendant.park("cheapest"));
//    }



}
