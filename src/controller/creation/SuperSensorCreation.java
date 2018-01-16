package controller.creation;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import metier.sensor.ISensor;
import metier.sensor.SuperSensor;
import metier.thread.SensorThread;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * MainController of the super-sensor creation window
 */
public class SuperSensorCreation implements Initializable {
    static final String SUPER_NAME="[SUPER] sensor N° ";

    ObservableList<ISensor> sensors;
    private SuperSensor superSensor;
    private List<UCSuperSensorController>  lsuperSensor;

    @FXML
    ListView lSensors;

    @FXML
    VBox weightSettings;


    public void initialize(URL url, ResourceBundle rb){
        lSensors.setCellFactory(unused -> new ListCell<ISensor>(){
            @Override
            protected void updateItem(ISensor item, boolean empty) {
                super.updateItem(item, empty);
                if(!isEmpty()){
                    textProperty().bind(item.nameProperty());
                }else{
                    textProperty().unbind();
                    textProperty().setValue("");
                }
            }
        });

        lsuperSensor=new ArrayList<>();
    }

    /**
     * Add a sensor to the super sensor, and display a new user control to choose the weight of the new sensor.
     */
    public void addSensor(){
        ISensor sensor = (ISensor)lSensors.getSelectionModel().getSelectedItem();
        UCSuperSensorController uc=new UCSuperSensorController(sensor);
        lsuperSensor.add(uc);
        weightSettings.getChildren().add(uc);
    }

    /**
     * Set the list of all sensors.
     * @param sensors the observable list of all the sensors of the application
     */
    public void setSensors(ObservableList<ISensor> sensors){
        this.sensors=sensors;
        lSensors.setItems(this.sensors);
    }

    /**
     * Create the super sensor from the informations provided by the user controls, and add it the list of all sensors.
     */
    public void createSuperSensor(){
        int weight;
        ISensor sensor;
        String name= SUPER_NAME +(sensors.size());
        superSensor=new SuperSensor(name);
        for(UCSuperSensorController uc : lsuperSensor){
            sensor=uc.getSensor();
            weight=uc.getWeight();
            superSensor.addSensor(sensor, weight);
        }
        sensors.add(superSensor);
        SensorThread initializedThread = new SensorThread(superSensor,1);
        initializedThread.start();
        ((Stage)lSensors.getScene().getWindow()).close();
    }
}
