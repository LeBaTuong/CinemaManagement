package serivce;

import models.Movie;
import models.Room;
import models.Seat;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SeatService {
    private final String fileSeat = "./data/seats.txt";
    Seat seat;

    public SeatService() {
        seat = new Seat();
        //seat = new Seat();
    }

    public List<Seat> getAllSeat() {
        List<Seat> seats = FileUtils.readData(fileSeat, Seat.class);
        return seats;
    }
    public List<Seat> getAllSeatItemByIDSeat(long id) {
        List<Seat> seats = getAllSeat();
//        List<Seat> result = new ArrayList<>();
//        for(Seat seat1: seats){
//            if(seat1.getSeatID()==id){
//                result.add(seat1);
//            }
//        }
//        List<OrderItem> result = new ArrayList<>();
//        for(OrderItem item: orderItems){
//            if(item.getIdOrder()==idorder){
//                result.add(item);
//            }
//        }
        List <Seat> result= seats.stream().filter(seat -> seat.getRoomID()==id).collect(Collectors.toList());
        return result;
    }
    public Seat findSeatById(long id) {
        List<Seat> seats=getAllSeat();
//        for(Order o: orders){
//            if(o.getId()==id){
//                return o;
//            }
//        }
        Seat result= seats.stream().filter(seat-> seat.getSeatID()==id).findFirst().orElseThrow(null);
        return result;
    }

}
