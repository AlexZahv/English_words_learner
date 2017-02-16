package main.services;

import main.dao.Dao;
import main.entities.TransferObj;
import main.entities.Word;
import main.utils.WordsLearnerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public class WordsLearnerServiceImpl {
    @Autowired
    private Dao<Word> wordDao;

    public int learnWord(TransferObj transferObj) {
        Word word = new Word();
        word.setData(transferObj.getData());
        word.setLanguageId(2);
        word.setRightAnswersCount(0);
        for (String translation : WordsLearnerUtils.getFormattedListFromCommaText(transferObj.getTranslations())) {
            Word translatiuonWord = new Word();
            translatiuonWord.setLanguageId(2);
            translatiuonWord.setRightAnswersCount(0);
            translatiuonWord.setData(translation);
            word.addTranslation(translatiuonWord);
        }
        try {
            wordDao.save(word);
        } catch (SQLException e) {
            return 1;
        }
        return 0;
    }

    public int updateWord(TransferObj transferObj) {
        Word word = new Word();
        word.setId(transferObj.getId());
        word.setData(transferObj.getData());
        word.setLanguageId(transferObj.getWordLanguageId());
        word.setRightAnswersCount(transferObj.getRightAnswersCount());
        int count = wordDao.update(word);
        return count == 0 ? 0 : 1;
    }

    public int deleteWord(TransferObj transferObj) {
        Word word = new Word();
        word.setData(transferObj.getData());
        word.setLanguageId(transferObj.getWordLanguageId());
        try {
            wordDao.delete(word);
        } catch (SQLException e) {
            return 1;
        }
        return 0;
    }

    public Word getWordForRepeat(TransferObj transferObj) {
        Word word = new Word();
        word.setLanguageId(transferObj.getWordLanguageId());

        return wordDao.getRandom(word,1).get(0);
    }

    public List<Word> getWordForPlay(TransferObj transferObj){
        Word word = new Word();
        word.setLanguageId(transferObj.getWordLanguageId());
        return wordDao.getRandom(word,3);
    }

}
