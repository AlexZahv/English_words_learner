package main.dao;

import main.entities.Word;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class TestService {
    private List<Word> wordsList;

    public TestService() {
        wordsList = new ArrayList<Word>();
        Word word1 = createWord("Дом");
        Word word2 = createWord("House");
        word1.addTranslation(word2);
        word2.addTranslation(word1);

        Word word3 = createWord("Солнце");
        Word word4 = createWord("Sun");
        word3.addTranslation(word4);
        word4.addTranslation(word3);
    }

    public Word createWord(String data) {
        Word word = new Word();
        word.setData(data);
        return word;
    }

    public Word getWordByData(String data) {
        wordsList = new ArrayList<Word>();
        Word word1 = createWord("Дом");
        Word word2 = createWord("House");
        word1.addTranslation(word2);

        Word word3 = createWord("Солнце");
        Word word4 = createWord("Sun");
        word3.addTranslation(word4);

        wordsList.add(word1);
        wordsList.add(word2);
        wordsList.add(word3);
        wordsList.add(word4);
        if (!data.equals("")) {
            for (Word word : wordsList)
                if (word.getData().equals(data))
                    return word;
            return null;
        } else {
            Random random = new Random();
            return wordsList.get(random.nextInt(wordsList.size()));
        }
    }

    public void saveWord(String data, String translationData) {
        for (Word word : wordsList) {
            if (word.getData().equals(data)) {
                for (Word translationWord : word.getTranslations()) {
                    if (translationWord.getData().equals(translationData))
                        return;
                }
                Word translationWord = createWord(translationData);
                word.addTranslation(translationWord);
                translationWord.addTranslation(word);
                return;
            }
        }
        Word word = createWord(data);
        Word translationWord = createWord(translationData);
        word.addTranslation(translationWord);

    }
}
