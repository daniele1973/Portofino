package com.manydesigns.portofino.model.io;

import com.manydesigns.portofino.model.Model;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;

import jakarta.xml.bind.JAXBException;
import java.io.IOException;

public interface ModelIO {
    Model load() throws IOException;

    FileObject getModelDirectory() throws FileSystemException;

    void save(Model model) throws IOException;

    void delete() throws IOException;
}
