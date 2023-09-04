package view;

import models.Movie;
import models.Room;
import models.Schedule;
import models.Seat;
import serivce.MovieService;
import serivce.RoomService;
import serivce.ScheduleService;
import utils.DateUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class ScheduleView {
    Scanner scanner = new Scanner(System.in);
    ScheduleService scheduleService;
    MovieService movieService;
    RoomService roomService;
    public ScheduleView(){
        scheduleService = new ScheduleService();
        movieService = new MovieService();
        roomService = new RoomService();
    }
    public void laucher() {
        boolean checkAction = false;
        do {
            System.out.println("Menu quản lý user: ");
            System.out.println("Nhập 1.xem danh sách chiếu  ");
            System.out.println("Nhập 2. Sửa danh sách");
            System.out.println("Nhập 3. thêm danh sách");
            System.out.println("Nhập 4. xóa danh sách theo id");
            System.out.println("Nhập 5: Xóa sản phẩm");
            System.out.println("Nhập 5: Sắp xếp theo (Vào trong chọn thêm menu: tên/tuổi/giới tính/dob + TĂNG DẦN/GIẢM DẦN) ");
            System.out.println("Nhập 6: Tìm kiếm theo ");

            /**
             System.out.println("Menu quản lý Sản phẩm: ");
             System.out.println("Nhập 1. Xem danh sách ");
             System.out.println("Nhập 2. Thêm user");
             System.out.println("Nhập 3. Sửa user");
             System.out.println("Nhập 4. Xóa user theo ID");
             System.out.println("Nhập 5: Sắp xếp theo (Vào trong chọn thêm menu: tên/tuổi/giới tính/dob + TĂNG DẦN/GIẢM DẦN) ");
             System.out.println("Nhập 6: Tìm kiếm theo ");
             */

            int actionMenu = Integer.parseInt(scanner.nextLine());
            switch (actionMenu) {
                case 1: {
                    showSchedule();
                    break;
                }
                case 2: {
                    UpdateSchedule();
                    break;
                }
                case 3: {
                    creatSchedule();
                    break;

                }
                case 4: {
                    updateShedule();
                    break;
                }
                case 5: {
                    deleteSchedule();
                    break;
                }


            }
        } while (checkAction);

    }

    private void deleteSchedule() {
        System.out.println("Nhập id cần xóa: ");
        long id = Long.parseLong(scanner.nextLine());
        scheduleService.deleteSchedule(id);
        showSchedule();
    }

    private void updateShedule() {
        System.out.println("Nhập id cần thay đổi: ");
        long id = Long.parseLong(scanner.nextLine());
        scheduleService.updateSchedule(id);

    }

    private void creatSchedule() {
        //Schedule schedule = new Schedule();
        System.out.println("Nhập ID phòng: ");
        long roomID = Long.parseLong(scanner.nextLine());
        System.out.println("Nhập Id Phim:");
        long movieId = Long.parseLong(scanner.nextLine());
        System.out.println("Nhập ngày chiếu: (YYYY-MM-dd)");
        LocalDate dob = DateUtils.parseDate(scanner.nextLine());
        System.out.println("Nhập thời gian bắt đầu: ");
        LocalTime time1=null;
        boolean checkContinueOrderItem = true;

        do {
            time1= LocalTime.parse(scanner.nextLine());
            if (!scheduleService.isValidTimeStartSchedule(roomID, dob, time1)) {
                System.err.println("The account already exists(Tài khoản đã tồn tại)");
                System.out.println("Please enter again(Mời nhập lại):");
                checkContinueOrderItem=true;

            }else {
                //System.out.println(time1);
                checkContinueOrderItem=false;
            }


        } while (checkContinueOrderItem);
        System.out.println("Nhập thời gian kết thúc: ");
        LocalTime time2 = LocalTime.parse(scanner.nextLine());
//        schedule.setRoomID(roomID);
//        schedule.setMovieID(movieId);
//        schedule.setScheduleDate(dob);
        //LocalTime timestart = scheduleService.isValidTimeStartSchedule(roomID, dob, time1);
        Schedule schedule = new Schedule(System.currentTimeMillis() % 100000, roomID, movieId, dob, time1, time2);
        scheduleService.createSchedule(schedule);
        showSchedule();


    }
    private void UpdateSchedule() {
        System.out.println("Nhập id cần thay đổi: ");
        long id = Long.parseLong(scanner.nextLine());
        scheduleService.updateSchedule(id);


    }

    public void showSchedule() {
        List<Schedule> schedules = scheduleService.getAllschedule();
        System.out.printf("%10s | %20s | %20s | %30s | %20s | %10s \n", "ScheduleID", "Room", "Movie", "Ngày chiếu", "thời gian bắt đầu", "thời gian kết thúc");
        for (Schedule schedule : schedules) {
            Movie movieID = movieService.findMoviceById(schedule.getMovieID());
            Room roomID = roomService.findRoomByIdRoom(schedule.getRoomID());
            System.out.printf("%10s | %20s | %20s | %30s | %20s | %10s \n",
                    schedule.getScheduleID(), roomID.getRoomName(), movieID.getMovieName(), schedule.getScheduleDate(),
                    schedule.getScheduleStart(), schedule.getScheduleEnd());
        }
    }
    public void showScheduleID(long id){
      Schedule schedule = scheduleService.findScheduleById(id);
        System.out.printf("%10s | %20s | %20s | %30s | %20s | %10s \n", "ScheduleID", "Room", "Movie", "Ngày chiếu", "thời gian bắt đầu", "thời gian kết thúc");
        Movie movieID = movieService.findMoviceById(schedule.getMovieID());
        Room roomID = roomService.findRoomByIdRoom(schedule.getRoomID());
        System.out.printf("%10s | %20s | %20s | %30s | %20s | %10s \n",
                schedule.getScheduleID(), roomID.getRoomName(), movieID.getMovieName(), schedule.getScheduleDate(),
                schedule.getScheduleStart(), schedule.getScheduleEnd());


    }
    public void showScheduleIDSeat(long id){
        Schedule schedule = scheduleService.findScheduleById(id);
        System.out.printf("%10s | %20s | %20s | %30s | %20s | %10s \n", "ScheduleID", "Room", "Movie", "Ngày chiếu", "thời gian bắt đầu", "thời gian kết thúc");
        Movie movieID = movieService.findMoviceById(schedule.getMovieID());
        Room roomID = roomService.findRoomByIdRoom(schedule.getRoomID());
        System.out.printf("%10s | %20s | %20s | %30s | %20s | %10s \n",
                schedule.getScheduleID(), roomID.getRoomName(), movieID.getMovieName(), schedule.getScheduleDate(),
                schedule.getScheduleStart(), schedule.getScheduleEnd());
        //Room room = roomService.findRoomByIdRoom(id);
        System.out.println("Tên phòng: " +roomID.getRoomName());
        System.out.println("số lượng ghế: " + roomID.getQuantitySeat());

        for(Seat seat: roomID.getSeats()) {
            System.out.printf("%10s | %30s | %30s \n",
                    seat.getSeatID(), seat.getSeatNumber(),seat.geteSeatStatus());
        }

    }

    public static void main(String[] args) {
        ScheduleView scheduleView = new ScheduleView();
        scheduleView.laucher();



    }


}
