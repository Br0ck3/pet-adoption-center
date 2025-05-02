package com.adoptme.model;

import java.time.LocalDate;

/**
 * Abstract class representing a pet in the adoption system.
 * Implements Comparable to allow sorting pets by name.
 */
public abstract class Pet implements Comparable<Pet> {
    private String name;
    private LocalDate birthDate;
    private String breed;
    private boolean adopted;
    private String description;

    /**
     * Constructor for creating a new pet.
     *
     * @param name        The pet's name
     * @param birthDate   The pet's birth date
     * @param breed       The pet's breed
     * @param description A description of the pet
     * @throws IllegalArgumentException if any required field is null or empty
     */
    protected Pet(String name, LocalDate birthDate, String breed, String description) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Pet name cannot be empty");
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
     * Gets the pet's birth date.
     *
     * @return The pet's birth date
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
     * Checks if the pet has been adopted.
     *
     * @return true if the pet has been adopted, false otherwise
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
        this.adopted = true;
    }

    /**
     * Gets the species of the pet.
     *
     * @return The pet's species
     */
    public abstract String getSpecies();

    /**
     * Compares this pet with another pet by name.
     *
     * @param other The other pet to compare with
     * @return A negative integer, zero, or a positive integer as this pet's name
     *         is less than, equal to, or greater than the specified pet's name
     */
    @Override
    public int compareTo(Pet other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    /**
     * Returns a string representation of the pet.
     *
     * @return A string containing the pet's details
     */
    @Override
    public String toString() {
        return String.format("%s (%s) - %s, Born: %s, %s",
                name, getSpecies(), breed, birthDate,
                adopted ? "Adopted" : "Available");
    }
} 