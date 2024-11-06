package by.tms.onlinerclonec30onl.domain;

//@Setter
//@Getter
//@EqualsAndHashCode
public class ProductType {
    private long id;
    private String nameType;
    private String nameTable;

    public ProductType(String type, String table) {
        this.nameType = type;
        this.nameTable = table;
    }

    public long getId() {
        return id;
    }

    public String getNameType() {
        return nameType;
    }

    public String getNameTable() {
        return nameTable;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }
}
