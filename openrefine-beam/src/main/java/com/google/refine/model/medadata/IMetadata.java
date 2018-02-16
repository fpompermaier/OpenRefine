package com.google.refine.model.medadata;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONWriter;

import com.google.refine.Jsonizable;
import com.google.refine.operations.OperationRegistry;
import com.google.refine.preference.PreferenceStore;
/**
 * Interface to import/export metadata 
 */
public interface IMetadata extends Jsonizable {
    public void loadFromJSON(JSONObject obj);
    
    public void loadFromFile(File metadataFile);
    
    public void loadFromStream(InputStream inputStream);
    
    public void writeToFile(OperationRegistry opRegistry, PreferenceStore prefStore, File metadataFile);
    
    /**
     * @param jsonWriter writer to save metadatea to
     * @param onlyIfDirty true to not write unchanged metadata
     * @throws JSONException
     */
    public void write(OperationRegistry opRegistry, PreferenceStore prefStore, JSONWriter jsonWriter, boolean onlyIfDirty);
    
    public MetadataFormat getFormatName();
    public void setFormatName(MetadataFormat format);
    
    public LocalDateTime getModified();

    public void updateModified();
    
    public boolean isDirty();

    public JSONObject getJSON(OperationRegistry opRegistry, PreferenceStore prefStore);
    
    public List<Exception> validate();
}
