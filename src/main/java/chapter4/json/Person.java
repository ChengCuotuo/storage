package chapter4.json;

import java.util.List;

/**
 * @Auth: chunlei.wang
 * @Date: 2019/09/09
 * @Desc:  实体类
 */
public class Person {

    private String name;
    private int age;
    private List<Integer> scores;

    public Person() {
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

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

    public List<Integer> getScores() {
        return scores;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }
}
