public class CarNotParkedException extends Throwable {

    private String errorMessage;

    CarNotParkedException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
