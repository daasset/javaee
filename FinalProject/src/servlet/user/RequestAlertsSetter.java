package servlet.user;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.stream.Collectors;

public class RequestAlertsSetter {
    public static void setAlerts(HttpServletRequest req) {
        String inError = req.getParameter("error");
        if (inError != null && !inError.isEmpty()) {
            String outError = Arrays.stream(inError.split("_")).collect(Collectors.joining(" "));
            outError = outError.substring(0, 1).toUpperCase() + outError.substring(1);
            req.setAttribute("error", outError);
        }
        String inSuccess = req.getParameter("success");
        if (inSuccess != null && !inSuccess.isEmpty()) {
            String outSuccess = Arrays.stream(inSuccess.split("_")).collect(Collectors.joining(" "));
            outSuccess = outSuccess.substring(0, 1).toUpperCase() + outSuccess.substring(1);
            req.setAttribute("success", outSuccess);
        }
    }
}
