package com.adoptme.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.adoptme.util.LocalDateTimeAdapter;
import com.adoptme.util.PetAgeComparator;
import com.adoptme.util.PetSpeciesComparator;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Generic class representing a shelter that can store and manage pets.
 *
 * @param <T> The type of pet this shelter can store (must extend Pet)
 */
public class Shelter<T extends Pet> {
    private final List<T> pets;
    private final Gson gson;

    /**
     * Creates a new empty shelter.
     */
    public Shelter() {
        this.pets = new ArrayList<>();
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .setPrettyPrinting()
                .create();
    }

    /**
     * Adds a pet to the shelter.
     *
     * @param pet The pet to add
     * @throws IllegalArgumentException if the pet is null or already exists in the shelter
     */
    public void addPet(T pet) {
        if (pet == null) {
            throw new IllegalArgumentException("Pet cannot be null");
        }
        if (pets.contains(pet)) {
            throw new IllegalArgumentException("Pet already exists in the shelter");
        }
        pets.add(pet);
    }

    /**
     * Removes a pet from the shelter.
     *
     * @param pet The pet to remove
     * @return true if the pet was removed, false if it wasn't found
     */
    public boolean removePet(T pet) {
        return pets.remove(pet);
    }

    /**
     * Gets all pets in the shelter.
     *
     * @return A list of all pets
     */
    public List<T> getAllPets() {
        return new ArrayList<>(pets);
    }

    /**
     * Gets all available (not adopted) pets in the shelter.
     *
     * @return A list of all available pets
     */
    public List<T> getAvailablePets() {
        return pets.stream()
                .filter(pet -> !pet.isAdopted())
                .collect(Collectors.toList());
    }

    /**
     * Gets all adopted pets in the shelter.
     *
     * @return A list of all adopted pets
     */
    public List<T> getAdoptedPets() {
        return pets.stream()
                .filter(Pet::isAdopted)
                .collect(Collectors.toList());
    }

    /**
     * Sorts pets by name (natural ordering).
     */
    public void sortByName() {
        Collections.sort(pets);
    }

    /**
     * Sorts pets by age.
     */
    public void sortByAge() {
        pets.sort(new PetAgeComparator());
    }

    /**
     * Sorts pets by species.
     */
    public void sortBySpecies() {
        pets.sort(new PetSpeciesComparator());
    }

    /**
     * Loads pets from a JSON file.
     *
     * @param filename The name of the JSON file to load from
     * @throws IOException if there's an error reading the file
     */
    public void loadPetsFromJson(String filename) throws IOException {
        try (FileReader reader = new FileReader(filename)) {
            List<T> loadedPets = gson.fromJson(reader, new TypeToken<List<T>>(){}.getType());
            if (loadedPets != null) {
                pets.clear();
                pets.addAll(loadedPets);
            }
        }
    }

    /**
     * Saves pets to a JSON file with a timestamp in the filename.
     *
     * @throws IOException if there's an error writing the file
     */
    public void savePetsToJson() throws IOException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filename = timestamp + "_pets.json";
        
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(pets, writer);
        }
    }
} 