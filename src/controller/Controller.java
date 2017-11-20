package controller;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import metier.*;


public class Controller implements Initializable{
    ObservableList<ICapteur> capteurs= FXCollections.observableArrayList();
    private ListProperty<ICapteur> lCapteurs=new SimpleListProperty<>(capteurs);
    @FXML
    private ListView<ICapteur> listCapteurs=new ListView<>();

    @FXML
    Label lbDigital;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listCapteurs.itemsProperty().bind(lCapteurs);

        listCapteurs.setCellFactory(unused -> new ListCell<ICapteur>(){
            @Override
            protected void updateItem(ICapteur item, boolean empty) {
                super.updateItem(item, empty);
                if(!isEmpty()){
                    textProperty().bind(item.nomProperty());
                }
            }
        });

        listCapteurs.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<ICapteur>() {
                    public void changed(ObservableValue<? extends ICapteur> observable,
                                        ICapteur oldValue, ICapteur newValue) {
                        if(oldValue!=null)
                            unbindDetail(oldValue);
                        if(newValue!=null)
                            bindDetail(newValue);
                    }
                });


    }


    @FXML
    public void newCapteur(){
        String nom="Capteur N°"+((List)capteurs).size();
        ICapteur c=new CapteurSimple(nom);
        capteurs.add(c);
    }

    public void unbindDetail(ICapteur oldValue){
        lbDigital.textProperty().unbind();
    }

    public void bindDetail(ICapteur newValue){
        lbDigital.setText(newValue.getTemperature().toString());
    }
}
