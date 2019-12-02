package graeme.hosford.rob.morgan.assignment02restconsumer.entities;

public class UserBid {
    private long bidId;
    private double bidAmount;

    public UserBid() {
    }

    public UserBid(long bidId, double bidAmount) {
        this.bidId = bidId;
        this.bidAmount = bidAmount;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public long getBidId() {
        return bidId;
    }

    public void setBidId(long bidId) {
        this.bidId = bidId;
    }
}
