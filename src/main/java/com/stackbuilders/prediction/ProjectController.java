package com.stackbuilders.prediction;

import com.stackbuilders.prediction.controller.PicoPlacaController;
import com.stackbuilders.prediction.model.PicoPlaca;
import com.stackbuilders.prediction.model.Plate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProjectController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("picoPlaca", new PicoPlaca());
        return "index";
    }

    @PostMapping("/predict")
    public String greetingSubmit(@ModelAttribute PicoPlaca picoPlaca, Model model) {
        try {
            PicoPlacaController ppc = new PicoPlacaController(
                    new Plate(picoPlaca.getPlate()),
                    PicoPlacaController.validateDateFormat(picoPlaca.getDate()),
                    PicoPlacaController.validateTimeFormat(picoPlaca.getTime()));
            picoPlaca.setPlate(picoPlaca.getPlate().toUpperCase());
            picoPlaca.setPrediction(ppc.validate());
        } catch (Exception e) {
            picoPlaca.setErrorMessage(e.getMessage());
            return "index";
        }
        model.addAttribute("picoPlaca", picoPlaca);
        return "prediction";
    }

}
