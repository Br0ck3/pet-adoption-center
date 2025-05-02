package com.adoptme.util;

import com.adoptme.model.Pet;
import java.util.Comparator;

/**
 * Comparator for sorting pets by age (birth date).
 */
public class PetAgeComparator implements Comparator<Pet> {
    /**
     * Compares two pets by their birth date.
     *
     * @param p1 The first pet to compare
     * @param p2 The second pet to compare
     * @return A negative integer, zero, or a positive integer as the first pet's
     *         birth date is before, equal to, or after the second pet's birth date
     */
    @Override
    public int compare(Pet p1, Pet p2) {
        return p1.getBirthDate().compareTo(p2.getBirthDate());
    }
} 