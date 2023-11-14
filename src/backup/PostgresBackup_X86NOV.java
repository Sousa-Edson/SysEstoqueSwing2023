/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backup;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Jorge
 */
public class PostgresBackup_X86NOV {

    public PostgresBackup_X86NOV() {

    }

    public static void realizaBackup() throws IOException, InterruptedException {
        
          JFileChooser save;
        int flag = 0;
        final List<String> comandos = new ArrayList<String>();
        
        save = new JFileChooser(new File("C:/BKPBANCO/"));
            int op = save.showSaveDialog(null);
            if (op == JFileChooser.APPROVE_OPTION) {
                File arq = save.getSelectedFile();
                String nomeDoArquivo = save.getName(arq);//aqui pega somente o nome do arquivo
                String pathDoArquivo = arq.toString(); //aqui pega o caminho do backup  
        String dir = "C:/BKPBANCO";
        comandos.add("C:\\Program Files (x86)\\PostgreSQL\\9.4\\bin\\pg_dump.exe"); //cecom
        comandos.add("-i");
        comandos.add("-h");
        comandos.add("localhost");
        comandos.add("-p");
        comandos.add("5432");
        comandos.add("-U");
        comandos.add("postgres");
        comandos.add("-F");
        comandos.add("c");
        comandos.add("-b");
        comandos.add("-v");
        comandos.add("-f");
        comandos.add(pathDoArquivo + "" + "" + getDateTime() + ".backup");   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.                         
        comandos.add("SysEstoqueSwing2023");
        ProcessBuilder pb = new ProcessBuilder(comandos);

        pb.environment().put("PGPASSWORD", "1");

        try {
            final Process process = pb.start();

            final BufferedReader r = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));
            String line = r.readLine();
                        line = r.readLine();
                    if (line != null) {
                        flag = 1;
                    } else {
                        JOptionPane.showMessageDialog(null, "erro");
                    }
            
            while (line != null) {
                System.err.println(line);
                line = r.readLine();
            }
            r.close();
            process.waitFor();
            process.destroy();
             if (flag == 1) {
                      JOptionPane.showMessageDialog(null, "backup realizado com sucesso.");
                    } else {
                    }
        } catch (IOException e) {
            e.printStackTrace();
             JOptionPane.showMessageDialog(null, "IOException e\nErro\nTente C:\\Program Files");
        } catch (InterruptedException ie) {
            ie.printStackTrace();
            JOptionPane.showMessageDialog(null, "InterruptedException ie\nErro\nTente C:\\Program Files");
        }
    }}

    public static void main(String args[]) throws IOException, InterruptedException {
        PostgresBackup_X86NOV b = new PostgresBackup_X86NOV();
        b.realizaBackup();
    }

    private static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy HHmm");
        Date date = new Date();
        return dateFormat.format(date);
    }

}
