public class CarAlreadyParkedException extends Throwable {

    private String errorMessage;

    CarAlreadyParkedException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
