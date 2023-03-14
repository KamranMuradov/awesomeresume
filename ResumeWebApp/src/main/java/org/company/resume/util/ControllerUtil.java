package org.company.resume.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerUtil {
    public static void errorPage(HttpServletResponse response, Exception e) {
        e.printStackTrace();
        try {
            response.sendRedirect("error?msg=" + e.getMessage());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
