package builders;

import interfaces.ItineraryBuilder;
import product.TravelItinerary;

public class LuxuryTravelBuilder implements ItineraryBuilder {
    private TravelItinerary itinerary;

    public LuxuryTravelBuilder() {
        this.itinerary = new TravelItinerary();
        this.itinerary.setTravelStyle("Luxury");
    }

    @Override
    public void setSRN(String srn) { itinerary.setSRN(srn); }

    @Override
    public void addDestination(String destination, int day) { 
        itinerary.addDestination(destination, day); 
        addCost(day, 2000.0);
    }

    @Override
    public void selectAccommodation(String accommodation, int day) { 
        itinerary.selectAccommodation(accommodation, day); 
        addCost(day, 10000.0);
    }

    @Override
    public void chooseTransport(String transport, int day) { 
        itinerary.chooseTransport("Private/First-Class: " + transport, day); 
        addCost(day, transport.equals("Flight") ? 15000.0 : 5000.0);
    }

    @Override
    public void addActivity(String activity, int day) { 
        itinerary.addActivity("Exclusive: " + activity, day); 
        addCost(day, 5000.0);
    }

    @Override
    public void addCost(int day, double cost) {
        itinerary.addCost(day, cost);
    }

    @Override
    public TravelItinerary build() { return itinerary; }
}