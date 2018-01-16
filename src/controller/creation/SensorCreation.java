package controller.creation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import metier.generation.ITemperatureGenerator;
import metier.generation.IntervalGeneration;
import metier.generation.RandomGeneration;
import metier.generation.RelativeGeneration;
import metier.sensor.ISensor;
import metier.sensor.SimpleSensor;
import metier.thread.SensorThread;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * MainController of the sensor creation window
 */
public class SensorCreation implements Initializable{
    static final String BASE_NAME ="sensor N°";

    /**
     * Observable list of all sensors
     */
    ObservableList<ISensor> sensors= FXCollections.observableArrayList();


    @FXML
    MenuButton choiceGenerator;
    @FXML
    Label lbMax;
    @FXML Label lbMin;
    @FXML
    TextField max;
    @FXML TextField min;
    @FXML Label lbRelativeTemp;
    @FXML Label lbIntervalRelative;
    @FXML TextField fixedTemp;
    @FXML TextField variationInterval;

    @Override
    public void initialize(URL url, ResourceBundle rb){

    }

    /**
     * Set the list of all sensors.
     * @param sensors the observable list of all the sensors of the application
     */
    public void setSensors(ObservableList<ISensor> sensors){
        this.sensors=sensors;
    }

    /**
     * Get the temperature generator of the sensor according to the selected item of the combo box.
     * @return an ITemperatureGenerator set accordingly to user's choice
     */
    public ITemperatureGenerator changeGeneration(){
        switch(choiceGenerator.getText()){
            case "Intervalle" :
                return min.getText()!= null && max.getText()!= null || min.getText()!="" && max.getText()!="" ?
                        new IntervalGeneration(Integer.parseInt(min.getText()),Integer.parseInt(max.getText())) : new RandomGeneration();
            case "Relative" :
                return fixedTemp.getText()!=null && variationInterval.getText()!=null || fixedTemp.getText()!="" && variationInterval.getText()!="" ?
                        new RelativeGeneration(Double.parseDouble(fixedTemp.getText()),Integer.parseInt(variationInterval.getText())) : new RandomGeneration() ;
            default :
                return new RandomGeneration();

        }
    }

    /**
     * Create a simple sensor from informations set in the controls of the window, and add it to the list of all sensors of the application.
     */
    public void createSensor(){
        String name= BASE_NAME +(sensors.size());
        ITemperatureGenerator generator = changeGeneration();
        ISensor isensor=new SimpleSensor(name,generator);
        sensors.add(isensor);
        SensorThread initializedThread = new SensorThread(isensor,1);
        initializedThread.start();
        ((Stage)max.getScene().getWindow()).close();
    }

    /**
     * Change the visibility of the controls necessary to set an interval generation method.
     * @param bool true if interval method is selected
     */
    private void changeIntervalVisibility(boolean bool){
        max.setVisible(bool);
        min.setVisible(bool);
        lbMax.setVisible(bool);
        lbMin.setVisible(bool);
    }

    /**
     * Change the visibility of the controls necessary to set a relative generation method.
     * @param bool true if relative method is selected
     */
    private void changeRelativeVisibility(boolean bool){
        lbRelativeTemp.setVisible(bool);
        lbIntervalRelative.setVisible(bool);
        fixedTemp.setVisible(bool);
        variationInterval.setVisible(bool);
    }

    /**
     * Change the choice of the generation method to interval generation. Display the controls necessary to set an interval generation method, and
     * hide other controls.
     */
    public void generateInterval(){
        choiceGenerator.setText("Intervalle");
        changeIntervalVisibility(true);
        changeRelativeVisibility(false);
    }

    /**
     * Change the choice of the generation method to relative generation. Display the controls necessary to set a relative generation method, and
     * hide other controls.
     */
    public void generateRelative(){
        choiceGenerator.setText("Relative");
        changeIntervalVisibility(false);
        changeRelativeVisibility(true);
    }

    /**
     * Change the choice of the generation method to random generation. Hide others controls.
     */
    public void generateRandom(){
        choiceGenerator.setText("Aléatoire");
        changeIntervalVisibility(false);
        changeRelativeVisibility(false);
    }
}
