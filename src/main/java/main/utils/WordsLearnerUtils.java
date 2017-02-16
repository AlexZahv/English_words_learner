package main.utils;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class WordsLearnerUtils {
    private static String[] commaMas = {",", ".", "/", ";", ":", "!"};

    /**
     * Return a list from the input String of commaText
     *
     * @param commaText
     * @return
     */
    public static List<String> getFormattedListFromCommaText(String commaText) {
        commaText = commaText.trim().toLowerCase();
        for (String comma : commaMas)
            commaText.replaceAll(comma, ",");
        return Arrays.asList(commaText.split(","));
    }
}
