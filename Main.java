import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Введите пол (f , m ): ");
            String gen = sc.nextLine();
            String gender;
            if (gen.equals("f") | gen.equals("m")) {
                if (gen.equals("f")) {
                    gender = "женский";
                } else {
                    gender = "мужской";
                }
            } else {
                throw new RuntimeException("Введите f или m!");
            }
            System.out.print("Введите ФИО (Пример: Фамилия,Имя,Отчество): ");
            String str = sc.next();
            String[] words = str.split(","); String surname = words[0]; String name = words[1];
            String patronymic = words[2];
            System.out.print("Введите дату рождения (Пример: dd,mm,yyyy): ");
            String birthday = sc.next();
            String[] birth = birthday.split(",");
            int day = Integer.parseInt(birth[0]); int mount = Integer.parseInt(birth[1]);
            int year = Integer.parseInt(birth[2]);
            if (day > 31 | mount > 12 | day == 0 | mount == 0 | year > 2024) {
                throw new RuntimeException("Неверно введена дата!");
            }
            System.out.print("Введите номер телефона(Привер: 123456789, без букв и других символов!): ");
            int phone = sc.nextInt();
            Human human = new Human(name, surname, patronymic, day, mount, year, phone, gender);
            fileHandler(human);
        } catch (Exception e) {
            System.out.println("Ошибка! Внимательно прочитайте примеры ввода и попробуйте ещё раз! " + e.getMessage());
            start();
        }

    }

    public static void fileHandler(Human human) throws IOException {
        String format = ".txt";
        String fileName = human.getSurname();
        String path = "C:\\" + fileName + format;
        FileWriter fw = new FileWriter(path, true);
        fw.write(human.toString());
        fw.close();

    }
}

class Human implements Iterable<Human>{
    private String name, surname, patronymic, gender;
    private int phone, day, mount, year;
    private List<Human> list = new ArrayList<>();

    public Human(String name, String surname, String patronymic, int day, int mount, int year, int phone, String gender) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.day = day;
        this.mount = mount;
        this.year = year;
        this.phone = phone;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return  "Фамилия = " + surname +
                ", Имя = " + name +
                ", Отчество = " + patronymic +
                ", Дата рождения = день: " + day +
                ", месяц: " + mount + ", год: " + year +
                ", Телефон = " + phone +
                ", Пол = " + gender + ".\n";
    }

    @Override
    public Iterator<Human> iterator() {
        return list.iterator();
    }

    public String getSurname() {
        return surname;
    }
}
