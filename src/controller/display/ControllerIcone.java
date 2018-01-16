package controller.display;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import metier.sensor.ISensor;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerIcone extends ControllerDisplay {
    static final String SNOW = "/ressources/flocon.png" ;
    static final String SUN = "/ressources/sun.png";
    static final String CLOUD = "/ressources/cloud.png";

    @FXML ImageView imgThermo;
    @FXML Label lbName;
    @FXML Label lbIndicator;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setSensor(ISensor sensor){
        this.sensor=sensor;

        lbName.textProperty().bind(this.sensor.nameProperty());
        lbIndicator.textProperty().bind(this.sensor.temperatureProperty().asString());
        sensor.temperatureProperty().addListener((o,oldV,newV)->{
            if ((double) newV < 0) imgThermo.setImage(new Image(SNOW));
            if ((double) newV > 20) imgThermo.setImage(new Image(SUN));
            if ((double) newV >= 0 && (double) newV <= 20) imgThermo.setImage(new Image(CLOUD));
        });
    }
}
