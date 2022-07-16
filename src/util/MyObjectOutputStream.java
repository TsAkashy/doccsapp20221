
package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


public class MyObjectOutputStream extends ObjectOutputStream {
 
    // Constructor of this class
    // 1. Default
    MyObjectOutputStream() throws IOException
    {
 
        // Super keyword refers to parent class instance
        super();
    }
 
    // Constructor of this class
    // 1. Parameterized constructor
    MyObjectOutputStream(OutputStream o) throws IOException
    {
        super(o);
    }

    public MyObjectOutputStream(FileOutputStream fileOutput) throws IOException {
        super(fileOutput);
    }
 
    // Method of this class
    public void writeStreamHeader() throws IOException
    {
        return;
    }
}