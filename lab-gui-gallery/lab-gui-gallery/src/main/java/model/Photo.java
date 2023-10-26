package model;

import java.io.ByteArrayInputStream;
import java.util.UUID;

import javafx.beans.property.*;
import javafx.scene.image.Image;


public class Photo {

    private StringProperty name = new SimpleStringProperty();

    private final Property<Image> photoData;

    public Photo(String extension, byte[] photoData) {
        this.photoData = new SimpleObjectProperty<Image>(new Image(new ByteArrayInputStream(photoData)));
        this.name.set( UUID.randomUUID().toString() + "." + extension);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Image getPhotoData() {
        return photoData.getValue();
    }

    public Property<Image> ImageProperty() {
        return photoData;
    }

    public StringProperty NameProperty() {
        return name;
    }
}
