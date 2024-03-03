import java.time.Duration;

public class SmartPlug extends SmartDevices{

    /**
     * Gives information about SmartPlug. If it is not plugged value will be false .
     */
    private boolean pluggedIn = false;

    /**
     * Ampere value of plugged item. Default value is 0 which means no energy will be consumed.
     */
    private double ampere = 0;

    /**
     * Voltage value of plug. It can not be changed.
     */
    private final int voltage = 220;


    /**
     * Consumed energy of plug. It is updated with switch operations.
     */
    private double consumedEnergy = 0.0;

    /**
     * Constructor of SmartPlug. It takes only name parameter.  Calls Smart Devices constructor with 1 parameter
     * @param name name of the plug.
     */
    public SmartPlug(String name) {
        super(name);
    }

    /**
     *  Constructor of SmartPlug. It takes 2 parameter. Calls Smart Devices constructor with 2 parameter
     * @param name name of the plug
     * @param status switch status of lamp. Can be on or off.
     */
    public SmartPlug(String name , String status){
        super(name, status);
    }

    /**
     * Constructor of SmartPlug. It takes 3 parameter.  Calls Smart Devices constructor with 2 parameter.
     * @param name ame
     * @param status switch status of lamp. Can be on or off.
     * @param ampere ampere value of plug. Should be positive value.
     */
    public SmartPlug(String name, String status, double ampere){
        super(name, status);
        this.ampere = ampere;
        pluggedIn =true;
    }

    /**
     * This method sets On Off times if conditions are met
     * @param bool boolean value gives information about switch mode of plug.
     */
    @Override
    public void setOnOffTimes(boolean bool) {
        if (bool) {  // If it is true
            if (pluggedIn) {  //And there is an item plugged it sets on time to that time.
                setOnTime(DeviceController.time);
            }
        } else {  // If it is switched off
            if (pluggedIn) // And there is an item plugged. It sets off time to that time.
                setOffTime(DeviceController.time);
        }
    }

    /**
     * Calculates consumed energy.
     */
    @Override
    public void calculateConsumedEnergy(){
            Duration minutes = Duration.between(getOnTime() , getOffTime());
            consumedEnergy += ampere*voltage*minutes.getSeconds()/3600;
            setOnTime(null); setOffTime(null);
    }

    /**
     * Setter method of pluggedIn. Also calculates energy if conditions are met.
     * @param isIn gives information about pluggedIn value. Takes false if setPluggedIn method calls this method. Otherwise, takes true.
     * @param ampere ampere value of setPlugIn method. It is not used in setPlugOff method.
     * @throws Exception DeviceException.
     */
    @Override
    public void setPluggedIn(boolean isIn,double ampere) throws Exception {
        if (isIn) {
            DeviceController.ampereAndMegabyteCheck(ampere,"Ampere"); //Checks ampere value.
             if(this.ampere == 0) { // There is no item plugged.
                 pluggedIn = true;
                 if(getStatus().equals("on")){setOnTime(DeviceController.time);}  //If plug is switched on sets onTime when Plug starts to consume energy.
                 this.ampere = ampere;
             }
             else{DeviceController.output += "ERROR: There is already an item plugged in to that plug!\n";}
        } else {
            if (pluggedIn) {
                pluggedIn = false;
                if(getStatus().equals("on")){setOffTime(DeviceController.time);}  //If plug is switched on sets offTime when Plug stops to consume energy. Status should be on.

                if(getOffTime() != getOnTime() && getOffTime() != null){
                    calculateConsumedEnergy();}
                this.ampere = 0;
            } else {
                DeviceController.output += ("ERROR: This plug has no item to plug out from that plug!\n");
            }
        }
    }

    /**
     * toString method of SmartPlug.
     * @return information.
     */
    @Override
    public String toString(){

            return String.format(("Smart Plug %s is %s and consumed %.2fW so far (excluding current device)"), getName(), getStatus(), consumedEnergy) + super.toString();
    }

}

