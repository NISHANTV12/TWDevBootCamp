import java.util.List;

public class Attendant {
    private List<ParkingLot> parkingLots;

    public Attendant(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public int park(Car car) {
        return parkingLots.get(0).park(car);
    }

    public Car unpark(int i) {
        Car car = new Car();
        car = parkingLots.get(0).unpark(i);
        return  car;
    }

}
