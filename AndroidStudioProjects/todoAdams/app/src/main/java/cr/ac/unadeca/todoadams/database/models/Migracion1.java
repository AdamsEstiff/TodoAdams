package cr.ac.unadeca.todoadams.database.models;

import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.SQLiteType;
import com.raizlabs.android.dbflow.sql.migration.AlterTableMigration;

import cr.ac.unadeca.todoadams.database.models.models.TodoTable;

/**
 * Created by pc on 26/2/2018.
 */
@Migration(version=3, database = TodoDatabase.class)
public class Migracion1 extends AlterTableMigration<TodoTable>{

    public Migracion1(Class<TodoTable> table) {
        super(table);
    }

    @Override
    public void onPreMigrate() {
        super.onPreMigrate();
        addColumn(SQLiteType.INTEGER,  "estado");
    }
}
