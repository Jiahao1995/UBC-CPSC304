package delegates;

public interface RentDelegate {
    void rentReserve(String type, String phone, String fromDate, String fromTime, String toDate, String toTime, String cardNo);
}
