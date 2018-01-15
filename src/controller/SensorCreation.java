package controller;

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

public class SensorCreation implements Initializable{
    static final String BASE_NAME ="sensor N°";

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

    public void setSensors(ObservableList<ISensor> sensors){
        this.sensors=sensors;
    }


    public ITemperatureGenerator changeGeneration(){
        switch(choiceGenerator.getText()){
            case "Intervalle" :
                return min.getText()!= null && max.getText()!= null || min.getText()!="" && max.getText()!="" ?
                        new IntervalGeneration(Integer.parseInt(min.getText()),Integer.parseInt(max.getText())) : new RandomGeneration();
            case "Relative" :
                return fixedTemp.getText()!=null && variationInterval.getText()!=null || fixedTemp.getText()!="" && variationInterval.getText()!="" ?
                        new RelativeGeneration(Integer.parseInt(fixedTemp.getText()),Integer.parseInt(variationInterval.getText())) : new RandomGeneration() ;
            default :
                return new RandomGeneration();

        }
    }

    public void createSensor(){
        String name= BASE_NAME +(sensors.size());
        ITemperatureGenerator generator = changeGeneration();
        ISensor isensor=new SimpleSensor(name,generator);
        sensors.add(isensor);
        SensorThread initializedThread = new SensorThread(isensor,1);
        initializedThread.start();
        ((Stage)max.getScene().getWindow()).close();
    }


    private void changeIntervalVisibility(boolean bool){
        max.setVisible(bool);
        min.setVisible(bool);
        lbMax.setVisible(bool);
        lbMin.setVisible(bool);
    }
    private void changeRelativeVisibility(boolean bool){
        lbRelativeTemp.setVisible(bool);
        lbIntervalRelative.setVisible(bool);
        fixedTemp.setVisible(bool);
        variationInterval.setVisible(bool);
    }
    public void generateInterval(){
        choiceGenerator.setText("Intervalle");
        changeIntervalVisibility(true);
        changeRelativeVisibility(false);
    }
    public void generateRelative(){
        choiceGenerator.setText("Relative");
        changeIntervalVisibility(false);
        changeRelativeVisibility(true);
    }
    public void generateRandom(){
        choiceGenerator.setText("Aléatoire");
        changeIntervalVisibility(false);
        changeRelativeVisibility(false);
    }
}
