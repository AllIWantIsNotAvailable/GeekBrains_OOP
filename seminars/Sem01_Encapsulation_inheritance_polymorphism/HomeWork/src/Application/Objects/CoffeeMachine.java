package Application.Objects;

import Application.Abstracts.CoffeeDrink;
import Application.Enums.CupSizes;
import Application.Interfaces.Products;
import Application.Interfaces.Vending;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeMachine implements Vending {
    Map<String, Integer> assortment;
    Milk milk;
    Milk stashedMilk;
    CoffeeBeans coffeeBeans;
    CoffeeBeans stashedCoffeeBeans;
    Water water;
    Water stashedWater;

    public CoffeeMachine(CoffeeBeans coffeeBeans, Water water, Milk milk) {
        this.coffeeBeans = coffeeBeans;
        this.water = water;
        this.milk = milk;
        this.assortment = new HashMap<>() {{
            put("Espresso", 1);
            put("Americano", 2);
            put("Cappuccino", 3);
            put("Latte", 4);
            put("Frappe", 5);
        }};
    }

    @Override
    public boolean isWorking() {
        return !(this.coffeeBeans == null || this.water == null) && this.milk != null;
    }

    @Override
    public void putProduct(Products product) {
        if (product instanceof CoffeeBeans tempCoffeeBeans) {
            if (this.coffeeBeans == null) this.coffeeBeans = tempCoffeeBeans;
            else this.coffeeBeans.setVolume(this.coffeeBeans.getVolume() + coffeeBeans.getVolume());
        } else if (product instanceof Water tempWater) {
            if (this.water == null) this.water = tempWater;
            else this.water.setVolume(this.water.getVolume() + water.getVolume());
        } else if (product instanceof Milk tempMilk) {
            if (this.milk == null) this.milk = tempMilk;
            else this.milk.setVolume(this.milk.getVolume() + milk.getVolume());
        }
    }

    @Override
    public void putProducts(List<Products> products) {
        for (Products product :
                products) {
            putProduct(product);
        }
    }

    private CoffeeBeans getStashed(CoffeeBeans coffeeBeans, float amount) {
        if (stashedCoffeeBeans == null) {
            if (coffeeBeans != null && amount <= coffeeBeans.getVolume()) {
                coffeeBeans.setVolume(coffeeBeans.getVolume() - amount);
                stashedCoffeeBeans = new CoffeeBeans(coffeeBeans.getName(), amount, coffeeBeans.getUnit(), coffeeBeans.getPlace());
                return stashedCoffeeBeans;
            } else {
                stashedCoffeeBeans = coffeeBeans;
                this.coffeeBeans = null;
            }
        } else {
            if (amount == stashedCoffeeBeans.getVolume()) {
                return stashedCoffeeBeans;
            } else if (amount < stashedCoffeeBeans.getVolume()) {
                float tailedStashedVolume = stashedCoffeeBeans.getVolume() - amount;
                stashedCoffeeBeans = new CoffeeBeans(stashedCoffeeBeans.getName(), amount, stashedCoffeeBeans.getUnit(), stashedCoffeeBeans.getPlace());
                if (this.coffeeBeans != null) {
                    this.coffeeBeans.setVolume(this.coffeeBeans.getVolume() + tailedStashedVolume);
                } else {
                    this.coffeeBeans = new CoffeeBeans(stashedCoffeeBeans.getName(), tailedStashedVolume, stashedCoffeeBeans.getUnit(), stashedCoffeeBeans.getPlace());
                }
                return stashedCoffeeBeans;
            } else if (coffeeBeans != null && amount < stashedCoffeeBeans.getVolume() + coffeeBeans.getVolume()) {
                float missingVolume = amount - stashedCoffeeBeans.getVolume();
                coffeeBeans.setVolume(coffeeBeans.getVolume() - missingVolume);
                stashedCoffeeBeans.setVolume(stashedCoffeeBeans.getVolume() + missingVolume);
            } else if (coffeeBeans != null && amount == stashedCoffeeBeans.getVolume() + coffeeBeans.getVolume()) {
                stashedCoffeeBeans.setVolume(stashedCoffeeBeans.getVolume() + coffeeBeans.getVolume());
                this.coffeeBeans = null;
                return stashedCoffeeBeans;
            } else {
                if (coffeeBeans != null) {
                    stashedCoffeeBeans.setVolume(stashedCoffeeBeans.getVolume() + coffeeBeans.getVolume());
                }
                this.coffeeBeans = null;
                return null;
            }
        }
        return null;
    }

    private Milk getStashed(Milk milk, float amount) {
        if (stashedMilk == null) {
            if (milk != null && amount <= milk.getVolume()) {
                milk.setVolume(milk.getVolume() - amount);
                stashedMilk = new Milk(milk.getName(), amount, milk.getUnit(), milk.getPlace());
                return stashedMilk;
            } else {
                stashedMilk = milk;
                this.milk = null;
            }
        } else {
            if (amount == stashedMilk.getVolume()) {
                return stashedMilk;
            } else if (amount < stashedMilk.getVolume()) {
                float tailedStashedVolume = stashedMilk.getVolume() - amount;
                stashedMilk = new Milk(stashedMilk.getName(), amount, stashedMilk.getUnit(), stashedMilk.getPlace());
                if (this.milk != null) {
                    this.milk.setVolume(this.milk.getVolume() + tailedStashedVolume);
                } else {
                    this.milk = new Milk(stashedMilk.getName(), tailedStashedVolume, stashedMilk.getUnit(), stashedMilk.getPlace());
                }
                return stashedMilk;
            } else if (milk != null && amount < stashedMilk.getVolume() + milk.getVolume()) {
                float missingVolume = amount - stashedMilk.getVolume();
                milk.setVolume(milk.getVolume() - missingVolume);
                stashedMilk.setVolume(stashedMilk.getVolume() + missingVolume);
            } else if (milk != null && amount == stashedMilk.getVolume() + milk.getVolume()) {
                stashedMilk.setVolume(stashedMilk.getVolume() + milk.getVolume());
                this.milk = null;
                return stashedMilk;
            } else {
                if (milk != null) {
                    stashedMilk.setVolume(stashedMilk.getVolume() + milk.getVolume());
                }
                this.milk = null;
                return null;
            }
        }
        return null;
    }

    private Water getStashed(Water water, float amount) {
        if (stashedWater == null) {
            if (water != null && amount <= water.getVolume()) {
                water.setVolume(water.getVolume() - amount);
                stashedWater = new Water(water.getName(), amount, water.getUnit(), water.getPlace());
                return stashedWater;
            } else {
                stashedWater = water;
                this.water = null;
            }
        } else {
            if (amount == stashedWater.getVolume()) {
                return stashedWater;
            } else if (amount < stashedWater.getVolume()) {
                float tailedStashedVolume = stashedWater.getVolume() - amount;
                stashedWater = new Water(stashedWater.getName(), amount, stashedWater.getUnit(), stashedWater.getPlace());
                if (this.water != null) {
                    this.water.setVolume(this.water.getVolume() + tailedStashedVolume);
                } else {
                    this.water = new Water(stashedWater.getName(), tailedStashedVolume, stashedWater.getUnit(), stashedWater.getPlace());
                }
                return stashedWater;
            } else if (water != null && amount < stashedWater.getVolume() + water.getVolume()) {
                float missingVolume = amount - stashedWater.getVolume();
                water.setVolume(water.getVolume() - missingVolume);
                stashedWater.setVolume(stashedWater.getVolume() + missingVolume);
            } else if (water != null && amount == stashedWater.getVolume() + water.getVolume()) {
                stashedWater.setVolume(stashedWater.getVolume() + water.getVolume());
                this.water = null;
                return stashedWater;
            } else {
                if (water != null) {
                    stashedWater.setVolume(stashedWater.getVolume() + water.getVolume());
                }
                this.water = null;
                return null;
            }
        }
        return null;
    }

    private CoffeeDrink getEspresso(CupSizes cupSize) {
        if (cupSize == null) {
            return null;
        }
        Map<CupSizes, Map<String, Float>> recipe = Espresso.recipe;
        CoffeeBeans portionCoffeeBeans = getStashed(this.coffeeBeans, recipe.get(cupSize).get("CoffeeBeans"));
        Water portionWater = getStashed(this.water, recipe.get(cupSize).get("Water"));
        if (portionCoffeeBeans == null || portionWater == null) {
            return null;
        }
        CoffeeDrink coffeeDrink = new Espresso(cupSize, recipe.get(cupSize).get("Amount"), portionCoffeeBeans, portionWater);
        stashedCoffeeBeans = null;
        stashedWater = null;
        return coffeeDrink;
    }

    private CoffeeDrink getEspresso() {
        return getEspresso(CupSizes.small);
    }

    private CoffeeDrink getAmericano(CupSizes cupSize) {
        if (cupSize == null) {
            return null;
        }
        Map<CupSizes, Map<String, Float>> recipe = Espresso.recipe;
        CoffeeBeans portionCoffeeBeans = getStashed(this.coffeeBeans, recipe.get(cupSize).get("CoffeeBeans"));
        Water portionWater = getStashed(this.water, recipe.get(cupSize).get("Water"));
        if (portionCoffeeBeans == null || portionWater == null) {
            return null;
        }
        CoffeeDrink coffeeDrink = new Americano(cupSize, recipe.get(cupSize).get("Amount"), portionCoffeeBeans, portionWater);
        stashedCoffeeBeans = null;
        stashedWater = null;
        return coffeeDrink;
    }

    private CoffeeDrink getAmericano() {
        return getAmericano(CupSizes.small);
    }

    private CoffeeDrink getCappuccino(CupSizes cupSize) {
        if (cupSize == null) {
            return null;
        }
        Map<CupSizes, Map<String, Float>> recipe = Cappuccino.recipe;
        CoffeeBeans portionCoffeeBeans = getStashed(this.coffeeBeans, recipe.get(cupSize).get("CoffeeBeans"));
        Water portionWater = getStashed(this.water, recipe.get(cupSize).get("Water"));
        Milk portionMilk = getStashed(this.milk, recipe.get(cupSize).get("Milk"));
        if (portionCoffeeBeans == null || portionWater == null || portionMilk == null) {
            return null;
        }
        CoffeeDrink coffeeDrink = new Cappuccino(cupSize, recipe.get(cupSize).get("Amount"), portionCoffeeBeans, portionWater, portionMilk);
        stashedCoffeeBeans = null;
        stashedWater = null;
        stashedMilk = null;
        return coffeeDrink;
    }

    private CoffeeDrink getCappuccino() {
        return getCappuccino(CupSizes.small);
    }

    private CoffeeDrink getLatte(CupSizes cupSize) {
        if (cupSize == null) {
            return null;
        }
        Map<CupSizes, Map<String, Float>> recipe = Cappuccino.recipe;
        CoffeeBeans portionCoffeeBeans = getStashed(this.coffeeBeans, recipe.get(cupSize).get("CoffeeBeans"));
        Water portionWater = getStashed(this.water, recipe.get(cupSize).get("Water"));
        Milk portionMilk = getStashed(this.milk, recipe.get(cupSize).get("Milk"));
        if (portionCoffeeBeans == null || portionWater == null || portionMilk == null) {
            return null;
        }
        CoffeeDrink coffeeDrink = new Latte(cupSize, recipe.get(cupSize).get("Amount"), portionCoffeeBeans, portionWater, portionMilk);
        stashedCoffeeBeans = null;
        stashedWater = null;
        stashedMilk = null;
        return coffeeDrink;
    }

    private CoffeeDrink getLatte() {
        return getLatte(CupSizes.small);
    }

    private CoffeeDrink getFrappe(CupSizes cupSize) {
        if (cupSize == null) {
            return null;
        }
        Map<CupSizes, Map<String, Float>> recipe = Cappuccino.recipe;
        CoffeeBeans portionCoffeeBeans = getStashed(this.coffeeBeans, recipe.get(cupSize).get("CoffeeBeans"));
        Water portionWater = getStashed(this.water, recipe.get(cupSize).get("Water"));
        if (portionCoffeeBeans == null || portionWater == null) {
            return null;
        }
        CoffeeDrink coffeeDrink = new Frappe(cupSize, recipe.get(cupSize).get("Amount"), portionCoffeeBeans, portionWater);
        stashedCoffeeBeans = null;
        stashedWater = null;
        return coffeeDrink;
    }

    private CoffeeDrink getFrappe() {
        return getFrappe(CupSizes.small);
    }

    public Products getProduct(String name, CupSizes cupSize) {
        return switch (assortment.get(name)) {
            case 1 -> cupSize == null ? getEspresso() : getEspresso(cupSize);
            case 2 -> cupSize == null ? getAmericano() : getAmericano(cupSize);
            case 3 -> cupSize == null ? getCappuccino() : getCappuccino(cupSize);
            case 4 -> cupSize == null ? getLatte() : getLatte(cupSize);
            case 5 -> cupSize == null ? getFrappe() : getFrappe(cupSize);
            default -> null;
        };
    }

    @Override
    public Products getProduct(String name) {
        return switch (assortment.get(name)) {
            case 1 -> getEspresso();
            case 2 -> getAmericano();
            case 3 -> getCappuccino();
            case 4 -> getLatte();
            case 5 -> getFrappe();
            default -> null;
        };
    }
}
