package ships;

public class CruiseShip extends Ship {

    private int maxNumberOfPassengers;

    public CruiseShip(String name, String year) {
        super(name, year);
    }

    public CruiseShip(String name, String year, int maxNumberOfPassengers) {
        super(name, year);
        this.maxNumberOfPassengers = maxNumberOfPassengers;
    }

    public int getMaxNumberOfPassengers() {
        return maxNumberOfPassengers;
    }

    public void setMaxNumberOfPassengers(int maxNumberOfPassengers) {
        this.maxNumberOfPassengers = maxNumberOfPassengers;
    }

    @Override
    public String toString() {
        return super.getName() + "{" +
                "maxNumberOfPassengers=" + maxNumberOfPassengers +
                '}';
    }

}
