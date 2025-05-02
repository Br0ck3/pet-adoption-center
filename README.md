# Adopt Me - Pet Adoption Management System

A Java desktop application for managing pet adoptions, following the MVC architecture.

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── adoptme/
│   │           ├── adapter/     # Adapter pattern implementation
│   │           ├── model/       # Core business logic and data
│   │           ├── util/        # Utility classes
│   │           ├── view/        # GUI components (TO BE IMPLEMENTED)
│   │           └── controller/  # Event handling (TO BE IMPLEMENTED)
│   └── resources/               # JSON data files
```

## Backend Implementation (Completed)

The backend has been implemented with the following features:
- Abstract `Pet` class with concrete subclasses (`Dog`, `Cat`, `Rabbit`)
- `ExoticAnimal` adapter pattern implementation
- Generic `Shelter<T extends Pet>` class for pet management
- JSON file handling for pet data
- Sorting capabilities (by name, age, species)
- Error handling for invalid inputs

## GUI Implementation (To Be Done)

### Required Views

1. **Main Window**
   - Menu bar with File (Load/Save) and View options
   - Tabbed pane for different pet categories
   - Status bar showing total pets and adopted pets count

2. **Pet List View**
   - Table displaying pet information
   - Sortable columns (Name, Species, Age, Breed, Status)
   - Filter options (Available/Adopted/All)
   - Search functionality

3. **Pet Details View**
   - Form for adding/editing pet information
   - Species-specific fields (e.g., size for dogs, indoor/outdoor for cats)
   - Validation feedback
   - Adopt/Return buttons

4. **Exotic Pets View**
   - Special handling for exotic pets
   - Additional requirements display
   - Reservation status

### Required Controllers

1. **Main Controller**
   - Handle menu actions (Load/Save)
   - Manage view switching
   - Update status bar

2. **Pet List Controller**
   - Handle table sorting
   - Implement filtering
   - Manage search functionality
   - Handle row selection

3. **Pet Details Controller**
   - Form validation
   - Add/Edit pet functionality
   - Adoption process
   - Error handling and user feedback

4. **Exotic Pets Controller**
   - Special handling for exotic pet reservations
   - Requirements validation
   - Status updates

### Implementation Guidelines

1. **View Layer**
   - Use Swing components
   - Follow Java look and feel
   - Implement responsive layouts
   - Add tooltips and help text
   - Use appropriate icons and colors

2. **Controller Layer**
   - Implement action listeners
   - Handle user input validation
   - Manage model updates
   - Provide user feedback
   - Handle exceptions gracefully

3. **Model Integration**
   - Use existing `Shelter` class
   - Implement data binding
   - Handle JSON file operations
   - Manage pet state changes

### Example Code Structure

```java
// View Example
public class PetListView extends JPanel {
    private JTable petTable;
    private JButton addButton;
    private JButton editButton;
    private JButton adoptButton;
    // ... other components
}

// Controller Example
public class PetListController {
    private PetListView view;
    private Shelter<Pet> model;
    
    public void initialize() {
        // Set up listeners
        // Bind model to view
        // Initialize data
    }
}
```

## Testing Requirements

1. **GUI Testing**
   - Test all user interactions
   - Verify form validation
   - Check error handling
   - Test file operations

2. **Integration Testing**
   - Test model-view-controller interaction
   - Verify data persistence
   - Test exotic pet handling

## Additional Features (Optional)

1. **Reporting**
   - Generate adoption reports
   - Export data to different formats
   - Print pet information

2. **Advanced Search**
   - Multiple criteria search
   - Save search preferences
   - Export search results

3. **User Preferences**
   - Save window positions
   - Remember sort preferences
   - Customize display options

## Getting Started

1. Clone the repository
2. Import as Maven project
3. Implement the view and controller packages
4. Run the application

## Dependencies

- Java 17
- Gson 2.13.1
- Swing (built-in)

## Notes

- Follow Java naming conventions
- Add appropriate comments
- Handle all edge cases
- Provide user-friendly error messages
- Implement proper logging
