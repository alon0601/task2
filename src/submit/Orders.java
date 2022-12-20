package submit;

import java.util.*;
import main.data.OrderInfo;


public class Orders {
    private Map<Integer,List<OrderInfo>> orders;
    private Map<Integer,List<OrderInfo>> waitingOrders;
    private int orderID;

    public Orders(){
        this.orders = new HashMap<>();
        this.waitingOrders = new HashMap<>();
        this.orderID = 1;
    }

    public int getOrderID() {
        return this.orderID;
    }

    public List<OrderInfo> getOrders(int memberID) {
        return orders.get(memberID);
    }

    public List<OrderInfo> getWaitingOrders(int showID) {
        return waitingOrders.get(showID);
    }

    public int newOrder(OrderInfo order, int waiting) {
        if(waiting == 1){
            List<OrderInfo> newI = this.waitingOrders.get(order.showId);
            if (newI == null) {
                newI = new ArrayList<>();
            }
            newI.add(order);
            this.waitingOrders.put(order.showId,newI);
        }
        else{
            List<OrderInfo> newI = this.orders.get(order.showId);
            if(newI != null){
                this.orders.put(order.memberId,newI);
            }
            else {
                this.orders.put(order.memberId, new LinkedList<>());
            }
        }
        int orderId = this.orderID;
        orderID++;
        return  orderId;
    }
}
