 //interface DeviceFeatures
interface DeviceFeatures {
    default void turnOn() {
        System.out.println("Device is turning on");
    }
        default void turnOff(){ 
            
            System.out.println("Device is turning off");

        }
        
        

    
}
//abstract class 1

abstract class PortableDevice implements DeviceFeatures {
    int batteryLife; 

    PortableDevice(int batteryLife) {
        this.batteryLife = batteryLife;
    }

    public void getBatteryInfo() {
        System.out.println("Battery Life: " + batteryLife + " hours");
    }
}

//abstract class 2
abstract class ComputingDevice implements DeviceFeatures {
    String processorType;

    ComputingDevice(String processorType) {
        this.processorType = processorType;
    }

    public void getProcessorInfo() {
        System.out.println("Processor Type: " + processorType);
    }
}
//Derived classes

// Tablet class 
class Tablet extends PortableDevice implements DeviceFeatures {
    boolean hasStylus;


    Tablet(int batteryLife, boolean hasStylus) {
        super(batteryLife);
        this.hasStylus = hasStylus;
    }

    public void getStylusInfo() {    
        System.out.println("Has Stylus: " + hasStylus);
    }

   
    public void turnOn() {
        System.out.println("Tablet is turning on...");
    }
}
//Mobile class
class Mobile extends PortableDevice {
    boolean is5GEnabled;

    Mobile(int batteryLife, boolean is5GEnabled) {
        super(batteryLife);
        this.is5GEnabled = is5GEnabled;
    }

    public void get5GInfo() {
        System.out.println("5G Enabled: " + is5GEnabled);
    }
}

//Laptop class
class Laptop extends ComputingDevice {
    int screenSize; 

    Laptop(String processorType, int screenSize) {
        super(processorType);
        this.screenSize = screenSize;
    }

    public void getScreenSizeInfo() {
        System.out.println("Screen Size: " + screenSize + " inches");
    }
}
//main method to demonstrate the system
public class DeviceHierarchySystem {
    public static void main(String[] args) {
        
        Tablet tablet = new Tablet(10, true);
        tablet.turnOn();

        tablet.getBatteryInfo();

        tablet.getStylusInfo();
        tablet.turnOff();

        System.out.println();

       
        
    
        
    }
}
















































