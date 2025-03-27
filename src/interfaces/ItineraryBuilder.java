package interfaces;

import product.TravelItinerary;

public interface ItineraryBuilder {
    void setSRN(String srn);
    void addDestination(String destination, int day);
    void selectAccommodation(String accommodation, int day);
    void chooseTransport(String transport, int day);
    void addActivity(String activity, int day);
    void addCost(int day, double cost); // Added to handle cost estimation
    TravelItinerary build();
}