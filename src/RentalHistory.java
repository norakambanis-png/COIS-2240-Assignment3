import java.util.List;
import java.util.ArrayList;

public class RentalHistory {
    private List<RentalRecord> rentalRecords = new ArrayList<>();

    public void addRecord(RentalRecord record) {
        rentalRecords.add(record);
    }

    public List<RentalRecord> getRentalHistory() {
        return rentalRecords;
    }

}