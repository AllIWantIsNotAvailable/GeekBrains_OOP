package Application.Services.Entities;

import Application.Model.Abstracts.Product;
import Application.Model.Abstracts.ProductForSale;
import Application.Model.Entities.CoffeeDrink;
import Application.Model.Entities.Factories.CoffeeMachine;
import Application.Services.Abstract.ProductService;

import java.util.HashMap;
import java.util.Map;

public class CoffeeService extends ProductService<CoffeeMachine> {
    public CoffeeService(CoffeeMachine factory) {
        super(factory);
    }

    @Override
    public Map<String, Float> getPriceList() {
        return new HashMap<>() {{
            put("Espresso", 120f);
            put("Espresso with milk", 130f);
            put("Americano", 150f);
            put("Americano with milk", 160f);
            put("Americano with sugar", 155f);
            put("Americano with milk and sugar", 165f);
            put("Cappuccino", 200f);
            put("Cappuccino with syrup", 220f);
            put("Cappuccino with sugar", 205f);
            put("Latte", 250f);
            put("Latte with syrup", 270f);
            put("Latte with sugar", 250f);
        }};
    }

    @Override
    protected void setPriceList(Map<String, Float> priceList) {
        super.priceList = getPriceList();
    }



    @Override
    protected Map<String, Map<String, Float>> getRecipeBook() {
        return new HashMap<>() {{
            put("Espresso", new HashMap<>() {{
                put("CoffeeBeans", 10f);
                put("Water", 30f);
            }});
            put("Espresso with milk", new HashMap<>() {{
                put("CoffeeBeans", 10f);
                put("Water", 30f);
                put("Milk", 10f);
            }});
            put("Americano", new HashMap<>() {{
                put("CoffeeBeans", 10f);
                put("Water", 60f);
            }});
            put("Americano with milk", new HashMap<>() {{
                put("CoffeeBeans", 10f);
                put("Water", 60f);
                put("Milk", 10f);
            }});
            put("Americano with sugar", new HashMap<>() {{
                put("CoffeeBeans", 10f);
                put("Water", 60f);
                put("Sugar", 5f);
            }});
            put("Americano with milk and sugar", new HashMap<>() {{
                put("CoffeeBeans", 10f);
                put("Water", 60f);
                put("Milk", 10f);
                put("Sugar", 5f);
            }});
            put("Cappuccino", new HashMap<>() {{
                put("CoffeeBeans", 10f);
                put("Water", 30f);
                put("Milk", 20f);
            }});
            put("Cappuccino with syrup", new HashMap<>() {{
                put("CoffeeBeans", 10f);
                put("Water", 30f);
                put("Milk", 20f);
                put("Syrup", 10f);
            }});
            put("Cappuccino with sugar", new HashMap<>() {{
                put("CoffeeBeans", 10f);
                put("Water", 30f);
                put("Milk", 20f);
                put("Sugar", 5f);
            }});
            put("Latte", new HashMap<>() {{
                put("CoffeeBeans", 10f);
                put("Water", 30f);
                put("Milk", 40f);
            }});
            put("Latte with syrup", new HashMap<>() {{
                put("CoffeeBeans", 10f);
                put("Water", 30f);
                put("Milk", 40f);
                put("Syrup", 10f);
            }});
            put("Latte with sugar", new HashMap<>() {{
                put("CoffeeBeans", 10f);
                put("Water", 30f);
                put("Milk", 40f);
                put("Sugar", 5f);
            }});
        }};
    }

    @Override
    protected void setRecipeBook(Map<String, Map<String, Float>> recipeBook) {
        super.recipeBook = recipeBook;
    }

    @Override
    protected CoffeeMachine getFactory() {
        return super.factory;
    }

    @Override
    protected void setFactory(CoffeeMachine factory) {
        super.factory = factory;
    }

    @Override
    public Float getProductPrice(String productName) {
        return getPriceList().get(productName);
    }

    @Override
    public void makeDeposit(Float cash) {
        super.deposit = cash;
    }

    private Float getDeposit() {
        return super.deposit;
    }

    @Override
    public void makeOrder(String productName) throws Exception {
        Float price = getPriceList().get(productName);
        Map<String, Float> recipe = getRecipeBook().get(productName);
        if (recipe != null && price != null) {
            setProductStashed(new CoffeeDrink(
                            productName,
                            getFactory()
                                    .makeCoffee(
                                            recipe.get("CoffeeBeans"),
                                            recipe.get("Water"),
                                            recipe.get("Milk"),
                                            recipe.get("Sugar"),
                                            recipe.get("Syrup")
                                    )
                                    .getComposition(),
                            price
                    )
            );
        }
    }

    private Product getProductStashed() {
        return super.productStashed;
    }

    private void setProductStashed(ProductForSale product) {
        super.productStashed = product;
    }

    @Override
    public Float getChange() {
        Float change = super.change;
        setChange(0f);
        return change;
    }

    private void setChange(Float cash) {
        super.change = cash;
    }

    private Float getProfit() {
        return super.cash;
    }

    private void makeProfit(Float cash) {
        super.cash = getProfit() + cash;
    }

    @Override
    public Product buyProduct(String productName, Float cash) throws Exception {
        makeDeposit(getDeposit() + cash);
        Float productPrice = getProductPrice(productName);
        if (productPrice != null && productPrice <= getDeposit()) {
            makeOrder(productName);

            setChange(getDeposit() - productPrice);
            makeProfit(productPrice);
            makeDeposit(0f);

            Product product = getProductStashed();
            setProductStashed(null);
            return product;
        }
        throw new Exception("Item not selling or not enough money on deposit");
    }
}