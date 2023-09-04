package eNum;

public enum EAuth {
    ADMIN(1,"ADMIN"),
    EMPLOYEE(2,"EMPLOYEE"),

    CLIENT(3,"CLIENT");
    private int id;
    private String name;

    public static EAuth findById(long id) {
        for (EAuth e : values()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    EAuth(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
