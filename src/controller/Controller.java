package controller;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import metier.*;



public class Controller implements Initializable{
    static final String baseName="Sensor N°";

    ObservableList<DisplaySensor> sensors= FXCollections.observableArrayList();
    private ListProperty<DisplaySensor> lSensors=new SimpleListProperty<>(sensors);

    @FXML    private ListView<DisplaySensor> listSensors=new ListView<>();
    @FXML    Tab tabDigits;
    @FXML    HBox contentDigital;
    @FXML    Label lbDigital;
    @FXML    Tab tabThermometer;
    @FXML    ProgressBar thermometer;
    @FXML    Tab tabIcone;
    @FXML    VBox contentImg;
    @FXML    Label lbIndicator;
    @FXML    ImageView imgThermo = new ImageView();



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listSensors.itemsProperty().bind(lSensors);

        listSensors.setCellFactory(unused -> new ListCell<DisplaySensor>(){
            @Override
            protected void updateItem(DisplaySensor item, boolean empty) {
                super.updateItem(item, empty);
                if(!isEmpty()){
                    textProperty().bind(item.getISensor().nameProperty());
                }else{
                    textProperty().unbind();
                    textProperty().setValue(null);
                }
            }
        });

        listSensors.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<DisplaySensor>() {
                    public void changed(ObservableValue<? extends DisplaySensor> observable,
                                        DisplaySensor oldValue, DisplaySensor newValue) {
                        if(oldValue!=null)
                            unbindDetail(oldValue);
                        if(newValue!=null)
                            bindDetail(newValue);
                    }
                });

        viewTab(contentDigital,tabDigits);
        viewTab(thermometer,tabThermometer);
        viewTab(contentImg,tabIcone);
    }

    private void viewTab(Node children, Tab tab){

        final VBox display=new VBox();
        display.getChildren().add(children);
        display.setAlignment(Pos.CENTER);
        tab.setContent(display);
    }


    @FXML
    public void changeGeneration(){

    }

    //ECHEC DE TRANSTYPAGE, displayingSensor , JE N'ARRIVE PAS A GENERALISE
    // A LA CLASSE MERE POUR TOUS LES DISPLAY;
    // SI ON INSTANCIE AVANT CHAQUE BINDING DANS BINDDETAIL()
    // ON ARRIVE A BIND LA TOUTE PREMIERE VALEUR;
    @FXML
    public void newSensor(){
        String name=baseName+((List)sensors).size();
        ISensor c=new SimpleSensor(name, 10);
        DisplaySensor displayingSensor=new IconeDisplay(c);
        sensors.add(displayingSensor);
        SensorThread initializedThread = new SensorThread(c);
        initializedThread.start();
    }

    public void unbindDetail(DisplaySensor oldValue){
        lbDigital.textProperty().unbind();
        lbIndicator.textProperty().unbind();
        imgThermo.imageProperty().unbind();
        thermometer.progressProperty().unbind();
    }
    public void bindDetail(DisplaySensor newValue){
        lbDigital.textProperty().bind(newValue.getISensor().temperatureProperty().asString());
        lbIndicator.textProperty().bind(newValue.getISensor().temperatureProperty().asString());
        imgThermo.imageProperty().bind(newValue.display());
        thermometer.progressProperty().bind(newValue.display());


    }

}
