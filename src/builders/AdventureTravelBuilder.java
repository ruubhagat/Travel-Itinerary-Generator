package builders;

import interfaces.ItineraryBuilder;
import product.TravelItinerary;

public class AdventureTravelBuilder implements ItineraryBuilder {
    private TravelItinerary itinerary;

    public AdventureTravelBuilder() {
        this.itinerary = new TravelItinerary();
        this.itinerary.setTravelStyle("Adventure");
    }

    @Override
    public void setSRN(String srn) { itinerary.setSRN(srn); }

    @Override
    public void addDestination(String destination, int day) { 
        itinerary.addDestination(destination, day); 
        addCost(day, 1500.0);
    }

    @Override
    public void selectAccommodation(String accommodation, int day) { 
        itinerary.selectAccommodation("Camping: " + accommodation, day); 
        addCost(day, 2000.0);
    }

    @Override
    public void chooseTransport(String transport, int day) { 
        itinerary.chooseTransport("Off-Road: " + transport, day); 
        addCost(day, transport.equals("Flight") ? 10000.0 : 3000.0);
    }

    @Override
    public void addActivity(String activity, int day) { 
        itinerary.addActivity("Thrilling: " + activity, day); 
        addCost(day, 3000.0);
    }

    @Override
    public void addCost(int day, double cost) {
        itinerary.addCost(day, cost);
    }

    @Override
    public TravelItinerary build() { return itinerary; }
}