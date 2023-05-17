package shopms;

public class EmpData {
    private Integer id;
    private String name;
    private Double total;

    public EmpData(Integer id, String name, Double total) {
        this.id = id;
        this.name = name;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getTotal() {
        return total;
    }
}
