package delegates;

public interface SearchDelegate {
    void search(
            String fromDate,
            String fromTime,
            String toDate,
            String toTime,
            String carType,
            String location);
    void reset();
}
