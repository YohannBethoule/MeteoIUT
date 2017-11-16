/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import meteoiut.CapteurSimple;
import meteoiut.ICapteur;

/**
 *
 * @author lulauriche
 */
public class ControllerDigital {
    private ICapteur cD;
    @FXML
    Label lbDigital;
    
    @FXML
    public void initialize(){
        cD = new CapteurSimple();
        lbDigital.setText(cD.getTemperature() + "°C");
    }
}
