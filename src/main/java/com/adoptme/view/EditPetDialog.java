package com.adoptme.view;

import com.adoptme.model.Cat;
import com.adoptme.model.Dog;
import com.adoptme.adapter.ExoticAnimal;
import com.adoptme.adapter.ExoticPetAdapter;
import com.adoptme.model.Pet;
import com.adoptme.model.Rabbit;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Dialog for editing pet information.
 */
public class EditPetDialog extends PetDialog {
    private final Pet pet;
    private JComboBox<String> speciesComboBox;
    private JTextField sizeField;
    private JCheckBox indoorCheckBox;
    private JTextField furTypeField;
    private JTextField typeField;

    /**
     * Creates a new edit pet dialog.
     *
     * @param owner The owner frame
     * @param pet   The pet to edit
     */
    public EditPetDialog(Frame owner, Pet pet) {
        super(owner, "Edit Pet");
        this.pet = pet;

        // Set initial values
        nameField.setText(pet.getName());
        birthDateField.setText(pet.getBirthDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        breedField.setText(pet.getBreed());
        descriptionArea.setText(pet.getDescription());

        // Create species combo box
        speciesComboBox = new JComboBox<>(new String[]{"Dog", "Cat", "Rabbit", "Exotic"});
        speciesComboBox.setSelectedItem(pet.getSpecies());
        speciesComboBox.setEnabled(false); // Don't allow changing species

        // Create species-specific panels
        JPanel speciesPanel = new JPanel(new CardLayout());
        JPanel dogPanel = new JPanel(new GridBagLayout());
        JPanel catPanel = new JPanel(new GridBagLayout());
        JPanel rabbitPanel = new JPanel(new GridBagLayout());
        JPanel exoticPanel = new JPanel(new GridBagLayout());

        // Dog panel
        sizeField = new JTextField(20);
        if (pet instanceof Dog) {
            sizeField.setText(((Dog) pet).getSize());
        }
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        dogPanel.add(new JLabel("Size:"), gbc);
        gbc.gridx = 1;
        dogPanel.add(sizeField, gbc);

        // Cat panel
        indoorCheckBox = new JCheckBox("Indoor Cat");
        if (pet instanceof Cat) {
            indoorCheckBox.setSelected(((Cat) pet).isIndoor());
        }
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        catPanel.add(indoorCheckBox, gbc);

        // Rabbit panel
        furTypeField = new JTextField(20);
        if (pet instanceof Rabbit) {
            furTypeField.setText(((Rabbit) pet).getFurType());
        }
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        rabbitPanel.add(new JLabel("Fur Type:"), gbc);
        gbc.gridx = 1;
        rabbitPanel.add(furTypeField, gbc);

        // Exotic panel
        typeField = new JTextField(20);
        if (pet instanceof ExoticPetAdapter) {
            typeField.setText(((ExoticPetAdapter) pet).getExoticAnimal().getAnimalType());
        }
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        exoticPanel.add(new JLabel("Type:"), gbc);
        gbc.gridx = 1;
        exoticPanel.add(typeField, gbc);

        // Add panels to card layout
        speciesPanel.add(dogPanel, "Dog");
        speciesPanel.add(catPanel, "Cat");
        speciesPanel.add(rabbitPanel, "Rabbit");
        speciesPanel.add(exoticPanel, "Exotic");

        // Add species components to form
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(new JLabel("Species:"), gbc);
        gbc.gridx = 1;
        formPanel.add(speciesComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(speciesPanel, gbc);

        // Show appropriate panel
        ((CardLayout) speciesPanel.getLayout()).show(speciesPanel, pet.getSpecies());
    }

    @Override
    protected boolean validateInput() {
        if (!super.validateInput()) {
            return false;
        }

        String species = (String) speciesComboBox.getSelectedItem();
        if (species == null) {
            showError("Please select a species");
            return false;
        }

        switch (species) {
            case "Dog":
                if (sizeField.getText().trim().isEmpty()) {
                    showError("Please enter the dog's size");
                    return false;
                }
                break;
            case "Rabbit":
                if (furTypeField.getText().trim().isEmpty()) {
                    showError("Please enter the rabbit's fur type");
                    return false;
                }
                break;
            case "Exotic":
                if (typeField.getText().trim().isEmpty()) {
                    showError("Please enter the exotic animal's type");
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

        // Create a new instance with updated values
        if (pet instanceof Dog) {
            Dog dog = (Dog) pet;
            return new Dog(name, birthDate, breed, sizeField.getText().trim(), description);
        } else if (pet instanceof Cat) {
            Cat cat = (Cat) pet;
            return new Cat(name, birthDate, breed, indoorCheckBox.isSelected(), description);
        } else if (pet instanceof Rabbit) {
            Rabbit rabbit = (Rabbit) pet;
            return new Rabbit(name, birthDate, breed, furTypeField.getText().trim(), description);
        } else if (pet instanceof ExoticPetAdapter) {
            ExoticPetAdapter adapter = (ExoticPetAdapter) pet;
            ExoticAnimal exoticAnimal = new ExoticAnimal(
                name,
                birthDate,
                adapter.getBreed(),
                description
            );
            return new ExoticPetAdapter(exoticAnimal);
        }

        return null;
    }
} 