package com.example.parking;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class APIController {
    private static final ParkingArea PARKING_AREA;
    private static String GENERAL_ERROR = "Error Occurred!";

    static {
        PARKING_AREA = new ParkingArea(2);
    }

    @RequestMapping(value = "/park", method = RequestMethod.GET)
    public ResponseEntity parkCar(@RequestParam(name="LicensePlate") String licensePlate) {
        boolean error = false;
        String responseMessage;
        try {
            Vehicle vehicle = new Vehicle(licensePlate);
            String slotName = PARKING_AREA.park(vehicle);
            responseMessage = "Slot Name : " + slotName;
        } catch (IllegalAccessException e) {
            responseMessage = e.getMessage();
        } catch (Exception e) {
            responseMessage = GENERAL_ERROR;
        }
        HttpStatus status = error ? HttpStatus.GONE : HttpStatus.OK;
        return new ResponseEntity(responseMessage, status);
    }

    @RequestMapping(value = "/unPark", method = RequestMethod.GET)
    public ResponseEntity unParkCar(
                                        @RequestParam(name="slotName") String slotName,
                                        @RequestParam(name="licencePlate") String licencePlate
    ) {
        boolean error = false;
        String responseMessage;
        try {
            Vehicle vehicle = PARKING_AREA.unPark(slotName, licencePlate);
            responseMessage = "Un-parked";
        } catch (IllegalAccessException e) {
            responseMessage = e.getMessage();
        } catch (Exception e) {
            responseMessage = GENERAL_ERROR;
        }
        HttpStatus status = error ? HttpStatus.GONE : HttpStatus.OK;
        return new ResponseEntity(responseMessage, status);
    }

    @RequestMapping(value = "/getAvailability", method = RequestMethod.GET)
    public ResponseEntity getAvailability() {
        return new ResponseEntity(
                                    PARKING_AREA.lotsAvailable() ? "Slot Available" : "Slot not Available",
                                    HttpStatus.OK
        );
    }
}