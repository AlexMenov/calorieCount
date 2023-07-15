import java.util.Scanner;

class Commands {
    static private String getAddSteps() {
        return "1. Указать количество пройденных шагов за день \n";
    }

    static private String getAddTarget() {
        return "2. Указать Вашу цель по количеству шагов в день \n";
    }

    static private String getGetStatistics() {
        return "3. Получить статистику за месяц \n";
    }

    static private String getChangeTarget() {
        return "4. Изменить цель по количеству шагов за день \n";
    }

    static private String getGetOut() {
        return "5. Выйти из приложения \n";
    }

    static void printCommands() {
        String message =
                "Укажите одну из следующих команд с помощью выбора номера: \n" +
                        getAddSteps() +
                        getAddTarget() +
                        getGetStatistics() +
                        getChangeTarget() +
                        getGetOut();
        System.out.println(message);
    }
}
