package com.adoptme.adapter;

import java.time.LocalDate;

/**
 * Simulated third-party class representing an exotic animal.
 * This class has a different interface than our Pet class.
 */
public class ExoticAnimal {
    private final String animalName;
    private final LocalDate dateOfBirth;
    private final String animalType;
    private final String specialRequirements;
    private boolean isReserved;

    public ExoticAnimal(String animalName, LocalDate dateOfBirth, String animalType, String specialRequirements) {
        this.animalName = animalName;
        this.dateOfBirth = dateOfBirth;
        this.animalType = animalType;
        this.specialRequirements = specialRequirements;
        this.isReserved = false;
    }

    public String getAnimalName() {
        return animalName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAnimalType() {
        return animalType;
    }

    public String getSpecialRequirements() {
        return specialRequirements;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void reserve() {
        this.isReserved = true;
    }
} 