import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class DeviceController {

    /**
     * The list contains all devices.
     */
    public static List<SmartDevices> smartDevices = new ArrayList<>();

    /**
     * Current time.
     */
    public static LocalDateTime time;

    /**
     * Output which will be printed at the end of the program.
     */
    public static String output = "";




    /**
     * Sorts smartDevices list according to ascending switch times.
     */
    public static void sortDevices(){
        Collections.sort(smartDevices,(devices1, device2) -> devices1.getLocalDateTime().compareTo(device2.getLocalDateTime()));

    }


    /**
     * This function operates all the switch operations when time changes.
     * If two devices have same switch time it does it in the same time. Function sorts it afterwards.
     * Then time is changed to the next switch value if it is before newTime.
     * when time equals newTime it operates last time and breaks.
     * @param newTime the amount of incoming damage*
     */
    public static void operations(LocalDateTime newTime){
    try{
        if(newTime.isAfter(smartDevices.get(0).getSwitchTime()) || newTime.isEqual(smartDevices.get(0).getSwitchTime())){
            time = smartDevices.get(0).getSwitchTime(); }
       do{
            for(SmartDevices smartDevice : smartDevices){
                if(smartDevice.getLocalDateTime().equals(time)){
                String status = (smartDevice.getStatus().equals("off")) ? "On" : "Off";
                smartDevice.setSwitch(status);
                smartDevice.setSwitchTime(null);}
            }
            sortDevices();  // Sorting after operations.
            if(newTime.isAfter(smartDevices.get(0).getLocalDateTime()) || newTime.isEqual(smartDevices.get(0).getLocalDateTime())){
                time = smartDevices.get(0).getLocalDateTime();
            }
            else{break;}
        }while (newTime.isAfter(time) || newTime.isEqual(time));
        time = newTime;

    }catch (Exception e){time = newTime;} // If first occurrence is null.
    }

    /**
     * zReport function. Gives information about all devices.
     */
    public static void zReport(){
        output += "Time is:\t" + DeviceController.time.format(Commands.outputFormatter) + "\n";
        for(SmartDevices smartDevice : smartDevices){
            output += smartDevice.toString();
        }
    }



    /**
     * This function gives the index of device with param name.
     * @param name name of the device.
     * @return index of the device.
     */
    public static int searchName(String name){

        for (int i = 0; i < smartDevices.size(); i++) {
            if(smartDevices.get(i).getName().equals(name)){return i;}

        }
        return -1;  //Return -1 if it is not found. Which means it does not exist. Gives IndexOutOfBoundsException.
    }

    /**
     * Checks for the value whether valid or not.
     * @param brightness which must be int and between 0-100(included).
     * @throws DeviceExceptions if it does not satisfy
     */
    public static void brightnessCheck(int brightness) throws DeviceExceptions {
        if(brightness < 0 || brightness > 100 ){throw new DeviceExceptions("ERROR: Brightness must be in range of 0%-100%!\n");}
    }

    /**
     * Checks for the value whether valid or not
     * @param kelvin which must be int value between 2000-6500
     * @throws DeviceExceptions if it does not satisfy
     */
    public static void kelvinCheck(int kelvin) throws DeviceExceptions {
        if(kelvin < 2000 || kelvin  > 6500 ){throw new DeviceExceptions("ERROR: Kelvin value must be in range of 2000K-6500K!\n");}
    }

    /**
     * Checks for the value whether valid or not
     * @param status String must be "On" or "Off"
     * @throws Exception if it does not satisfy
     */
    public static void statusCheck(String status) throws Exception {
        if(!(status.equals("On") || status.equals("Off"))){throw new Exception() ; } //Format error due to wrong command
    }

    /**
     * Checks for the value whether valid or not
     * @param color Color value must be decoded and should be between 0x0 and 0xFFFFFF
     * @throws Exception if it does not satisfy
     */
    public static void colorCheck(String color) throws Exception {
        try{
            if(Integer.decode(color) > 16777215 || Integer.decode(color) < 0){throw new DeviceExceptions("ERROR: Color code value must be in range of 0x0-0xFFFFFF!\n");}
        }catch (NumberFormatException e ){
                throw new Exception();  //Format error due to wrong command
        }
    }

    /**
     * Checks for the value whether valid or not
     * @param value these values must be positive
     * @param quantity String quantity takes values "Ampere" or "Megabyte". it gives information about quantity.
     * @throws DeviceExceptions if it does not satisfy
     */
    public static void ampereAndMegabyteCheck(double value,String quantity) throws DeviceExceptions {
        if(value <= 0){throw new DeviceExceptions(String.format("ERROR: %s value must be a positive number!\n",quantity));}

    }






}
