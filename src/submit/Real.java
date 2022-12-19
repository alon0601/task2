package submit;

import main.bridge.Bridge;
import main.data.OrderInfo;
import main.data.ShowInfo;

import java.util.Date;
import java.util.List;

public class Real implements Bridge {
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

    }

    @Override
    public int addNewShow(String user, String pass, ShowInfo showInfo) {
        return 0;
    }

    @Override
    public void reserveMemberChairs(int showID, int from, int to) {

    }

    @Override
    public int newOrder(OrderInfo order) {
        int waiting = 0;
        if(order.name == null || order.name =="")
                throw new RuntimeException("member can not order tickets without name!");
        if(order.phone == null || order.phone == "")
                throw new RuntimeException("member can not order tickets without phone number!");
        if(order.chairsIds == null || order.chairsIds.length == 0)
                throw new RuntimeException("member can not order tickets without chairs!");
        ShowInfo show = this.shows.getShow(order.showId);
        if(show == null)
                throw new RuntimeException("member can not order tickets to unknown show!");
        for(int i = 0;i < order.chairsIds.length;i++){
            if(order.memberId <= 0 && order.chairsIds[i] >= show.reserveMemberChairs.getKey() && order.chairsIds[i] <= show.reserveMemberChairs.getValue()){
                throw new RuntimeException("Only Pais members can order reserved chairs!");
            }
        }
//        if(show.lastOrderDate)
//        int orderID = orders.newOrder(order,waiting);
        int orderID = 0;
        return orderID;
    }

    @Override
    public List<OrderInfo> getWaitings(int id) {
        return orders.getWaitingOrders();
    }
}
