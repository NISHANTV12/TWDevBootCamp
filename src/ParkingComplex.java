import java.util.HashMap;

public class ParkingComplex {

    private HashMap<ParkingLot, Integer> parkingCost = new HashMap<ParkingLot, Integer>();

    private ParkingLot premiumParking = new ParkingLot(5, ParkingLotType.PREMIUM,2);
    private ParkingLot executiveParking = new ParkingLot(5, ParkingLotType.EXECUTIVE,2);
    private ParkingLot standardParking = new ParkingLot(5, ParkingLotType.STANDARD,2);

    public ParkingComplex() {
        parkingCost.put(premiumParking, 100);
        parkingCost.put(executiveParking, 70);
        parkingCost.put(standardParking, 50);
    }

    public int park(ParkingLotType parkingType, Car objCar) {
        int parkingSlot = 0;
        switch (parkingType) {
            case PREMIUM:
                parkingSlot = premiumParking.park(objCar);
                break;
            case EXECUTIVE:
                parkingSlot = executiveParking.park(objCar);
                break;
            case STANDARD:
                parkingSlot = standardParking.park(objCar);
                break;
        }
        return parkingSlot;
    }
}



