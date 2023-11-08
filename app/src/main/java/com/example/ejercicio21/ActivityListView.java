package com.example.ejercicio21;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ejercicio21.tabla.video;
import com.example.ejercicio21.transacciones.transacciones;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

public class ActivityListView extends AppCompatActivity {

    ListView lista;
    ArrayList<String> listaInformacion;
    ArrayList<video>listavideo;
    SQLiteConexion conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        lista = findViewById(R.id.listview); // Asegúrate de tener un ListView con el ID listView en tu diseño XML
        conexion = new SQLiteConexion(getApplicationContext(), transacciones.NameDataBase, null, 1);

        try {
            consultarLista();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void consultarLista() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        listavideo = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + transacciones.tablavideo, null);

        while (cursor.moveToNext()) {
            video video = new video();

            // Obtén la URL del video desde el cursor
            @SuppressLint("Range") String videoUrl = cursor.getString(cursor.getColumnIndex(transacciones.tablavideo));

            // Log para verificar la URL antes de agregarla a la lista
            Log.d("ConsultaLista", "Video URL: " + videoUrl);

            video.setVideo(videoUrl);
            listavideo.add(video);
        }

        cursor.close();
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<>();

        for (int i = 0; i < listavideo.size(); i++) {
            listaInformacion.add(listavideo.get(i).getVideo());
        }

        // Log para verificar los valores en la lista antes de configurar el adaptador
        Log.d("ListaInformacion", listaInformacion.toString());

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaInformacion);
        lista.setAdapter(adaptador);
    }
}