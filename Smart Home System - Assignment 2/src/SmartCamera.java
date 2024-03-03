import java.time.Duration;

public class SmartCamera extends SmartDevices{
    /**
     * megabytesConsumedPerMinute. This value initiates when constructor is called.
     */
    private final double megabytesConsumedPerMinute;

    /**
     * Total consumption
     */
    private double megabytesConsumed = 0;

    /**
     * Constructor of Smart Camera. It calls SmartDevices constructor.
     * @param name  name of the device.
     * @param megabytesConsumedPerMinute megabytes consume per minute of the device
     */

    public SmartCamera(String name,double megabytesConsumedPerMinute) {
        super(name);
        this.megabytesConsumedPerMinute = megabytesConsumedPerMinute;
    }


    /**
     * Constructor of Smart Camera with 3 param. It calls SmartDevices constructor.
     * @param name  name of the device.
     * @param megabytesConsumedPerMinute megabytes consume per minute of the device
     * @param status Indicates switched off or on.
     */
    public SmartCamera(String name,double megabytesConsumedPerMinute, String status) {
        super(name, status);
        this.megabytesConsumedPerMinute = megabytesConsumedPerMinute;
    }


    /**
     * Calculates consumed energy.
     */
    public void calculateConsumedEnergy(){
        Duration minutes = Duration.between(getOnTime() , getOffTime());
        megabytesConsumed += megabytesConsumedPerMinute*minutes.getSeconds()/60;
    }

    /**
     * sets the time when the device is switched on or off.
     * @param bool boolean value of device. Specifies the function which will ne called.
     */
    @Override
    public void setOnOffTimes(boolean bool) {
        if (bool) {
                setOnTime(DeviceController.time);
            }
        else {
                setOffTime(DeviceController.time);
        }
    }

    /**
     * Gives the information about device.
     * @return information.
     */
    @Override
    public String toString(){
        return String.format(("Smart Camera %s is %s and used %.2f MB of storage so far (excluding current status)"), getName(), getStatus(), megabytesConsumed) + super.toString();
    }


}
