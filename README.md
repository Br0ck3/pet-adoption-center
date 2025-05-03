# Adopt Me - Pet Adoption Management System

A Java desktop application for managing pet adoptions, following the MVC architecture. This project was completed independently by Brock Burchett.

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
│   │           ├── view/        # GUI components
│   │           └── controller/  # Event handling
│   └── resources/               # JSON data files
```

## Features

### Backend Implementation
- Abstract `Pet` class with concrete subclasses (`Dog`, `Cat`, `Rabbit`)
- `ExoticAnimal` adapter pattern implementation
- Generic `Shelter<T extends Pet>` class for pet management
- JSON file handling for pet data
- Sorting capabilities (by name, age, species)
- Error handling for invalid inputs

### GUI Implementation
- **Main Window**
  - Menu bar with File (Load/Save) and View options
  - Tabbed pane for different pet categories
  - Status bar showing total pets and adopted pets count

- **Pet List View**
  - Table displaying pet information
  - Sortable columns (Name, Species, Age, Breed, Status)
  - Filter options (Available/Adopted/All)
  - Search functionality

- **Pet Details View**
  - Form for adding/editing pet information
  - Species-specific fields (e.g., size for dogs, indoor/outdoor for cats)
  - Validation feedback
  - Adopt/Return buttons

- **Exotic Pets View**
  - Special handling for exotic pets
  - Additional requirements display
  - Reservation status

## Technical Details

### Architecture
- **Model-View-Controller (MVC) Pattern**
  - Model: `Pet`, `Shelter`, and related classes
  - View: Swing-based GUI components
  - Controller: Event handling and business logic

### Design Patterns
- **Adapter Pattern**: `ExoticPetAdapter` for integrating exotic animals
- **Factory Pattern**: Pet creation through dialogs
- **Observer Pattern**: View updates based on model changes

### Testing
- Comprehensive JUnit tests for core functionality
- Test coverage for pet management operations
- Validation of exotic pet integration

## Getting Started

1. **Prerequisites**
   - Java 17 or higher
   - Maven 3.8 or higher

2. **Building the Project**
   ```bash
   mvn clean install
   ```

3. **Running the Application**
   ```bash
   mvn exec:java -Dexec.mainClass="com.adoptme.view.MainWindow"
   ```

## Author
- Brock Burchett - Sole developer and maintainer
