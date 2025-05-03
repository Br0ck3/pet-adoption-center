package com.adoptme.view;

import com.adoptme.adapter.ExoticAnimal;
import com.adoptme.adapter.ExoticPetAdapter;
import com.adoptme.model.Pet;
import com.adoptme.model.Shelter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * View for displaying and managing exotic pets.
 */
public class ExoticPetsView extends JPanel {
    private final Shelter<Pet> model;
    private final JTable petTable;
    private final DefaultTableModel tableModel;
    private final JComboBox<String> filterComboBox;
    private final JTextField searchField;

    /**
     * Creates a new exotic pets view.
     *
     * @param model The shelter model
     */
    public ExoticPetsView(Shelter<Pet> model) {
        this.model = model;
        setLayout(new BorderLayout());

        // Create table model
        String[] columnNames = {"Name", "Type", "Special Requirements", "Age", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Create table
        petTable = new JTable(tableModel);
        petTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(petTable);
        add(scrollPane, BorderLayout.CENTER);

        // Create toolbar
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        // Add filter combo box
        filterComboBox = new JComboBox<>(new String[]{"All", "Available", "Adopted"});
        filterComboBox.addActionListener(e -> refresh());
        toolBar.add(new JLabel("Filter: "));
        toolBar.add(filterComboBox);

        // Add search field
        searchField = new JTextField(20);
        searchField.addActionListener(e -> refresh());
        toolBar.add(new JLabel("Search: "));
        toolBar.add(searchField);

        // Add buttons
        JButton addButton = new JButton("Add Exotic Pet");
        JButton editButton = new JButton("Edit Pet");
        JButton adoptButton = new JButton("Adopt/Return");

        addButton.addActionListener(e -> showAddExoticPetDialog());
        editButton.addActionListener(e -> showEditPetDialog());
        adoptButton.addActionListener(e -> toggleAdoptionStatus());

        toolBar.addSeparator();
        toolBar.add(addButton);
        toolBar.add(editButton);
        toolBar.add(adoptButton);

        add(toolBar, BorderLayout.NORTH);

        // Initial refresh
        refresh();
    }

    /**
     * Refreshes the table with current exotic pet data.
     */
    public void refresh() {
        tableModel.setRowCount(0);
        String filter = (String) filterComboBox.getSelectedItem();
        String searchText = searchField.getText().toLowerCase();

        List<Pet> pets = switch (filter) {
            case "Available" -> model.getAvailablePets();
            case "Adopted" -> model.getAdoptedPets();
            default -> model.getAllPets();
        };

        for (Pet pet : pets) {
            if (pet instanceof ExoticPetAdapter) {
                ExoticAnimal exoticPet = ((ExoticPetAdapter) pet).getExoticAnimal();
                if (searchText.isEmpty() || 
                    exoticPet.getAnimalName().toLowerCase().contains(searchText) ||
                    exoticPet.getAnimalType().toLowerCase().contains(searchText) ||
                    exoticPet.getSpecialRequirements().toLowerCase().contains(searchText)) {
                    
                    String age = exoticPet.getDateOfBirth().format(DateTimeFormatter.ISO_LOCAL_DATE);
                    String status = exoticPet.isReserved() ? "Adopted" : "Available";
                    
                    tableModel.addRow(new Object[]{
                        exoticPet.getAnimalName(),
                        exoticPet.getAnimalType(),
                        exoticPet.getSpecialRequirements(),
                        age,
                        status
                    });
                }
            }
        }
    }

    /**
     * Shows the add exotic pet dialog.
     */
    private void showAddExoticPetDialog() {
        AddExoticPetDialog dialog = new AddExoticPetDialog((Frame) SwingUtilities.getWindowAncestor(this));
        Pet pet = dialog.showDialog();
        if (pet != null) {
            try {
                model.addPet(pet);
                refresh();
                JOptionPane.showMessageDialog(this, "Exotic pet added successfully!");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Shows the edit pet dialog.
     */
    private void showEditPetDialog() {
        int selectedRow = petTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a pet to edit");
            return;
        }

        String petName = (String) tableModel.getValueAt(selectedRow, 0);
        Pet pet = model.getAllPets().stream()
                .filter(p -> p instanceof ExoticPetAdapter)
                .filter(p -> ((ExoticPetAdapter) p).getExoticAnimal().getAnimalName().equals(petName))
                .findFirst()
                .orElse(null);

        if (pet != null) {
            EditPetDialog dialog = new EditPetDialog((Frame) SwingUtilities.getWindowAncestor(this), pet);
            Pet updatedPet = dialog.showDialog();
            if (updatedPet != null) {
                refresh();
                JOptionPane.showMessageDialog(this, "Pet updated successfully!");
            }
        }
    }

    /**
     * Toggles the adoption status of the selected pet.
     */
    private void toggleAdoptionStatus() {
        int selectedRow = petTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a pet");
            return;
        }

        String petName = (String) tableModel.getValueAt(selectedRow, 0);
        Pet pet = model.getAllPets().stream()
                .filter(p -> p instanceof ExoticPetAdapter)
                .filter(p -> ((ExoticPetAdapter) p).getExoticAnimal().getAnimalName().equals(petName))
                .findFirst()
                .orElse(null);

        if (pet != null) {
            try {
                if (pet.isAdopted()) {
                    pet.markAsAvailable();
                    JOptionPane.showMessageDialog(this, pet.getName() + " has been returned!");
                } else {
                    pet.markAsAdopted();
                    JOptionPane.showMessageDialog(this, pet.getName() + " has been adopted!");
                }
                refresh();
            } catch (IllegalStateException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
} 