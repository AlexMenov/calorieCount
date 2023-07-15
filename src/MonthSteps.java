import java.io.Serializable;
import java.util.Arrays;

class MonthSteps implements Serializable {
    private final int days = 30;
    private final int mediumStep = 70;
    private final int mediumCaloriesBurned = 58;
    private final int[] monthSteps = new int[days];

    MonthSteps() {
        Arrays.fill(monthSteps, 0);
    }

    void setSteps(final int day, final int steps) {
        try {
            if (day <= 0 || steps < 0) throw new IllegalArgumentException();
            monthSteps[day - 1] = steps;
            System.out.println("Данные успешно сохранены!");
        } catch (IllegalArgumentException e) {
            System.out.println("Число шагов в день не может быть отрицательным!");
        }
    }

    void getAllSteps(int stepsTarget) {
        var message = new StringBuilder();
        int sumSteps = 0;
        int maxSteps = 0;
        int stepsTargetDays = 0;
        for (int i = 0; i < days; i++) {
            int currentSteps = monthSteps[i];
            if (currentSteps > stepsTarget) stepsTargetDays++;
            if (maxSteps < currentSteps) maxSteps = currentSteps;
            sumSteps += currentSteps;
            message.append(i + 1).append(" день ").append(currentSteps).append(", \n");
        }

        if (message.length() > 0) {
            message.deleteCharAt(message.length() - 3);
            System.out.println(message);
            System.out.println("Общее количество шагов за месяц - " + sumSteps);
            System.out.println("Максимальное пройденное количество шагов в месяце - " + maxSteps);
            System.out.println("Среднее количество шагов - " + sumSteps / days);
            System.out.println("Пройденная дистанция - " + sumSteps / mediumStep + " км");
            System.out.println("Количество сожженных килокалорий - " + sumSteps / mediumCaloriesBurned);
            System.out.println("Максимальное количество дней выше целевого значения - " + stepsTargetDays);
        } else {
            System.out.println("Статистика для указанного месяца отсутствует.");
        }
    }
}
