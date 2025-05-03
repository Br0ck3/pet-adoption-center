package com.adoptme.model;

import java.time.LocalDate;

/**
 * Abstract class representing a pet in the adoption system.
 * Implements Comparable to allow sorting pets by name.
 * 
 * This is the base class for all pets in our system.
 * It was created as part of our CS3330 project.
 */
public abstract class Pet implements Comparable<Pet> {
    private final String name;
    private final String species;
    private final LocalDate birthDate;
    private final String breed;
    private boolean adopted;
    private final String description;
    // private double weight;
    // private String favoriteFood;
    // private Image photo;

    /**
     * Creates a new pet.
     *
     * @param name The pet's name
     * @param birthDate The pet's date of birth
     * @param species The pet's species
     * @param breed The pet's breed
     * @param description A description of the pet
     * @throws IllegalArgumentException if any required field is null or empty
     */
    protected Pet(String name, LocalDate birthDate, String species, String breed, String description) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Pet name cannot be empty");
        }
        if (species == null || species.trim().isEmpty()) {
            throw new IllegalArgumentException("Species cannot be empty");
        }
        if (birthDate == null) {
            throw new IllegalArgumentException("Birth date cannot be null");
        }
        if (breed == null || breed.trim().isEmpty()) {
            throw new IllegalArgumentException("Breed cannot be empty");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }

        this.name = name.trim();
        this.species = species.trim();
        this.birthDate = birthDate;
        this.breed = breed.trim();
        this.description = description.trim();
        this.adopted = false;
    }

    /**
     * Gets the pet's name.
     *
     * @return The pet's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the pet's species.
     *
     * @return The pet's species
     */
    public String getSpecies() {
        return species;
    }

    /**
     * Gets the pet's date of birth.
     *
     * @return The pet's date of birth
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Gets the pet's breed.
     *
     * @return The pet's breed
     */
    public String getBreed() {
        return breed;
    }

    /**
     * Gets the pet's description.
     *
     * @return The pet's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the pet is adopted.
     *
     * @return true if the pet is adopted, false otherwise
     */
    public boolean isAdopted() {
        return adopted;
    }

    /**
     * Marks the pet as adopted.
     *
     * @throws IllegalStateException if the pet is already adopted
     */
    public void markAsAdopted() {
        if (adopted) {
            throw new IllegalStateException("Pet is already adopted");
        }
        adopted = true;
    }

    /**
     * Marks the pet as available.
     *
     * @throws IllegalStateException if the pet is not adopted
     */
    public void markAsAvailable() {
        if (!adopted) {
            throw new IllegalStateException("Pet is not adopted");
        }
        adopted = false;
    }

    /**
     * Compares this pet with another pet by name.
     *
     * @param other The other pet to compare with
     * @return A negative integer, zero, or a positive integer as this pet's name
     *         is less than, equal to, or greater than the specified pet's name
     */
    @Override
    public int compareTo(Pet other) {
        return name.compareTo(other.name);
    }

    /**
     * Returns a string representation of the pet.
     * I made this method to help with debugging and display.
     *
     * @return A string containing the pet's details
     */
    @Override
    public String toString() {
        return String.format("%s (%s - %s)", name, species, breed);
    }
} 