
import java.time.LocalDate;




public class Notebook {
    
    private Integer id;
    public String modelName;
    public Factory Manufacturer;
    public OS operatingSystem;
    public Color color;
    public CPU CPU;
    public GPU GPU;
    public HDD_type HDD;
    public LocalDate Manufactured;
    public int MemorySize;
    public int  HDD1Size;
    public int HDD2Size;
    public double price;
  


  
    public void setid(Integer ID) {
        id = ID ;
    }
    public Integer getid() {
        return id;
    }
    
    public Notebook(Integer id, String modelName, Factory manufacturer, OS operatingSystem, Color color, CPU cPU,
            GPU gPU, HDD_type hDD, LocalDate manufactured, int memorySize, int hDD1Size, int hDD2Size, double price) {
        this.id = id;
        this.modelName = modelName;
        Manufacturer = manufacturer;
        this.operatingSystem = operatingSystem;
        this.color = color;
        CPU = cPU;
        GPU = gPU;
        HDD = hDD;
        Manufactured = manufactured;
        MemorySize = memorySize;
        HDD1Size = hDD1Size;
        HDD2Size = hDD2Size;
        this.price = price;
    }

}

