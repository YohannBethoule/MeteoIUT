package controller;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.layout.VBox;
import metier.*;


public class Controller implements Initializable{
    static final String baseName="Sensor N°";

    private Thread initializeThread;

    ObservableList<ISensor> sensors= FXCollections.observableArrayList();
    private ListProperty<ISensor> lSensors=new SimpleListProperty<>(sensors);
    @FXML
    private ListView<ISensor> listSensors=new ListView<>();

    @FXML
    Label lbDigital;

    @FXML
    Button updateButton;

    @FXML
    Tab tabDigits;

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
                    textProperty().setValue(null);
                }
            }
        });

        listSensors.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<ISensor>() {
                    public void changed(ObservableValue<? extends ISensor> observable,
                                        ISensor oldValue, ISensor newValue) {
                        if(oldValue!=null)
                            unbindDetail(oldValue);
                        if(newValue!=null)
                            bindDetail(newValue);
                    }
                });

        final VBox displayDigits=new VBox();
        displayDigits.getChildren().add(lbDigital);
        tabDigits.setContent(displayDigits);
    }

    @FXML
    public void changeGeneration(){

    }

    @FXML
    public void newSensor(){
        String name=baseName+((List)sensors).size();
        ISensor c=new SimpleSensor(name, 10);
        sensors.add(c);
        initializeThread=new Thread(new RunnableSensor(c));
        initializeThread.start();
    }

    public void unbindDetail(ISensor oldValue){
        lbDigital.textProperty().unbind();
    }

    public void bindDetail(ISensor newValue){
        lbDigital.textProperty().bind(newValue.temperatureProperty().asString());
    }

    @FXML
    public void updateSensor(){
        listSensors.getSelectionModel().getSelectedItem().update();
    }
}
