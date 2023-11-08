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

        conexion = new SQLiteConexion(getApplicationContext(), "DBActual",null,1);
        lista = (ListView) findViewById(R.id.listview);

        try {
            consultarLista();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        lista.setAdapter(adaptador);
    }

    private void consultarLista() throws SQLException {
        SQLiteDatabase db = conexion.getReadableDatabase();
        video video = null;
        listavideo = new ArrayList<video>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + transacciones.tablavideo, null);
        if (cursor.moveToFirst()) {
            do {
                video = new video();

                // Obtén la URL del video desde el cursor
                @SuppressLint("Range") String videoUrl = cursor.getString(cursor.getColumnIndex("video"));

                Log.d("ConsultaLista", "Video URL: " + videoUrl); // Agrega este log

                video.setVideo(videoUrl);
                listavideo.add(video);
            } while (cursor.moveToNext());
        }
        cursor.close();
        obtenerLista();
    }

    // Método para convertir Blob a URL (debes implementar este método)
    private String convertBlobToUrl(Blob blob) {
        // Implementa la lógica para convertir el Blob a una URL o cadena que represente la ubicación del video
        // Esto dependerá de cómo estás almacenando tus videos y cómo deseas representarlos como URL
        // Puede ser la ubicación de un archivo en el sistema de archivos, una URL de servidor, etc.
        // Retorna la URL como una cadena
        // Ejemplo de implementación:
        return "https://ejemplo.com/ruta/al/video.mp4";
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();
        for (int i = 0; i < listavideo.size(); i++) {
            listaInformacion.add(listavideo.get(i).getVideo());
        }

        // Agrega este log para verificar los valores en la lista antes de configurar el adaptador
        Log.d("ListaInformacion", listaInformacion.toString());

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        lista.setAdapter(adaptador);
    }
}