import java.time.LocalDateTime;

public abstract class SmartDevices {

    /**
     * On time value of SmartPlug and SmartCamera. On time is a LocalDateTime indicates the time plug starts to consume energy.
     */

    private LocalDateTime onTime;

    /**
     * Off time value of SmartPlug and SmartCamera. Off time is a LocalDateTime indicates the time plug stops to consume energy.
     */

    private LocalDateTime offTime;

    /**
     * Name of the device
     */

    private String name ;

    /**
     * False if the device is switched off, True if the device is switched on. Default value is false
     */
    private boolean status = false ;

    /**
     * Time when device switch its status.
     */
    private LocalDateTime switchTime = null;


    /**
     * Constructor method of SmartDevice. SmartDevices object can not be created because class is abstract.
     * @param name name of the device
     */
    public SmartDevices(String name){
        this.name = name;

    }

    /**
     * Constructor method of SmartDevice with 2 parameter. SmartDevices object can not be created because class is abstract.
     * @param name name of the device
     * @param status True if switched on, False if switched off.
     */
    public SmartDevices(String name,String status){
        this.name = name;

        if(status.equals("On")){
            this.status = true;
            onTime = DeviceController.time;
        }

    }

    /**
     * Setter method of name.
     * @param name name of the device
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method of name
     * @return name of the device
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method of setOffTime.
     * @param offTime sets onTime when Plug stops to consume energy.
     */
    public void setOffTime(LocalDateTime offTime) {
        this.offTime = offTime;
    }

    /**
     * Getter method of setOffTime.
     * @return offTime
     */
    public LocalDateTime getOffTime() {
        return offTime;
    }
    /**
     * Setter method of setOnTime.
     * @param onTime sets onTime when Plug starts to consume energy.
     */
    public void setOnTime(LocalDateTime onTime) {
        this.onTime = onTime;
    }

    /**
     * Getter method of setOnTime.
     * @return onTime
     */
    public LocalDateTime getOnTime() {
        return onTime;
    }


    /**
     * Setter method of status. It also calculates set on or off time if it is plug or camera.
     * @param status String value of switch value.
     */
    public void setSwitch(String status){
       if(status.equals("On")){
           if(this.status){
               DeviceController.output +=("ERROR: This device is already switched on!\n");
           }
           else{
                    this.status = true;
                    setOnOffTimes(true);} // This checks if an item is plugged or not if it is a plug. If it is a camera sets directly.

       }
       else{
           if(!this.status){
               DeviceController.output +=("ERROR: This device is already switched off!\n");
           }
           else{
               this.status = false;
               setOnOffTimes(false);  // This checks if an item is plugged or not if it is a plug. If it is a camera sets directly.
            if(getOffTime() != null && getOnTime() != null){
               if(getOffTime().isAfter(getOnTime()) ){
                   calculateConsumedEnergy();}
            }
           }
           }
       }

    /**
     * Getter method of status
     * @return String on or off for toString methods.
     */
    public String getStatus(){
        if(status){return "on";}
        else{return "off";}
    }

    /**
     * Setter Method of switchTime
     * @param switchTime LocalDateTime value of switch.
     */
    public void setSwitchTime(LocalDateTime switchTime) {
        this.switchTime = switchTime;
    }

    /**
     * Getter Method of switchTime.
     * @return switchTime
     */
    public LocalDateTime getSwitchTime(){
        return switchTime;
    }


    /**
     * It returns switch time. But for sorting algorithm it returns 999999 value if the value is null.
     * Because null values should be on lower part when the list is sorted.
     * @return
     */
    public LocalDateTime getLocalDateTime() {
        if(switchTime == null){
            return LocalDateTime.of(999999, 1, 1, 0, 0, 0);
        }
        else {
            return switchTime;
        }
    }



    //----------------------------------------------------------------------------------
    // Polymorphism methods of SmartDevices. It also handles exceptions.
    // If there is no device that can call function on their class body. These methods are called and prints error.


    /**
     * Only SmartPlug uses this method.
     * It sets pluggedIn value.
     */
    public void setPluggedIn(boolean isIn,double ampere) throws Exception {DeviceController.output += "ERROR: This device is not a smart plug!\n";}
    /**
     *  Only SmartPlug and SmartCamera use this method
     *  calculates consumed energy or megabyte value.
     */
    public void calculateConsumedEnergy(){}

    /**
     * Only SmartPlug and SmartCamera use this method.
     * It sets On and Off time of devices.
     */
    public void setOnOffTimes(boolean bool) {}

    /**
     * Only SmartLamp and SmartColorLamp use this method.
     * Sets kelvin and brightness. On SmartColorLamp it also sets it to white mode.
     */
    public void setWhite(int kelvin,int brightness) throws Exception {DeviceController.output +="ERROR: This device is not a smart lamp!\n";}

    /**
     * Only SmartLamp and SmartColorLamp use this method.
     * Sets kelvin. On SmartColorLamp it also sets it to white mode.
     */
    public void setKelvin(int kelvin) throws Exception {DeviceController.output +="ERROR: This device is not a smart lamp!\n";}

    /**
     * Only SmartLamp and SmartColorLamp use this method.
     * Sets brightness.
     */
    public void setBrightness(int brightness) throws Exception {DeviceController.output += "ERROR: This device is not a smart lamp!\n";}

    /**
     * Only SmartColorLamp uses this method.
     * Sets color value and brightness. The method also sets it to color mode if not.
     */
    public void setColor(String color,int brightness) throws Exception { DeviceController.output +="ERROR: This device is not a smart color lamp!\n";}


    /**
     * Only SmartColorLamp uses this method.
     * Sets color value. The method also sets it to color mode if not.
     */
    public void setColorCode(String colorCode) throws Exception {DeviceController.output +="ERROR: This device is not a smart color lamp!\n";}


    /**
     * This method returns switchTime value. It is created because format gives error if the value is null.
     * @param switchTime switchTime of device.
     * @return switchTime information
     */
    public String formatter(LocalDateTime switchTime){
        if(switchTime == null){return null;}
        else{ return  switchTime.format(Commands.outputFormatter);}
    }

    /**
     * toString Method of SmartDevices.
     * @return information.
     */
    public String toString(){
        return String.format((", and its time to switch its status is %s.\n") , formatter(switchTime));
    }


}



