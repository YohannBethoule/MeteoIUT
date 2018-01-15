package controller;

import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageViewWithPath extends ImageView{
    private StringProperty path;

    public ImageViewWithPath(Image value, StringProperty path){
        super(value);
        this.path=path;
    }
}
