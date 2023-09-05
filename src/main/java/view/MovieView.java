package view;

import eNum.EMovieType;
import models.Client;
import models.Movie;
import serivce.MovieService;
import utils.DateUtils;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

public class MovieView {
    Scanner scanner = new Scanner(System.in);
    MovieService movieService;
    static boolean checkActionMenu = true;
    static boolean repeatMenu = true;

    public MovieView() {
        movieService = new MovieService();
    }

    public void laucher() {
        boolean checkAction = false;
        do {
            System.out.println("client manage menulist: ");
            System.out.println("1. List Movies");
            System.out.println("2. find Movie");
            System.out.println("3. Remove Movie");
            System.out.println("4. addMovie");
            System.out.println("Nhập 5: Sắp xếp theo (Vào trong chọn thêm menu: tên/tuổi/giới tính/dob + TĂNG DẦN/GIẢM DẦN) ");
            System.out.println("6. UpdateMovie ");
            System.out.println("0. Back to Menu");

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
                    showMovies();
                    break;
                }
                case 2: {
                    searchMovies();
                    break;
                }
                case 3: {
                    removeMovie();
                    break;
                }
                case 4: {
                    addMovie();
                    break;
                }
                case 6: {
                    updateMovie();
                }

            }
        } while (checkAction);


    }

    private void updateMovie() {
        System.out.println("Nhập id cần thay đổi: ");
        long id = Long.parseLong(scanner.nextLine());
        movieService.updateMovie(id);

    }

    private void addMovie() {
        System.out.println("Nhập tên phim: ");
        String name = scanner.nextLine();
        System.out.println("Nhập description:");
        String description = scanner.nextLine();
        System.out.println("Nhập rate: ");
        double rate = Double.parseDouble(scanner.nextLine());
        System.out.println("Nhập thể loại: ");
        for (EMovieType eMovieType : EMovieType.values()) {
            System.out.println(eMovieType.getType() + " : " + eMovieType.getId());
        }
        int eMovieType = Integer.parseInt(scanner.nextLine());
        EMovieType eMovieType1 = EMovieType.getStatusMovieType(eMovieType);

        Movie movie = new Movie(System.currentTimeMillis() % 100000, name, description, rate, eMovieType1);

        movieService.createMovie(movie);

        showMovies();

    }

    private void removeMovie() {
        System.out.println("Enter the ID you want to remove: ");
        long id = Long.parseLong(scanner.nextLine());
        movieService.deleteMovie(id);
        showMovies();
    }

    private void searchMovies() {
        int choose = 0;
        do {
            System.out.println("Search Movie: ");
            System.out.println("1. Search FullName ");
            System.out.println("2. Search MovieType");
            choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 1: {
                    System.out.println("Enter the name you want to search: ");
                    String movieName = scanner.nextLine();
                    List<Movie> movies = movieService.searchMovie(movieName);
                    for (Movie m : movies) {
                        System.out.printf("%10s | %30s | %30s | %10s | %10s  \n",
                                m.getMovieId(), m.getMovieName(), m.getMovieDescription(), m.getRate(), m.geteMovieType());
                    }
                    break;

                }
                case 2: {
                    System.out.println("Enter the Auth you want to search: ");
                    String eMovieType = scanner.nextLine();
                    List<Movie> movies = movieService.searchMovie(eMovieType);
                    for (Movie m : movies) {
                        System.out.printf("%10s | %30s | %30s | %10s | %10s \n",
                                m.getMovieId(), m.getMovieName(), m.getMovieDescription(), m.getRate(), m.geteMovieType());

                    }

                }
                default:
                    System.err.println("Nhập không đúng, vui lòng nhập lại !!!");
                    continue;
            }
            do {
                System.out.println("Ban có muốn tiếp tục tìm kiếm tên phim hay không? ");
                System.out.println("Nhập 1. Tiếp tục");
                System.out.println("Nhập 2. Kết thúc");
                int actionMenuContinue = Integer.parseInt(scanner.nextLine());
                switch (actionMenuContinue) {
                    case 1:
                        checkActionMenu = true;
                        repeatMenu = false;
                        break;
                    case 2:
                        checkActionMenu = false;
                        repeatMenu = false;
                        break;
                    default:
                        System.out.println("Không đúng lệnh, vui lòng nhập lại:");
                }
            } while (repeatMenu);
        } while (checkActionMenu);
    }

    private void showMovies() {
        //long movieId, String movieName, String movieDescription, double rate, EMovieType eMovieType
        List<Movie> movies = movieService.getAllMovies();
        System.out.printf("%10s | %30s | %30s | %10s | %10s  \n", "ID", "FULLName", "EMAIL", "PASSWORD", "PHONENAME");
        for (Movie m : movies) {
            System.out.printf("%10s | %30s | %30s | %10s | %10s \n",
                    m.getMovieId(), m.getMovieName(), m.getMovieDescription(), m.getRate(), m.geteMovieType());

        }
    }

    public static void main(String[] args) {
        MovieView movieView = new MovieView();
        movieView.laucher();
    }
}
