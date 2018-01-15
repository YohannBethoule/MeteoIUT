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


    static final String TITLE="Station Météo";
    static final String ERROR_SUPER="Aucun sous capteurs attribué, calcul de température impossible";
    static final String SUPER_CLASS="metier.sensor.SuperSensor";

    ObservableList<ISensor> sensors= FXCollections.observableArrayList();
    private ListProperty<ISensor> lSensors=new SimpleListProperty<>(sensors);

    @FXML private ListView<ISensor> listSensors=new ListView<>();

    @FXML TextField weight;
    @FXML TextField nbSensor;
    @FXML Button addSimpleToSuper;
    @FXML Button addSuperToSuper;
    @FXML Label lbNbSensor;
    @FXML Label lbWeight;

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
                            if(newValue.getClass().getName()==SUPER_CLASS){
                                changeSuperVisibility(true);
                            }
                            else{
                                changeSuperVisibility(false);
                            }
                    }
                });
    }
    private void changeSuperVisibility(boolean bool){
        lbNbSensor.setVisible(bool);
        lbWeight.setVisible(bool);
        addSimpleToSuper.setVisible(bool);
        addSuperToSuper.setVisible(bool);
        weight.setVisible(bool);
        nbSensor.setVisible(bool);
    }

<<<<<<< HEAD


    @FXML
    public void newSuperSensor()throws Exception{
        Stage stage=new Stage();
        SuperSensorCreation ctrlCreation;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/supersensor_creation.fxml"));
        stage.setScene(new Scene(loader.load()));
        ctrlCreation=loader.getController();
        ctrlCreation.setSensors(sensors);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle(TITLE);
        stage.show();


=======
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
    public void addSimpleToSuper(){
            newSubsSensor((SuperSensor)selectedSensor);
    }
    @FXML
    public void addSuperToSuper(){
        createSubsSuperSensor((SuperSensor)selectedSensor);
    }
    private void newSubsSensor(SuperSensor superSensor){
        ITemperatureGenerator generator = changeGeneration();
        for(int i=0; i<Integer.parseInt(nbSensor.getText());i++){
            SimpleSensor simpleSensor = new SimpleSensor(BASE_NAME+i,generator);
            superSensor.addSensor(simpleSensor,Integer.parseInt(weight.getText()));
            SensorThread initializedThread = new SensorThread(simpleSensor,1);
            initializedThread.start();
        }
    }
    private void createSubsSuperSensor(SuperSensor superSensor){
        for(int i=0; i<Integer.parseInt(nbSensor.getText());i++){
            SuperSensor subSuper = new SuperSensor(SUPER_NAME+i);
            superSensor.addSensor(subSuper,Integer.parseInt(weight.getText()));
            SensorThread initializedThread = new SensorThread(subSuper,1);
            initializedThread.start();
        }
    }
    @FXML
    public void newSuperSensor()throws Exception{
        String name= SUPER_NAME +(sensors.size());
        SuperSensor superSensor=new SuperSensor(name);
        sensors.add(superSensor);
        SensorThread initializedSuperThread = new SensorThread(superSensor,1);
        initializedSuperThread.start();
>>>>>>> 92c26a5fe4a2d73847e37f43ce4e6ff8d7d2bec9
    }

    @FXML
    public void newSensor() throws Exception{
        Stage stage=new Stage();
        SensorCreation ctrlCreation;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/sensor_creation.fxml"));
        stage.setScene(new Scene(loader.load()));
        ctrlCreation=loader.getController();
        ctrlCreation.setSensors(sensors);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle(TITLE);
        stage.show();
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
