# Travel-Itinerary-Generator

A Java-based application implementing the **Builder Design Pattern** to create personalized, multi-day travel itineraries. Users can choose from Budget, Luxury, or Adventure travel styles, with Indian destinations, accommodations, transport options, and destination-specific activities. Costs are estimated in rupees (₹).

## Project Directory Structure

```
TravelItineraryGenerator
├─ README.md
├─ compile_and_run.sh
└─ src
   ├─ builders
   │  ├─ AdventureTravelBuilder.java
   │  ├─ BudgetTravelBuilder.java
   │  └─ LuxuryTravelBuilder.java
   ├─ director
   │  └─ ItineraryDirector.java
   ├─ interfaces
   │  └─ ItineraryBuilder.java
   ├─ main
   │  └─ Main.java
   └─ product
      └─ TravelItinerary.java
```


## Prerequisites
- Java Development Kit (JDK) 8 or higher (`java -version` to check)
- Git (for cloning the repository)
- A terminal (Linux/macOS/Windows with Bash support, e.g., Git Bash)

## Setup and Running
Run the following commands in your terminal to set up and execute the project:

```bash
# Clone the repository and navigate into it
git clone https://github.com/yourusername/TravelItineraryGenerator.git
cd TravelItineraryGenerator

# Verify Java is installed (should return version info)
java -version

# Make the script executable (Linux/macOS only; skip on Windows if using manual commands)
chmod +x compile_and_run.sh

# Compile and run the project
./compile_and_run.sh

# Alternative for Windows or if script fails
# mkdir bin
# javac -d bin src/*/*.java
# java -cp bin main.Main
```
