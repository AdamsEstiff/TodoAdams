package cr.ac.unadeca.todoadams.database.models;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by pc on 5/2/2018.
 */
//declarando las constante del nombre de la base de datos y su version
@Database(name = TodoDatabase.NAME, version = TodoDatabase.VERSION)
public class TodoDatabase {
    public static final String NAME = "TodoAdams";

    public static final int VERSION = 2;
}
