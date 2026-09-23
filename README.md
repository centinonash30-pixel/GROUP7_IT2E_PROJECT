# Desktop Assets Distribution System (DADS)

A Java console-based prototype for tracking and distributing company desktop
equipment (PCs, monitors, keyboards, etc.) to employees.

## Business Domain
IT Asset Management — Corporate/Enterprise IT Operations

## Target User
IT Asset Custodian: the staff member responsible for issuing and reclaiming
company equipment.

## Problem It Solves
Small and mid-size offices often track equipment loans using spreadsheets or
memory, which leads to lost assets, no accountability trail, and confusion
over what's currently available to issue. DADS gives the custodian a simple
tool to register assets, distribute them, log returns, and check stock at a
glance.

## Features
1. Register New Asset
2. View All Assets
3. Distribute Asset to Employee
4. Process Asset Return
5. Search Asset by ID
6. View Distribution Summary Report
7. Exit

## Project Structure
```
DADS/
├── src/
│   ├── Main.java          # Entry point, main menu loop
│   ├── Asset.java         # Asset model (instance variables per asset)
│   └── AssetManager.java  # Core business logic (register/distribute/return/report)
└── README.md
```

## How to Run

### Requirements
- Java JDK 8 or higher installed (`javac` and `java` on your PATH)

### Steps
1. Clone this repository:
   ```
   git clone https://github.com/centinonash30-pixel/Desktapassetsystem.git
   cd Desktapassetsystem
   ```
2. Compile the source files:
   ```
   cd src
   javac *.java
   ```
3. Run the program:
   ```
   java Main
   ```
4. Follow the on-screen menu (enter a number 1–7) to register assets,
   distribute them to employees, process returns, search, or view the
   summary report.

## Sample Run
```
=========================================
  DESKTOP ASSETS DISTRIBUTION SYSTEM
=========================================
>> Asset registered successfully with ID: 1001
>> Asset registered successfully with ID: 1002
>> Asset registered successfully with ID: 1003

----------- MAIN MENU -----------
1. Register New Asset
2. View All Assets
3. Distribute Asset to Employee
4. Process Asset Return
5. Search Asset by ID
6. View Distribution Summary Report
7. Exit
----------------------------------
Enter choice: 3
Enter asset ID to distribute: 1001
Enter employee name: Juan Dela Cruz
>> SUCCESS: Asset #1001 (Dell OptiPlex 7010) distributed to Juan Dela Cruz.
```

## Core Logic Used
- **Variables & Data Types:** `int`, `String`, `boolean`, `ArrayList<Asset>`
- **Operators:** relational (`==`, `>=`), logical (`&&`, `||`), arithmetic (`*`, `/`, `++`)
- **Control Structures:** `switch-case` (main menu), `if-else` ladders (validation),
  ternary expression (`statusTag()` in Asset.java)
- **Scope:** Instance variables declared in `Asset` and `AssetManager`
  (persist per object); local variables declared inside method bodies
  (e.g., `choice`, `name`, loop variable `a`) exist only during that call.

## Team
_(Add team member names and roles here)_
