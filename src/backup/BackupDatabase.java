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

public class BackupDatabase {

    public static void main(String[] args) {

        String containerName = "postgresql";

        String host = "localhost";
        String port = "5432";
        String database = "SysEstoqueSwing2023";
        String user = "admin";
        String password = "123456";
        String backupFileName = "backup.sql";

        realizarBackupLinux(containerName, host, port, database, user, password, backupFileName);
    }

    public static void realizarBackupLinux(String containerName, String host, String port, String database, String user, String password, String backupFileName) {
        try {
            // Obtendo o diretório home do usuário
            String homeDir = System.getProperty("user.home");
            String backupPath = homeDir + "/" + backupFileName;

            String comandoDocker = "/usr/bin/docker";
            String comando = comandoDocker + " exec -i " + containerName
                    + " pg_dump -U " + user + " -d " + database + " -F c -b -v -f " + backupPath;

            ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", comando);
            processBuilder.environment().put("PGPASSWORD", password);

// Verificar se o comando docker está disponível
            if (isComandoDisponivel(comandoDocker)) {
                Process process = processBuilder.start();
                int exitCode = process.waitFor();

                if (exitCode == 0) {
                    System.out.println("Backup realizado com sucesso.");
                } else {
                    System.out.println("Erro ao realizar backup. Código de saída: " + exitCode);
                }
            } else {
                System.out.println("Comando docker não encontrado. Verifique se o Docker está instalado e no seu PATH.");
            }

            processBuilder.environment().put("PGPASSWORD", password);

            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Backup realizado com sucesso.");
            } else {
                System.out.println("Erro ao realizar backup. Código de saída: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static boolean isComandoDisponivel(String comando) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("which", comando);
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            System.out.println("exit"+exitCode);
            return exitCode == 0;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}
