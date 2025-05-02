package com.adoptme.model;

import java.time.LocalDate;

/**
 * Represents a rabbit in the adoption system.
 */
public class Rabbit extends Pet {
    private final String furType;

    /**
     * Constructor for creating a new rabbit.
     *
     * @param name        The rabbit's name
     * @param birthDate   The rabbit's birth date
     * @param breed       The rabbit's breed
     * @param description A description of the rabbit
     * @param furType     The type of fur the rabbit has
     * @throws IllegalArgumentException if furType is null or empty
     */
    public Rabbit(String name, LocalDate birthDate, String breed, String description, String furType) {
        super(name, birthDate, breed, description);
        if (furType == null || furType.trim().isEmpty()) {
            throw new IllegalArgumentException("Rabbit fur type cannot be empty");
        }
        this.furType = furType.trim();
    }

    /**
     * Gets the rabbit's fur type.
     *
     * @return The rabbit's fur type
     */
    public String getFurType() {
        return furType;
    }

    /**
     * Gets the species of the rabbit.
     *
     * @return "Rabbit"
     */
    @Override
    public String getSpecies() {
        return "Rabbit";
    }

    /**
     * Returns a string representation of the rabbit.
     *
     * @return A string containing the rabbit's details
     */
    @Override
    public String toString() {
        return super.toString() + ", Fur Type: " + furType;
    }
} 