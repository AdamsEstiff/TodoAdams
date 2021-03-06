package cr.ac.unadeca.todoadams.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.sufficientlysecure.htmltextview.HtmlResImageGetter;

import java.util.List;

import cr.ac.unadeca.todoadams.R;
import cr.ac.unadeca.todoadams.database.models.models.TodoTable;
import cr.ac.unadeca.todoadams.database.models.models.TodoTable_Table;
import cr.ac.unadeca.todoadams.subclase.ToDoViewHolder;

public class MainActivity extends AppCompatActivity {
private RecyclerView lista;
private static Context QuickContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuickContext = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                TodoTable actividad1= new TodoTable();
//                actividad1.nombre="Juan Pablo";
//                actividad1.actividad="Limpiar el carro";
//                actividad1.save();
//                TodoTable actividad = SQLite.select().from(TodoTable.class).querySingle();
//                if(actividad1 !=null) {
//                    Snackbar.make(view, "se obtuvo la actividad " + actividad.actividad + " Para usuario " + actividad.nombre+ " con id "+actividad.id, Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show();
//                }
                //   SQLite.delete(TodoTable.class).where(TodoTable_Table.id.is((long)1)).execute();
                //    SQLite.update(TodoTable.class).set(TodoTable_Table.actividad.eq("Limpiar la casa")).where(TodoTable_Table.id.is((long)1));
                Intent actividad = new Intent(getApplicationContext(),FormularioActivity.class);

                getApplicationContext().startActivity(actividad);
            }
        });
        lista = findViewById(R.id.lista);
        lista.setLayoutManager(new LinearLayoutManager(this));
        List<TodoTable>info =SQLite.select().from(TodoTable.class).queryList();
        lista.setAdapter(new ToDoAdapter(info));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            List<TodoTable> info = SQLite.select().from(TodoTable.class).queryList();
            lista.setAdapter(new ToDoAdapter(info));
        }
        return super.onOptionsItemSelected(item);
    }
    public static class ToDoAdapter extends RecyclerView.Adapter<ToDoViewHolder> {
        private final List<TodoTable> listToDoTable;
        private final LayoutInflater inflater;

        public ToDoAdapter(List<TodoTable> listToDoTables) {
            this.inflater = LayoutInflater.from(QuickContext);
            this.listToDoTable = listToDoTables;
        }

        @Override
        public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.objecto, parent, false);
            return new ToDoViewHolder(view);
        }

        public void animateTo(List<TodoTable> models) {
            applyAndAnimateRemovals(models);
            applyAndAnimateAdditions(models);
            applyAndAnimateMovedItems(models);
        }

        private void applyAndAnimateRemovals(List<TodoTable> newModels) {
            for (int i = listToDoTable.size() - 1; i >= 0; i--) {
                final TodoTable model = listToDoTable.get(i);
                if (!newModels.contains(model)) {
                    removeItem(i);
                }
            }
        }

        private void applyAndAnimateAdditions(List<TodoTable> newModels) {
            for (int i = 0, count = newModels.size(); i < count; i++) {
                final TodoTable model = newModels.get(i);
                if (!listToDoTable.contains(model)) {
                    addItem(i, model);
                }
            }
        }

        private void applyAndAnimateMovedItems(List<TodoTable> newModels) {
            for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
                final TodoTable model = newModels.get(toPosition);
                final int fromPosition = listToDoTable.indexOf(model);
                if (fromPosition >= 0 && fromPosition != toPosition) {
                    moveItem(fromPosition, toPosition);
                }
            }
        }

        public TodoTable removeItem(int position) {
            final TodoTable model = listToDoTable.remove(position);
            notifyItemRemoved(position);
            return model;
        }

        public void addItem(int position, TodoTable model) {
            listToDoTable.add(position, model);
            notifyItemInserted(position);
        }

        public void moveItem(int fromPosition, int toPosition) {
            final TodoTable model = listToDoTable.remove(fromPosition);
            listToDoTable.add(toPosition, model);
            notifyItemMoved(fromPosition, toPosition);
        }
        @Override
        public void onBindViewHolder(final ToDoViewHolder holder, final int position) {
            final TodoTable current = listToDoTable.get(position);
            holder.html.setHtml(ActividadAString(current),
                    new HtmlResImageGetter(holder.html));
            holder.html.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (current.estado<2){
                        current.estado=2;
                    }else {
                        current.estado=1;

                    }
                    notifyDataSetChanged();
                }
            });
            holder.borrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    current.delete();
                    removeItem(position);
                    notifyDataSetChanged();
                }
            });
        }
        private  String ActividadAString(TodoTable todo){
            String color = "#000000";

            if (todo.estado==2){
                color="#bc20aa";

            }


            String html= "<a><big><b> <font color =\""+ color +"\">"+todo.nombre+"<b></big>";
            html+="<br>"+ todo.actividad +"</a>";
            return html;
        }
        @Override
        public int getItemCount() {
            return listToDoTable.size();
        }

    }

}
