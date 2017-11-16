/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meteoiut;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author lulauriche
 */
public class CapteurSimple implements ICapteur{
    private IntegerProperty temperatureProperty = new SimpleIntegerProperty();
    private boolean b;

    public void CapteurSimple(){
        b=false;
        setTemperature();
    }

    public void setTemperature() {
        if(b) this.temperatureProperty.set(0);
        else this.temperatureProperty.set(40);
    }
    
    @Override
    public int getTemperature(){
        return temperatureProperty.get();
    }
    public IntegerProperty temperatureProperty(){
        return temperatureProperty;
    }

    @Override
    public void update(){
        b=!b;
        setTemperature();
    }
}