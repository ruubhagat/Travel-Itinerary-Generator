package builders;

import interfaces.ItineraryBuilder;
import product.TravelItinerary;

public class BudgetTravelBuilder implements ItineraryBuilder {
    private TravelItinerary itinerary;

    public BudgetTravelBuilder() {
        this.itinerary = new TravelItinerary();
        this.itinerary.setTravelStyle("Budget");
    }

    @Override
    public void setSRN(String srn) { itinerary.setSRN(srn); }

    @Override
    public void addDestination(String destination, int day) { 
        itinerary.addDestination(destination, day); 
        addCost(day, 500.0);
    }

    @Override
    public void selectAccommodation(String accommodation, int day) { 
        itinerary.selectAccommodation("Hostel: " + accommodation, day); 
        addCost(day, 1000.0);
    }

    @Override
    public void chooseTransport(String transport, int day) { 
        itinerary.chooseTransport("Public Transport: " + transport, day); 
        addCost(day, transport.equals("Flight") ? 3000.0 : 500.0);
    }

    @Override
    public void addActivity(String activity, int day) { 
        itinerary.addActivity("Free/Low-Cost: " + activity, day); 
        addCost(day, 200.0);
    }

    @Override
    public void addCost(int day, double cost) {
        itinerary.addCost(day, cost);
    }

    @Override
    public TravelItinerary build() { return itinerary; }
}