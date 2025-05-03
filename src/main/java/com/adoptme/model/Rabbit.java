package com.adoptme.model;

import java.time.LocalDate;

/**
 * Represents a rabbit in the pet adoption system.
 * Rabbits have specific attributes like fur type.
 */
public class Rabbit extends Pet {
    private final String furType;

    /**
     * Creates a new rabbit.
     *
     * @param name The rabbit's name
     * @param birthDate The rabbit's date of birth
     * @param breed The rabbit's breed
     * @param furType The rabbit's fur type
     * @param description A description of the rabbit
     */
    public Rabbit(String name, LocalDate birthDate, String breed, String furType, String description) {
        super(name, birthDate, "Rabbit", breed, description);
        this.furType = furType;
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
     * Returns a string representation of the rabbit.
     *
     * @return A string containing the rabbit's details
     */
    @Override
    public String toString() {
        return String.format("%s (Rabbit - %s, %s)", getName(), getBreed(), furType);
    }
} 