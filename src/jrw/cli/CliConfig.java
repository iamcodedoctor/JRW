package jrw.cli;

/**
 * CliConfig represents the user's intent as expressed via
 * command-line arguments.
 *
 * It contains no logic and performs no IO.
 */
public class CliConfig {

    public enum InputType {
        STRING,
        FILE,
        STDIN
    }

    private InputType inputType;

    // Used when inputType == STRING or FILE
    private String inputValue;

    // Output flags
    private boolean printToConsole;
    private String outputFile;
    private String appendFile;
    private boolean helpRequested;

    public InputType getInputType() {
        return inputType;
    }

    public void setInputType(InputType inputType) {
        this.inputType = inputType;
    }

    public String getInputValue() {
        return inputValue;
    }

    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
    }

    public boolean isPrintToConsole() {
        return printToConsole;
    }

    public void setPrintToConsole(boolean printToConsole) {
        this.printToConsole = printToConsole;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public String getAppendFile() {
        return appendFile;
    }

    public void setAppendFile(String appendFile) {
        this.appendFile = appendFile;
    }

    public boolean isHelpRequested(){
        return this.helpRequested;
    }

    public void setHelpRequested(boolean helpRequested){
        this.helpRequested = helpRequested;
    }
}

