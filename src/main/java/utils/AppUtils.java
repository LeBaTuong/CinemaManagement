package utils;

import models.Seat;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AppUtils {
    private static final Scanner sc;

    static {
        sc = new Scanner(System.in);
    }

    public static String getString(String str) {
        try {
            System.out.println(str);
            String data = sc.nextLine();
            if (data.equals("")) {
                throw new Exception();
            }
            return data.trim();
        } catch (Exception e) {
            System.out.println("Empty data. Input again!");
            return getString(str);
        }
    }

    public static int getInt(String str) {
        try {
            return Integer.parseInt(getString(str));
        } catch (Exception e) {
            System.out.println("Please input number(Mời nhập số)");
            return getInt(str);
        }
    }

    public static int getIntWithBound(String str, int begin, int end) {
        try {
            int number = getInt(str);
            if (number < begin || number > end) {
                throw new NumberFormatException(String.format("Please input number between " + begin + " and " + end + "(Vui lòng nhập số từ " + begin + " đến " + end + ")"));
            }
            return number;
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return getIntWithBound(str, begin, end);
        }
    }

    public static String getStringWithPattern(String str, String pattern) {
        String result = getString(str);
        if (!Pattern.compile(pattern).matcher(result).matches()) {
            System.err.println("Please input phone:03 or 05 or 07 or 08 or 09(Nhập không hợp lệ, bắt đầu bằng:03 hoặc 05 hoặc 07 hoặc 08 hoặc 09)");
            getStringWithPattern(str, pattern);
        }
        return result;
    }

    public static String getStringWithPattern1(String str, String pattern) {
        String result = getString(str);
        if (!Pattern.compile(pattern).matcher(result).matches()) {
            System.err.println("Please input email: (Nhập không hợp lệ)");
            getStringWithPattern(str, pattern);
        }
        return result;
    }

    public static LocalDate getUserDateOfBirth() {
        System.out.println("Enter DOB(Nhập ngày sinh)");
        LocalDate userInput = null;
        boolean isValid = false;
        int invalidCount = 0;
        while (!isValid) {
            try {
                userInput = AppUtils.getDate();
                int minimumAge = 18;
                LocalDate minimumDateOfBirth = LocalDate.now().minusYears(minimumAge);
                if (userInput.isAfter(minimumDateOfBirth)) {
                    System.err.println("You are not yet 18 years old(Bạn chưa đủ 18 tuổi).");
                    continue;

                }
                isValid = true;
            } catch (Exception e) {
                invalidCount++;
                System.err.println("Invalid date format. Please try again(Định dạng ngày không hợp lệ. Vui lòng thử lại).");
                if (invalidCount >= 3) {
                    System.err.println("You have entered an invalid date 3 times. The program will exit(Bạn đã nhập sai quá 3 lần. Chương trình sẽ thoát).");
                    System.exit(0);
                }
            }

        }

        return userInput;
    }

    public static LocalDate getDate() {
        String input = sc.nextLine();
        try {
            return LocalDate.parse(input);
        } catch (Exception e) {
            System.err.println("Invalid date format:YYYY-MM-DD. Please try again(Định dạng ngày không hợp lệ:YYYY-MM-DD. Vui lòng thử lại)");
            return getDate();
        }
    }
//    public static void checkSeat(String seat) {
//
//
//            if (seat.g.equals(EStatusBill.DELETE)) {
//
//                totalAmount += bill.getTotal();
//            }
//        }
//        displayCheckBill(start, end);
//        String total = covertPriceToString(totalAmount);
//        System.out.println("Doanh thu: "+total);
//    }
}
