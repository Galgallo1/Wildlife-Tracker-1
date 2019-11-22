package models;

public class Animal extends Wildlife {

    public static final String DATABASE_TYPE = "animal";

    public Animal(String name) {
        this.name = name;
        this.type = DATABASE_TYPE;
    }
}
