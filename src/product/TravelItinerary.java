package product;

import java.util.HashMap;
import java.util.Map;

public class TravelItinerary {
    private String srn;
    private Map<Integer, String> destinations = new HashMap<>();
    private Map<Integer, String> accommodations = new HashMap<>();
    private Map<Integer, String> transports = new HashMap<>();
    private Map<Integer, String> activities = new HashMap<>();
    private Map<Integer, Double> costs = new HashMap<>();
    private String travelStyle;

    public void setSRN(String srn) { this.srn = srn; }
    public void addDestination(String destination, int day) { destinations.put(day, destination); }
    public void selectAccommodation(String accommodation, int day) { accommodations.put(day, accommodation); }
    public void chooseTransport(String transport, int day) { transports.put(day, transport); }
    public void addActivity(String activity, int day) { activities.put(day, activity); }
    public void setTravelStyle(String travelStyle) { this.travelStyle = travelStyle; }

    public void addCost(int day, double cost) {
        costs.put(day, costs.getOrDefault(day, 0.0) + cost);
    }

    public void modifyComponent(String component, String value, int day) {
        switch (component.toLowerCase()) {
            case "destination": destinations.put(day, value); break;
            case "accommodation": accommodations.put(day, value); break;
            case "transport": transports.put(day, value); break;
            case "activity": activities.put(day, value); break;
            default: System.out.println("Invalid component!");
        }
    }

    public void displayItinerary() {
        System.out.println("=== Travel Itinerary ===");
        System.out.println("SRN: " + srn);
        System.out.println("Travel Style: " + travelStyle);
        double totalCost = 0.0;
        for (int day : destinations.keySet()) {
            System.out.println("\nDay " + day + ":");
            System.out.println("  Destination: " + destinations.getOrDefault(day, "Not Set"));
            System.out.println("  Accommodation: " + accommodations.getOrDefault(day, "Not Set"));
            System.out.println("  Transport: " + transports.getOrDefault(day, "Not Set"));
            System.out.println("  Activity: " + activities.getOrDefault(day, "Not Set"));
            double dayCost = costs.getOrDefault(day, 0.0);
            System.out.println("  Estimated Cost: ₹" + String.format("%.2f", dayCost));
            totalCost += dayCost;
        }
        System.out.println("\nTotal Estimated Cost: ₹" + String.format("%.2f", totalCost));
        System.out.println("======================");
    }
}