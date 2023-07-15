import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StepTracker.writeEmptyMonthsToFile();
        var scanner = new Scanner(System.in);
        while (true) {
            Commands.printCommands();
            String command = scanner.nextLine().toLowerCase().trim();
            if (command.equals("1")) {
                System.out.println("Укажите данные в формате - месяц день шаги (январь 15 2500)");
                var data = scanner.nextLine().trim().split(" ");
                if (data.length >= 3) {
                    StepTracker.setMonthDaySteps(data[0], data[1], data[2]);
                } else {
                    System.out.println("Необходимо указать - месяц день шаги (январь 15 2500)");
                }
            }
            if (command.equals("2") || command.equals("4")) {
                System.out.println("Укажите целевое количество шагов, например, 10000");
                try {
                    var data = scanner.nextLine().trim();
                    StepTracker.setStepsTarget(Integer.parseInt(data));
                } catch (NumberFormatException e) {
                    System.out.println("Введите числовое значение!");
                }
            }
            if (command.equals("3")) {
                System.out.println("Укажите месяц в формате - январь");
                var data = scanner.nextLine().toLowerCase().trim();
                StepTracker.getMonthSteps(data);
            }
            if (command.equals("5")) {
                System.exit(0);
            }
        }
    }
}