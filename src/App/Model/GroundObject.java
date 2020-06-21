package App.Model;

public class GroundObject {
    private int x;
    private int y;
    private String classification;
    private String aircraft_id;

    public GroundObject(int x, int y, String classification) {
        this.x = x;
        this.y = y;
        this.classification = classification;
    }

    public GroundObject(int x, int y, String classification, String aircraft_id) {
        this.x = x;
        this.y = y;
        this.classification = classification;
        this.aircraft_id = aircraft_id;
    }

    public String getAircraft_id() {
        return aircraft_id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getClassification() {
        return classification;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroundObject that = (GroundObject) o;

        if (getX() != that.getX()) return false;
        if (getY() != that.getY()) return false;
        return getClassification().equals(that.getClassification());
    }

    @Override
    public int hashCode() {
        int result = getX();
        result = 31 * result + getY();
        result = 31 * result + getClassification().hashCode();
        return result;
    }
}
