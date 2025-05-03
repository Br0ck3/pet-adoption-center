package com.adoptme.view;

import com.adoptme.model.Pet;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Base dialog for pet-related dialogs.
 */
public abstract class PetDialog extends JDialog {
    protected JTextField nameField;
    protected JTextField birthDateField;
    protected JTextField breedField;
    protected JTextArea descriptionArea;
    protected JButton okButton;
    protected JButton cancelButton;
    protected boolean confirmed;
    protected JPanel formPanel;

    /**
     * Creates a new pet dialog.
     *
     * @param owner The owner frame
     * @param title The dialog title
     */
    public PetDialog(Frame owner, String title) {
        super(owner, title, true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(owner);

        // Create form panel
        formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Name field
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Name:"), gbc);
        nameField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        // Birth date field
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Birth Date (YYYY-MM-DD):"), gbc);
        birthDateField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(birthDateField, gbc);

        // Breed field
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Breed:"), gbc);
        breedField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(breedField, gbc);

        // Description area
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Description:"), gbc);
        descriptionArea = new JTextArea(5, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        gbc.gridx = 1;
        formPanel.add(scrollPane, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        okButton.addActionListener(e -> {
            if (validateInput()) {
                confirmed = true;
                dispose();
            }
        });

        cancelButton.addActionListener(e -> {
            confirmed = false;
            dispose();
        });

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Add components to dialog
        setLayout(new BorderLayout());
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Validates the input fields.
     *
     * @return true if all fields are valid, false otherwise
     */
    protected boolean validateInput() {
        if (nameField.getText().trim().isEmpty()) {
            showError("Please enter a name");
            return false;
        }

        try {
            LocalDate.parse(birthDateField.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            showError("Please enter a valid birth date (YYYY-MM-DD)");
            return false;
        }

        if (breedField.getText().trim().isEmpty()) {
            showError("Please enter a breed");
            return false;
        }

        if (descriptionArea.getText().trim().isEmpty()) {
            showError("Please enter a description");
            return false;
        }

        return true;
    }

    /**
     * Shows an error message.
     *
     * @param message The error message
     */
    protected void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Shows the dialog and returns the created pet.
     *
     * @return The created pet, or null if the dialog was cancelled
     */
    public abstract Pet showDialog();

    /**
     * Gets the name entered in the dialog.
     *
     * @return The name
     */
    public String getName() {
        return nameField.getText().trim();
    }

    /**
     * Gets the birth date entered in the dialog.
     *
     * @return The birth date
     */
    protected LocalDate getBirthDate() {
        return LocalDate.parse(birthDateField.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * Gets the breed entered in the dialog.
     *
     * @return The breed
     */
    protected String getBreed() {
        return breedField.getText().trim();
    }

    /**
     * Gets the description entered in the dialog.
     *
     * @return The description
     */
    protected String getDescription() {
        return descriptionArea.getText().trim();
    }

    /**
     * Checks if the dialog was confirmed.
     *
     * @return true if confirmed, false if cancelled
     */
    protected boolean isConfirmed() {
        return confirmed;
    }
}