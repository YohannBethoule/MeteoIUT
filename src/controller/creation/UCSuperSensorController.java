package controller.creation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import metier.sensor.ISensor;

import java.io.IOException;

public class UCSuperSensorController extends HBox {
    @FXML
    Label sensorName;

    @FXML
    ComboBox<Integer> weight;

    private ISensor sensor;

    public UCSuperSensorController(ISensor sensor){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vues/UCSuperSensor.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.sensor=sensor;
        sensorName.setText(sensor.getName());
        weight.getSelectionModel().selectFirst();
    }

    /**
     * @return the sensor displayed by the user control
     */
    public ISensor getSensor(){
        return sensor;
    }

    /**
     * @return the selected weight for the sensor
     */
    public int getWeight() {
        return weight.getValue();
    }
}
