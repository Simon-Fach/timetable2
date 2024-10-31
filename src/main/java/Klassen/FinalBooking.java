package Klassen;

public class FinalBooking extends Booking {
    String id;
    String name;

    public FinalBooking(String id,String name , Booking booking) {
        this.id = id;
        this.name = name;
        this.room = booking.getRoom();
        this.weekday = booking.getWeekday();
        this.startTime = booking.getStartTime();
        this.endTime = booking.getEndTime();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
