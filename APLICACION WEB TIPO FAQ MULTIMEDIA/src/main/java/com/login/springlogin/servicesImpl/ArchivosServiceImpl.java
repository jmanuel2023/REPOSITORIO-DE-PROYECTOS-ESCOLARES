package com.login.springlogin.servicesImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class ArchivosServiceImpl {
    private String PATH_FILES = "./src/main/resources/static/uploads/";

     public String saveFile(MultipartFile file){
        if(!file.isEmpty()){
            //Generar nombre unico para el archivo
            String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();
            Path path = Paths.get(PATH_FILES + fileName );

            //Guardamos el archivo en la carpeta
            try {
                Files.write(path, file.getBytes());
                System.out.println("Archivo guardado");
             //Files.copy(file.getInputStream(), path.resolve(fileName));
         } catch (IOException e) {
            System.out.println("Error al guardar el archivo");
            return "Error";
         }
            return fileName;
         }
         return "Error";
    }
    public void deleteFile(String fileName){
        try {
            //Path path = Paths.get(PATH_FILES + fileName );
            Files.delete(Paths.get(PATH_FILES + fileName));
        } catch (IOException e) {
            System.out.println("Error al eliminar el archivo");
        }
    }

}
