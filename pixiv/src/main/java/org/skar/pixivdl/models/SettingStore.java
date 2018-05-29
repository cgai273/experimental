package org.skar.pixivdl.models;

import javafx.beans.property.*;

import java.io.File;

public class SettingStore {
    ObjectProperty<File> saveLocation = new SimpleObjectProperty<>();
    BooleanProperty favFilter = new SimpleBooleanProperty(false);
    IntegerProperty favFilterCount = new SimpleIntegerProperty(0);

    public SettingStore() {

    }

    public void setSaveLocation(File directory) {
        saveLocation.setValue(directory);
    }

    public File getSaveLocation() {
        return saveLocation.getValue();
    }

    public Integer getFavFilterCount() {
        return favFilterCount.get();
    }

    public Boolean isFavFilterActive() {
        return favFilter.get();
    }

    public void updateFavFilterCount(Integer i) {
        favFilterCount.set(i);
    }

    public void toggleViewFilter(Boolean enable) {
        favFilter.setValue(enable);
    }
}
