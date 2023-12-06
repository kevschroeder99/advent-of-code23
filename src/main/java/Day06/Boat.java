package Day06;

import java.math.BigInteger;

public class Boat {

    public int speed;

    public BigInteger distance;

    public int charge;

    public Boat(int speed, int charge, BigInteger distance){
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

    public BigInteger getDistance() {
        return distance;
    }

    public void setDistance(BigInteger distance) {
        this.distance = distance;
    }
}
