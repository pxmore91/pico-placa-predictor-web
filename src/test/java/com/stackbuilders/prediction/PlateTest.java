package com.stackbuilders.prediction;

import com.stackbuilders.prediction.helper.PlateException;
import com.stackbuilders.prediction.model.Plate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlateTest {

    @Test
    public void setGetPlate() throws PlateException {
        String inputPlate = "abc123";
        Plate plate = new Plate(inputPlate);
        assertEquals("ABC123", plate.getFullPlate());
    }

    @Test
    public void setGetPlate2() throws PlateException {
        String inputPlate = "abc1234";
        Plate plate = new Plate(inputPlate);
        assertEquals("ABC1234", plate.getFullPlate());
    }

    @Test
    public void getLastNumber() throws PlateException {
        String inputPlate = "ABC1234";
        Plate plate = new Plate(inputPlate);
        assertEquals(4, plate.getLastNumber());
    }

    @Test
    public void getLastNumber2() throws PlateException {
        String inputPlate = "ABC123";
        Plate plate = new Plate(inputPlate);
        assertEquals(3, plate.getLastNumber());
    }

    @Test
    public void testPlateException() {
        Assertions.assertThrows(PlateException.class, () -> {
            String inputPlate = "anything";
            Plate plate = new Plate(inputPlate);
        });
    }

    @Test
    public void testPlateException2() {
        Assertions.assertThrows(PlateException.class, () -> {
            String inputPlate = "ABCD1234";
            Plate plate = new Plate(inputPlate);
        });
    }

    @Test
    public void testPlateException3() {
        Assertions.assertThrows(PlateException.class, () -> {
            String inputPlate = "AB1234";
            Plate plate = new Plate(inputPlate);
        });
    }

    @Test
    public void testPlateException4() {
        Assertions.assertThrows(PlateException.class, () -> {

            String inputPlate = "ABC12345";
            Plate plate = new Plate(inputPlate);
        });
    }

    @Test
    public void testPlateException5() {
        Assertions.assertThrows(PlateException.class, () -> {
            String inputPlate = "ABC12";
            Plate plate = new Plate(inputPlate);
        });
    }

    @Test
    public void testPlateException6() {
        Assertions.assertThrows(PlateException.class, () -> {
            String inputPlate = "A1";
            Plate plate = new Plate(inputPlate);
        });
    }


}