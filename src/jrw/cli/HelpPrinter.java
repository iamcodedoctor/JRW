package jrw.cli;

/**
 * Prints help and usage information for jrw.
 */
public class HelpPrinter {

    public void print() {
        System.out.println("""
            jrw — Java Read/Write utility

            Usage:
              jrw [options]

            Input options (choose one):
              -s <string>       Use the given string as input
              -i <file>         Read input from file
              (default)         Read from standard input

            Output options (at least one required):
              -p                Print output to console
              -o <file>         Write output to file (overwrite)
              -a <file>         Write output to file (append)

            Other options:
              -h                Show this help message

            Examples:
              jrw -s "hello" -p
              jrw -i input.txt -o output.txt
              echo "hi" | jrw -p
            """);
    }
}