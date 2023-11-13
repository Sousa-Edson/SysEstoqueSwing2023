/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backup;

/**
 *
 * @author edson
 */
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BackupPostgres {

    // Configurações do banco de dados PostgreSQL
    private static final String DB_HOST = "localhost"; // ou o IP do seu contêiner Docker
    private static final String DB_PORT = "5432";
    private static final String DB_NAME = "SysEstoqueSwing2023";
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "123456";

    // Caminho onde o arquivo de backup será salvo
    private static final String BACKUP_PATH = "/home/edson/";

   private static final String DB_CONTAINER_NAME = "postgresql"; 

    public static void main(String[] args) {
        try {
            
            
            
            // Obtém a data atual para incluir no nome do arquivo de backup
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String backupFileName = "backup_" + timestamp + ".sql";

            // Constrói o comando para executar o backup dentro do contêiner Docker
            
//            String command = String.format("/usr/bin/docker exec -t %s pg_dump -U %s -d %s -F c -b -v -f %s%s",
//        DB_CONTAINER_NAME, DB_USER, DB_NAME, BACKUP_PATH, backupFileName);

            
//            String command = String.format("/usr/bin/docker exec -t %s pg_dump -U %s -d %s -F c -b -v -f %s%s",
//        DB_CONTAINER_NAME, DB_USER, DB_NAME, BACKUP_PATH, backupFileName);

String command="docker exec -t postgresql pg_dump -U admin -d SysEstoqueSwing2023 > backup4.sql";
            System.out.println(""+command);
            
//            String command = String.format("docker exec -t %s pg_dump -U %s -d %s -F c -b -v -f %s%s",
//                    DB_CONTAINER_NAME, DB_USER, DB_NAME, BACKUP_PATH, backupFileName);

            // Executa o comando no terminal
            Process process = new ProcessBuilder().command("bash", "-c", command).start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Backup realizado com sucesso. Arquivo salvo em: " + BACKUP_PATH + backupFileName);
            } else {
                System.out.println("Erro ao realizar o backup. Código de saída: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

