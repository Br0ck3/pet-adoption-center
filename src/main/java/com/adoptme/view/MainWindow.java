package com.adoptme.view;

import com.adoptme.controller.MainController;
import com.adoptme.model.Pet;
import com.adoptme.model.Shelter;

import javax.swing.*;
import java.awt.*;

/**
 * The main window of the Adopt Me application.
 */
public class MainWindow extends JFrame {
    private final MainController controller;
    private final JTabbedPane tabbedPane;
    private final JLabel statusLabel;
    private final PetListView petListView;
    private final ExoticPetsView exoticPetsView;

    /**
     * Creates a new main window.
     *
     * @param shelter The shelter model
     */
    public MainWindow(Shelter<Pet> shelter) {
        this.controller = new MainController(this, shelter);
        this.petListView = new PetListView(shelter);
        this.exoticPetsView = new ExoticPetsView(shelter);

        // Set up the window
        setTitle("Adopt Me - Pet Adoption Center");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Create menu bar
        JMenuBar menuBar = createMenuBar();
        setJMenuBar(menuBar);

        // Create main content
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("All Pets", petListView);
        tabbedPane.addTab("Dogs", new PetListView(shelter, "Dog"));
        tabbedPane.addTab("Cats", new PetListView(shelter, "Cat"));
        tabbedPane.addTab("Rabbits", new PetListView(shelter, "Rabbit"));
        tabbedPane.addTab("Exotic Pets", exoticPetsView);

        // Create status bar
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusLabel = new JLabel();
        statusBar.add(statusLabel);

        // Add components to window
        add(tabbedPane, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);

        // Show welcome message
        JOptionPane.showMessageDialog(
            this,
            "Welcome to Adopt Me!\n\n" +
            "This application helps you manage pets in an adoption center.\n" +
            "You can add, edit, and adopt pets, as well as view statistics.",
            "Welcome",
            JOptionPane.INFORMATION_MESSAGE
        );

        // Update status
        updateStatus();
    }

    /**
     * Creates the application menu bar.
     *
     * @return The created menu bar
     */
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadItem = new JMenuItem("Load Pets");
        JMenuItem saveItem = new JMenuItem("Save Pets");
        JMenuItem exitItem = new JMenuItem("Exit");

        loadItem.addActionListener(e -> controller.loadPets());
        saveItem.addActionListener(e -> controller.savePets());
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // View menu
        JMenu viewMenu = new JMenu("View");
        JMenuItem sortByNameItem = new JMenuItem("Sort by Name");
        JMenuItem sortByAgeItem = new JMenuItem("Sort by Age");
        JMenuItem sortBySpeciesItem = new JMenuItem("Sort by Species");

        sortByNameItem.addActionListener(e -> controller.sortByName());
        sortByAgeItem.addActionListener(e -> controller.sortByAge());
        sortBySpeciesItem.addActionListener(e -> controller.sortBySpecies());

        viewMenu.add(sortByNameItem);
        viewMenu.add(sortByAgeItem);
        viewMenu.add(sortBySpeciesItem);

        menuBar.add(fileMenu);
        menuBar.add(viewMenu);

        return menuBar;
    }

    /**
     * Updates the status bar with current pet counts.
     */
    public void updateStatus() {
        int totalPets = controller.getTotalPets();
        int adoptedPets = controller.getAdoptedPets();
        statusLabel.setText(String.format("Total Pets: %d | Adopted: %d | Available: %d",
                totalPets, adoptedPets, totalPets - adoptedPets));
    }

    /**
     * Shows an error message dialog.
     *
     * @param message The error message to display
     */
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Shows an information message dialog.
     *
     * @param message The message to display
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Gets the pet list view.
     *
     * @return The pet list view
     */
    public PetListView getPetListView() {
        return petListView;
    }

    /**
     * Gets the exotic pets view.
     *
     * @return The exotic pets view
     */
    public ExoticPetsView getExoticPetsView() {
        return exoticPetsView;
    }

    /**
     * Main method to start the application.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Shelter<Pet> shelter = new Shelter<>();
            MainWindow window = new MainWindow(shelter);
            window.setVisible(true);
        });
    }
} 