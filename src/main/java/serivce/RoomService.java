package serivce;

import models.Room;
import models.Schedule;
import models.Seat;
import utils.FileUtils;

import java.util.List;
import java.util.stream.Collectors;

public class RoomService {
    private final String fileRoom = "./data/rooms.txt";
    //RoomService roomService;
    SeatService seatService;
    ScheduleService scheduleService;


    public RoomService() {
        //roomService = new RoomService();
        seatService = new SeatService();
        scheduleService = new ScheduleService();
    }

    public List<Room> getAllRoom() {
        List<Room> rooms = FileUtils.readData(fileRoom, Room.class);
        for (Room room : rooms) {
            List<Seat> seats = seatService.getAllSeatItemByIDSeat(room.getRoomID());
            room.setSeats(seats);
        }
        return rooms;
    }

    public List<Room> getAllRoomSchedule() {
        List<Room> rooms = FileUtils.readData(fileRoom, Room.class);
        for (Room room : rooms) {
            List<Schedule> schedules = scheduleService.getAllScheduleByRoomID(room.getRoomID());
            room.setSchedules(schedules);
        }
        return rooms;
    }

    public Room findRoomByIDRoomSchedule(long id) {
        List<Room> rooms = getAllRoomSchedule();
        return rooms.stream().filter(room -> room.getRoomID() == id).findFirst().orElseThrow(null);
    }

//    public Room getAllSeatItemByIDRoom(long id) {
//        List<Room> rooms = getAllRoom();
//        List<OrderItem> result = new ArrayList<>();
//        for(OrderItem item: orderItems){
//            if(item.getIdOrder()==idorder){
//                result.add(item);
//            }
//        }
//        return rooms.stream().filter(seat -> seat.getRoomID() == id).findFirst().orElseThrow(null);
//
//    }

    public Room findRoomByIdRoom(long id) {
        List<Room> rooms = getAllRoom();
        return rooms.stream().filter(room -> room.getRoomID() == id).findFirst().orElseThrow(null);
    }


}
