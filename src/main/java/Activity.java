import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Activity {
    private final SimpleStringProperty activities;
    private final SimpleStringProperty category;
    private final SimpleStringProperty price;
    private final SimpleStringProperty accessibility;
    private final SimpleStringProperty participants;
    private final SimpleStringProperty link;

    public Activity(String activities, String category, String price, String accessibility, String participants, String link) {
        this.activities = new SimpleStringProperty(activities);
        this.category = new SimpleStringProperty(category);
        this.price = new SimpleStringProperty(price);
        this.accessibility = new SimpleStringProperty(accessibility);
        this.participants = new SimpleStringProperty(participants);
        this.link = new SimpleStringProperty(link);
    }

    public StringProperty getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities.set(activities);
    }

    public StringProperty getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public StringProperty getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public StringProperty getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(String accessibility) {
        this.accessibility.set(accessibility);
    }

    public StringProperty getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants.set(participants);
    }

    public StringProperty getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link.set(link);
    }

    public static String convertDataToString(double data){
        if(data>=0.8){
            return "HIGH";
        }
        else if(data>=0.5){
            return "MEDIUM";
        }
        else{
            return "LOW";
        }
    }

}