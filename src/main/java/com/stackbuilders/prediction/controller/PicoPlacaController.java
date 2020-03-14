package com.stackbuilders.prediction.controller;

import com.stackbuilders.prediction.model.Plate;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PicoPlacaController {
    static final String DATE_PATTERN = "dd-MM-yyyy";
    static final String TIME_PATTERN = "HH:mm";
    private Date dateToValidate;
    private Date timeToValidate;
    private Plate plateToValidate;
    private HashMap<Integer, String> restrictedNumbers;
    private List<Date> restrictedTimes;

    public PicoPlacaController(Plate plateToValidate, Date dateToValidate, Date timeToValidate) throws Exception {
        this.dateToValidate = dateToValidate;
        this.timeToValidate = timeToValidate;
        this.plateToValidate = plateToValidate;
        this.restrictedNumbers = new HashMap<>();
        this.restrictedTimes = new ArrayList<>();
        this.loadConfigurations();
    }

    public static Date validateDateFormat(String date) throws ParseException {
        return validateDateTimeFormat(date, PicoPlacaController.DATE_PATTERN);
    }

    public static Date validateTimeFormat(String time) throws ParseException {
        return validateDateTimeFormat(time, PicoPlacaController.TIME_PATTERN);
    }

    /*Function to validate whether a string */
    private static Date validateDateTimeFormat(String dateTime, String dateTimePattern) throws ParseException {
        SimpleDateFormat simpleDF = new SimpleDateFormat(dateTimePattern);
        simpleDF.setLenient(false);
        try {
            return simpleDF.parse(dateTime);
        } catch (ParseException e) {
            throw new ParseException("Date/Time format is incorrect. The required pattern is: " + dateTimePattern, e.getErrorOffset());
        }
    }

    public String validate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.dateToValidate);
        if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY &&
                calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            int currentDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            String restrictedPlateNumbers = this.restrictedNumbers.get(currentDay == 0 ? 7 : currentDay);
            for (int i = 0; i < this.restrictedTimes.size(); i += 2) {
                Date startTime = this.restrictedTimes.get(i);
                Date endTime = this.restrictedTimes.get(i + 1);
                if ((this.timeToValidate.equals(startTime) || this.timeToValidate.after(startTime)) &&
                        (this.timeToValidate.equals(endTime) || this.timeToValidate.before(endTime))) {
                    if (restrictedPlateNumbers.contains(Integer.toString(plateToValidate.getLastNumber()))) {
                        return ("The car CANNOT be on the road");
                    }
                }
            }
        }
        return ("The car CAN be on the road");
    }

    private void loadConfigurations() throws Exception {
        InputStream inputStream = new ClassPathResource("config/PicoPlacaConfig.xml").getInputStream();
        DocumentBuilderFactory documentBF = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentB = documentBF.newDocumentBuilder();
            Document document = documentB.parse(inputStream);

            NodeList restrictions = document.getElementsByTagName("restriction");

            for (int i = 0; i < restrictions.getLength(); i++) {
                Node node = restrictions.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    this.restrictedNumbers.put(
                            Integer.parseInt(element.getAttribute("dayNumber")),
                            element.getElementsByTagName("numbers").item(0).getTextContent());
                }
            }

            NodeList restrictedTimes = document.getElementsByTagName("restrictedTime");

            for (int j = 0; j < restrictedTimes.getLength(); j++) {
                String restrictedTime = restrictedTimes.item(j).getTextContent();
                String[] startend = restrictedTime.split("-");
                this.restrictedTimes.add(PicoPlacaController.validateTimeFormat(startend[0]));
                this.restrictedTimes.add(PicoPlacaController.validateTimeFormat(startend[1]));
            }
        } catch (SAXException | IOException | ParserConfigurationException | ParseException e) {
            throw new Exception("Error in the configuration file");
        }
    }
}
