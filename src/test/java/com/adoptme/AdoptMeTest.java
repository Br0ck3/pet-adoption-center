package com.adoptme;

import com.adoptme.model.Dog;
import com.adoptme.model.Cat;
import com.adoptme.model.Rabbit;
import com.adoptme.model.Pet;
import com.adoptme.model.Shelter;
import com.adoptme.adapter.ExoticAnimal;
import com.adoptme.adapter.ExoticPetAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Adopt Me application.
 * Tests the core functionality of the pet adoption system.
 */
public class AdoptMeTest {
    private Shelter<Pet> shelter;
    private Dog dog;
    private Cat cat;
    private Rabbit rabbit;
    private ExoticAnimal exoticAnimal;
    private ExoticPetAdapter exoticPet;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        shelter = new Shelter<>();
        
        // Create test pets
        dog = new Dog("Buddy", LocalDate.of(2020, 1, 1), "Labrador", "Large", 
            "Friendly and energetic");
        
        cat = new Cat("Whiskers", LocalDate.of(2021, 3, 15), "Siamese", true,
            "Quiet and affectionate");
        
        rabbit = new Rabbit("Fluffy", LocalDate.of(2022, 5, 20), "Lionhead", "Long",
            "Playful and curious");
        
        exoticAnimal = new ExoticAnimal("Spike", LocalDate.of(2021, 7, 10), "Bearded Dragon",
            "Needs heat lamp");
        exoticPet = new ExoticPetAdapter(exoticAnimal);
    }

    /**
     * Tests adding pets to the shelter.
     * I made this test to verify that pets can be added correctly.
     */
    @Test
    public void testAddPets() {
        // Add pets to shelter
        shelter.addPet(dog);
        shelter.addPet(cat);
        shelter.addPet(rabbit);
        shelter.addPet(exoticPet);

        // Check if pets were added
        List<Pet> allPets = shelter.getAllPets();
        assertEquals(4, allPets.size(), "Should have 4 pets in shelter");
        assertTrue(allPets.contains(dog), "Shelter should contain the dog");
        assertTrue(allPets.contains(cat), "Shelter should contain the cat");
        assertTrue(allPets.contains(rabbit), "Shelter should contain the rabbit");
        assertTrue(allPets.contains(exoticPet), "Shelter should contain the exotic pet");
    }

    /**
     * Tests pet adoption functionality.
     * I made this test to verify that pets can be marked as adopted.
     */
    @Test
    public void testAdoptPets() {
        // Add pets to shelter
        shelter.addPet(dog);
        shelter.addPet(cat);

        // Adopt pets
        dog.markAsAdopted();
        cat.markAsAdopted();

        // Check adoption status
        assertTrue(dog.isAdopted(), "Dog should be adopted");
        assertTrue(cat.isAdopted(), "Cat should be adopted");

        // Check shelter lists
        List<Pet> adoptedPets = shelter.getAdoptedPets();
        assertEquals(2, adoptedPets.size(), "Should have 2 adopted pets");
        assertTrue(adoptedPets.contains(dog), "Adopted list should contain the dog");
        assertTrue(adoptedPets.contains(cat), "Adopted list should contain the cat");

        List<Pet> availablePets = shelter.getAvailablePets();
        assertEquals(0, availablePets.size(), "Should have no available pets");
    }

    /**
     * Tests pet return functionality.
     * I made this test to verify that adopted pets can be returned.
     */
    @Test
    public void testReturnPets() {
        // Add and adopt pets
        shelter.addPet(dog);
        shelter.addPet(cat);
        dog.markAsAdopted();
        cat.markAsAdopted();

        // Return pets
        dog.markAsAvailable();
        cat.markAsAvailable();

        // Check adoption status
        assertFalse(dog.isAdopted(), "Dog should be available");
        assertFalse(cat.isAdopted(), "Cat should be available");

        // Check shelter lists
        List<Pet> availablePets = shelter.getAvailablePets();
        assertEquals(2, availablePets.size(), "Should have 2 available pets");
        assertTrue(availablePets.contains(dog), "Available list should contain the dog");
        assertTrue(availablePets.contains(cat), "Available list should contain the cat");

        List<Pet> adoptedPets = shelter.getAdoptedPets();
        assertEquals(0, adoptedPets.size(), "Should have no adopted pets");
    }

    /**
     * Tests sorting functionality.
     * I made this test to verify that pets can be sorted by name.
     */
    @Test
    public void testSortByName() {
        // Add pets in random order
        shelter.addPet(cat);    // Whiskers
        shelter.addPet(dog);    // Buddy
        shelter.addPet(rabbit); // Fluffy

        // Sort by name
        shelter.sortByName();

        // Check order
        List<Pet> sortedPets = shelter.getAllPets();
        assertEquals("Buddy", sortedPets.get(0).getName(), "First pet should be Buddy");
        assertEquals("Fluffy", sortedPets.get(1).getName(), "Second pet should be Fluffy");
        assertEquals("Whiskers", sortedPets.get(2).getName(), "Third pet should be Whiskers");
    }

    /**
     * Tests sorting by age.
     * I made this test to verify that pets can be sorted by birth date.
     */
    @Test
    public void testSortByAge() {
        // Add pets
        shelter.addPet(dog);    // 2020-01-01
        shelter.addPet(cat);    // 2021-03-15
        shelter.addPet(rabbit); // 2022-05-20

        // Sort by age
        shelter.sortByAge();

        // Check order (oldest first)
        List<Pet> sortedPets = shelter.getAllPets();
        assertEquals("Buddy", sortedPets.get(0).getName(), "Oldest pet should be Buddy");
        assertEquals("Whiskers", sortedPets.get(1).getName(), "Middle pet should be Whiskers");
        assertEquals("Fluffy", sortedPets.get(2).getName(), "Youngest pet should be Fluffy");
    }

    /**
     * Tests sorting by species.
     * I made this test to verify that pets can be sorted by species.
     */
    @Test
    public void testSortBySpecies() {
        // Add pets
        shelter.addPet(cat);    // Cat
        shelter.addPet(dog);    // Dog
        shelter.addPet(rabbit); // Rabbit
        shelter.addPet(exoticPet); // Exotic

        // Sort by species
        shelter.sortBySpecies();

        // Check order
        List<Pet> sortedPets = shelter.getAllPets();
        assertEquals("Cat", sortedPets.get(0).getSpecies(), "First pet should be a cat");
        assertEquals("Dog", sortedPets.get(1).getSpecies(), "Second pet should be a dog");
        assertEquals("Exotic", sortedPets.get(2).getSpecies(), "Third pet should be exotic");
        assertEquals("Rabbit", sortedPets.get(3).getSpecies(), "Fourth pet should be a rabbit");
    }

    /**
     * Tests exotic pet functionality.
     * I made this test to verify that exotic pets work correctly.
     */
    @Test
    public void testExoticPet() {
        // Add exotic pet
        shelter.addPet(exoticPet);

        // Check exotic pet properties
        assertEquals("Spike", exoticPet.getName(), "Exotic pet name should be Spike");
        assertEquals("Exotic", exoticPet.getSpecies(), "Exotic pet species should be Exotic");
        assertEquals("Bearded Dragon", exoticPet.getBreed(), "Exotic pet breed should be Bearded Dragon");

        // Check adoption status
        assertFalse(exoticPet.isAdopted(), "Exotic pet should start as available");
        exoticPet.markAsAdopted();
        assertTrue(exoticPet.isAdopted(), "Exotic pet should be adoptable");
    }
} 