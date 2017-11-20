package metier;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public interface ICapteur {

    public String getNom();
    public StringProperty nomProperty() ;
    public void setNom(String nom);

    public IntegerProperty temperatureProperty();
    public Integer getTemperature();
    public void setTemperature();

    public void update();

}
