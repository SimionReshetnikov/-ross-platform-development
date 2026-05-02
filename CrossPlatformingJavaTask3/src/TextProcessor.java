import java.util.*;
import java.util.stream.Collectors;

public class TextProcessor {

    private Map<String, Integer> wordsMap;

    public TextProcessor() {
        wordsMap = new HashMap<>();
    }

    public void addText(String text) {
        String[] words = text.toLowerCase().split("[\\s\\p{Punct}]+");

        for (String word : words) {
            if (wordsMap.containsKey(word)) {
                wordsMap.put(word, wordsMap.get(word) + 1);
            } else {
                wordsMap.put(word, 1);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : wordsMap.entrySet()) {
            sb.append(entry.getKey()).append(" - ").append(entry.getValue())
                    .append("\n");
        }

        return sb.toString().trim();
    }

    public String getWordsWithMinCount(int minCount) {
        int finalMinCount = Math.max(minCount, 0);

        return wordsMap.entrySet().stream()
                .filter(entry -> entry.getValue() >= finalMinCount)
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .map(entry -> entry.getKey() + " - " + entry.getValue())
                .collect(Collectors.joining("\n"));
    }

    public int getWordFrequency(String word) {
        return wordsMap.getOrDefault(word, 0);
    }

    public String getSorByDescendingFrequency() {
        return wordsMap.entrySet().stream()
                .sorted((e1, e2) ->
                        e2.getValue().compareTo(e1.getValue()))
                .map(entry -> entry.getKey() + " - " + entry.getValue())
                .collect(Collectors.joining("\n"));
    }
}
