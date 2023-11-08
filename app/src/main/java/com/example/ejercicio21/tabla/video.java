package com.example.ejercicio21.tabla;

import java.sql.Blob;

public class video {
    private Blob video;

    public video() {
        // Constructor sin argumentos
    }

    public Blob getVideo() {
        return video;
    }

    public void setVideo(Blob video) {
        this.video = video;
    }
}