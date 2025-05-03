package com.adoptme.controller;

import com.adoptme.adapter.ExoticAnimal;
import com.adoptme.adapter.ExoticPetAdapter;
import com.adoptme.model.Pet;
import com.adoptme.model.Shelter;
import com.adoptme.util.PetAgeComparator;
import com.adoptme.util.PetSpeciesComparator;
import com.adoptme.view.MainWindow;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Controller for the main application window.
 */
public class MainController {
    private final MainWindow view;
    private final Shelter<Pet> model;
    private final Gson gson;

    /**
     * Creates a new main controller.
     *
     * @param view  The main window view
     * @param model The shelter model
     */
    public MainController(MainWindow view, Shelter<Pet> model) {
        this.view = view;
        this.model = model;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    /**
     * Loads pets from both regular and exotic pets JSON files.
     */
    public void loadPets() {
        try {
            // Load regular pets
            List<Pet> regularPets = gson.fromJson(
                    new FileReader("pets.json"),
                    new TypeToken<List<Pet>>(){}.getType()
            );
            if (regularPets != null) {
                regularPets.forEach(model::addPet);
            }

            // Load exotic pets
            List<ExoticAnimal> exoticAnimals = gson.fromJson(
                    new FileReader("exotic_pets.json"),
                    new TypeToken<List<ExoticAnimal>>(){}.getType()
            );
            if (exoticAnimals != null) {
                exoticAnimals.forEach(animal -> model.addPet(new ExoticPetAdapter(animal)));
            }

            view.getPetListView().refresh();
            view.getExoticPetsView().refresh();
            view.updateStatus();
            view.showMessage("Pets loaded successfully!");
        } catch (IOException e) {
            view.showError("Error loading pets: " + e.getMessage());
        }
    }

    /**
     * Saves pets to timestamped JSON files.
     */
    public void savePets() {
        String timestamp = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        
        try {
            // Save regular pets
            List<Pet> regularPets = model.getAllPets().stream()
                    .filter(pet -> !(pet instanceof ExoticPetAdapter))
                    .toList();
            try (FileWriter writer = new FileWriter("pets_" + timestamp + ".json")) {
                gson.toJson(regularPets, writer);
            }

            // Save exotic pets
            List<ExoticAnimal> exoticAnimals = model.getAllPets().stream()
                    .filter(pet -> pet instanceof ExoticPetAdapter)
                    .map(pet -> ((ExoticPetAdapter) pet).getExoticAnimal())
                    .toList();
            try (FileWriter writer = new FileWriter("exotic_pets_" + timestamp + ".json")) {
                gson.toJson(exoticAnimals, writer);
            }

            view.showMessage("Pets saved successfully!");
        } catch (IOException e) {
            view.showError("Error saving pets: " + e.getMessage());
        }
    }

    /**
     * Sorts pets by name.
     */
    public void sortByName() {
        model.sortByName();
        view.getPetListView().refresh();
        view.getExoticPetsView().refresh();
    }

    /**
     * Sorts pets by age.
     */
    public void sortByAge() {
        model.sortByAge();
        view.getPetListView().refresh();
        view.getExoticPetsView().refresh();
    }

    /**
     * Sorts pets by species.
     */
    public void sortBySpecies() {
        model.sortBySpecies();
        view.getPetListView().refresh();
        view.getExoticPetsView().refresh();
    }

    /**
     * Gets the total number of pets.
     *
     * @return The total number of pets
     */
    public int getTotalPets() {
        return model.getAllPets().size();
    }

    /**
     * Gets the number of adopted pets.
     *
     * @return The number of adopted pets
     */
    public int getAdoptedPets() {
        return model.getAdoptedPets().size();
    }
} 