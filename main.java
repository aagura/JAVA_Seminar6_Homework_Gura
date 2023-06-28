import java.io.IOException;
import java.util.Scanner;


// Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
// Создать множество ноутбуков.
// Написать метод, который будет запрашивать у пользователя критерий фильтрации и выведет ноутбуки, отвечающие фильтру.
// NoteBook notebook1 = new NoteBook
// NoteBook notebook2 = new NoteBook
// NoteBook notebook3 = new NoteBook
// NoteBook notebook4 = new NoteBook
// NoteBook notebook5 = new NoteBook

// Например: “Введите цифру, соответствующую необходимому критерию:
// 1 - ОЗУ
// 2 - Объем ЖД
// 3 - Операционная система
// 4 - Цвет

// Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно также в Map.

// Отфильтровать ноутбуки их первоначального множества и вывести проходящие по условиям.

// Класс сделать в отдельном файле

// приветствие
// Выбор параметра
// выбор конкретнее
// вывод подходящих
public class main {
    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" dd, MM ,yyyy", Locale.ENGLISH);
    public static void main(String[] args) throws IOException {
        Clrscr.clrscr();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Добро пожаловать в магазин ноутбуков");
            System.out.println("1. Внести поступивший ноутбук");
            System.out.println("2. Выбрать ноутбук");
            System.out.println("3. Выход");
            System.out.print("Введите ваш выбор: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            shop cShop = new shop(); 

            switch (choice) {
                case 1:
                    Clrscr.clrscr();
                    cShop.addNewGoods (scanner);
                    System.out.println("Нажмите Enter");
                    System.in.read();
                    Clrscr.clrscr();
                    break;
                case 2:
                    Clrscr.clrscr();
                    cShop.chooseGood (scanner);
                    Clrscr.clrscr();
                    break;
                case 3:
                    System.out.println("Программа завершена.");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        } while (choice != 3);
        
    }
}
