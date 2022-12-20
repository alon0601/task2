package submit;

import main.bridge.Bridge;
import main.data.OrderInfo;
import main.data.ShowInfo;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Real implements Bridge {
    private final int error = -1;
    private Orders orders = new Orders();
    private Shows shows = new Shows();
    @Override
    public void addCity(String city) {
        shows.addCity(city);
    }

    @Override
    public void addHall(String city, String hall, int sits) {
        shows.addHall(city,hall,sits);
    }

    @Override
    public void addAdmin(String city, String user, String pass) {
        shows.addAdmin(user,pass,city);
    }

    @Override
    public int addNewShow(String user, String pass, ShowInfo showInfo) {

        return shows.addShow(user,pass,showInfo);
    }


    @Override
    public void reserveMemberChairs(int showID, int from, int to) {
        this.shows.getShow(showID).setReserveMemberChairs(from,to);
    }

    @Override
    public int newOrder(OrderInfo order) {
        int waiting = 0;
        if(order.name == null || order.name =="")
                return error;
        if(order.phone == null || order.phone == "")
                return error;
        if(order.chairsIds == null || order.chairsIds.length == 0)
             return error;
        ShowInfo show = this.shows.getShow(order.showId);
        if (show == null)
            return error;
        for (int i = 0; i < order.chairsIds.length; i++) {
            if (order.memberId <= 0 && show.reserveMemberChairs[order.chairsIds[i]] == 1) {
                return error;
            }
        }
        long millis = Instant.now().toEpochMilli();
        if(millis>shows.getShow(order.showId).lastOrderDate)
            return error;

        int orderID = orders.newOrder(order,1);
        return orderID;
    }

    @Override
    public List<OrderInfo> getWaitings(int id) {
        return orders.getWaitingOrders(id);
    }
}
