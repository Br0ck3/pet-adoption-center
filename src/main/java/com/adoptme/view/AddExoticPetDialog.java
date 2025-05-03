package com.adoptme.view;

import com.adoptme.adapter.ExoticAnimal;
import com.adoptme.adapter.ExoticPetAdapter;
import com.adoptme.model.Pet;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

/**
 * Dialog for adding new exotic pets.
 */
public class AddExoticPetDialog extends PetDialog {
    private final JTextField typeField;

    /**
     * Creates a new add exotic pet dialog.
     *
     * @param parent The parent frame
     */
    public AddExoticPetDialog(Frame parent) {
        super(parent, "Add Exotic Pet");

        // Add type field
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 4;
        ((JPanel) getContentPane().getComponent(0)).add(new JLabel("Animal Type:"), gbc);
        gbc.gridx = 1;
        typeField = new JTextField(20);
        ((JPanel) getContentPane().getComponent(0)).add(typeField, gbc);

        // Update labels
        ((JLabel) ((JPanel) getContentPane().getComponent(0)).getComponent(4)).setText("Special Requirements:");
        ((JLabel) ((JPanel) getContentPane().getComponent(0)).getComponent(6)).setText("Breed/Type:");

        pack();
    }

    @Override
    protected boolean validateInput() {
        if (!super.validateInput()) {
            return false;
        }

        if (typeField.getText().trim().isEmpty()) {
            showError("Animal type cannot be empty");
            return false;
        }

        return true;
    }

    @Override
    public Pet showDialog() {
        setVisible(true);
        if (!isConfirmed()) {
            return null;
        }

        String name = getName();
        LocalDate birthDate = getBirthDate();
        String type = typeField.getText().trim();
        String requirements = getDescription();

        ExoticAnimal exoticAnimal = new ExoticAnimal(name, birthDate, type, requirements);
        return new ExoticPetAdapter(exoticAnimal);
    }
} 