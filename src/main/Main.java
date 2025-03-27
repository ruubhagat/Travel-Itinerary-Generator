package main;

import builders.BudgetTravelBuilder;
import builders.LuxuryTravelBuilder;
import builders.AdventureTravelBuilder;
import director.ItineraryDirector;
import interfaces.ItineraryBuilder;
import product.TravelItinerary;
import java.util.Scanner;

public class Main {
    private static final String[] BUDGET_DESTINATIONS = {"Mumbai", "Delhi", "Kolkata"};
    private static final String[] LUXURY_DESTINATIONS = {"Jaipur", "Udaipur", "Goa"};
    private static final String[] ADVENTURE_DESTINATIONS = {"Manali", "Rishikesh", "Leh"};
    private static final String[] BUDGET_ACCOMMODATIONS = {"Shared Dorm", "Single Room", "Guest House"};
    private static final String[] LUXURY_ACCOMMODATIONS = {"5-Star Suite", "5-Star Penthouse", "5-Star Villa"};
    private static final String[] ADVENTURE_ACCOMMODATIONS = {"Tent", "Cabin", "Base Camp"};
    private static final String[] TRANSPORT_OPTIONS = {"Bus", "Flight", "Train"};
    private static final String[][] BUDGET_ACTIVITIES = {
        {"Visit Marine Drive", "Street Food Tour", "Local Market Shopping"},
        {"Red Fort Visit", "Chandni Chowk Tour", "Qutub Minar Sightseeing"},
        {"Howrah Bridge Walk", "Victoria Memorial Tour", "Kumartuli Visit"}
    };
    private static final String[][] LUXURY_ACTIVITIES = {
        {"Royal Palace Dinner", "Elephant Ride", "Amber Fort Tour"},
        {"Lake Palace Dinner", "Boat Ride on Lake Pichola", "Spa Day"},
        {"Beachfront Dining", "Yacht Cruise", "Casino Night"}
    };
    private static final String[][] ADVENTURE_ACTIVITIES = {
        {"Skiing in Solang", "Paragliding", "River Rafting"},
        {"White Water Rafting", "Bungee Jumping", "Rock Climbing"},
        {"Trekking to Pangong Lake", "Mountain Biking", "Camel Safari"}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ItineraryDirector director = new ItineraryDirector();

        System.out.println("Enter your SRN (e.g., PES1UG23CS808): ");
        String srn = scanner.nextLine();

        System.out.println("Choose travel style (1: Budget, 2: Luxury, 3: Adventure): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        ItineraryBuilder builder;
        String[] destinations;
        String[] accommodations;
        String[][] activities;
        switch (choice) {
            case 1:
                builder = new BudgetTravelBuilder();
                destinations = BUDGET_DESTINATIONS;
                accommodations = BUDGET_ACCOMMODATIONS;
                activities = BUDGET_ACTIVITIES;
                break;
            case 2:
                builder = new LuxuryTravelBuilder();
                destinations = LUXURY_DESTINATIONS;
                accommodations = LUXURY_ACCOMMODATIONS;
                activities = LUXURY_ACTIVITIES;
                break;
            case 3:
                builder = new AdventureTravelBuilder();
                destinations = ADVENTURE_DESTINATIONS;
                accommodations = ADVENTURE_ACCOMMODATIONS;
                activities = ADVENTURE_ACTIVITIES;
                break;
            default:
                System.out.println("Invalid choice! Defaulting to Budget.");
                builder = new BudgetTravelBuilder();
                destinations = BUDGET_DESTINATIONS;
                accommodations = BUDGET_ACCOMMODATIONS;
                activities = BUDGET_ACTIVITIES;
        }

        director.setBuilder(builder);

        System.out.println("Enter number of days: ");
        int days = scanner.nextInt();
        scanner.nextLine();

        String[][] preferences = new String[days][4];
        for (int day = 1; day <= days; day++) {
            System.out.println("\n--- Day " + day + " ---");

            System.out.println("Choose a destination:");
            for (int i = 0; i < destinations.length; i++) {
                System.out.println((i + 1) + ": " + destinations[i]);
            }
            int destChoice = scanner.nextInt();
            scanner.nextLine();
            int destIndex = (destChoice > 0 && destChoice <= destinations.length) ? destChoice - 1 : 0;
            preferences[day - 1][0] = destinations[destIndex];

            System.out.println("Choose an accommodation:");
            for (int i = 0; i < accommodations.length; i++) {
                System.out.println((i + 1) + ": " + accommodations[i]);
            }
            int accChoice = scanner.nextInt();
            scanner.nextLine();
            preferences[day - 1][1] = (accChoice > 0 && accChoice <= accommodations.length) ? 
                accommodations[accChoice - 1] : accommodations[0];

            System.out.println("Choose transport:");
            System.out.println("1: Bus");
            System.out.println("2: Flight");
            System.out.println("3: " + (choice == 3 ? "Jeep" : "Train"));
            int transChoice = scanner.nextInt();
            scanner.nextLine();
            preferences[day - 1][2] = (transChoice > 0 && transChoice <= 3) ? 
                (transChoice == 3 && choice == 3 ? "Jeep" : TRANSPORT_OPTIONS[transChoice - 1]) : TRANSPORT_OPTIONS[0];

            System.out.println("Choose an activity for " + destinations[destIndex] + ":");
            for (int i = 0; i < activities[destIndex].length; i++) {
                System.out.println((i + 1) + ": " + activities[destIndex][i]);
            }
            int actChoice = scanner.nextInt();
            scanner.nextLine();
            preferences[day - 1][3] = (actChoice > 0 && actChoice <= activities[destIndex].length) ? 
                activities[destIndex][actChoice - 1] : activities[destIndex][0];
        }

        director.constructTravelPlan(srn, days, preferences);
        TravelItinerary itinerary = builder.build();
        itinerary.displayItinerary();

        System.out.println("\nWould you like to modify a day? (yes/no): ");
        String modify = scanner.nextLine();
        while (modify.equalsIgnoreCase("yes")) {
            System.out.println("Enter day to modify (1-" + days + "): ");
            int day = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter component to modify (destination/accommodation/transport/activity): ");
            String component = scanner.nextLine();
            String value;

            if (component.equalsIgnoreCase("destination")) {
                System.out.println("Choose a new destination:");
                for (int i = 0; i < destinations.length; i++) {
                    System.out.println((i + 1) + ": " + destinations[i]);
                }
                int newDestChoice = scanner.nextInt();
                scanner.nextLine();
                int newDestIndex = (newDestChoice > 0 && newDestChoice <= destinations.length) ? newDestChoice - 1 : 0;
                value = destinations[newDestIndex];
                if (day <= days) {
                    System.out.println("Choose a new activity for " + value + ":");
                    for (int i = 0; i < activities[newDestIndex].length; i++) {
                        System.out.println((i + 1) + ": " + activities[newDestIndex][i]);
                    }
                    int newActChoice = scanner.nextInt();
                    scanner.nextLine();
                    preferences[day - 1][3] = (newActChoice > 0 && newActChoice <= activities[newDestIndex].length) ? 
                        activities[newDestIndex][newActChoice - 1] : activities[newDestIndex][0];
                }
                itinerary.modifyComponent(component, value, day);
                itinerary.modifyComponent("activity", preferences[day - 1][3], day);
            } else if (component.equalsIgnoreCase("accommodation")) {
                System.out.println("Choose a new accommodation:");
                for (int i = 0; i < accommodations.length; i++) {
                    System.out.println((i + 1) + ": " + accommodations[i]);
                }
                int newAccChoice = scanner.nextInt();
                scanner.nextLine();
                value = (newAccChoice > 0 && newAccChoice <= accommodations.length) ? 
                    accommodations[newAccChoice - 1] : accommodations[0];
                itinerary.modifyComponent(component, value, day);
            } else if (component.equalsIgnoreCase("transport")) {
                System.out.println("Choose new transport:");
                System.out.println("1: Bus");
                System.out.println("2: Flight");
                System.out.println("3: " + (choice == 3 ? "Jeep" : "Train"));
                int newTransChoice = scanner.nextInt();
                scanner.nextLine();
                value = (newTransChoice > 0 && newTransChoice <= 3) ? 
                    (newTransChoice == 3 && choice == 3 ? "Jeep" : TRANSPORT_OPTIONS[newTransChoice - 1]) : TRANSPORT_OPTIONS[0];
                itinerary.modifyComponent(component, value, day);
            } else if (component.equalsIgnoreCase("activity")) {
                int destIndex = 0;
                for (int i = 0; i < destinations.length; i++) {
                    if (destinations[i].equals(preferences[day - 1][0])) {
                        destIndex = i;
                        break;
                    }
                }
                System.out.println("Choose a new activity for " + preferences[day - 1][0] + ":");
                for (int i = 0; i < activities[destIndex].length; i++) {
                    System.out.println((i + 1) + ": " + activities[destIndex][i]);
                }
                int newActChoice = scanner.nextInt();
                scanner.nextLine();
                value = (newActChoice > 0 && newActChoice <= activities[destIndex].length) ? 
                    activities[destIndex][newActChoice - 1] : activities[destIndex][0];
                itinerary.modifyComponent(component, value, day);
            } else {
                System.out.println("Invalid component! Please use destination, accommodation, transport, or activity.");
                continue; // Skip to next iteration if component is invalid
            }

            itinerary.displayItinerary();
            System.out.println("\nModify another day? (yes/no): ");
            modify = scanner.nextLine();
        }

        scanner.close();
    }
}