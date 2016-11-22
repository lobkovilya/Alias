package ru.nsu.fit.lobkov.alias;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Lobkov on 21.11.2016.
 */

public class ListDataGetter implements DataGetter {
    private ArrayList<String> wordList = new ArrayList<>(Arrays.asList("hello", "world", "planet", "war", "stone", "android", "cat", "dog"));
    private int cursor = 0;

    @Override
    public String getNextWord() {
        ++cursor;
        if (cursor == wordList.size()) {
            cursor = 0;
        }
        return wordList.get(cursor);
    }

    @Override
    public String getCurrentWord() {
        return wordList.get(cursor);
    }
}
