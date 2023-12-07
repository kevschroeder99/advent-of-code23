package Day07;

public class PlayHand {

    private int bid;

    private String hand;

    private int rank;

    public PlayHand(String hand, int bid, int rank) {
        this.hand = hand;
        this.bid = bid;
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getHand() {
        return hand;
    }

    public void setHand(String hand) {
        this.hand = hand;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }
}
