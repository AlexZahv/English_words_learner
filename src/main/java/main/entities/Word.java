package main.entities;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
public class Word implements Serializable {

    private long id;
    private String data;
    private long userId;
    private long languageId;
    private int rightAnswersCount;
    private List<Word> translations;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<Word> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Word> translations) {
        this.translations = translations;
    }

    public void addTranslation(Word word) {
        if (translations == null)
            translations = new ArrayList<Word>();
        translations.add(word);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(long languageId) {
        this.languageId = languageId;
    }

    public int getRightAnswersCount() {
        return rightAnswersCount;
    }

    public void setRightAnswersCount(int rightAnswersCount) {
        this.rightAnswersCount = rightAnswersCount;
    }
}
