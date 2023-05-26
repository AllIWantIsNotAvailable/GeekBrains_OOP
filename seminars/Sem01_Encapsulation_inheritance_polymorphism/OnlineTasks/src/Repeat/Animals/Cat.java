package Repeat.Animals;

public class Cat extends Animal{
    private String name;
    private Integer age;

    public Cat(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public void voice() {
        System.out.println("I'm Cat");
    }

    @Override
    public String toString() {
        return "Classes.Animals.Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
