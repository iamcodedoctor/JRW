package jrw.cli;

public class CliParser {

    public CliConfig parse(String[] args) {
        CliConfig config = new CliConfig();

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            switch (arg) {
                case "-h":
                    ensureValue(args, i);
                    config.setHelpRequested(true);
                    break;

                case "-s":
                    ensureValue(args, i);
                    config.setInputType(CliConfig.InputType.STRING);
                    config.setInputValue(args[++i]);
                    break;

                case "-i":
                    ensureValue(args, i);
                    config.setInputType(CliConfig.InputType.FILE);
                    config.setInputValue(args[++i]);
                    break;

                case "-p":
                    config.setPrintToConsole(true);
                    break;

                case "-o":
                    ensureValue(args, i);
                    config.setOutputFile(args[++i]);
                    break;

                case "-a":
                    ensureValue(args, i);
                    config.setAppendFile(args[++i]);
                    break;

                default:
                    throw new IllegalArgumentException("Unknown flag: " + arg);
            }
        }

        applyDefaults(config);
        validate(config);

        return config;
    }

    private void ensureValue(String[] args, int index) {
        if (index + 1 >= args.length) {
            throw new IllegalArgumentException("Missing value for " + args[index]);
        }
    }

    private void applyDefaults(CliConfig config) {
        if (config.getInputType() == null) {
            config.setInputType(CliConfig.InputType.STDIN);
        }
    }

    private void validate(CliConfig config) {
        if (config.getInputType() == CliConfig.InputType.STRING &&
            config.getInputValue() == null) {
            throw new IllegalArgumentException("String input requires a value");
        }

        if (config.getInputType() == CliConfig.InputType.FILE &&
            config.getInputValue() == null) {
            throw new IllegalArgumentException("File input requires a path");
        }

        boolean hasOutput =
                config.isPrintToConsole() ||
                config.getOutputFile() != null ||
                config.getAppendFile() != null;

        if (!hasOutput) {
            throw new IllegalArgumentException("No output specified");
        }

        if (config.getOutputFile() != null && config.getAppendFile() != null) {
            throw new IllegalArgumentException("Cannot use -o and -a together");
        }
    }
}