public class Employee extends Person {
    public Employee(String name, int age) {
        super(name, age);
    }

    public String print() {
        return getName() + " " + getAge();
    }
}
