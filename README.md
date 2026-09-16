# Java Battleship (Bataille Navale)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Swing](https://img.shields.io/badge/Java_Swing-316192?style=for-the-badge)

A desktop graphical implementation of the classic Battleship game, developed entirely in Java. This project was built to deepen my expertise in Object-Oriented Programming (OOP) principles and to design a robust application using the strict Model-View-Controller (MVC) architectural pattern.

## 🏗️ System Architecture (MVC)

The codebase is strictly separated into three packages, ensuring high cohesion and low coupling:

*   **`modele` (Data & Business Logic):** The operational core of the game. It handles the `Flotte` (Fleet), grid coordinates (`Position`), and computes the hit/miss/sunken logic. The fleet is built using a strong inheritance tree originating from a base `Bateau` class, branching into 5 distinct ship types:
    *   *Torpedo Boat (Size: 2)*
    *   *Destroyer (Size: 3)*
    *   *Submarine (Size: 3)*
    *   *Battleship (Size: 4)*
    *   *Aircraft Carrier (Size: 5)*
*   **`vue` (Graphical Interface):** Built with Java Swing and AWT. It utilizes `JFrame` and `JPanel` with `BorderLayout` to structure the dual-grid display and information panel. The grid rendering relies on overriding the `paintComponent` method for dynamic UI updates.
*   **`controleur` (Event Handling):** Manages user inputs through `MouseListener` interfaces. It translates grid clicks into coordinates and communicates with the model to register a volley against the opponent's fleet.

## 💡 Technical Highlights

*   **Polymorphism & Inheritance:** Ship behaviors, sizes, and colors are dynamically handled through OOP principles rather than hardcoded logic.
*   **Automated Opponent:** Features an algorithm allowing the computer to instantly retaliate after the player's turn.
*   **Dynamic Visual Feedback:** The UI provides real-time tracking of the battlefield. Missed shots are marked with a black cross, while successful hits are marked in red.

## 🚀 Execution Guide

### Prerequisites
Ensure you have the **Java Development Kit (JDK)** installed on your machine.

### Build & Run
Open a terminal in the root directory of the project and execute the following commands to compile and launch the game:

```bash
# Compile all Java files into a 'bin' directory
javac -d bin Application.java modele/*.java vue/*.java controleur/*.java

# Run the compiled application
java -cp bin Application
```

Developed by Vianney Chaillou | ESAIP Engineering Student 
