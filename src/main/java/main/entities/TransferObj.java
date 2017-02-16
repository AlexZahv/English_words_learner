package main.entities;

import org.springframework.stereotype.Component;

@Component
public class TransferObj {
    private long id;
    private String data;
    private String translations;
    private long UserId;
    private long wordLanguageId;
    private long translationLanguageId;
    private int rightAnswersCount;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTranslations() {
        return translations;
    }

    public void setTranslations(String translations) {
        this.translations = translations;
    }

    public long getUserId() {
        return UserId;
    }

    public void setUserId(long userId) {
        UserId = userId;
    }

    public long getWordLanguageId() {
        return wordLanguageId;
    }

    public void setWordLanguageId(long wordLanguageId) {
        this.wordLanguageId = wordLanguageId;
    }

    public long getTranslationLanguageId() {
        return translationLanguageId;
    }

    public void setTranslationLanguageId(long translationLanguageId) {
        this.translationLanguageId = translationLanguageId;
    }

    public int getRightAnswersCount() {
        return rightAnswersCount;
    }

    public void setRightAnswersCount(int rightAnswersCount) {
        this.rightAnswersCount = rightAnswersCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
