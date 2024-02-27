package com.kt.hearthstonepacktrackerbackend.service;

import com.kt.hearthstonepacktrackerbackend.model.PackType;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlotService {

    public ResponseEntity<Resource> getPlottedImage(PackType packType) {

        // Call the Python script to generate the image
        String pythonScriptPath = "/home/kt/Projects/HearthstonePackTracker-Python/main.py";
        callPythonScript(pythonScriptPath, String.valueOf(packType));


        // Return the image bytes
        Path imagePath = Paths.get("/home/kt/Projects/HearthstonePackTracker-Python/plots/"+ packType + ".png");
        byte[] imageBytes;
        try {
            imageBytes = Files.readAllBytes(imagePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ByteArrayResource resource = new ByteArrayResource(imageBytes);

        return ResponseEntity.ok()
                .header("Content-Disposition", "inline; filename=" + imagePath.getFileName().toString())
                .contentLength(imageBytes.length)
                .contentType(MediaType.IMAGE_PNG)
                .body(resource);
    }

    private static void callPythonScript(String scriptPath, String packType) {

        // Put the Python command together
        List<String> command = new ArrayList<>();
        command.add("python3");
        command.add(scriptPath);
        command.add(packType);


        // Run the Python script
        ProcessBuilder processBuilder = new ProcessBuilder(command);

        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Python script exited with code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
