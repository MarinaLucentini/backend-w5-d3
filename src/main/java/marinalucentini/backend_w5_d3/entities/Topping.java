package marinalucentini.backend_w5_d3.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Topping extends MenuElement{


    public Topping(String name, double price, int calories) {
        super(name, price, calories);
    }
}
