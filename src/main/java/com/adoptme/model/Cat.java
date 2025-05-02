package com.adoptme.model;

import java.time.LocalDate;

/**
 * Represents a cat in the adoption system.
 */
public class Cat extends Pet {
    private final boolean isIndoor;

    /**
     * Constructor for creating a new cat.
     *
     * @param name        The cat's name
     * @param birthDate   The cat's birth date
     * @param breed       The cat's breed
     * @param description A description of the cat
     * @param isIndoor    Whether the cat is an indoor cat
     */
    public Cat(String name, LocalDate birthDate, String breed, String description, boolean isIndoor) {
        super(name, birthDate, breed, description);
        this.isIndoor = isIndoor;
    }

    /**
     * Checks if the cat is an indoor cat.
     *
     * @return true if the cat is an indoor cat, false otherwise
     */
    public boolean isIndoor() {
        return isIndoor;
    }

    /**
     * Gets the species of the cat.
     *
     * @return "Cat"
     */
    @Override
    public String getSpecies() {
        return "Cat";
    }

    /**
     * Returns a string representation of the cat.
     *
     * @return A string containing the cat's details
     */
    @Override
    public String toString() {
        return super.toString() + ", " + (isIndoor ? "Indoor" : "Outdoor") + " cat";
    }
} 