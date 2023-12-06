package Day06;

public class Boat {

    public int speed;

    public int distance;

    public int charge;

    public Boat(int speed, int charge, int distance){
        this.speed = 0;
        this.charge = charge;
        this.distance = distance;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
