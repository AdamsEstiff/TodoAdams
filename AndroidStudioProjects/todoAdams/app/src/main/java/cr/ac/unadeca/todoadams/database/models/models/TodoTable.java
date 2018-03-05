package cr.ac.unadeca.todoadams.database.models.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import cr.ac.unadeca.todoadams.database.models.TodoDatabase;

/**
 * Created by pc on 5/2/2018.
 */
@Table(database = TodoDatabase.class)
public class TodoTable extends BaseModel {
    //no utilizamos int
    @PrimaryKey(autoincrement = true)
    public long id;
    @Column
    public String nombre;
    @Column
    public String actividad;
    @Column
    public int estado;
}
