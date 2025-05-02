package com.adoptme.model;

import java.time.LocalDate;

/**
 * Represents a dog in the adoption system.
 */
public class Dog extends Pet {
    private final String size;

    /**
     * Constructor for creating a new dog.
     *
     * @param name        The dog's name
     * @param birthDate   The dog's birth date
     * @param breed       The dog's breed
     * @param description A description of the dog
     * @param size        The dog's size (Small, Medium, Large)
     * @throws IllegalArgumentException if size is null or empty
     */
    public Dog(String name, LocalDate birthDate, String breed, String description, String size) {
        super(name, birthDate, breed, description);
        if (size == null || size.trim().isEmpty()) {
            throw new IllegalArgumentException("Dog size cannot be empty");
        }
        this.size = size.trim();
    }

    /**
     * Gets the dog's size.
     *
     * @return The dog's size
     */
    public String getSize() {
        return size;
    }

    /**
     * Gets the species of the dog.
     *
     * @return "Dog"
     */
    @Override
    public String getSpecies() {
        return "Dog";
    }

    /**
     * Returns a string representation of the dog.
     *
     * @return A string containing the dog's details
     */
    @Override
    public String toString() {
        return super.toString() + ", Size: " + size;
    }
} 