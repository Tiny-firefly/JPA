package cn.fbd.demo.reflectDemo.domain;

public class Person {
    private  String name;
    private Integer age;
    public  String a;


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

    public void test(){
        String a = this.name;
    }

    public void eat(){
        System.out.println("eat.....");
    }
    public void  eat(String foodType){
        System.out.println("eat: "+foodType);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", a='" + a + '\'' +
                '}';
    }

    public Person(String name, Integer age, String a) {
        this.name = name;
        this.age = age;
        this.a = a;
    }

    public Person(){}
}
