package pl.edu.agh.school.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import pl.edu.agh.school.persistence.IPersistenceManger;
import pl.edu.agh.school.persistence.SerializablePersistenceManager;


public class SchoolModule extends AbstractModule {

    @Provides
    public IPersistenceManger providePersistenceManager(SerializablePersistenceManager spm) {
        return spm;
    }

    @Provides
    @Named("guice-teachers")
    public String setTeachersStorageFileName() {
        return "guice-teachers.dat";
    }

    @Provides
    @Named("guice-classes")
    public String setClassStorageFileName() {
        return "guice-classes.dat";
    }

}
