package com.stackbuilders.prediction;

import com.stackbuilders.prediction.controller.PicoPlacaController;
import com.stackbuilders.prediction.model.Plate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PicoPlacaTest {
    static final String DATE_PATTERN = "dd-MM-yyyy";
    static final String TIME_PATTERN = "HH:mm";

    @Test
    public void validateDateFormat() throws ParseException {
        DateFormat formatter = new SimpleDateFormat(PicoPlacaTest.DATE_PATTERN);
        assertEquals(formatter.parse("12-03-2020"), PicoPlacaController.validateDateFormat("12-03-2020"));

    }

    @Test
    public void exceptionValidateDateFormat() throws Exception {
        Assertions.assertThrows(ParseException.class, () -> {
            PicoPlacaController.validateDateFormat("12-25-2020");
        });
    }

    @Test
    public void exceptionValidateDateFormat2() {
        Assertions.assertThrows(ParseException.class, () -> {
            PicoPlacaController.validateDateFormat("anything");
        });

    }

    @Test
    public void validateTimeFormat() throws ParseException {
        DateFormat formatter = new SimpleDateFormat(PicoPlacaTest.TIME_PATTERN);
        assertEquals(formatter.parse("12:30"), PicoPlacaController.validateTimeFormat("12:30"));
    }

    @Test
    public void exceptionValidateTimeFormat() {
        Assertions.assertThrows(ParseException.class, () -> {
            PicoPlacaController.validateTimeFormat("50:20");
        });
    }

    @Test
    public void exceptionValidateTimeFormat2() {
        Assertions.assertThrows(ParseException.class, () -> {
            PicoPlacaController.validateTimeFormat("anything");
        });
    }

    @Test
    public void validateMorningRestriction() throws Exception {
        String inputPlate = "hbb1567",
                inputDate = "12-03-2020", //THURSDAY
                inputTime = "08:00";
        Plate plate = new Plate(inputPlate);
        PicoPlacaController pp = new PicoPlacaController(plate, PicoPlacaController.validateDateFormat(inputDate), PicoPlacaController.validateTimeFormat(inputTime));
        assertEquals("The car CANNOT be on the road", pp.validate());
    }

    @Test
    public void validateAfternoonRestriction() throws Exception {
        String inputPlate = "hbb1567",
                inputDate = "12-03-2020", //THURSDAY
                inputTime = "17:00";
        Plate plate = new Plate(inputPlate);
        PicoPlacaController pp = new PicoPlacaController(plate, PicoPlacaController.validateDateFormat(inputDate), PicoPlacaController.validateTimeFormat(inputTime));
        assertEquals("The car CANNOT be on the road", pp.validate());
    }

    @Test
    public void validateExtremeTimes() throws Exception {
        String inputPlate = "hbb1567",
                inputDate = "12-03-2020", //THURSDAY
                inputTime = "07:00";
        Plate plate = new Plate(inputPlate);
        PicoPlacaController pp = new PicoPlacaController(plate, PicoPlacaController.validateDateFormat(inputDate), PicoPlacaController.validateTimeFormat(inputTime));
        assertEquals("The car CANNOT be on the road", pp.validate());
    }

    @Test
    public void validateExtremeTimes2() throws Exception {
        String inputPlate = "hbb1567",
                inputDate = "12-03-2020", //THURSDAY
                inputTime = "09:30";
        Plate plate = new Plate(inputPlate);
        PicoPlacaController pp = new PicoPlacaController(plate, PicoPlacaController.validateDateFormat(inputDate), PicoPlacaController.validateTimeFormat(inputTime));
        assertEquals("The car CANNOT be on the road", pp.validate());
    }

    @Test
    public void validateExtremeTimes3() throws Exception {
        String inputPlate = "hbb1567",
                inputDate = "12-03-2020", //THURSDAY
                inputTime = "16:00";
        Plate plate = new Plate(inputPlate);
        PicoPlacaController pp = new PicoPlacaController(plate, PicoPlacaController.validateDateFormat(inputDate), PicoPlacaController.validateTimeFormat(inputTime));
        assertEquals("The car CANNOT be on the road", pp.validate());
    }

    @Test
    public void validateExtremeTimes4() throws Exception {
        String inputPlate = "hbb1567",
                inputDate = "12-03-2020", //THURSDAY
                inputTime = "19:30";
        Plate plate = new Plate(inputPlate);
        PicoPlacaController pp = new PicoPlacaController(plate, PicoPlacaController.validateDateFormat(inputDate), PicoPlacaController.validateTimeFormat(inputTime));
        assertEquals("The car CANNOT be on the road", pp.validate());
    }

    @Test
    public void validateMorningAllowedTimes() throws Exception {
        String inputPlate = "hbb1567",
                inputDate = "12-03-2020", //THURSDAY
                inputTime = "6:59";
        Plate plate = new Plate(inputPlate);
        PicoPlacaController pp = new PicoPlacaController(plate, PicoPlacaController.validateDateFormat(inputDate), PicoPlacaController.validateTimeFormat(inputTime));
        assertEquals("The car CAN be on the road", pp.validate());
    }

    @Test
    public void validateMorningAllowedTimes2() throws Exception {
        String inputPlate = "hbb1567",
                inputDate = "12-03-2020", //THURSDAY
                inputTime = "5:00";
        Plate plate = new Plate(inputPlate);
        PicoPlacaController pp = new PicoPlacaController(plate, PicoPlacaController.validateDateFormat(inputDate), PicoPlacaController.validateTimeFormat(inputTime));
        assertEquals("The car CAN be on the road", pp.validate());
    }

    @Test
    public void validateMorningAllowedTimes3() throws Exception {
        String inputPlate = "hbb1567",
                inputDate = "12-03-2020", //THURSDAY
                inputTime = "9:31";
        Plate plate = new Plate(inputPlate);
        PicoPlacaController pp = new PicoPlacaController(plate, PicoPlacaController.validateDateFormat(inputDate), PicoPlacaController.validateTimeFormat(inputTime));
        assertEquals("The car CAN be on the road", pp.validate());
    }

    @Test
    public void validateMorningAllowedTimes4() throws Exception {
        String inputPlate = "hbb1567",
                inputDate = "12-03-2020", //THURSDAY
                inputTime = "11:30";
        Plate plate = new Plate(inputPlate);
        PicoPlacaController pp = new PicoPlacaController(plate, PicoPlacaController.validateDateFormat(inputDate), PicoPlacaController.validateTimeFormat(inputTime));
        assertEquals("The car CAN be on the road", pp.validate());
    }

    @Test
    public void validateAfternoonAllowedTimes() throws Exception {
        String inputPlate = "hbb1567",
                inputDate = "12-03-2020", //THURSDAY
                inputTime = "15:59";
        Plate plate = new Plate(inputPlate);
        PicoPlacaController pp = new PicoPlacaController(plate, PicoPlacaController.validateDateFormat(inputDate), PicoPlacaController.validateTimeFormat(inputTime));
        assertEquals("The car CAN be on the road", pp.validate());
    }

    @Test
    public void validateAfternoonAllowedTimes2() throws Exception {
        String inputPlate = "hbb1567",
                inputDate = "12-03-2020", //THURSDAY
                inputTime = "14:00";
        Plate plate = new Plate(inputPlate);
        PicoPlacaController pp = new PicoPlacaController(plate, PicoPlacaController.validateDateFormat(inputDate), PicoPlacaController.validateTimeFormat(inputTime));
        assertEquals("The car CAN be on the road", pp.validate());
    }

    @Test
    public void validateAfternoonAllowedTimes3() throws Exception {
        String inputPlate = "hbb1567",
                inputDate = "12-03-2020", //THURSDAY
                inputTime = "19:31";
        Plate plate = new Plate(inputPlate);
        PicoPlacaController pp = new PicoPlacaController(plate, PicoPlacaController.validateDateFormat(inputDate), PicoPlacaController.validateTimeFormat(inputTime));
        assertEquals("The car CAN be on the road", pp.validate());
    }

    @Test
    public void validateAfternoonAllowedTimes4() throws Exception {
        String inputPlate = "hbb1567",
                inputDate = "12-03-2020", //THURSDAY
                inputTime = "20:30";
        Plate plate = new Plate(inputPlate);
        PicoPlacaController pp = new PicoPlacaController(plate, PicoPlacaController.validateDateFormat(inputDate), PicoPlacaController.validateTimeFormat(inputTime));
        assertEquals("The car CAN be on the road", pp.validate());
    }

    @Test
    public void validateAllowedDay() throws Exception {
        String inputPlate = "hbb1567",
                inputDate = "11-03-2020",//WEDNESDAY
                inputTime = "08:30";
        Plate plate = new Plate(inputPlate);
        PicoPlacaController pp = new PicoPlacaController(plate, PicoPlacaController.validateDateFormat(inputDate), PicoPlacaController.validateTimeFormat(inputTime));
        assertEquals("The car CAN be on the road", pp.validate());
    }

    @Test
    public void validateAllowedDay2() throws Exception {
        String inputPlate = "hbb1567",
                inputDate = "11-03-2020", //WEDNESDAY
                inputTime = "17:30";
        Plate plate = new Plate(inputPlate);
        PicoPlacaController pp = new PicoPlacaController(plate, PicoPlacaController.validateDateFormat(inputDate), PicoPlacaController.validateTimeFormat(inputTime));
        assertEquals("The car CAN be on the road", pp.validate());
    }


}