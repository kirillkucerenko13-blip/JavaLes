package org.example.reader;

import java.io.IOException;

public interface DataReader {
    String readData(String source) throws IOException;
}