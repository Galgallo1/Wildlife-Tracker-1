package models;

public class Sightings {
    private int id;
    private String location;
    private String rangerName;
    private int wildlifeId;

    public Sightings(String location, String rangerName, int wildlifeId) {
        this.location = location;
        this.rangerName = rangerName;
        this.wildlifeId = wildlifeId;
    }

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getRangerName() {
        return rangerName;
    }

    public int getWildlifeId() {
        return wildlifeId;
    }
}
