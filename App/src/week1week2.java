import java.util.*;

public class week1week2 {
    enum Status { EMPTY, OCCUPIED, DELETED }

    static class Spot {
        String licensePlate;
        Status status = Status.EMPTY;
    }

    private Spot[] lot = new Spot[500];

    public week1week2() {
        for (int i = 0; i < lot.length; i++) lot[i] = new Spot();
    }

    public String parkVehicle(String licensePlate) {
        int spot = Math.abs(licensePlate.hashCode()) % lot.length;
        int probes = 0;

        while (lot[spot].status == Status.OCCUPIED && probes < lot.length) {
            spot = (spot + 1) % lot.length;
            probes++;
        }

        if (lot[spot].status != Status.OCCUPIED) {
            lot[spot].licensePlate = licensePlate;
            lot[spot].status = Status.OCCUPIED;
            return "Assigned spot #" + spot + " (" + probes + " probes)";
        }
        return "Lot Full";
    }

    public static void main(String[] args) {
        week1week2 lot = new week1week2();
        System.out.println(lot.parkVehicle("ABC123"));
        System.out.println(lot.parkVehicle("XYZ789"));
    }
}