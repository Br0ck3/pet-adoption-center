package com.adoptme.model;

import java.time.LocalDate;

/**
 * Represents a dog in the pet adoption system.
 * Dogs have specific attributes like size and breed.
 */
public class Dog extends Pet {
    private final String size;

    /**
     * Creates a new dog.
     *
     * @param name The dog's name
     * @param birthDate The dog's date of birth
     * @param breed The dog's breed
     * @param size The dog's size (Small, Medium, Large)
     * @param description A description of the dog
     */
    public Dog(String name, LocalDate birthDate, String breed, String size, String description) {
        super(name, birthDate, "Dog", breed, description);
        this.size = size;
    }

    /**
     * Gets the dog's size.
     *
     * @return The dog's size
     */
    public String getSize() {
        return size;
    }

    @Override
    public String toString() {
        return String.format("%s (Dog - %s, %s)", getName(), getBreed(), size);
    }
} 