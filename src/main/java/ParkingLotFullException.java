public class ParkingLotFullException extends Throwable {

    private String errorMessage;

    ParkingLotFullException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
