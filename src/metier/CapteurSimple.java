package metier;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CapteurSimple implements ICapteur {

    private StringProperty nom=new SimpleStringProperty();
    private IntegerProperty temperature=new SimpleIntegerProperty();
    private boolean b;


    public CapteurSimple(String nom){
        this.nom.set(nom);
        b=false;
        setTemperature();
    }


    public StringProperty nomProperty() {
        return nom;
    }
    public String getNom() { return nom.get(); }
    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public IntegerProperty temperatureProperty(){
        return temperature;
    }
    public Integer getTemperature(){ return temperature.get(); }
    public void setTemperature() {
        if(b) temperature.set(0);
        else temperature.set(40);
    }

    public void update(){
        b=!b;
        setTemperature();
    }
}
