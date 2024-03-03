public class SmartColorLamp extends SmartLamp{


    /**
     * Color is a String that can is a hexadecimal number.
     */
   private String color;


    /**
     * Color mode can be switched by setColor Functions
     */
   private boolean colorMode = false;

    /**
     * Simple constructor of SmartColorLamp. It calls SmartDevices constructor.
     * @param name name of the device
     */
    public SmartColorLamp(String name) {
        super(name);
    }


     /**
     * Constructor of Smart Color Lamp with 3 param. It calls SmartLamps constructor.
     * @param name  name of the device.
     * @param status Indicates switched off or on.
     */
    public SmartColorLamp(String name, String status) {
        super(name, status);
    }

    /**
     * Constructor of Smart Color Lamp with 4 param. It calls SmartLamps constructor.
     * @param name  name of the device.
     * @param status Indicates switched off or on.
     * @param kelvin kelvin value of lamp
     * @param brightness brightness of lamp.
     */

    public SmartColorLamp(String name, String status, int kelvin, int brightness){
        super(name, status, kelvin, brightness);
    }


    /**
     * Constructor of Smart Color Lamp with 4 param. It calls SmartLamps constructor. Activates color mode.
     * @param name  name of the device.
     * @param status Indicates switched off or on.
     * @param color colorCode of the device
     * @param brightness brightness of lamp.
     */
    public SmartColorLamp(String name, String status, String color, int brightness) throws Exception {
        super(name, status);
        setBrightness(brightness);
        this.color = color;
        colorMode = true;
    }

    /**
     * Overrides setKelvin method. After that turns off color mode.
     */
    @Override
    public void setKelvin(int kelvin) throws Exception {
        super.setKelvin(kelvin);
        colorMode = false;
    }

    /**
     * Overrides setBrightness method. After that turns off color mode.
     */
    @Override
    public void setBrightness(int brightness) throws Exception{
        super.setBrightness(brightness);
        colorMode = false;
    }

    /**
     * Sets color and brightness of the device checks if the color code is correct and activates color mode.
     * @param color color value.
     * @param brightness brightness value
     * @throws Exception if the values are invalid.
     */
    public void setColor(String color,int brightness) throws Exception {
        DeviceController.colorCheck(color);
        setBrightness(brightness);
        setColorCode(color);
        colorMode = true;
    }

    /**
     * Sets color of the device checks if the color code is correct and activates color mode.
     * @param color color value.
     * @throws Exception if the values are invalid.
     */
    public void setColorCode(String color) throws Exception {
        DeviceController.colorCheck(color);
        this.color = color;
        colorMode =true;
    }

    /**
     * It overrides setWhite method. It sets kelvin and brightness value and sets color mode to false
     * @param kelvin kelvin value
     * @param brightness brightness value
     * @throws Exception if the values are invalid.
     */
    @Override
    public void setWhite(int kelvin,int brightness) throws Exception {
        super.setWhite(kelvin, brightness);
        colorMode = false;
    }

    /**
     * ToString method of the SmartColorLamp
     * @return information
     */
    public String toString(){
        if(!colorMode){
            return String.format("Smart Color Lamp %s is %s and its color value is %dK with %d%% brightness", getName(), getStatus(), getKelvin(), getBrightness())+ String.format((", and its time to switch its status is %s.\n") ,formatter(getSwitchTime())); }
        else{
            return String.format("Smart Color Lamp %s is %s and its color value is %s with %d%% brightness", getName(), getStatus(), color, getBrightness())+ String.format((", and its time to switch its status is %s.\n") , formatter(getSwitchTime()));}
    }
}


