package goinfo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name="GHSAHMS1")
public class RepairItem {

    @Id
    @Getter @Setter @Column(unique=true, name="Code") private String code;
    @Getter @Setter @Column(name="Name") private String name;
    @Getter @Setter @Column(name="Note") private String note;

    protected RepairItem() {}

    public RepairItem(String code, String name, String note) {
        this.code = code;
        this.name = name;
        this.note = note;
    }

    @Override
    public String toString() {
        return "";
    }

}
