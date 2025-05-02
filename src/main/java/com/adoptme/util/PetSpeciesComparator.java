package com.adoptme.util;

import com.adoptme.model.Pet;
import java.util.Comparator;

/**
 * Comparator for sorting pets by species.
 */
public class PetSpeciesComparator implements Comparator<Pet> {
    /**
     * Compares two pets by their species.
     *
     * @param p1 The first pet to compare
     * @param p2 The second pet to compare
     * @return A negative integer, zero, or a positive integer as the first pet's
     *         species is less than, equal to, or greater than the second pet's species
     */
    @Override
    public int compare(Pet p1, Pet p2) {
        return p1.getSpecies().compareToIgnoreCase(p2.getSpecies());
    }
} 