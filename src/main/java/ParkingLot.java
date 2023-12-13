import java.util.List;
import java.util.Set;

public class ParkingLot {

    private int capacity;

    private Set<Car> cars;

    private final List<MembersToNotify> membersToNotifyList;


    public ParkingLot(int capacity, Set<Car> cars, List<MembersToNotify> membersToNotifyList) {
        this.capacity = capacity;
        this.cars = cars;
        this.membersToNotifyList = membersToNotifyList;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean parkCar(Car car, ParkingLot parkingLot) throws ParkingLotException {
        int parkingCapacity = parkingLot.getCapacity();
        if (parkingCapacity <= 0) {
            throw new ParkingLotException("Parking full !");
        }
        parkingCapacity--;
        parkingLot.setCapacity(parkingCapacity);
        if(parkingCapacity == 0){
        for(MembersToNotify member : parkingLot.membersToNotifyList) {
            member.notifyFull(parkingLot);
        }
        }
        return true;
    }

    public boolean unParkCar(Car car, ParkingLot parkingLot) throws ParkingLotException {
        int parkingCapacity = parkingLot.getCapacity();
        if (parkingCapacity <= -1) {
            throw new ParkingLotException("Parking empty !");
        }
        parkingCapacity++;
        parkingLot.setCapacity(parkingCapacity);
        if(parkingCapacity == 1){
            for(MembersToNotify member : parkingLot.membersToNotifyList) {
                member.notifyAvailable(parkingLot);
            }
        }
        return true;
    }

    public boolean isParked(Car car) {
        return cars.contains(car);
    }
}
