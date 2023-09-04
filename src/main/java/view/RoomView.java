package view;

import models.Room;
import models.Seat;
import serivce.RoomService;

import java.util.Scanner;

public class RoomView {
    Scanner scanner = new Scanner(System.in);
    private RoomService roomService;
    public RoomView(){
        roomService = new RoomService();
    }

    public static void main(String[] args) {

        RoomView roomView = new RoomView();
        roomView.launcher();
    }
    public void launcher() {
        boolean checkAction = false;
        do {
            System.out.println("Nhập id để hiện phòng muốn chọn: ");
            System.out.println("Nhập 1.phòng 1");
            System.out.println("Nhập 2.phòng 2");
            long id = Long.parseLong(scanner.nextLine());
            Room room = roomService.findRoomByIdRoom(id);
            System.out.println("Tên phòng: " +room.getRoomName());
            System.out.println("số lượng ghế: " + room.getQuantitySeat());

            for(Seat seat: room.getSeats()) {
                System.out.printf("%10s | %30s | %30s \n",
                        seat.getSeatID(), seat.getSeatNumber(),seat.geteSeatStatus());
            }

        } while (checkAction);

    }
}

