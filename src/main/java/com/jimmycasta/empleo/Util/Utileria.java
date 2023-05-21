package com.jimmycasta.empleo.Util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class Utileria {
    public static String guardarArchivo(MultipartFile multipart, String ruta) {

        //Se obtiene el nombre original del archivo
        String nombreOriginal = multipart.getOriginalFilename();

        //se eliminan espacios en blanco en el nombre de archivo.
        if (nombreOriginal != null) {

            nombreOriginal.replace(" ","_");
        }

        try {
            //Se forma el nombre del archivo para guardarlo en el disco
            File imageFile = new File(ruta + nombreOriginal);

            //Opcional se imprime en consola el nombre
            System.out.println("Nombre archivo" + imageFile.getAbsolutePath());

            //Se guarda fisicamente el archivo en el disco.
            multipart.transferTo(imageFile);

            return nombreOriginal;

        } catch (IOException e) {

            throw new RuntimeException(e);

        }
    }
}
