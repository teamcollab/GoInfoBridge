package goinfo;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * Created by Spooky on 2013/12/26.
 */
public class TestDataBaseCreater {
    public static void createDatabase(){
        EmbeddedDatabaseBuilder builderMajor = new EmbeddedDatabaseBuilder();
        builderMajor = builderMajor.setType(EmbeddedDatabaseType.HSQL).addScript(
                "majorSchema.sql");
        builderMajor.setName("majorDb");
        builderMajor.build();

        EmbeddedDatabaseBuilder builderMinor = new EmbeddedDatabaseBuilder();
        builderMinor = builderMinor.setType(EmbeddedDatabaseType.HSQL).addScript(
                "minorSchema.sql");
        builderMinor.setName("minorDb");
        builderMinor.build();

    }
}
