package director;

import interfaces.ItineraryBuilder;

public class ItineraryDirector {
    private ItineraryBuilder builder;

    public void setBuilder(ItineraryBuilder builder) {
        this.builder = builder;
    }

    public void constructTravelPlan(String srn, int days, String[][] preferences) {
        builder.setSRN(srn);
        for (int day = 1; day <= days; day++) {
            builder.addDestination(preferences[day - 1][0], day);
            builder.selectAccommodation(preferences[day - 1][1], day);
            builder.chooseTransport(preferences[day - 1][2], day);
            builder.addActivity(preferences[day - 1][3], day);
        }
    }
}