package serivce;


import eNum.EMovieType;
import models.Client;
import models.Movie;
import utils.AppUtils;
import utils.FileUtils;
import utils.AppUtils;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MovieService {

    List<Movie> movies;
    Scanner scanner = new Scanner(System.in);
    private static final String fileMovie = "./data/movies.txt";

    public static List<Movie> getAllMovies() {
        return FileUtils.readData(fileMovie, Movie.class);
    }

    public List<Movie> searchMovie(String kw) {
        List<Movie> movies1 = getAllMovies();
        List<Movie> result = new ArrayList<>();
        result = movies1.stream().filter(movie -> searchMovieByKW(movie, kw)).collect(Collectors.toList());
        if (result == null) {
            System.out.println("Car not found. Please try again!");
        }
        return result;
    }

    public Movie findMoviceById(long id) {
        List<Movie> movies1 = getAllMovies();
//        for(Order o: orders){
//            if(o.getId()==id){
//                return o;
//            }
//        }
        Movie result = movies1.stream().filter(movie -> movie.getMovieId() == id).findFirst().orElseThrow(null);
        return result;
    }

    public boolean searchMovieByKW(Movie movie, String kw) {
        return movie.getMovieName().toLowerCase().contains(kw.toLowerCase()) ||
                movie.geteMovieType().toString().toLowerCase().contains(kw.toLowerCase());
    }

    public void deleteMovie(long id) {
        List<Movie> movies1 = getAllMovies();
        movies1.remove(movies1.stream().filter(client -> client.getMovieId() == id).findFirst().orElseThrow(null));
        FileUtils.writerData(fileMovie, movies1);
        System.out.println("Movie with id: " + id + " has been removed successfully");

    }

    public void createMovie(Movie movie) {
        List<Movie> movies1 = getAllMovies();
        movies1.add(movie);
        FileUtils.writerData(fileMovie, movies1);


    }

    public void updateMovie(long id) {

        List<Movie> movies1 = getAllMovies();
        for (int i = 0; i < movies1.size(); i++) {
            if (movies1.get(i).getMovieId() == id) {
                int choice = AppUtils.getIntWithBound("Mời chọn ví trí 1(Name)->" +
                        "3(Thể loại)", 1, 3);
                switch (choice) {
                    case 1: {
                        System.out.println("Nhập tên cần thay: ");
                        String name = scanner.nextLine();
                        movies1.get(i).setMovieName(name);
                        FileUtils.writerData(fileMovie, movies1);
                        System.out.println("Sửa thành công");
                        break;
                    }
                    case 2: {
                        System.out.println("Nhập miêu tả cần thay: ");
                        String descripton = scanner.nextLine();
                        movies1.get(i).setMovieDescription(descripton);
                        FileUtils.writerData(fileMovie, movies1);
                        System.out.println("Sửa thành công");
                        break;
                    }
                    case 3: {
                        System.out.println("Nhập miêu tả cần thay: ");
                        String movieType = scanner.nextLine();
                        movies1.get(i).seteMovieType(EMovieType.valueOf(movieType));
                        FileUtils.writerData(fileMovie, movies1);
                        System.out.println("Sửa thành công");
                        break;
                    }

                }
            }
        }
    }
}

