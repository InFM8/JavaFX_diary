package com.example.diary.test;

import com.example.diary.model.Diary;
import com.example.diary.model.DiaryDAO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DiaryTest {
    DiaryDAO diaryDAO = new DiaryDAO();

    public Diary diaryLocalPositive;
    public Diary diaryLocalNegative;
    public Diary diaryDB;

    @Before
    public void setup() {
        diaryLocalPositive = new Diary("test", "test", 10);
        diaryLocalNegative = new Diary("ntest" , "ntest", 11);
    }
    @Test
    public void createDiaryPositiveTest() {
        diaryDAO.create(diaryLocalPositive);
        diaryDB = diaryDAO.searchByTitle(diaryLocalPositive.getTitle());
        compareDiaryEquals(diaryLocalPositive, diaryDB);
    }
    @Test
    public void updateDiaryPositiveTest() {
        diaryDAO.update(diaryLocalPositive);
        diaryDB = diaryDAO.searchByTitle(diaryLocalPositive.getTitle());
        compareDiaryEquals(diaryLocalPositive, diaryDB);
    }

    @Test
    public void deleteDiaryPositiveTest() {
        diaryDAO.deleteByID(10);
        diaryDB = diaryDAO.searchByTitle(diaryLocalPositive.getTitle());
        compareDiaryEquals(diaryLocalPositive, diaryDB);
    }



    private void compareDiaryEquals(Diary diaryLocal, Diary diaryDB) {
        Assert.assertEquals(diaryLocal.getTitle(), diaryDB.getTitle());
        Assert.assertEquals(diaryLocal.getText(), diaryDB.getText());
    }
}
