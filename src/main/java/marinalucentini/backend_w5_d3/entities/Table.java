package marinalucentini.backend_w5_d3.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import marinalucentini.backend_w5_d3.enums.TableStatus;

import java.util.List;
@Getter
@Setter
@ToString
public class Table {
    private int numberTable;
    private int maxPerson;
    private TableStatus tableStatus = TableStatus.FREE;
private List<Order> orders;

    public Table(int numberTable, int maxPerson) {
        this.numberTable = numberTable;
        this.maxPerson = maxPerson;
    }
}
