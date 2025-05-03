package com.adoptme;

import com.adoptme.model.Pet;
import com.adoptme.model.Shelter;
import com.adoptme.view.MainWindow;

import javax.swing.*;
import java.io.IOException;

/**
 * Main application class for the Adopt Me pet adoption system.
 */
public class AdoptMeApp {
    /**
     * Main method that starts the application.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Error setting look and feel: " + e.getMessage());
        }

        // Create and show the application window
        SwingUtilities.invokeLater(() -> {
            Shelter<Pet> shelter = new Shelter<>();
            try {
                // Try to load initial data
                shelter.loadPetsFromJson("src/main/resources/pets.json");
                shelter.loadPetsFromJson("src/main/resources/exotic_pets.json");
            } catch (IOException e) {
                System.err.println("Error loading initial data: " + e.getMessage());
            }

            MainWindow mainWindow = new MainWindow(shelter);
            mainWindow.setVisible(true);
        });
    }
} 