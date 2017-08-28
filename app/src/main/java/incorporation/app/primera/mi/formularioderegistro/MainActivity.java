package incorporation.app.primera.mi.formularioderegistro;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private String nombre, apellido, password, repeat_password, correo, sexo, ciudad, hobbies = " ";
    private static String fecha = " ";
    private EditText eNombre, eApellido, ePass, ePassAgain, eCorreo;
    private RadioButton rMasculino, rFemenino;
    private static TextView fecha_muestra;
    private Spinner sCiudad;
    private CheckBox cCine, cDormir, cComer, cBailar;

    private TextView tInfo;
    private Button Regristro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eNombre = (EditText) findViewById(R.id.nombre);
        eApellido = (EditText) findViewById(R.id.apellido);
        ePass = (EditText) findViewById(R.id.password);
        ePassAgain = (EditText) findViewById(R.id.password_again);
        eCorreo = (EditText) findViewById(R.id.correo);
        rMasculino = (RadioButton) findViewById(R.id.masculino);
        rFemenino = (RadioButton) findViewById(R.id.femenino);
        sCiudad = (Spinner) findViewById(R.id.ciudad);
        cCine = (CheckBox) findViewById(R.id.cine);
        cDormir = (CheckBox) findViewById(R.id.dormir);
        cComer = (CheckBox) findViewById(R.id.comer);
        cBailar = (CheckBox) findViewById(R.id.bailar);

        fecha_muestra = (TextView) findViewById(R.id.fecha_muestra);
        tInfo = (TextView) findViewById(R.id.info);
        Regristro = (Button) findViewById(R.id.bRegistrar);

        //-------------------configurando el spinner--------------------------------------------------------
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Ciudades, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sCiudad.setAdapter(adapter);
//--------------------------------------------------------------------------------------------------
        sCiudad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ciudad = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//--------------------------------------------------------------------------------------------------
        Regristro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombre = " ";
                apellido = " ";
                password = " ";
                repeat_password = " ";
                correo = " ";
                hobbies = " ";


                nombre = eNombre.getText().toString();
                apellido = eApellido.getText().toString();
                password = ePass.getText().toString();
                repeat_password = ePassAgain.getText().toString();
                correo = eCorreo.getText().toString();
                if (rMasculino.isChecked()) {
                    sexo = "Masculino";
                } else {
                    sexo = "Femenino";
                }
                if (cCine.isChecked()) {
                    hobbies += "Cine ";
                }
                if (cDormir.isChecked()) {
                    hobbies += "Dormir ";
                }
                if (cComer.isChecked()) {
                    hobbies += "Comer ";
                }
                if (cBailar.isChecked()) {
                    hobbies += "bailar ";
                }
                if ((nombre.equals("")) || (apellido.equals("")) || (password.equals("")) || (repeat_password.equals("")) || (correo.equals("")) || (fecha.equals(" ")) || (hobbies.equals(" "))) {

                    Toast.makeText(getApplicationContext(), "¡¡Faltan campos por llenar!!", Toast.LENGTH_SHORT).show();

                } else if (!password.equals(repeat_password)) {

                    Toast.makeText(getApplicationContext(), "¡¡Contraseñas Diferentes!!", Toast.LENGTH_SHORT).show();

                } else {

                    tInfo.setText("Nombre: " + nombre + "\nApellido: " + apellido + "\nPassword: " + password + "\nCorreo: " + correo + "\nSexo: " + sexo + "\nFecha de nacimiento: " + fecha + "\nCiudad: " + ciudad + "\nHobbies: " + hobbies);

                }
            }
        });//fin onClick

    }//fin onCreate

    //----------------------------------------------------------------------------------------------
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c;
            int year = 1990, month = 1, day = 1;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
            }

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);

        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            month = month + 1;
            fecha = "" + day + "/" + month + "/" + year;
            fecha_muestra.setText(fecha);
        }
    }
    //----------------------------------------------------------------------------------------------

    public void pal_picker(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void limpiar(View view) {

        nombre = " ";
        apellido = " ";
        password = " ";
        repeat_password = " ";
        correo = " ";
        hobbies = " ";
        fecha = " ";

        eNombre.setText("");
        eApellido.setText("");
        ePass.setText("");
        ePassAgain.setText("");
        eCorreo.setText("");
        fecha_muestra.setText("");
        hobbies = "";
        tInfo.setText("");
    }

}
