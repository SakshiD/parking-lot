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

    public boolean parkCar(Car car) throws CarAlreadyParkedException, ParkingLotFullException {
        if (this.isParked(car)) {
            throw new CarAlreadyParkedException("Car is already parked in this parking lot!");
        }
        int parkingCapacity = this.getCapacity();
        if (parkingCapacity <= 0) {
            throw new ParkingLotFullException("Parking full !");
        }
        parkingCapacity--;
        this.setCapacity(parkingCapacity);
        if(parkingCapacity == 0){
        for(MembersToNotify member : this.membersToNotifyList) {
            member.notifyFull(this);
        }
        }
        return true;
    }

    public boolean unParkCar(Car car) throws ParkingLotException, CarNotParkedException {
        if (!this.isParked(car)) {
            throw new CarNotParkedException("Car is NOT parked in this parking lot!");
        }
        int parkingCapacity = this.getCapacity();
        if (parkingCapacity <= -1) {
            throw new ParkingLotException("Parking empty !");
        }
        parkingCapacity++;
        this.setCapacity(parkingCapacity);
        if(parkingCapacity == 1){
            for(MembersToNotify member : this.membersToNotifyList) {
                member.notifyAvailable(this);
            }
        }
        return true;
    }

    public boolean isParked(Car car) {
        return cars.contains(car);
    }
}
