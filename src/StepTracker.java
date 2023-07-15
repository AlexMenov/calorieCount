
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class StepTracker {
    private static int stepsTarget = 10000;
    private final static List<HashMap<String, MonthSteps>> obs = new ArrayList<>();
    private final static String path = "months.bin";
    private final static String[] months = {"январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};

    public static int getStepsTarget() {
        return stepsTarget;
    }

    public static void setStepsTarget(int stepsTarget) {
        StepTracker.stepsTarget = stepsTarget;
    }

    static void writeEmptyMonthsToFile() {
        var file = Files.exists(Paths.get(path));
        if (!file) {
            System.out.println(!file);
            Map<String, MonthSteps> month = new HashMap<>();
            for (String monthName : months) {
                month.put(monthName, new MonthSteps());
            }
            try (var output = new FileOutputStream(path, true); var out = new ObjectOutputStream(output)) {
                out.writeObject(month);
                out.flush();
                System.out.println("Файл успешно создан и записан.");
            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }

    static void getMonthSteps(String month) {
        getMonths();
        for (var ob : obs) {
            if (ob.containsKey(month)) {
                MonthSteps monthSteps = ob.get(month);
                monthSteps.getAllSteps(getStepsTarget());
            }
        }
    }

    static void setMonthDaySteps(final String month, final String day, final String steps) {
        getMonths();
        try {
            Files.delete(Paths.get(path));
        } catch (IOException e) {
        }
        try (var output = new FileOutputStream(path, true); var out = new ObjectOutputStream(output)) {
            for (var ob : obs) {
                if (ob.containsKey(month)) {
                    ob.get(month).setSteps(Integer.parseInt(day), Integer.parseInt(steps));
                }
                out.writeObject(ob);
            }
            out.flush();
        } catch (IOException e) {
            System.out.println("Произошла ошибка при сохранени данных");
        }
    }

    private static void getMonths() {
        try (var input = new FileInputStream(path); var in = new ObjectInputStream(input)) {
            HashMap<String, MonthSteps> m;
            while (true) {
                try {
                    m = (HashMap<String, MonthSteps>) in.readObject();
                    obs.add(m);
                } catch (Exception e) {
                    break;
                }
            }
        } catch (IOException e) {
            for (StackTraceElement stack : e.getStackTrace()) {
                System.out.println("class - " + stack.getClassName() + ", " + stack.getLineNumber() + ", " + e.getMessage());
            }
        }
    }
}
