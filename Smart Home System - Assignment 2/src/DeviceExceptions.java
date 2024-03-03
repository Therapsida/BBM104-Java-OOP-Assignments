/**
 * This exception is created for getting messages of exceptions
 * Because every exception has different messages this exception makes easier to  handle exceptions.
 */
public class DeviceExceptions extends Exception{


    /**
     * Constructor of custom exception. Takes message
     * @param message
     */
    public DeviceExceptions(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
