package delegates;

public interface ReservationDelegate {
    void reserve(String type, String fromDate, String fromTime, String toDate, String toTime);
}
