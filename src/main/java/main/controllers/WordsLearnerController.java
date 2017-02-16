package main.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import main.entities.TransferObj;
import main.entities.Word;
import main.services.WordsLearnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class WordsLearnerController {
    @Autowired
    private WordsLearnerServiceImpl wordsLearnerService;

    @RequestMapping(value = "home/description")
    public String getDescription() {
        return "Описание с сервера";
    }

    @RequestMapping(value = "learn/save")
    public int saveWord(@RequestBody TransferObj transferObj) throws SQLException {
        return wordsLearnerService.learnWord(transferObj);
    }

    @RequestMapping(value = "learn/delete")
    public int deleteWord(@RequestBody TransferObj transferObj) throws SQLException {
        return wordsLearnerService.deleteWord(transferObj);
    }

    @RequestMapping(value = "repeat/next",method = RequestMethod.POST)
    public Word getWordsForRepeat(@RequestBody TransferObj transferObj) throws JsonProcessingException, SQLException {
        return wordsLearnerService.getWordForRepeat(transferObj);
    }

    @RequestMapping(value = "repeat/update",method = RequestMethod.POST)
    public int updateRepeatedWord(@RequestBody TransferObj transferObj) throws JsonProcessingException, SQLException {
        return wordsLearnerService.updateWord(transferObj);
    }

    @RequestMapping(value = "play/next",method = RequestMethod.POST)
    public List<Word> getWordsForPlay(@RequestBody TransferObj transferObj) throws JsonProcessingException, SQLException {
        return wordsLearnerService.getWordForPlay(transferObj);
    }
}
