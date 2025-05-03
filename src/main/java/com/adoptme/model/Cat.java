package com.adoptme.model;

import java.time.LocalDate;

/**
 * Represents a cat in the pet adoption system.
 * Cats have specific attributes like indoor/outdoor preference.
 */
public class Cat extends Pet {
    private final boolean isIndoor;

    /**
     * Creates a new cat.
     *
     * @param name The cat's name
     * @param birthDate The cat's date of birth
     * @param breed The cat's breed
     * @param isIndoor Whether the cat is indoor-only
     * @param description A description of the cat
     */
    public Cat(String name, LocalDate birthDate, String breed, boolean isIndoor, String description) {
        super(name, birthDate, "Cat", breed, description);
        this.isIndoor = isIndoor;
    }

    /**
     * Checks if the cat is indoor-only.
     *
     * @return true if the cat is indoor-only, false otherwise
     */
    public boolean isIndoor() {
        return isIndoor;
    }

    /**
     * Returns a string representation of the cat.
     *
     * @return A string containing the cat's details
     */
    @Override
    public String toString() {
        return String.format("%s (Cat - %s, %s)", getName(), getBreed(), isIndoor ? "Indoor" : "Outdoor");
    }
} 