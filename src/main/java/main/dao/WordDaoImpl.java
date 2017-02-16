package main.dao;

import main.entities.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class WordDaoImpl implements Dao<Word> {
    private static String INSERT_QUERY = "insert into word(data,language_id,right_answers_count) values (?,?,?)";
    private static String UPDATE_QUERY = "update word set data=?,language_id=?,right_answers_count=? where id=?";
    private static String INSERT_JOIN_QUERY = "insert into word_join(word_id,translation_id,user_id) values (?,?,?)";
    private static String SELECT_JOIN_QUERY = "select w.id,w.data,w.language_id,w.right_answers_count " +
            "from word w join word_join wj on wj.translation_id=w.id  where wj.word_id=? and w.data<>?" +
            " UNION " +
            "select w.id,w.data,w.language_id,w.right_answers_count " +
            "from word w join word_join wj on wj.word_id=w.id  where wj.translation_id=? and w.data<>?";
    private static String SELECT_QUERY = "select w.id,w.data,w.language_id,w.right_answers_count from word w where w.data=? and w.language_id=?";
    private static String DELETE_JOIN_QUERY = "delete from word_join where word_id=? or translation_id=?";
    private static String DELETE_QUERY = "delete from word where id=?";
    private static String SELECT_ALL_QUERY = "select w.id,w.data,w.language_id,w.right_answers_count from word w where w.language_id=? ORDER BY RANDOM() limit ?";
    private static String SELECT_RANDOM_QUERY = "select w.id,w.data,w.language_id,w.right_answers_count from word w where w.language_id=? ORDER BY RANDOM() limit ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Word get(Word word) throws SQLException {
        List<Word> list = jdbcTemplate.query(SELECT_QUERY, new Object[]{word.getData(),
                word.getLanguageId()}, new WordMapper());
        if (list != null && !list.isEmpty())
            list.get(0).setTranslations(getTranslationsList(list.get(0)));
        return list.isEmpty() ? null : list.get(0);
    }

    private List<Word> getTranslationsList(Word word) {
        List<Word> list = jdbcTemplate.query(SELECT_JOIN_QUERY, new Object[]{word.getId(),
                word.getData(), word.getId(), word.getData()}, new WordMapper());

        return list.isEmpty() ? null : list;
    }

    @Override
    public long save(final Word word) throws SQLException {

        long resultId = 0l;
        Word wordFromGetQuery;
        if ((wordFromGetQuery = get(word)) == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            int row = this.jdbcTemplate.update(new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(Connection connection)
                        throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, new String[]{"id"});
                    ps.setString(1, word.getData().trim());
                    ps.setLong(2, word.getLanguageId());
                    ps.setLong(3, word.getRightAnswersCount());
                    return ps;
                }
            }, keyHolder);
            if (row > 0)
                resultId = keyHolder.getKey().longValue();
        } else
            resultId = wordFromGetQuery.getId();
        if (word.getTranslations() == null)
            return resultId;
        else
            for (Word translationWord : word.getTranslations()) {
                long translationWordId = save(translationWord);
                jdbcTemplate.update(INSERT_JOIN_QUERY, new Object[]{resultId, translationWordId, 2});
            }
        return 0;
    }

    @Override
    public List<Word> getAll(Word word) {
        List<Word> list = jdbcTemplate.query(SELECT_ALL_QUERY, new Object[]{word.getLanguageId()}, new WordMapper());
        if (list != null) {
            for (Word resultWord : list)
                resultWord.setTranslations(getTranslationsList(resultWord));
        }
        return list;
    }

    @Override
    public int update(Word word) {
        return jdbcTemplate.update(UPDATE_QUERY, new Object[]{word.getData(), word.getLanguageId(), word.getRightAnswersCount(), word.getId()});
    }

    @Override
    public void delete(Word word) throws SQLException {
        Word deletedWord = get(word);
        if (deletedWord != null) {
            jdbcTemplate.update(DELETE_JOIN_QUERY, new Object[]{word.getId(), word.getId()});
            jdbcTemplate.update(DELETE_QUERY, new Object[]{word.getId()});
        }
    }

    class WordMapper implements RowMapper<Word> {
        @Override
        public Word mapRow(ResultSet resultSet, int i) throws SQLException {
            Word word1 = new Word();
            word1.setData(resultSet.getString("data").trim());
            word1.setLanguageId(resultSet.getLong("language_id"));
            word1.setRightAnswersCount(resultSet.getInt("right_answers_count"));
            word1.setId(resultSet.getLong("id"));
            return word1;
        }
    }

    @Override
    public List<Word> getRandom(Word word, int count) {
        List<Word> list = jdbcTemplate.query(SELECT_ALL_QUERY , new Object[]{word.getLanguageId(), count}, new WordMapper());
        if (list != null) {
            if (count == 1)
                list.get(0).setTranslations(getTranslationsList(list.get(0)));
            return list;
        }
        return new ArrayList<Word>();
    }
}
