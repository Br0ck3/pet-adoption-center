package com.adoptme.adapter;

import com.adoptme.model.Pet;
import java.time.LocalDate;

/**
 * Adapter class that wraps an ExoticAnimal to conform to the Pet interface.
 * Implements the Adapter pattern to allow exotic animals to be treated as regular pets.
 */
public class ExoticPetAdapter extends Pet {
    private final ExoticAnimal exoticAnimal;

    /**
     * Creates a new adapter for an exotic animal.
     *
     * @param exoticAnimal The exotic animal to adapt
     */
    public ExoticPetAdapter(ExoticAnimal exoticAnimal) {
        super(
            exoticAnimal.getAnimalName(),
            exoticAnimal.getDateOfBirth(),
            exoticAnimal.getAnimalType(),
            exoticAnimal.getSpecialRequirements()
        );
        this.exoticAnimal = exoticAnimal;
    }

    /**
     * Gets the species of the exotic pet.
     *
     * @return The exotic animal's type
     */
    @Override
    public String getSpecies() {
        return "Exotic " + exoticAnimal.getAnimalType();
    }

    /**
     * Marks the exotic pet as adopted.
     * This actually calls the reserve() method on the underlying ExoticAnimal.
     *
     * @throws IllegalStateException if the exotic animal is already reserved
     */
    @Override
    public void markAsAdopted() {
        if (exoticAnimal.isReserved()) {
            throw new IllegalStateException("Exotic animal is already reserved");
        }
        exoticAnimal.reserve();
        super.markAsAdopted();
    }

    /**
     * Returns a string representation of the exotic pet.
     *
     * @return A string containing the exotic pet's details
     */
    @Override
    public String toString() {
        return super.toString() + " (Exotic)";
    }
} 