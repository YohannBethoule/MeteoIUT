package controller;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.stage.Stage;
import metier.generation.ITemperatureGenerator;
import metier.generation.IntervalGeneration;
import metier.generation.RandomGeneration;
import metier.generation.RelativeGeneration;
import metier.sensor.ISensor;
import metier.sensor.SimpleSensor;
import metier.sensor.SuperSensor;
import metier.thread.SensorThread;


public class Controller implements Initializable{
    static final String BASE_NAME ="Sensor N°";
    static final String SUPER_NAME="[SUPER] Sensor N° ";
    static final String TITLE="Station Météo";
    static final String ERROR_SUPER="Aucun sous capteurs attribué, calcul de température impossible";

    ObservableList<ISensor> sensors= FXCollections.observableArrayList();
    private ListProperty<ISensor> lSensors=new SimpleListProperty<>(sensors);

    @FXML private ListView<ISensor> listSensors=new ListView<>();
    @FXML MenuButton choiceGenerator;
    @FXML Label lbMax;
    @FXML Label lbMin;
    @FXML TextField max;
    @FXML TextField min;
    @FXML Label lbRelativeTemp;
    @FXML Label lbIntervalRelative;
    @FXML TextField fixedTemp;
    @FXML TextField variationInterval;
    @FXML TextField weight;
    @FXML TextField nbSensor;

    private ISensor selectedSensor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listSensors.itemsProperty().bind(lSensors);

        listSensors.setCellFactory(unused -> new ListCell<ISensor>(){
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

        listSensors.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<ISensor>() {
                    public void changed(ObservableValue<? extends ISensor> observable,
                                        ISensor oldValue, ISensor newValue) {
                        if(newValue!=null)
                            selectedSensor=newValue;
                    }
                });
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
    @FXML
    public void newSuperSensor()throws Exception{
        String name= SUPER_NAME +(sensors.size());
        ISensor isensor=new SuperSensor(name);;
        if(nbSensor.getText()!=null && Integer.parseInt(nbSensor.getText())>0){
           for(int i=0; i<Integer.parseInt(nbSensor.getText());i++){
              // isensor.addSensor(new SimpleSensor(BASE_NAME+i,new RandomGeneration()));
           }

        }
        else{
            throw new Exception(ERROR_SUPER);
        }
        sensors.add(isensor);
        SensorThread initializedThread = new SensorThread(isensor,1);
        initializedThread.start();
    }

    @FXML
    public void newSensor(){
        String name= BASE_NAME +(sensors.size());
        ITemperatureGenerator generator = changeGeneration();
        ISensor isensor=new SimpleSensor(name,generator);
        sensors.add(isensor);
        SensorThread initializedThread = new SensorThread(isensor,1);
        initializedThread.start();
    }

    @FXML
    public void digitalView() throws Exception{
        ControllerDigital ctrlDigital;
        Stage stage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/digital_view.fxml"));
        stage.setScene(new Scene(loader.load()));
        ctrlDigital=loader.getController();
        ctrlDigital.setSensor(selectedSensor);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle(TITLE);
        stage.show();
    }
    @FXML
    public void iconeView()throws Exception{
        ControllerIcone ctrlIcone;
        Stage stage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/icone_view.fxml"));
        stage.setScene(new Scene(loader.load()));
        ctrlIcone=loader.getController();
        ctrlIcone.setSensor(selectedSensor);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle(TITLE);
        stage.show();
    }
    @FXML
    public void thermometerView()throws Exception{
        ControllerThermometer ctrlThermometer;
        Stage stage=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/thermometer_view.fxml"));
        stage.setScene(new Scene(loader.load()));
        ctrlThermometer=loader.getController();
        ctrlThermometer.setSensor(selectedSensor);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle(TITLE);
        stage.show();
    }

}
