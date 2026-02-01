package jrw;

import jrw.cli.CliRunner;

public class Main {
    public static void main(String[] args) throws Exception {
        CliRunner runner = new CliRunner();

        runner.run(args);
    }
}
