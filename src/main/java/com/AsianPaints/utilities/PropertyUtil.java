package com.AsianPaints.utilities;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class PropertyUtil {
    public static Properties loadProperties(String filePath) throws IOException {
        Properties prob = new Properties();
        FileInputStream fs = new FileInputStream(filePath);
        prob.load(fs);
        return prob;
    }
}
