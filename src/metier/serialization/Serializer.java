package metier.serialization;

import metier.sensor.ISensor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Serializer {
    private final static String DATA_FILE="ressources/data.xml";
    /**
     * Load all the ISensors
     *
     * @return the list of ISensors loaded
     */
    public static List<ISensor> loadSensors() {
        List<ISensor> result = null;
        try (XMLDecoder ois = new XMLDecoder(new FileInputStream(DATA_FILE))) {
            result = ((ArrayList<SerializableSensor>)ois.readObject()).stream().map(n -> n.getSensor()).collect(Collectors.toList());
        }
        catch (IOException e) {
            System.err.println("Error reading xml file");
        }
        return result;
    }

    /**
     * Save sensors using the XML serialization
     *
     * @param sensors all the ISensors to serialize
     */
    public static void saveSensors(List<ISensor> sensors) {
        try (XMLEncoder oos = new XMLEncoder(new FileOutputStream(DATA_FILE))) {
            List<SerializableSensor> bn = sensors.stream().map(n -> new SerializableSensor(n)).collect(Collectors.toList());
            oos.writeObject(bn);
        }
        catch (IOException e) {
            System.err.println("Error writing xml file");
        }
    }
}
