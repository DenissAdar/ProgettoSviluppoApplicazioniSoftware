package catering.businesslogic.task;

public class Preparation {
    private String name;
    private boolean terminated;

    public Preparation(String name) {
        this.name = name;
        terminated = false;
    }

    public String getName() {
        return name;
    }
}
