public class SmartLamp extends SmartDevices{

    /**
     * Kelvin value of SmartLamp. Default value is 4000K.
     */
    private int kelvin = 4000;

    /**
     * Brightness of lamp. Takes between 0-100%
     */
    private int brightness = 100;


    /**
     * Constructor of SmartLamp. Calls SmartDevices constructor.
     * @param name name of the lamp
     */
    public SmartLamp(String name){
        super(name);

    }

    /**
     * Constructor of SmartLamp with 2 parameter . Sets switch status.
     * @param name name of the lamp
     * @param status switch status of lamp. Can be on or off.
     */
    public SmartLamp(String name,String status){
        super(name, status);

    }

    /**
     * Constructor of SmartLamp with 3 parameter . Sets status,kelvin and brightness value.
     * @param name name of the lamp
     * @param status  switch status of lamp. Can be on or off.
     * @param kelvin kelvin value of lamp
     * @param brightness brightness value of lamp
     */
    public SmartLamp(String name,String status,int kelvin, int brightness){
            super(name, status);
            this.kelvin = kelvin;
            this.brightness = brightness;


    }

    /**
     * Sets kelvin value of lamp. Method checks if kelvin value is valid or not. Otherwise throws error.
     * @param kelvin kelvin value of lamp.
     * @throws Exception DeviceException
     */
    public void setKelvin(int kelvin) throws Exception {
        DeviceController.kelvinCheck(kelvin);
        this.kelvin = kelvin;
    }

    /**
     * Getter method of kelvin.
     * @return kelvin value.
     */
    public int getKelvin() {
        return kelvin;
    }

    /**
     * Getter method of  brightness.
     * @return kelvin value.
     */

    public int getBrightness() {
        return brightness;
    }


    /**
     * Setter method of brightness. It checks brightness value if it is valid or not.
     * @param brightness brightness value.
     * @throws Exception DeviceException
     */
    public void setBrightness(int brightness) throws Exception {
        DeviceController.brightnessCheck(brightness);
        this.brightness = brightness;
    }

    /**
     * It overrides setWhite method. It sets kelvin and brightness value.
     * @param kelvin kelvin value
     * @param brightness brightness value
     * @throws Exception if the values are invalid.
     */
    @Override
    public void setWhite(int kelvin,int brightness) throws Exception {
        DeviceController.kelvinCheck(kelvin); // checks first if it is invalid or not.
        DeviceController.brightnessCheck(brightness);
        setKelvin(kelvin);
        setBrightness(brightness);
    }

    /**
     * toString method of SmartLamp.
     * @return information.
     */
    @Override
    public String toString(){
        return String.format("Smart Lamp %s is %s and its kelvin value is %dK with %d%% brightness", getName(), getStatus(), kelvin, brightness) + super.toString();
    }
}
