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

    @Test
    public void animal_instantiateWithName_String(){
        Animal testAnimal = setUpAnimal();
        assertEquals("lion", testAnimal.getName());
    }
    @Test
    public void animal_instantiateWithCorrectType_String(){
        Animal testAnimal = setUpAnimal();
        assertEquals("animal", testAnimal.getType());
    }
    @Test
    public void animal_instantiateWithCorrectId_int(){

    }

    //helper class
    public Animal setUpAnimal(){
        return new Animal("lion");
    }
}