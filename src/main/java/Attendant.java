import java.util.List;

public class Attendant {
    private List<ParkingLot> availableParkingLots;

    public Attendant(List<ParkingLot> availableParkingLots) {
        this.availableParkingLots = availableParkingLots;
    }

    public boolean parkByAttendant(Car car) throws CarAlreadyParkedException, ParkingLotFullException {
        boolean carParked = false;
        long carIsAlreadyParked = availableParkingLots.stream().filter(a -> a.isParked(car)).count();
        if (carIsAlreadyParked > 0) {
            throw new ParkingLotFullException("Car is already parked !");
        }
        for (ParkingLot availableParkingLot : availableParkingLots) {
            try {
                carParked = availableParkingLot.parkCar(car);
            } catch (ParkingLotFullException e) {
                System.out.println("Parking lot " + availableParkingLot + " is full !");
            }
        }
        return carParked;
    }

    public boolean unParkByAttendant(Car car) throws ParkingLotException {
        boolean carUnparked = false;
        if (availableParkingLots == null) {
            throw new ParkingLotException("No parking lot available !");
        }
        for (ParkingLot availableParkingLot : availableParkingLots) {
            try {
                carUnparked = availableParkingLot.unParkCar(car);
            } catch (CarNotParkedException e) {
                System.out.println("Car is NOT parked in parking lot  " + availableParkingLot);
            }
        }
        return carUnparked;
    }
}
