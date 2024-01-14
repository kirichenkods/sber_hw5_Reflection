import Proxy.Calculator;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {
    public static void main(String[] args) {
        //1 Имплементировать следующий интерфейс в классе CalculatorImpl
//        Calculator calculator = new CalculatorImpl();
//        System.out.println(calculator.calc(5)); //120

        //2 Вывести на консоль все методы класса, включая все родительские методы
        // (включая приватные)
        Class<Employee> employeeClass = Employee.class; //получаем ссылку на класс
        printDeclaredMethods(employeeClass);



        //3 Вывести все геттеры класса
        Class<Person> personClass = Person.class;
        printGetters(personClass);

        //4 Проверить что все String константы имеют значение = их имени
        // public static final String MONDAY = "MONDAY";
        Class<Week> weekClass = Week.class;

        try {
            System.out.println(isConstNameEqualsValue(weekClass));
        } catch (IllegalAccessException e) {
            System.err.println("Не удалось получить значение для поля" +
                    "" + e.getMessage());
        }

    }

    public static void printDeclaredMethods(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods){
            System.out.println(method.getName());
        }
        if (clazz.getSuperclass() != null) {
            printDeclaredMethods(clazz.getSuperclass());
        }
    }

    public static void printGetters(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("get") || method.getName().startsWith("is")) {
                System.out.println(method.getName());
            }
        }
    }

    public static boolean isConstNameEqualsValue(Class<?> clazz) throws IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if ( !(field.getType().equals(String.class) &&
                    Modifier.isFinal(field.getModifiers()) &&
                    field.getName() == field.get(null)) ) {
                return false;
            }
        }
        return true;
    }

}