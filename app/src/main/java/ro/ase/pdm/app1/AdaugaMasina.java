package ro.ase.pdm.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AdaugaMasina extends AppCompatActivity {

    private TextView tvData;
    private Button btnData;
    private Button btnSubmit;

    private TextView tvMarca;
    private TextView tvAn;
    private RadioGroup rgTipMasina;

    private Masina masinaInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_masina);

        tvData = findViewById(R.id.tbData);
        btnData = findViewById(R.id.buttonData);

        MaterialDatePicker materialDatePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Select Date").setSelection(MaterialDatePicker.todayInUtcMilliseconds()).build();

        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), "Tag_Picker");
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        tvData.setText(materialDatePicker.getHeaderText());
                    }
                });
            }
        });

        btnSubmit = findViewById(R.id.buttonSubmit);

        tvMarca = findViewById(R.id.MarcaTextBox);
        tvAn = findViewById(R.id.AnTextBox);
        rgTipMasina = findViewById(R.id.radioGroupTip);


        btnSubmit.setOnClickListener(e -> {

            if (validations()) {
                Intent intent = new Intent();
                intent.putExtra("MASINA", masinaInsert);
                setResult(RESULT_OK, intent);
                finish();
            }

        });

    }

    private boolean validations() {
        if (tvMarca.getText().toString().isEmpty()) {
            Toast.makeText(this, "Marca nu trebuie sa fie nula!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (tvAn.getText().toString().isEmpty()) {
            Toast.makeText(this, "Anul nu trebuie sa fie nul!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (tvData.getText().toString().isEmpty()) {
            Toast.makeText(this, "Data nu trebuie sa fie nula!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (rgTipMasina.getCheckedRadioButtonId() != R.id.radioButtonElectrica &&
                rgTipMasina.getCheckedRadioButtonId() != R.id.radioButtonBenzina &&
                rgTipMasina.getCheckedRadioButtonId() != R.id.radioButtonDiesel) {
            Toast.makeText(this, "Tipul de masina trebuie sa fie selectat!", Toast.LENGTH_SHORT).show();
            return false;
        }

        masinaInsert = new Masina();
        masinaInsert.setMarca(tvMarca.getText().toString());
        masinaInsert.setAnProductie(Integer.parseInt(tvAn.getText().toString()));

        if (rgTipMasina.getCheckedRadioButtonId() == R.id.radioButtonElectrica) {
            masinaInsert.setTip(TipMasina.ELECTRICA);
        } else if (rgTipMasina.getCheckedRadioButtonId() == R.id.radioButtonDiesel) {
            masinaInsert.setTip(TipMasina.DIESEL);
        } else {
            masinaInsert.setTip(TipMasina.BENZINA);
        }

        try {
            masinaInsert.setDataVanzare(new SimpleDateFormat("MMM dd,yyyy").parse(tvData.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return true;
    }

}