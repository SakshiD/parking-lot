import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Observer;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ParkingLotTest {

    private MembersToNotify membersToNotify;

    @BeforeEach
    void setUp() {
        membersToNotify = mock(MembersToNotify.class);
    }

    @Test
    public void shouldReturnTrueIfCarIsParkedSuccessfully() throws ParkingLotException {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(10, Set.of(car), List.of(mock(MembersToNotify.class)));
        assertTrue(parkingLot.parkCar(car, parkingLot));
    }


    @Test
    public void shouldReturnFalseWhen0Capacity() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(0, Set.of(car), List.of(mock(MembersToNotify.class)));
        assertThrows(ParkingLotException.class,() -> parkingLot.parkCar(car, parkingLot));
    }

    @Test
    public void shouldReturnFalseWhen1Capacity() throws ParkingLotException {
        Car car1 = new Car();
        Car car2 = new Car();

        ParkingLot parkingLot = new ParkingLot(1, Set.of(car1), List.of(mock(MembersToNotify.class)));

        assertTrue(parkingLot.parkCar(car1, parkingLot));
        assertThrows(ParkingLotException.class,() -> parkingLot.parkCar(car2, parkingLot));
    }

    @Test
    public void shouldReturnTrueWhenCarIsUnparked() throws ParkingLotException {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(10, Set.of(car), List.of(mock(MembersToNotify.class)));
        assertTrue(parkingLot.unParkCar(car, parkingLot));
    }

    @Test
    public void shouldThrowErrorWhenNoCapacity() throws ParkingLotException {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(-1, Set.of(car), List.of(mock(MembersToNotify.class)));
        assertThrows(ParkingLotException.class,() -> parkingLot.unParkCar(car, parkingLot));
    }


    @Test
    public void shouldReturnTrueInCarIsAlreadyParked() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(10, Set.of(car), List.of(mock(MembersToNotify.class)));
        assertTrue(parkingLot.isParked(car));
    }

    @Test
    public void shouldReturnFalseWhenTheCarIsNotParked() throws ParkingLotException {
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot = new ParkingLot(10, Set.of(car1), List.of(mock(MembersToNotify.class)));
        assertFalse(parkingLot.isParked(car2));
    }

    @Test
    public void shouldNotifyWhenParkingIsFull() throws ParkingLotException {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1, Set.of(car), List.of(membersToNotify));
        parkingLot.parkCar(car, parkingLot);
        verify(membersToNotify, times(1)).notifyFull(Mockito.any());
    }

    @Test
    public void shouldNotifyWhenParkingIsAvailable() throws ParkingLotException {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(0, Set.of(car), List.of(membersToNotify));
        parkingLot.unParkCar(car, parkingLot);
        verify(membersToNotify, times(1)).notifyAvailable(Mockito.any());
    }
}
