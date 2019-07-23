package com.example.lenovo.mobilbasagrisi;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.lenovo.mobilbasagrisi.DbModel.KayitModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AgriEkle extends AppCompatActivity {
    DatabaseHelper myDb;
    TextView mItemSelected,ilacSelected;

    Button tetikleyici,ilac;
    String[] listItems;
    String[] ilacItems;
    boolean[] ilacişaretlenmişItems;
    boolean[] işaretlenmişItems;
    ArrayList<Integer> tetikleyiciItems=new ArrayList<>();
    ArrayList<Integer> IlacItems=new ArrayList<>();
    Calendar c;
    int day,month,year,hour,minute;
    String format;
    private EditText basEdit;
    private EditText bitistxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agri_ekle);
        myDb=new DatabaseHelper(this);
        mItemSelected=findViewById(R.id.tvItemSelected);
        ilacSelected=findViewById(R.id.ilacItemSelected);
        tetikleyici=findViewById(R.id.tetikleyicibtn);
        ilac=findViewById(R.id.ilacbtn);
        Button kaydetbtn=findViewById(R.id.kaydetbtn);
        basEdit=findViewById(R.id.baslangıcedt);
        bitistxt=findViewById(R.id.bitisedt);
        SeekBar siddetbar=findViewById(R.id.siddetbar);
        final TextView seekBarValue=findViewById(R.id.seekBarValue);
        c = Calendar.getInstance();
        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);
        hour=c.get(Calendar.HOUR_OF_DAY);
        minute=c.get(Calendar.MINUTE);
        listItems=getResources().getStringArray(R.array.tetikleyici_item);
        ilacItems=getResources().getStringArray(R.array.ilac_item);
        işaretlenmişItems=new boolean[listItems.length];
        ilacişaretlenmişItems=new boolean[ilacItems.length];
        ilac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ilacgetir();


            }
        });
        tetikleyici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tetikleyicigetir();
             }
        });
        //bitistxt.setText(day + "/" + (month+1 ) + "/" + year);
        //basEdit.setText(day + "/" + (month+1 ) + "/" + year);
        bitistxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitistarihgetir();
            }
        });
        basEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baslangictarihgetir();
            }
        });

        siddetbar.setMax(10);
        siddetbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        kaydetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder builder = new StringBuilder();
                for(String values : listItems){
                    builder.append(values);
                }
                KayitModel model = new KayitModel();
                model.setBasTarih(basEdit.getText().toString());
                model.setSiddet(seekBarValue.getText().toString());
                model.setSonTarih(bitistxt.getText().toString());
                model.setTetikleyici(mItemSelected.getText().toString());
                model.setIlac(ilacSelected.getText().toString());
                if(myDb.kayitEkle(model)){
                    Toast.makeText(getApplicationContext(),"Ekleme İşlemi Başarılı",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AgriEkle.this,MainActivity.class);
                    startActivity(intent);
                    return;
                }
                Toast.makeText(getApplicationContext(),"Ekleme İşlemi Başarısız",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(AgriEkle.this,MainActivity.class);
                startActivity(intent);


            }
        });

    }

    public void tetikleyicigetir(){  //tetikleyici getiriyoruz.
        AlertDialog.Builder mbuilder=new AlertDialog.Builder(AgriEkle.this);
        mbuilder.setMultiChoiceItems(listItems, işaretlenmişItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                if (isChecked) {
                    if (!tetikleyiciItems.contains(position)) {
                        tetikleyiciItems.add(position);

                    } else {
                        tetikleyiciItems.remove(position);
                    }
                }
            }
        });
        mbuilder.setCancelable(false);

        mbuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String item= "";
                for(int i=0;i<tetikleyiciItems.size();i++){
                    item=item+listItems[tetikleyiciItems.get(i)];
                    if(i !=tetikleyiciItems.size()-1){
                        item=item+", ";
                    }
                }
                mItemSelected.setText(item);
            }
        });
        mbuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        mbuilder.setNeutralButton(R.string.clear_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                for(int i=0;i<işaretlenmişItems.length;i++){
                    işaretlenmişItems[i]=false;
                    tetikleyiciItems.clear();
                    mItemSelected.setText("");
                }
            }
        });
        AlertDialog mDialog=mbuilder.create();
        mDialog.show();



    }
    public void ilacgetir(){
        AlertDialog.Builder mbuilder=new AlertDialog.Builder(AgriEkle.this);

        mbuilder.setMultiChoiceItems(ilacItems, ilacişaretlenmişItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                if (isChecked) {
                    if (!IlacItems.contains(position)) {
                        IlacItems.add(position);

                    } else {
                        IlacItems.remove(position);
                    }
                }
            }
        });
        mbuilder.setCancelable(false);

        mbuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String item= "";
                for(int i=0;i<IlacItems.size();i++){
                    item=item+ilacItems[IlacItems.get(i)];
                    if(i !=IlacItems.size()-1){
                        item=item+", ";
                    }
                }
                ilacSelected.setText(item);
            }
        });
        mbuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        mbuilder.setNeutralButton(R.string.clear_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                for(int i=0;i<ilacişaretlenmişItems.length;i++){
                    ilacişaretlenmişItems[i]=false;
                    IlacItems.clear();
                    ilacSelected.setText("");
                }
            }
        });
        AlertDialog mDialog=mbuilder.create();
        mDialog.show();



    }
    public void bitistarihgetir(){

        String nowdate = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String nowdatetag = new SimpleDateFormat("yyyy-MM-dd").format(new Date());


        //bitistxt.setText(nowdate);
        bitistxt.setTag(nowdatetag);

        bitistxt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {


                    Calendar simdikizaman = Calendar.getInstance();
                    int year = simdikizaman.get(Calendar.YEAR);
                    int month = simdikizaman.get(Calendar.MONTH);
                    final int day = simdikizaman.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePicker;
                    datePicker = new DatePickerDialog(AgriEkle.this, new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear,
                                              int dayOfMonth) {

                            monthOfYear++;

                            String m = monthOfYear + "";
                            if (m.length() == 1) m = "0" + m;

                            String d = dayOfMonth + "";
                            if (d.length() == 1) d = "0" + d;

                            bitistxt.setText(d + "." + m + "." + year);
                            bitistxt.setTag(year + "-" + m + "-" + d);

                        }
                    }, year, month, day);


                    datePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", datePicker);
                    datePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", datePicker);

                    datePicker.show();
                }
                return true;
            }
        });

    }













    public void baslangictarihgetir(){
        String nowdate = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String nowdatetag = new SimpleDateFormat("yyyy-MM-dd").format(new Date());


        //basEdit.setText(nowdate);
        basEdit.setTag(nowdatetag);

        basEdit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {


                    Calendar simdikizaman = Calendar.getInstance();
                    int year = simdikizaman.get(Calendar.YEAR);
                    int month = simdikizaman.get(Calendar.MONTH);
                    final int day = simdikizaman.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePicker;
                    datePicker = new DatePickerDialog(AgriEkle.this, new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear,
                                              int dayOfMonth) {

                            monthOfYear++;

                            String m = monthOfYear + "";
                            if (m.length() == 1) m = "0" + m;

                            String d = dayOfMonth + "";
                            if (d.length() == 1) d = "0" + d;

                            basEdit.setText(d + "." + m + "." + year);
                            basEdit.setTag(year + "-" + m + "-" + d);

                        }
                    }, year, month, day);


                    datePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", datePicker);
                    datePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", datePicker);

                    datePicker.show();
                }
                return true;
            }
        });

    }




    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
