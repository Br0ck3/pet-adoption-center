package com.adoptme.view;

import com.adoptme.model.Cat;
import com.adoptme.model.Dog;
import com.adoptme.model.Pet;
import com.adoptme.model.Rabbit;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

/**
 * Dialog for adding new pets.
 */
public class AddPetDialog extends PetDialog {
    private final JComboBox<String> speciesComboBox;
    private final JPanel speciesPanel;
    private final JTextField sizeField;
    private final JCheckBox indoorCheckBox;
    private final JTextField furTypeField;

    /**
     * Creates a new add pet dialog.
     *
     * @param parent The parent frame
     */
    public AddPetDialog(Frame parent) {
        super(parent, "Add Pet");
        
        // Create species combo box
        speciesComboBox = new JComboBox<>(new String[]{"Dog", "Cat", "Rabbit"});
        speciesComboBox.addActionListener(e -> updateSpeciesPanel());

        // Create species-specific panels
        speciesPanel = new JPanel(new CardLayout());

        // Dog panel
        JPanel dogPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dogPanel.add(new JLabel("Size:"));
        sizeField = new JTextField(10);
        dogPanel.add(sizeField);

        // Cat panel
        JPanel catPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        indoorCheckBox = new JCheckBox("Indoor Cat");
        catPanel.add(indoorCheckBox);

        // Rabbit panel
        JPanel rabbitPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rabbitPanel.add(new JLabel("Fur Type:"));
        furTypeField = new JTextField(10);
        rabbitPanel.add(furTypeField);

        speciesPanel.add(dogPanel, "Dog");
        speciesPanel.add(catPanel, "Cat");
        speciesPanel.add(rabbitPanel, "Rabbit");

        // Add components to form
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 4;
        ((JPanel) getContentPane().getComponent(0)).add(new JLabel("Species:"), gbc);
        gbc.gridx = 1;
        ((JPanel) getContentPane().getComponent(0)).add(speciesComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        ((JPanel) getContentPane().getComponent(0)).add(speciesPanel, gbc);

        pack();
    }

    /**
     * Updates the species-specific panel based on the selected species.
     */
    private void updateSpeciesPanel() {
        CardLayout layout = (CardLayout) speciesPanel.getLayout();
        layout.show(speciesPanel, (String) speciesComboBox.getSelectedItem());
    }

    @Override
    protected boolean validateInput() {
        if (!super.validateInput()) {
            return false;
        }

        String species = (String) speciesComboBox.getSelectedItem();
        switch (species) {
            case "Dog":
                if (sizeField.getText().trim().isEmpty()) {
                    showError("Size cannot be empty for dogs");
                    return false;
                }
                break;
            case "Rabbit":
                if (furTypeField.getText().trim().isEmpty()) {
                    showError("Fur type cannot be empty for rabbits");
                    return false;
                }
                break;
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
        String breed = getBreed();
        String description = getDescription();
        String species = (String) speciesComboBox.getSelectedItem();

        return switch (species) {
            case "Dog" -> new Dog(name, birthDate, breed, sizeField.getText().trim(), description);
            case "Cat" -> new Cat(name, birthDate, breed, indoorCheckBox.isSelected(), description);
            case "Rabbit" -> new Rabbit(name, birthDate, breed, furTypeField.getText().trim(), description);
            default -> throw new IllegalStateException("Unexpected species: " + species);
        };
    }
} 