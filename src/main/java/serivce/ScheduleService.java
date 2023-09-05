package serivce;

import eNum.EMovieType;
import models.Movie;
import models.Schedule;
import utils.AppUtils;
import utils.DateUtils;
import utils.FileUtils;
import utils.ValidateUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static utils.AppUtils.getString;

public class ScheduleService {
    Scanner scanner = new Scanner(System.in);
    private static final String fileSchedule = "./data/schedules.txt";

    public static List<Schedule> getAllschedule() {
        List<Schedule> schedules = FileUtils.readData(fileSchedule, Schedule.class);
        return schedules;
    }

    public List<Schedule> getAllScheduleByRoomID(long roomID) {
        List<Schedule> schedules = getAllschedule();
        List<Schedule> result = schedules.stream().filter(schedule -> schedule.getRoomID() == roomID).collect(Collectors.toList());
        return result;
    }

    public Schedule findScheduleById(long id) {
        List<Schedule> schedules = getAllschedule();
//        for(Order o: orders){
//            if(o.getId()==id){
//                return o;
//            }
//        }
        Schedule result = schedules.stream().filter(schedule -> schedule.getScheduleID() == id).findFirst().orElseThrow(null);
        return result;
    }

    public void updateSchedule(long id) {
        List<Schedule> schedules = getAllschedule();
        System.out.println("Nhập id phòng caanf thay đổi: ");
        long roomID = Long.parseLong(scanner.nextLine());
        System.out.println("Nhập id phim cần suwar: ");
        long movieID = Long.parseLong(scanner.nextLine());
        System.out.println("Nhập ngày chiếu cần thay đổi: ");
        LocalDate date = DateUtils.parseDate(scanner.nextLine());
        System.out.println("Nhập thời gian bắt đầu");
        LocalTime time1 = null;
        boolean checkContinueOrderItem = true;

        do {
            time1 = LocalTime.parse(scanner.nextLine());
            if (!isValidTimeStartSchedule(roomID, date, time1)) {
                System.err.println("The account already exists(Tài khoản đã tồn tại)");
                System.out.println("Please enter again(Mời nhập lại):");
                checkContinueOrderItem = true;

            } else {
                //System.out.println(time1);
                checkContinueOrderItem = false;
            }


        } while (checkContinueOrderItem);
        System.out.println("Nhập thời gian kết thúc: ");
        LocalTime time2 = LocalTime.parse(scanner.nextLine());
        for (int i = 0; i < schedules.size(); i++) {
            if (schedules.get(i).getScheduleID() == id) {
                schedules.get(i).setRoomID(roomID);
                schedules.get(i).setMovieID(movieID);
                schedules.get(i).setScheduleDate(date);
                schedules.get(i).setScheduleStart(time1);
                schedules.get(i).setScheduleEnd(time2);
                FileUtils.writerData(fileSchedule, schedules);
                System.out.println("sửa thành công");
            }
        }
    }

    public void deleteSchedule(long id) {
        List<Schedule> schedules = getAllschedule();
        schedules.remove(schedules.stream().filter(schedule -> schedule.getScheduleID() == id).findFirst().orElseThrow(null));
        FileUtils.writerData(fileSchedule, schedules);
    }

    public void createSchedule(Schedule schedule) {
        List<Schedule> schedules = getAllschedule();
        schedules.add(schedule);
        FileUtils.writerData(fileSchedule, schedules);
    }

    public boolean isSchedule(long roomId, LocalDate date) {

        List<Schedule> schedules = getAllschedule();
//        schedules.stream().filter(e -> e.getRoomID()==roomId && e.getScheduleDate()==date).collect(Collectors.toList());
        for (Schedule s : schedules) {
            if (s.getRoomID() == roomId && s.getScheduleDate().equals(date)) {
                return true;
            }
        }
        return false;

    }

    public boolean isValidTimeStartSchedule(long roomId, LocalDate date, LocalTime time) {

        int count = 0;
        List<Schedule> schedules = getAllschedule();
//        schedules.stream().filter(e -> e.getRoomID()==roomId && e.getScheduleDate()==date).collect(Collectors.toList());
        for (Schedule s : schedules) {
            if (s.getRoomID() == roomId && s.getScheduleDate().equals(date)) {
                count++;
                if (isValidTimeStart(time, s.getScheduleEnd()))
                    return true;
            }
        }
        if (count == 0) {
            if (date.isAfter(LocalDate.now())) {
                return true;
            }
        }
        return false;
    }

    //    public boolean isValidTimeStartSchedule(long roomId, LocalDate date, LocalTime time) {
//
//       int count  = 0;
//        List<Schedule> schedules = getAllschedule();
////        schedules.stream().filter(e -> e.getRoomID()==roomId && e.getScheduleDate()==date).collect(Collectors.toList());
//        for (Schedule s : schedules) {
//            if (s.getRoomID() == roomId && s.getScheduleDate().equals(date)) {
//                count++;
//                if(isValidTimeStart(time, s.getScheduleEnd()))
//                    return true;
//            }
//        }
//        if (count == 0) {
//            if (date.isAfter(LocalDate.now())) {
//                return true;
//            }
//        }
//        return false;
//    }
    public boolean isValidTimeStart(LocalTime timePresent, LocalTime timeCheck) {
        long minutesDifference = timeCheck.until(timePresent, ChronoUnit.MINUTES);

        if (minutesDifference >= 15 && minutesDifference <= 30) {
            return true;
        } else {
            return false;
        }
    }
    //public


    public static void main(String[] args) {
        ScheduleService scheduleService = new ScheduleService();
        System.out.println(scheduleService.isValidTimeStartSchedule(1, LocalDate.of(2023, 8, 31), LocalTime.of(15, 35)));
    }

}

