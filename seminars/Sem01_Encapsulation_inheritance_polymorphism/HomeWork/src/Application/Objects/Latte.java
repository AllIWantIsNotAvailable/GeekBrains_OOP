package Application.Objects;

import Application.Abstracts.CoffeeDrink;
import Application.Enums.CupSizes;
import Application.Enums.Temperature;
import Application.Enums.Units;

import java.util.HashMap;
import java.util.Map;

public class Latte extends CoffeeDrink {

    public static Map<CupSizes, Map<String, Float>> recipe;
    static {
        recipe = new HashMap<>() {{
            put(CupSizes.medium, new HashMap<>() {{
                        put("CoffeeBeans", 9.0f);
                        put("Water", 30.0f);
                        put("Milk", 120.0f);
                        put("Amount", get("Water") + get("Milk"));
                    }}
            );
            put(CupSizes.large, new HashMap<>() {{
                        put("CoffeeBeans", 18.0f);
                        put("Water", 60.0f);
                        put("Milk", 240.0f);
                        put("Amount", get("Water") + get("Milk"));
                    }}
            );
        }};
    }

    Milk milk;

    public Latte(CupSizes size, Float amount, CoffeeBeans coffeeBeans, Water water, Milk milk) {
        super(switch (size) {
                    case medium -> "Средний";
                    case large -> "Большой";
                    default -> "";
                } + " латте",
                amount,
                size,
                Temperature.medium,
                coffeeBeans,
                water);
        this.milk = milk;
    }

    @Override
    public String getName() {
        return super.name;
    }

    @Override
    public Float getVolume() {
        return super.volume;
    }

    @Override
    public Units getUnit() {
        return super.unit;
    }

    @Override
    public String toString() {
        return "Latte{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", volume=" + volume +
                ", unit=" + unit +
                ", coffeeBeans=" + coffeeBeans +
                ", water=" + water +
                ", temperature=" + temperature +
                ", milk=" + milk +
                '}';
    }
}
