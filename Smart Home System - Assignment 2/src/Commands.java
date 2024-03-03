import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Commands {


    /**
     * Parameter will be used for formatting LocalDateTime.
     */
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("y-M-d_H:m:s");

    /**
     * Parameter will be used for outputs.
     */
    public static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");






    /**
     * This function looks for the command and passes an array to the relevant function.
     * Main reason of this function is exception handling. Other functions in this class throws exceptions with messages.
     * This function handles exceptions and write outputs according to the exception.
     * @param command lines of the input. After splitting it with \t it will be used for commands.
     * @throws Exception if the program has to be terminated only if SetInitialTime is missing or wrong.
     */
    public static void command(String command) throws Exception {
        String[] splitCommands = command.trim().split("\t");   // Split commands
    try {
        switch (splitCommands[0]) {
            case "Add":
                add(splitCommands);
                break;
            case "Remove":
                remove(splitCommands);
                break;
            case "Nop":
                nop();
                break;
            case "SkipMinutes":
                skipMinutes(splitCommands);
                break;
            case "SetTime":
                commandsSetTime(splitCommands);
                break;
            case "SetSwitchTime":
                commandsSetSwitchTime(splitCommands);
                break;
            case "Switch":
                commandsSwitch(splitCommands);
                break;
            case "ChangeName":
                commandsChangeName(splitCommands);
                break;
            case "PlugIn":
                commandsPlugIn(splitCommands);
                break;
            case "PlugOut":
                commandsPlugOut(splitCommands);
                break;
            case "SetKelvin":
                commandsSetKelvin(splitCommands);
                break;
            case "SetBrightness":
                commandsSetBrightness(splitCommands);
                break;
            case "SetColorCode":
                commandsSetColorCode(splitCommands);
                break;
            case "SetWhite":
                commandsSetWhite(splitCommands);
                break;
            case "SetColor":
                commandsSetColor(splitCommands);
                break;

            case "ZReport":
                DeviceController.zReport();
                break;

            default:
                DeviceController.output += "ERROR: Erroneous command!\n";  // Default value means the line lacks command.

        }
    }

        catch (IndexOutOfBoundsException e ){
            DeviceController.output += ("ERROR: Erroneous command!\n");  // IndexOutOfBoundsException occurs when the command is wrong.
        }
        catch (DeviceExceptions e){             //  DeviceException is a custom exception which is thrown for particular messages.
        DeviceController.output += e.getMessage();
    }

        catch (Exception e ){   //  Other exceptions with no particular exceptions gives these error messages.
             DeviceController.output += ("ERROR: Erroneous command!\n");}
            }



    /**
     * This function calls objects function after some operations
     * @param splitCommands which includes name and ampere
     * @throws Exception can give because setPluggedIn exceptions. IndexOutOfBoundsException can also occur.
     */
    private static void commandsPlugIn(String[] splitCommands) throws Exception {
        if(splitCommands.length > 3){throw new Exception();}
        int deviceNo =   DeviceController.searchName(splitCommands[1]);
        double ampere = Double.parseDouble(splitCommands[2]);
        DeviceController.smartDevices.get(deviceNo).setPluggedIn(true,ampere);
    }



    /**
     * The same function as PlugIn but with no ampere values.
     * @param splitCommands which includes name.
     * @throws Exception IndexOutOfBoundsException can  occur.
     */
    private static void commandsPlugOut(String[] splitCommands) throws Exception {
        if(splitCommands.length > 2){throw new Exception();}
        int deviceNo =   DeviceController.searchName(splitCommands[1]);
        DeviceController.smartDevices.get(deviceNo).setPluggedIn(false,0);


    }


    /**
     * This function calls objects function after some operations
     * @param splitCommands which includes name and kelvin
     * @throws Exception can give because setKelvin exceptions(Kelvin value must be between 2000-6500 ). IndexOutOfBoundsException can also occur.
     */
    private static void commandsSetKelvin(String[] splitCommands) throws Exception {
        if(splitCommands.length > 3){throw new Exception();}
        int deviceNo =   DeviceController.searchName(splitCommands[1]);
        int kelvin =  Integer.parseInt(splitCommands[2]);
        DeviceController.smartDevices.get(deviceNo).setKelvin(kelvin);

    }



    /**
     * This function calls objects function after some operations
     * @param splitCommands which includes name and brightness
     * @throws Exception can give because setBrightness exceptions(Brightness is an integer and between 0-100). IndexOutOfBoundsException can also occur.
     */
    private static void commandsSetBrightness(String[] splitCommands) throws Exception {
        if(splitCommands.length > 3){throw new Exception();}
        int deviceNo =   DeviceController.searchName(splitCommands[1]);
        int brightness =  Integer.parseInt(splitCommands[2]);
        DeviceController.smartDevices.get(deviceNo).setBrightness(brightness);

            }

    /**
     * This function calls objects function after some operations
     * @param splitCommands which includes name and colorCode
     * @throws Exception can give because setColorCode exceptions such as wrong hexadecimal number. IndexOutOfBoundsException can also occur.
     */
    private static void commandsSetColorCode(String[] splitCommands) throws Exception {
        if(splitCommands.length > 3){throw new Exception();}
        int deviceNo =   DeviceController.searchName(splitCommands[1]);
        String color = splitCommands[2];
        DeviceController.smartDevices.get(deviceNo).setColorCode(color);
        }



    /**
     * This function calls objects function after some operations
     * @param splitCommands which includes name, kelvin and brightness values.
     * @throws Exception can give because setWhite exceptions such as wrong kelvin and brightness value. IndexOutOfBoundsException can also occur.
     */

    private static void commandsSetWhite(String[] splitCommands) throws Exception {
        if(splitCommands.length > 4){throw new Exception();}
         int deviceNo =   DeviceController.searchName(splitCommands[1]);
         int kelvin = Integer.parseInt(splitCommands[2]);
         int brightness = Integer.parseInt(splitCommands[3]);
         DeviceController.smartDevices.get(deviceNo).setWhite( kelvin, brightness);


        }

        /**
     * This function calls objects function after some operations
     * @param splitCommands which includes name, colorCode and brightness
     * @throws Exception can give because setColor exceptions such as wrong color and brightness value. IndexOutOfBoundsException can also occur.
     */

    private static void commandsSetColor(String[] splitCommands) throws Exception {
        if(splitCommands.length > 4){throw new Exception();}
        int deviceNo =   DeviceController.searchName(splitCommands[1]);
        String color = splitCommands[2];
        int brightness = Integer.parseInt(splitCommands[3]);
        DeviceController.smartDevices.get(deviceNo).setColor(color,brightness);


    }


    /**
     * This function calls objects function after some operations
     * @param splitCommands which includes 2 names(old and new name).
     * @throws Exception can give because  exceptions such as Same name, both names are same. IndexOutOfBoundsException can also occur.
     */

    private static void commandsChangeName(String[] splitCommands) throws Exception {
        if(splitCommands.length > 3){throw new Exception();}
        int secondDevice = DeviceController.searchName(splitCommands[2]);
        if (splitCommands[1].equals(splitCommands[2])) {
            throw new DeviceExceptions("ERROR: Both of the names are the same, nothing changed!\n");}
        if(secondDevice != -1){
            throw new DeviceExceptions("ERROR: There is already a smart device with same name!\n");}

        int deviceNo = DeviceController.searchName(splitCommands[1]);
        DeviceController.smartDevices.get(deviceNo).setName(splitCommands[2]);


    }


    /**
     * This function calls objects function after some operations
     * @param splitCommands which includes name and status(switched or not)
     * @throws Exception can give because setStatusCheck and setSwitch exceptions such as wrong input,  already switched.IndexOutOfBoundsException can also occur.
     */

    private static void commandsSwitch(String[] splitCommands) throws Exception {
        if(splitCommands.length > 3){throw new Exception();}
        int deviceNo = DeviceController.searchName(splitCommands[1]);
        DeviceController.statusCheck(splitCommands[2]);
        try{
            DeviceController.smartDevices.get(deviceNo).setSwitch(splitCommands[2]);}
        catch (ArrayIndexOutOfBoundsException e ){
            throw new DeviceExceptions("ERROR: There is not such a device!\n");}
    }




    /**
     * This function calls objects function after some operations.
     * After setting switch time List of the objects should be sorted.
     * @param splitCommands which includes name and status(switched or not)
     */
    private static void commandsSetSwitchTime(String[] splitCommands) throws Exception {
                if(splitCommands.length > 3){throw new Exception();}
                int deviceNo = DeviceController.searchName(splitCommands[1]);
            try {
                LocalDateTime dateTime = LocalDateTime.parse(splitCommands[2], formatter);

                if(dateTime.isBefore(DeviceController.time)){throw new DeviceExceptions("ERROR: Switch time cannot be in the past!\n");}
                else if (dateTime.isEqual(DeviceController.time)) {
                    DeviceController.smartDevices.get(deviceNo).setSwitchTime(DeviceController.time);
                    DeviceController.sortDevices();
                    DeviceController.operations(dateTime);
                }
                
                else {DeviceController.smartDevices.get(deviceNo).setSwitchTime(dateTime);}
                DeviceController.sortDevices();

            }
            catch ( DateTimeException e){DeviceController.output +=  "ERROR: Time format is not correct!\n";}
    }

    /**
     * Nop function set time to the next switchDate (Which is the first element of the list if it exists).
     * If first device has no switch time (it means others also do not have switch dates) it prints error.
     * Operations function makes operations according to the time which will be the actual time after the operations.
     */
    private static void nop() {

        if(DeviceController.smartDevices.size() == 0 || DeviceController.smartDevices.get(0).getSwitchTime() == null ){
            DeviceController.output +="ERROR: There is nothing to switch!\n";}
         else{
            LocalDateTime newTime = DeviceController.smartDevices.get(0).getSwitchTime();
            DeviceController.operations(newTime);}
        }
    /**
     * Sets the given time as the current time.
     * This function also makes operations because of the switch times of the devices.
     * @param splitCommands which includes time.
     * @throws Exception can give DeviceExceptions.
     */
    private static void commandsSetTime(String[] splitCommands) throws Exception{
        try {
            if(splitCommands.length > 2){throw new Exception();}
            LocalDateTime dateTime = LocalDateTime.parse((splitCommands[1]), formatter);
            if(dateTime.isBefore(DeviceController.time)){throw new DeviceExceptions("ERROR: Time cannot be reversed!\n");}
            if(dateTime.isEqual(DeviceController.time)){throw new DeviceExceptions("ERROR: There is nothing to change!\n");}
            LocalDateTime newTime = LocalDateTime.parse((splitCommands[1]), formatter);
            DeviceController.operations(newTime);
        }catch (DateTimeException e ){
            DeviceController.output +=  "ERROR: Time format is not correct!\n";
        }



        }

    /**
     * Add minutes to the current time.
     * This function also makes operations because of the switch times of the devices.
     * @param splitCommands which includes minutes.
     * @throws Exception can give DeviceExceptions.(Wrong inputs, reverse time or value 0)
     */

    private static void skipMinutes(String[] splitCommands) throws Exception {
        if(splitCommands.length > 2){throw new Exception();}
        int minutes = Integer.parseInt(splitCommands[1]);
        if(minutes < 0){throw new DeviceExceptions("ERROR: Time cannot be reversed!\n");}
        else if(minutes == 0){throw new DeviceExceptions("ERROR: There is nothing to skip!\n");}
        LocalDateTime newTime =  DeviceController.time.plusMinutes(Integer.parseInt(splitCommands[1]));
        DeviceController.operations(newTime);



    }




    /**
     * Removes given device.
     * This function switches off the device if it is not switched off.
     * Gives information about the removed device.
     * @param splitCommands which includes name.
     */

    private static void remove(String[] splitCommands){
        try {
            if(splitCommands.length > 2){throw new Exception();}
            String name = splitCommands[1];
            SmartDevices smartDevice =  DeviceController.smartDevices.get(DeviceController.searchName(name));
            if(smartDevice.getStatus().equals("on")){smartDevice.setSwitch("Off");}
            String information = smartDevice.toString();
            DeviceController.smartDevices.remove(smartDevice);
            DeviceController.output += "SUCCESS: Information about removed smart device is as follows:\n" + information;
        }catch (Exception e){
            DeviceController.output +=  "ERROR: There is nothing to remove!\n";} // There is no information about this error ,so it is implemented as this.


        }



    /**
     * Add devices to the SmartDevices list. Before calling the constructors, function makes sure that every value satisfies the conditions.
     * These conditions are checked by DeviceControllers check Functions.
     * @param splitCommands which includes name.
     * @throws Exception DeviceExceptions which occur due to DeviceControls check Functions.
     * IndexOutOfBoundsException can also occur.
     */

            public static void add(String[] splitCommands) throws Exception {


                String name  =  splitCommands[2];


                switch (splitCommands[1]) {
                    case "SmartCamera":
                        double megabytes = Double.parseDouble(splitCommands[3]);
                        if(DeviceController.searchName(name) != -1){throw new DeviceExceptions("ERROR: There is already a smart device with same name!\n");}
                        DeviceController.ampereAndMegabyteCheck(megabytes,"Megabyte");
                        if (splitCommands.length == 5) {
                            DeviceController.statusCheck(splitCommands[4]);
                            DeviceController.smartDevices.add(new SmartCamera(name, megabytes, splitCommands[4]));
                        } else if (splitCommands.length == 4) {
                            DeviceController.smartDevices.add(new SmartCamera(name, megabytes));

                        } else {
                            throw new Exception();  // Other lengths of input is wrong so gives error.
                        }
                        break;


                    case "SmartPlug":

                        if (splitCommands.length == 5) {
                            // Checking parts
                            double ampere = Double.parseDouble(splitCommands[4]);
                            String status =    splitCommands[3];
                            if(DeviceController.searchName(name) != -1){throw new DeviceExceptions("ERROR: There is already a smart device with same name!\n");}
                            DeviceController.statusCheck(status); DeviceController.ampereAndMegabyteCheck(ampere,"Ampere");

                            DeviceController.smartDevices.add(new SmartPlug(name, status, ampere));
                        } else if (splitCommands.length == 4) {
                            String status =    splitCommands[3];
                            if(DeviceController.searchName(name) != -1){throw new DeviceExceptions("ERROR: There is already a smart device with same name!\n");}
                            // Checking parts
                            DeviceController.statusCheck(status);

                            DeviceController.smartDevices.add(new SmartPlug(name, status));
                        } else if (splitCommands.length == 3) {
                            if(DeviceController.searchName(name) != -1){throw new DeviceExceptions("ERROR: There is already a smart device with same name!\n");}
                            DeviceController.smartDevices.add(new SmartPlug(name));
                        } else {
                            throw new Exception(); // Other lengths of input is wrong so gives error.
                        }
                        break;

                    case "SmartLamp":
                        if(DeviceController.searchName(name) != -1){throw new DeviceExceptions("ERROR: There is already a smart device with same name!\n");}
                        if (splitCommands.length == 3) {
                            DeviceController.smartDevices.add(new SmartLamp(name));}
                         else{
                             String status = splitCommands[3];
                             DeviceController.statusCheck(status);

                             if (splitCommands.length == 6) {
                                 // Checking parts
                                 int kelvin =   Integer.parseInt(splitCommands[4]); int brightness = Integer.parseInt(splitCommands[5]);
                                 DeviceController.kelvinCheck(kelvin);
                                 DeviceController.brightnessCheck(brightness);

                                 DeviceController.smartDevices.add(new SmartLamp(name, status, kelvin,brightness));
                        } else if (splitCommands.length == 4) {
                            DeviceController.smartDevices.add(new SmartLamp(name, status));
                        } else {
                            throw new Exception(); // Other lengths of input is wrong so gives error.
                        }
                         }
                        break;

                    case "SmartColorLamp":
                        if(DeviceController.searchName(name) != -1){throw new DeviceExceptions("ERROR: There is already a smart device with same name!\n");}
                        if (splitCommands.length == 3) {
                        DeviceController.smartDevices.add(new SmartColorLamp(name));}
                        else {
                            String status = splitCommands[3];
                            DeviceController.statusCheck(status);

                            if (splitCommands.length == 6) {
                                try {
                                    // Checking parts
                                    int brightness = Integer.parseInt(splitCommands[5]);
                                    DeviceController.brightnessCheck(brightness);
                                    int kelvin =  Integer.parseInt(splitCommands[4]);
                                    DeviceController.kelvinCheck(kelvin);

                                    DeviceController.smartDevices.add(new SmartColorLamp(name, status, kelvin, brightness));
                                } catch (NumberFormatException e) {
                                    // Checking parts
                                    int brightness = Integer.parseInt(splitCommands[5]);
                                    DeviceController.brightnessCheck(brightness);
                                    DeviceController.colorCheck(splitCommands[4]);

                                    DeviceController.smartDevices.add(new SmartColorLamp(name, status, splitCommands[4], brightness));
                                }
                            } else if (splitCommands.length == 4) {
                                DeviceController.smartDevices.add(new SmartColorLamp(name, status));
                            } else {
                                throw new Exception(); // Other lengths of input is wrong so gives error.
                            }
                        }
                        break;

                }
                DeviceController.sortDevices(); // It should sort devices after adding.


        }


    /**
     * Sets Initial Time. This command should be the first line of the input.txt.
     * Initial Time should be null in order to operate.
     * @param command which includes time and command.
     * @throws Exception can give Exceptions .If it gives errors with messages program terminates and others are handled by this function.
     */

    public static void setInitialTime(String command) throws Exception {

        try{
                DeviceController.output += String.format(("COMMAND: %s\n"), command);
                String[] splitCommands = command.split("\t");   // Split commands
                if(splitCommands.length > 2){throw new IndexOutOfBoundsException();}
                DeviceController.time = LocalDateTime.parse((splitCommands[1]), formatter);
                DeviceController.output += String.format(("SUCCESS: Time has been set to %s!\n"), DeviceController.time.format(outputFormatter));

        } catch (DateTimeException e){
            throw new Exception("ERROR: Format of the initial date is wrong! Program is going to terminate!\n"); }
        catch (IndexOutOfBoundsException e){
            throw new Exception("ERROR: First command must be set initial time! Program is going to terminate!\n");}
    }

}












