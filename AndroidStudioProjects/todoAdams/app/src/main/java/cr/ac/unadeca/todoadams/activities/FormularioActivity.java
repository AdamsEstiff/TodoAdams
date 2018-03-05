package cr.ac.unadeca.todoadams.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cr.ac.unadeca.todoadams.R;
import cr.ac.unadeca.todoadams.database.models.models.TodoTable;

public class FormularioActivity extends AppCompatActivity {
    private TextView lblNombre, lblActividad;
    private EditText txtNombre, txtActividad;
    private Button btnSave;

        @Override
        protected void onCreate (Bundle savedInstanceState)
        {//inicia la actividad y atrae los campos y demas
            super.onCreate(savedInstanceState);
            setContentView(R.layout.formulario);//definir el diseño de formulario
            lblNombre = findViewById(R.id.lblNombre);//variables locales que se le establecen a la actividad como haciamos en C#
            lblActividad = findViewById(R.id.lblActividad);//esto se hace de esta manera para que se maneje de forma local
            txtNombre = findViewById(R.id.txtNombre);
            txtActividad = findViewById(R.id.txtActividad);
            btnSave = findViewById(R.id.btnSave);
            //Este es el evento para que el botton guarde la información
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    guardar();
                }
            });
        }
        //Todos los fomrularios tienen que tener validacion para ver si la informacion es correcta

    private boolean validacion() {
        boolean send = true;
        if (txtNombre.getText().toString().isEmpty()) {
            send = false;

        }
        if (txtActividad.getText().toString().isEmpty()) {
            send = false;
        }
        return send;
    }

    private void guardar() {
        if (validacion()) {
            //para guardar informacion
            TodoTable registro = new TodoTable();
            registro.nombre = txtNombre.getText().toString();
            registro.actividad = txtActividad.getText().toString();
            registro.save();
            finish();
        } else {
            //para mensajes de error segun la validacion
            Toast.makeText(this, getResources().getString(R.string.error_valid), Toast.LENGTH_LONG).show();
        }
    }
}

