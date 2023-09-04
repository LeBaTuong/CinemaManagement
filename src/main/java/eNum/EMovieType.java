package eNum;

public enum EMovieType {
    ACTION(1, "Action"),
    ROMANCE (2, "Romance"),
    FICTIONAL (3, "Fictional");
    private int id;
    private String type;
    EMovieType(int id, String type){
        this.id=id;
        this.type= type;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public static EMovieType getStatusMovieType(int id) {
        for (EMovieType e : values()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
}
