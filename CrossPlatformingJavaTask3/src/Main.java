import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Укажите путь к файлу");
            System.out.println("Пример: java Main input.txt");
            return;
        }

        String filePath = args[0];

        try {
            String text = Files.readString(Path.of(filePath));

            TextProcessor processor = new TextProcessor();
            processor.addText(text);

            System.out.println("Все слова:");
            System.out.println(processor);

            System.out.println("\nСлова с частотой >= 2:");
            System.out.println(processor.getWordsWithMinCount(2));

        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
    }
}