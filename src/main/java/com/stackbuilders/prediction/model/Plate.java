package com.stackbuilders.prediction.model;

import com.stackbuilders.prediction.helper.PlateException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Plate {
    private String fullPlate;
    private String letters;
    private int numbers;

    public Plate(String fullPlate) throws PlateException {
        this.fullPlate = fullPlate.toUpperCase();
        Pattern platePattern = Pattern.compile("^[A-Z]{3}(\\d{3}|\\d{4})$");
        boolean validPlate = platePattern.matcher(this.fullPlate).matches();
        if (!validPlate) {
            throw new PlateException();
        }
        this.setLettersNumbers(this.fullPlate);
    }

    private void setLettersNumbers(String fullPlate) {
        Pattern onlyLetters = Pattern.compile("[A-Z]+");
        Matcher matchL = onlyLetters.matcher(fullPlate);
        letters = matchL.find() ? matchL.group() : "";
        Pattern onlyNumber = Pattern.compile("\\d+");
        Matcher matchN = onlyNumber.matcher(fullPlate);
        numbers = matchN.find() ? Integer.parseInt(matchN.group()) : 0;
    }

    public String getFullPlate() {
        return fullPlate;
    }

    public String getLetters() {
        return letters;
    }

    public int getNumbers() {
        return numbers;
    }

    public int getLastNumber() {
        return this.numbers % 10;
    }
}
