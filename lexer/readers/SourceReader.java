package lexer.readers;

import java.io.*;

/**
 * This class is used to manage the source program input stream;
 * each read request will return the next usable character; it
 * maintains the source column position of the character
 */
public class SourceReader implements IReader {
    public static final String READ_PREFIX = "READLINE:   ";

    private BufferedReader source;
    // line number of source program
    private int lineNumber = 0;
    // position of last character processed
    private int position;
    // if true then last character read was newline so read in the next line
    private boolean isPriorEndLine = true;
    private String nextLine;
    String printFile = "";
    private int rLine = 0;
    private int rPosition = 0;

    /**
     * Construct a new SourceReader
     * 
     * @param sourceFile the String describing the user's source file
     * @exception IOException is thrown if there is an I/O problem
     */
    public SourceReader(String sourceFile) throws IOException {
        // System.out.println("Source file: " + sourceFile);
        // System.out.println("user.dir: " + System.getProperty("user.dir"));
        source = new BufferedReader(new FileReader(sourceFile));
    }

    public SourceReader(BufferedReader reader) throws IOException {
        this.source = reader;
    }

    public void close() {
        try {
            source.close();
        } catch (Exception e) {
            /* no-op */ }
    }

    /**
     * read next char; track line #, character position in line<br>
     * return space for newline
     * 
     * @return the character just read in
     * @IOException is thrown for IO problems such as end of file
     */
    public char read() throws IOException {
        if (isPriorEndLine) {
            lineNumber++;
            position = -1;
            nextLine = source.readLine();
            buildPrintFile();

            if (nextLine != null) {
                System.out.println(READ_PREFIX + nextLine);
            }

            isPriorEndLine = false;
        }

        if (nextLine == null) {
            // hit eof or some I/O problem
            throw new IOException();
        }

        if (nextLine.length() == 0) {
            isPriorEndLine = true;
            return ' ';
        }

        position++;
        if (position >= nextLine.length()) {
            isPriorEndLine = true;
            return ' ';
        }

        return nextLine.charAt(position);
    }

    /**
     * @return the position of the character just read in
     */
    public int getPosition() {
        return position;
    }

    /**
     * @return the line number of the character just read in
     */
    public int getLineno() {
        try {
            return lineNumber;
        } catch (Exception e) {
            return 0;
        }
    }

    private void buildPrintFile() {
        if (rLine < getLineno()) {
            printFile += String.format("\n %5s: ", getLineno());
            rLine++;
        }
        while (rPosition < getPosition()) {
            printFile += " ";
            rPosition++;
        }
        printFile += nextLine;
    }

    public String getSourceFile() {
        return printFile;
    }
}