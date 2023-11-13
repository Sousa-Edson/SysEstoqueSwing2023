/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backup;

/**
 *
 * @author edson
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DockerCommandExecution {

    public static void main(String[] args) {
        String command = "/usr/local/bin/docker exec -t postgresql pg_dump -U admin -d SysEstoqueSwing2023 > backup4.sql";

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", command);
            Process process = processBuilder.start();
           
            // Aguarde o término do processo
            int exitCode = process.waitFor();

            // Leia a saída do processo
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("exit" + exitCode);

            // Se o código de saída for 0, a execução foi bem-sucedida
            if (exitCode == 0) {
                System.out.println("Comando executado com sucesso");
            } else {
                System.out.println("Falha ao executar o comando. Código de saída: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
