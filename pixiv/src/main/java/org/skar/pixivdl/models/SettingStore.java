package org.skar.pixivdl.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.File;

public class SettingStore {
    ObjectProperty<File> saveLocation = new SimpleObjectProperty<>();

    public SettingStore() {

    }

    public void setSaveLocation(File directory) {
        saveLocation.setValue(directory);
    }

    public File getSaveLocation() {
        return saveLocation.getValue();
    }
}
