import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

public class AttendantTest {


    private ParkingLot parkingLot;

    private Car car;
    private FirstLotSelector firstLotSelector;
    private LeastCarsParkedSelector leastCarsParkedSelector;

    @BeforeEach
    void setUp() {
        parkingLot = Mockito.mock(ParkingLot.class);
        car = Mockito.mock(Car.class);
        firstLotSelector = Mockito.mock(FirstLotSelector.class);
        leastCarsParkedSelector=Mockito.mock(LeastCarsParkedSelector.class);
    }

    @Test
    public void attendantShouldParkCarSuccesfullyWhenParkingLotReturnSuccess() throws CarAlreadyParkedException, ParkingLotFullException {
        Attendant attendant = new Attendant(List.of(parkingLot), firstLotSelector);

        Mockito.when(parkingLot.parkCar(any())).thenReturn(true);
        Mockito.when(firstLotSelector.selectParkingLotAccordingToScheme(any())).thenReturn(parkingLot);
        assertTrue(attendant.parkByAttendant(car));
    }

    @Test
    public void attendantShouldParkCarNotSuccesfullyWhenParkingLotReturnError() throws CarAlreadyParkedException, ParkingLotFullException {
        Attendant attendant = new Attendant(List.of(parkingLot), firstLotSelector);
        Mockito.when(parkingLot.parkCar(any())).thenThrow(ParkingLotFullException.class);
        Mockito.when(firstLotSelector.selectParkingLotAccordingToScheme(any())).thenReturn(parkingLot);
        assertFalse(attendant.parkByAttendant(car));
    }

    @Test
    public void attendantShouldUnParkCarSuccesfullyWhenParkingLotReturnSuccess() throws ParkingLotException, CarNotParkedException {
        Attendant attendant = new Attendant(List.of(parkingLot), firstLotSelector);
        Mockito.when(firstLotSelector.selectParkingLotAccordingToScheme(any())).thenReturn(parkingLot);
        Mockito.when(parkingLot.unParkCar(any())).thenReturn(true);
        assertTrue(attendant.unParkByAttendant(car));
    }

    @Test
    public void attendantShouldUnparkCarNotSuccesfullyWhenParkingLotReturnError() throws ParkingLotException, CarNotParkedException {
        Attendant attendant = new Attendant(List.of(parkingLot), firstLotSelector);
        Mockito.when(firstLotSelector.selectParkingLotAccordingToScheme(any())).thenReturn(parkingLot);
        Mockito.when(parkingLot.unParkCar(any())).thenThrow(ParkingLotException.class);
        assertThrows(ParkingLotException.class, () -> attendant.unParkByAttendant(car));
    }
}
