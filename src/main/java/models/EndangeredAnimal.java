package models;


public class EndangeredAnimal extends Wildlife {

    public static final String HEALTHY ="healthy";
    public static final String OKAY ="okay";
    public static final String ILL ="ill";
    public static final String NEWBORN ="newborn";
    public static final String YOUNG ="young";
    public static final String ADULT ="adult";

    public static final String DATABASE_TYPE = "endangered animal";

    public EndangeredAnimal(String name) {
        this.name = name;
        this.type = DATABASE_TYPE;
    }
}
