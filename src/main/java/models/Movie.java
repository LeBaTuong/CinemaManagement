package models;

import eNum.EAuth;
import eNum.EMovieType;
import serivce.IParseModel;
import utils.DateUtils;

public class Movie implements IParseModel {
    private long movieId;
    private String movieName;
    private String movieDescription;
    private double rate;
    private EMovieType eMovieType;

    public Movie() {
    }

    public Movie(long movieId, String movieName, String movieDescription, double rate, EMovieType eMovieType) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.rate = rate;
        this.eMovieType = eMovieType;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public EMovieType geteMovieType() {
        return eMovieType;
    }

    public void seteMovieType(EMovieType eMovieType) {
        this.eMovieType = eMovieType;
    }

    @Override
    public Object parse(String line) {
        //2, Iron man,description, 4.8, ACTION
        String[] item = line.split(",");
        Movie movie = new Movie(Long.parseLong(item[0]), item[1], item[2], Double.parseDouble(item[3]), EMovieType.valueOf(item[4]));
        return movie;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", this.movieId, this.movieName, this.movieDescription, this.rate, this.eMovieType);
    }
}
