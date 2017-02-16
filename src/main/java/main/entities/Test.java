package main.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Test {
    @Id
    @GeneratedValue
    private int id;
    private String data;
    @ManyToMany
    @JoinTable(name = "words", joinColumns = @JoinColumn(name = "word1_id"), inverseJoinColumns = @JoinColumn(name = "word2_id"))
    private List<Test> tests;
    @ManyToMany
    @JoinTable(name = "words", joinColumns = @JoinColumn(name = "word1_id"), inverseJoinColumns = @JoinColumn(name = "word2_id"))
    private List<Test> answers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public List<Test> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Test> answers) {
        this.answers = answers;
    }
}
