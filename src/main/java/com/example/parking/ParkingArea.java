package com.example.parking;

import java.util.LinkedList;
import java.util.List;

public class ParkingArea {
    private final List<ParkingSlot> slots;

    private static final String INVALID_LOT_ID = "Invalid lot id";
    private static final String PARKING_LOT_NOT_AVAILABLE = "Parking lots are not available";

    public ParkingArea (int noOfSlots) {
        slots = new LinkedList<>();
        for (int i = 0; i < noOfSlots; i ++) {
            slots.add(new ParkingSlot(i +""));
        }
    }

    private ParkingSlot getAvailableLot() {
        for (ParkingSlot slot : slots) {
            if (!slot.isFilled()) {
                return slot;
            }
        }
        return null;
    }


    public boolean lotsAvailable() {
        return getAvailableLot() != null;
    }

    public String park(Vehicle vehicle) throws IllegalAccessException {
        ParkingSlot lot = getAvailableLot();
        if (lot == null) {
            throw new IllegalAccessException(PARKING_LOT_NOT_AVAILABLE);
        }

        lot.park(vehicle);
        return lot.getName();
    }

    public Vehicle unPark(String lotName, String licencePlate) throws IllegalAccessException {
        Vehicle vehicle = null;
        for (ParkingSlot lot : slots) {
            if (lot.getName().equals(lotName)) {
                vehicle = lot.unPark(licencePlate);
                break;
            }
        }

        if (vehicle == null) {
            throw new IllegalAccessException(INVALID_LOT_ID);
        }

        return vehicle;
    }
}
