import java.util.ArrayList;
import java.util.List;

class ParkingLot {
    private int numLevels;
    private int numSlotsPerLevel;
    private boolean[][] slots;

    public ParkingLot(int numLevels, int numSlotsPerLevel) {
        this.numLevels = numLevels;
        this.numSlotsPerLevel = numSlotsPerLevel;
        this.slots = new boolean[numLevels][numSlotsPerLevel];
        for (int i = 0; i < numLevels; i++) {
            for (int j = 0; j < numSlotsPerLevel; j++) {
                slots[i][j] = true;
            }
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (int level = 0; level < numLevels; level++) {
            for (int slot = 0; slot < numSlotsPerLevel; slot++) {
                if (slots[level][slot]) {
                    slots[level][slot] = false;
                    vehicle.setLevel(level);
                    vehicle.setSlot(slot);
                    return true;
                }
            }
        }
        return false;
    }

    public void removeVehicle(Vehicle vehicle) {
        int level = vehicle.getLevel();
        int slot = vehicle.getSlot();
        slots[level][slot] = true;
    }

    public List<int[]> getEmptySlots() {
        List<int[]> emptySlots = new ArrayList<>();
        for (int level = 0; level < numLevels; level++) {
            for (int slot = 0; slot < numSlotsPerLevel; slot++) {
                if (slots[level][slot]) {
                    emptySlots.add(new int[]{level, slot});
                }
            }
        }
        return emptySlots;
    }
}

class Vehicle {
    private String vehicleType;
    private int level;
    private int slot;

    public Vehicle(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
}

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(3, 5);

        // Park a car
        Vehicle car = new Vehicle("car");
        if (parkingLot.parkVehicle(car)) {
            System.out.println("Car parked successfully");
        } else {
            System.out.println("No empty slots available");
        }

        // Park a motorcycle
        Vehicle motorcycle = new Vehicle("motorcycle");
        if (parkingLot.parkVehicle(motorcycle)) {
            System.out.println("Motorcycle parked successfully");
        } else {
            System.out.println("No empty slots available");
        }

        // Remove the car
        parkingLot.removeVehicle(car);

        // Get the empty slots
        List<int[]> emptySlots = parkingLot.getEmptySlots();
        System.out.println("Empty slots:");
        for (int[] slot : emptySlots) {
            System.out.println("Level: " + slot[0] + ", Slot: " + slot[1]);
        }
    }
}
