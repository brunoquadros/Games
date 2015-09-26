package br.com.ceduphh.games;

/**
 * Created by Francisco on 29/09/2014.
 */
public class Game extends Entity {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Game{ name='" + name + "' , age=" + age+"}";
    }
}
