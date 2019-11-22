package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animal_instantiateCorrectly_true(){
        Animal testAnimal = setUpAnimal();
        assertTrue(testAnimal instanceof Animal);
    }

    //helper class
    public Animal setUpAnimal(){
        return new Animal("lion");
    }
}