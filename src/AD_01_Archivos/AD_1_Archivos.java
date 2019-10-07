/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AD_01_Archivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oracle
 */
public class AD_1_Archivos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("1");
        File file = new File("/home/oracle/NetBeansProjects/AD_01_Archivos/arquivosdir");
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println(file.getAbsolutePath() + " creado correctamente");
            } else {
                System.out.println(file.getAbsolutePath() + " creacion fallo.");
            }
        } else {
            System.out.println(file.getAbsolutePath() + " ya existe");
        }

        System.out.println("2");
        File file2 = new File(file.getAbsoluteFile() + "/Products1.txt");
        if (!file2.exists()) {
            try {
                if (file2.createNewFile()) {
                    System.out.println(file2.getAbsolutePath() + " creado correctamente");
                } else {
                    System.out.println(file2.getAbsolutePath() + " creacion fallo.");
                }
            } catch (IOException ex) {
                Logger.getLogger(AD_1_Archivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println(file2.getAbsolutePath() + " ya existe");
        }
        
        System.out.println("3");
        System.out.println(file.getAbsolutePath() +" "+ (file.exists() ? "Si" : "No") + " existe");
        System.out.println(file2.getAbsolutePath() +" "+ (file.exists() ? "Si" : "No") + " existe");

        System.out.println("4");
        File file3 = new File("/home/oracle/NetBeansProjects/AD_01_Archivos/arquivosdir/subdir");
        if (!file3.exists()) {
            if (file3.mkdir()) {
                System.out.println(file3.getAbsolutePath() + " creado correctamente");
            } else {
                System.out.println(file3.getAbsolutePath() + " creacion fallo.");
            }
        } else {
            System.out.println(file3.getAbsolutePath() + " ya existe");
        }

        File file4 = new File(file3.getAbsoluteFile() + "/Products2.txt");
        if (!file4.exists()) {
            try {
                if (file4.createNewFile()) {
                    System.out.println(file4.getAbsolutePath() + " creado correctamente");
                } else {
                    System.out.println(file4.getAbsolutePath() + " creacion fallo.");
                }
            } catch (IOException ex) {
                Logger.getLogger(AD_1_Archivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println(file4.getAbsolutePath() + " ya existe");
        }

        System.out.println("5");
        for (String string : file.list()) {
            System.out.println(string);
        }

        System.out.println("6");
        System.out.println(file.getAbsolutePath());
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.append(Long.MAX_VALUE);
        }
        printOnFile(file2, sb.toString());
        
        System.out.println("7");
        System.out.println("Nombre: " + file2.getName());
        System.out.println("Ruta: " + file2.getAbsolutePath());
        System.out.println("Escritura: " + file2.canWrite());
        System.out.println("Lectura: " + file2.canRead());
        System.out.println("Tamaño: " + byteSizeFormatter(file2.length() + ""));

        System.out.println("8");
        file2.setReadOnly();
        System.out.println("Escritura: " + file2.canWrite());

        System.out.println("9");
        file2.setWritable(true);
        System.out.println("Escritura: " + file2.canWrite());

        System.out.println("10");
        if (file2.delete()) {
            System.out.println(file2.getAbsolutePath() + " eliminado");
        } else {
            System.out.println(file2.getAbsolutePath() + " no eliminado");
        }

        System.out.println("11");
        if (deleteDirectory(file)) {
            System.out.println(file.getAbsolutePath() + " eliminado");
        } else {
            System.out.println(file.getAbsolutePath() + " no eliminado");
        }

    }

    /**
     * Recursive Byte string formatter ex: 10000 = 10.000
     *
     * @param bytes string containing digits to be formated
     * @return x.xxx.xxx.xxx style string
     */
    private static String byteSizeFormatter(String bytes) {
        StringBuilder sb = new StringBuilder();
        if (bytes.length() < 4) {
            sb.insert(0, bytes);
        } else {
            sb.insert(0, "." + bytes.substring(bytes.length() - 3));
            sb.insert(0, byteSizeFormatter(bytes.substring(0, bytes.length() - 3)));
        }
        return sb.toString();
    }

    public static boolean deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            File[] children = dir.listFiles();
            for (File children1 : children) {
                boolean success = deleteDirectory(children1);
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
    
    public static void printOnFile(File file, String string) {
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.println(string);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AD_1_Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
