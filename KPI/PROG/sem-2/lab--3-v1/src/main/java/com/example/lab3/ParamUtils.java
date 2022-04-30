package com.example.lab3;

import javax.servlet.http.HttpServletRequest;
import java.time.temporal.ValueRange;
import java.util.HashMap;
import java.util.Map;

public class ParamUtils {

    static String[] paramNames = new String[]{"aFrom", "aTo", "aStep", "bFrom", "bTo", "bStep",
            "cFrom", "cTo", "cStep", "dFrom", "dTo", "dStep",};

    public static Map<String, Double> getDoubleParams(Map<String, String[]> params) {
        Map<String, Double> res = new HashMap<>();
        for (String name : params.keySet()) {
            res.put(name, Double.parseDouble(params.get(name)[0]));
        }
        return res;
    }


    public static String validate(Map<String, String[]> map) {
        StringBuilder res = new StringBuilder("Помилка в параметрах:");


        if (!validaTeNubmerOfParams(map)) return "Присутні неочікуванні параметри";
        for (int i = 0; i < paramNames.length; i++) {

            if (map.get(paramNames[i]) == null) {
                res.append(paramNames[i]).append(", ");
                continue;
            }
            if (map.get(paramNames[i])[0].isEmpty()) {
                res.append(paramNames[i++]).append(", ");
                continue;
            }
            if (!validateNumberOfValues(map, paramNames[i])
                    || !validateDouble(map, paramNames[i])
                    || (((i - 1) % 3 == 0) && !validateFromToDifference(map, paramNames[i], paramNames[i - 1]))
                    || (((i - 2) % 3 == 0) && !validateStep(map, paramNames[i], paramNames[i - 1], paramNames[i - 2]))) {
                res.append(paramNames[i]).append(", ");
            }
        }
        return (res.toString().equals("Помилка в параметрах:")) ? null : res.toString();
    }

    private static boolean validateStep(Map<String, String[]> map, String nameFrom, String nameTo, String nameStep) {;
        return (((Double.parseDouble(map.get(nameTo)[0]) - Double.parseDouble(map.get(nameFrom)[0])) / Double.parseDouble(map.get(nameStep)[0])) < 10) ||
                Double.parseDouble(map.get(nameStep)[0]) == 0;
    }

    private static boolean validateNumberOfValues(Map<String, String[]> map, String name) {
        return map.get((String) name).length == 1;
    }

    private static boolean validaTeNubmerOfParams(Map<String, String[]> map) {
        return map.size() == 12;
    }

    private static boolean validateDouble(Map<String, String[]> map, String name) {
        return map.get(name)[0].matches("-?\\d+(\\.\\d+)?");
    }

    private static boolean validateFromToDifference(Map<String, String[]> map, String nameFrom, String nameTo) {
        return Double.parseDouble(map.get((String) nameFrom)[0]) >
                Double.parseDouble(map.get((String) nameTo)[0]);
    }


    public static int countParamsLength(Map<String, Double> params) {
        int res = 1;
        String[] paramsNames = new String[]{"a", "b", "c", "d"};
        for (String paramName : paramsNames) {
            System.out.println(res);
            res *= (int) Math.ceil((params.get(paramName + "To") -
                    params.get(paramName + "From")) /
                    params.get(paramName + "Step"));
        }
        return res;
    }

    public static double[] setParamsFromRequest(HttpServletRequest request) {
        Map<String, Double> requestParams = (Map<String, Double>) request.getAttribute("params");
        double[] params = new double[12];
        for (int i = 0; i < 12; i++) {
            params[i] = requestParams.get(paramNames[i]);
        }
        return params;
    }

    public static double[] setParamsFromCookies(CookiesService cookiesUtils) {
        double[] params = new double[12];
        for (int i = 0; i < 12; i++) {
            params[i] = Double.parseDouble(cookiesUtils.findCookieValue(paramNames[i]));
        }
        return params;
    }
}
