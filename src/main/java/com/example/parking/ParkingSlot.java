package com.example.parking;

public class ParkingSlot {
    private String slotName;
    private boolean isFilled;

    private Vehicle vehicle;
    private long parkedTime;

    private static final String LOT_IS_ALREADY_FILLED = "Lot is already filled";
    private static final String WRONG_NUMBER_PLATE = "Wrong Number Plate";

    ParkingSlot(String lot) {
        this.slotName = lot;
        this.isFilled = false;
    }

    public String getName() {
        return this.slotName;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public void park (Vehicle vehicle) throws IllegalAccessException {
        if (isFilled) {
            throw new IllegalAccessException(LOT_IS_ALREADY_FILLED);
        }
        this.isFilled = true;
        this.vehicle = vehicle;
        this.parkedTime = System.currentTimeMillis();
    }

    public Vehicle unPark(String licencePlate) throws IllegalAccessException {
        if (!isFilled
                && this.vehicle.getLicencePlate().equals(licencePlate)) {
            throw new IllegalAccessException(WRONG_NUMBER_PLATE);
        }

        isFilled = false;
        return vehicle;
    }

    @Override
    public boolean equals(Object o) {
        ParkingSlot lot2 = (ParkingSlot)o;
        return  this.slotName.equals(lot2.getName());
    }
}
