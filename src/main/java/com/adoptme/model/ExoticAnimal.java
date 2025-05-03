package com.adoptme.model;

import java.time.LocalDate;

/**
 * Represents an exotic animal in the pet adoption system.
 */
public class ExoticAnimal {
    private String name;
    private String type;
    private LocalDate birthDate;
    private String specialRequirements;
    private boolean adopted;

    /**
     * Creates a new exotic animal.
     *
     * @param name The name of the animal
     * @param type The type of animal (e.g., "Reptile", "Bird", etc.)
     * @param birthDate The birth date of the animal
     * @param specialRequirements Special care requirements for the animal
     */
    public ExoticAnimal(String name, String type, LocalDate birthDate, String specialRequirements) {
        this.name = name;
        this.type = type;
        this.birthDate = birthDate;
        this.specialRequirements = specialRequirements;
        this.adopted = false;
    }

    /**
     * Gets the name of the animal.
     *
     * @return The animal's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the animal.
     *
     * @param name The new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the type of animal.
     *
     * @return The animal type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of animal.
     *
     * @param type The new type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the birth date of the animal.
     *
     * @return The birth date
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the birth date of the animal.
     *
     * @param birthDate The new birth date
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Gets the special care requirements.
     *
     * @return The special requirements
     */
    public String getSpecialRequirements() {
        return specialRequirements;
    }

    /**
     * Sets the special care requirements.
     *
     * @param specialRequirements The new requirements
     */
    public void setSpecialRequirements(String specialRequirements) {
        this.specialRequirements = specialRequirements;
    }

    /**
     * Checks if the animal is adopted.
     *
     * @return true if adopted, false otherwise
     */
    public boolean isAdopted() {
        return adopted;
    }

    /**
     * Marks the animal as adopted.
     */
    public void markAsAdopted() {
        this.adopted = true;
    }

    /**
     * Marks the animal as available for adoption.
     */
    public void markAsAvailable() {
        this.adopted = false;
    }
} 