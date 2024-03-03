
public class Main {

    /**
     * Reads inputs and call Commands.commands() finally writes content.
     * @param args array contains input file's directory and output file's directory.
     */
    public static void main(String[] args) {

        String[] input = FileInput.readFile(args[0], true, true);

        try {

            Commands.setInitialTime(input[0]);

            for (int i = 1 ; i < input.length ; i++) {
                if (!input[i].equals("")) {

                    DeviceController.output += String.format(("COMMAND: %s\n"), input[i]);
                    Commands.command(input[i]);

                }
            }
            if(!(input[input.length-1].equals("ZReport"))){     //If ZReport is not the last input it prints ZReport.
                DeviceController.output += "ZReport:\n" ;   DeviceController.zReport();
            }

        } catch (Exception e) {
            DeviceController.output += e.getMessage() ;  //Exceptions that terminates program.
        }
    finally {
            FileOutput.writeToFile(args[1],DeviceController.output,false,false);
    }


    }
}



