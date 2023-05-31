import Application.Product;
import Application.VendingMachine;

import java.util.ArrayList;
import java.util.List;

public class Main {
    /*
    ### OnlineTasks ###
    Задача 1 - Абстракция:
     Реализуйте:
      - класс Товар, содержащий данные о товаре;
      - ТорговыйАвтомат, содержащий в себе методы:
        * initProducts (List <Product>) сохраняющий в себе список исходных продуктов;
        * getProduct(String name).

    Задача 2 - Инкапсуляция:
     Реализуйте:
      - конструкторы;
      - get/set методы;
      - постройте логику ТорговогоАвтомата на основе кода сделанного в предыдущем задании.

    TODO Задача 3 - Наследование:
     - Сделайте класс Товар абстрактным:
        * создайте нескольких наследников (к примеру: БутылкаВоды);
     - сделайте интерфейсом ТорговыйАвтомат:
        * реализуйте класс какого-то одного типа ТорговогоАвтомата (пример: ПродающийБутылкиВодыАвтомат).

    TODO Задача 4 - Полиморфизм:
     1) Товар:
        - переопределите метод toString для Товара;
        - запустите программу;
        - после этого переопределите для наследника этот метод;
        - после запуска обратите внимание на изменение поведения;
     2) ТорговыйАвтомат:
        - создайте перегруженный метод выдачи товара ТорговымАвтоматом дополнив дополнительным входным
     параметром (пример: getProduct(String name) выдающий товар по имени;
        - создайте метод возвращающий товар по имени и какому-либо параметру товара getProduct(String name, int volume).

    ### HomeWork ###
    TODO 1. Задачи:
     - Создать наследника реализованного класса ГорячийНапиток с дополнительным полем int температура.

    TODO 2. Задачи:
     - Создать класс ГорячихНапитковАвтомат реализующий интерфейс ТорговыйАвтомат;
     - реализовать перегруженный метод getProduct(int name, int volume, int temperature), выдающий продукт
     соответствующий имени, объёму и температуре.

    TODO 3. Задачи:
     - В main проинициализировать несколько ГорячихНапитков и ГорячихНапитковАвтомат и воспроизвести
     логику, заложенную в программе.

    TODO 4. Всё вышеуказанное создать согласно принципам ООП, пройденным на семинаре.
    
    ### Комментарии ### 
    TODO Заменить возврат пустых списков(объектов) на null.
     */
    public static void main(String[] args) {
        part1_Task1and2();
    }

    public static void part1_Task1and2() {
        List<Product> products = getProductsList();
        for (Product product:
             products) {
            System.out.println(product);
        }
        System.out.println();

        products.addAll(getProductsList());
        products.addAll(getProductsList());
        products.addAll(getProductsList());

        VendingMachine vendingMachine = new VendingMachine(products);
        for (Product product :
                products) {
            System.out.println(vendingMachine.getProduct(product.getProductName()));
        }
    }

    private static List<Product> getProductsList() {
        return new ArrayList<>() {
            {
                add(new Product("SNICKERS"));
                add(new Product("MARS"));
                add(new Product("BOUNTI"));
                add(new Product("TWIX"));
                add(new Product("БАБАЕВСКИЙ"));
                add(new Product("PICNIC"));
                add(new Product("NUTS"));
                add(new Product("KITKAT"));
                add(new Product("MILKY WAY"));
                add(new Product("MILKA"));
            }
        };
    }
}