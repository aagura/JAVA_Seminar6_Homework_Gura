import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class shop {

public ArrayList <Notebook> baza;

DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

public static <T extends Enum<T>> T selectEnum(Class<T> enumClass, Scanner scanner) {
        System.out.println("Доступные значения:");
        T[] enumValues = enumClass.getEnumConstants();
        for (int i = 0; i < enumValues.length; i++) {
            System.out.println((i + 1) + ". " + enumValues[i]);
        }
        int choice;
        do {
            System.out.print("Выберите значение (введите номер): ");
            choice = scanner.nextInt();
            scanner.nextLine();
        } while (choice < 1 || choice > enumValues.length);
        Clrscr.clrscr();
        return enumValues[choice - 1];
    }

public void addNewGoods(Scanner scanner) throws FileNotFoundException {
    baza = readBaze();
    Integer ID = 0;
    Integer cur_id = 0;
    Integer MaxId = 0;
        if (baza.isEmpty()){
            ID = 1;
        }
        else {
            for (Notebook item : baza){
            cur_id= item.getid();
                if (cur_id> MaxId){
                MaxId =cur_id;
                }
            }
            ID =MaxId+1;
    }

    Clrscr.clrscr();
    System.out.println("Введите название модели: ");
    String modelName = scanner.nextLine();
    System.out.println("Выберите производителя");
    Factory manufacturer = selectEnum(Factory.class, scanner);
    System.out.println("Выберите операционную систему");
    OS operatingSystem = selectEnum(OS.class, scanner);
    System.out.println("Выберите цвет");
    Color color = selectEnum(Color.class, scanner);
    System.out.println("Выберите процессор");
    CPU cPU = selectEnum(CPU.class, scanner);
    System.out.println("Выберите видеокарту");
    GPU gPU = selectEnum(GPU.class, scanner);
    System.out.println("Выберите тип накопителя");
    HDD_type hDD = selectEnum(HDD_type.class, scanner);
    System.out.println("Введите дату производства dd.mm.yyyy: ");
    LocalDate manufactured = LocalDate.parse(scanner.nextLine(), formatter);
    System.out.println("Введите объем памяти: ");
    Integer memorySize = Integer.parseInt(scanner.nextLine());
    System.out.println("Введите объем ЖД 1: ");
    Integer hDD1Size = Integer.parseInt(scanner.nextLine());
    System.out.println("Введите объем ЖД 2: ");
    Integer hDD2Size = Integer.parseInt(scanner.nextLine());
    System.out.println("Введите стоимость: ");
    Double price = Double.parseDouble(scanner.nextLine());
    Notebook newNotebook = new Notebook(ID, modelName, manufacturer, operatingSystem, color, cPU,
            gPU, hDD, manufactured, memorySize, hDD1Size, hDD2Size, price);
    baza.add(newNotebook);
    try {
        FileWriter writer = new FileWriter("data.csv", true);
        writer.write(newNotebookToString(newNotebook) + System.lineSeparator());
        writer.flush();
        writer.close();
        System.out.println("Товар успешно добавлен");
    } catch (IOException e) {
        System.out.println("Ошибка при записи в файл: " + e.getMessage());
    }
}

String newNotebookToString(Notebook notebook) {
    StringBuilder sb = new StringBuilder();
    sb.append("id: ").append(notebook.getid()).append(", ");
    sb.append("modelName: ").append(notebook.modelName).append(", ");
    sb.append("Manufacturer: ").append(notebook.Manufacturer).append(", ");
    sb.append("operatingSystem: ").append(notebook.operatingSystem).append(", ");
    sb.append("color: ").append(notebook.color).append(", ");
    sb.append("CPU: ").append(notebook.CPU).append(", ");
    sb.append("GPU: ").append(notebook.GPU).append(", ");
    sb.append("HDD: ").append(notebook.HDD).append(", ");
    sb.append("Manufactured: ").append(notebook.Manufactured.format(formatter)).append(", ");
    sb.append("MemorySize: ").append(notebook.MemorySize).append(", ");
    sb.append("HDD1Size: ").append(notebook.HDD1Size).append(", ");
    sb.append("HDD2Size: ").append(notebook.HDD2Size).append(", ");
    sb.append("price: ").append(notebook.price); // Добавить символ новой строки
    return sb.toString();
}

    



public ArrayList<Notebook> readBaze() throws FileNotFoundException {
    ArrayList<Notebook> baza = new ArrayList<Notebook>();
    String absolutePath = new File("data.csv").getAbsolutePath();
    File file = new File(absolutePath);

    try (Scanner scanfile = new Scanner(file)) {
        while (scanfile.hasNextLine()) {
            String data = scanfile.nextLine();
            String[] elements = data.split(System.lineSeparator());

            for (String element : elements) {
                String[] pairs = element.split(",");
                Integer ID = 0;
                String modelName = null;
                Factory manufacturer = null;
                Color color = null;
                OS operatingSystem = null;
                GPU gPU = null;
                CPU cPU = null;
                LocalDate manufactured = null;
                HDD_type hDD = null;
                int hDD1Size = 0;
                int memorySize = 0;
                double price = 0;
                int hDD2Size = 0;

                for (String pair : pairs) {
                    String[] keyValue = pair.split(":");
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();

                    if (key.equals("id")) {
                        ID = Integer.parseInt(value);
                    } else if (key.equals("modelName")) {
                        modelName = value;
                    } else if (key.equals("operatingSystem")) {
                        operatingSystem = OS.valueOf(value);
                    } else if (key.equals("Manufacturer")) {
                        manufacturer = Factory.valueOf(value);
                    } else if (key.equals("CPU")) {
                        cPU = CPU.valueOf(value);
                    } else if (key.equals("HDD")) {
                        hDD = HDD_type.valueOf(value);
                    } else if (key.equals("GPU")) {
                        gPU = GPU.valueOf(value);
                    } else if (key.equals("MemorySize")) {
                        memorySize = Integer.parseInt(value);
                    } else if (key.equals("HDD1Size")) {
                        hDD1Size = Integer.parseInt(value);
                    } else if (key.equals("HDD2Size")) {
                        hDD2Size = Integer.parseInt(value);
                    } else if (key.equals("price")) {
                        price = Double.parseDouble(value);
                    } else if (key.equals("Manufactured")) {
                        manufactured = LocalDate.parse(value, formatter);
                    }
                }

                Notebook nextNotebook = new Notebook(ID, modelName, manufacturer, operatingSystem, color, cPU,
                        gPU, hDD, manufactured, memorySize, hDD1Size, hDD2Size, price);
                baza.add(nextNotebook);
            
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }



    return baza;
}


    
 public void chooseGood(Scanner scanner) throws FileNotFoundException {
    baza = readBaze();

    boolean exit = false;
    Factory manufacturer = null;
    OS operatingSystem = null;
    Color color = null;
    CPU cPU = null;
    GPU gPU = null;
    HDD_type hDD = null;
    Integer memorySize = null;
    Integer hDD1Size = null;
    Integer hDD2Size = null;
    Double maxPrice = null;

    while (!exit) {
        Clrscr.clrscr();
        System.out.println("Меню:");
        System.out.println("1. Выбор производителя");
        System.out.println("2. Выбор операционной системы");
        System.out.println("3. Выбор цвета");
        System.out.println("4. Выбор процессора");
        System.out.println("5. Выбор видеокарты");
        System.out.println("6. Выбор типа и объема накопителя");
        System.out.println("7. Ввод минимального объема памяти");
        System.out.println("8. Ввод максимальной стоимости");
        System.out.println("9. Показать подходящие");
        System.out.println("10. Выход");

        System.out.print("Выберите пункт меню: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                Clrscr.clrscr();
                System.out.print("Выберите производителя: ");
                manufacturer = selectEnum(Factory.class, scanner);
                break;
            case 2:
                Clrscr.clrscr();
                System.out.print("Выберите операционную систему: ");
                operatingSystem = selectEnum(OS.class, scanner);
                break;
            case 3:
                Clrscr.clrscr();
                System.out.print("Выберите цвет: ");
                color = selectEnum(Color.class, scanner);
                break;
            case 4:
                Clrscr.clrscr();
                System.out.print("Выберите процессор: ");
                cPU = selectEnum(CPU.class, scanner);
                break;
            case 5:
                Clrscr.clrscr();
                System.out.print("Выберите видеокарту: ");
                gPU = selectEnum(GPU.class, scanner);
                break;
            case 6:
                Clrscr.clrscr();
                System.out.print("Выберите тип накопителя: ");
                hDD = selectEnum(HDD_type.class, scanner);
                if (hDD == HDD_type.HDD) {
                    System.out.print("Введите минимальный объем HDD: ");
                    hDD1Size = Integer.parseInt(scanner.nextLine());
                } else if (hDD == HDD_type.SSD) {
                    System.out.print("Введите минимальный объем SSD: ");
                    hDD2Size = Integer.parseInt(scanner.nextLine());
                } else if (hDD == HDD_type.HDD_plus_SSD) {
                    System.out.print("Введите минимальный объем HDD: ");
                    hDD1Size = Integer.parseInt(scanner.nextLine());
                    System.out.print("Введите минимальный объем SDD: ");
                    hDD2Size = Integer.parseInt(scanner.nextLine());
                }
                break;
            case 7:
                Clrscr.clrscr();
                System.out.print("Введите минимальный объем памяти: ");
                memorySize = Integer.parseInt(scanner.nextLine());
                break;
            case 8:
                Clrscr.clrscr();
                System.out.print("Введите максимальную стоимость: ");
                maxPrice = Double.parseDouble(scanner.nextLine());
                break;
            case 9:
                Clrscr.clrscr();
                ArrayList<Notebook> filteredNotebooks = new ArrayList<>();
                for (Notebook notebook : baza) {
                    if ((manufacturer == null || notebook.Manufacturer == manufacturer) &&
                            (operatingSystem == null || notebook.operatingSystem == operatingSystem) &&
                            (color == null || notebook.color == color) &&
                            (cPU == null || notebook.CPU == cPU) &&
                            (gPU == null || notebook.GPU == gPU) &&
                            (hDD == null || notebook.HDD == hDD) &&
                            (memorySize == null || notebook.MemorySize >= memorySize) &&
                            (hDD1Size == null || notebook.HDD1Size >= hDD1Size) &&
                            (hDD2Size == null || notebook.HDD2Size >= hDD2Size) &&
                            (maxPrice == null || notebook.price <= maxPrice)) {
                        filteredNotebooks.add(notebook);
                    }
                }
                System.out.println("Результаты поиска:");
                for (Notebook notebook : filteredNotebooks) {
                    System.out.println(newNotebookToString(notebook));
                }
                System.out.print("Нажмите Enter");
                scanner.nextLine();
                break;
            case 10:
                exit = true;
                break;
            default:
                System.out.println("Неверный пункт меню. Попробуйте снова.");
                break;
        }
    }
}

        
        
    
}

