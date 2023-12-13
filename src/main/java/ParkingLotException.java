public class ParkingLotException extends Throwable {

    private String errorMessage;

    ParkingLotException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
