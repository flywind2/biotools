/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.flywind2.biotools.ngs.fastq;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipInputStream;

/**
 *
 * @author SuFeng <flywind2.su@gmail.com>
 */
public class CompressFileReader {
    
    private File file;
    
    
    private FileInputStream fis;
    
    
    
    private BufferedReader br;
    
    public CompressFileReader(File  file) throws FileNotFoundException, IOException{
        this.file = file;
        fis = new FileInputStream(this.file);
        System.out.println("file size " + fis.available());
        ZipInputStream zis = new ZipInputStream(new FileInputStream(this.file));
        System.out.println("Zip Stream size is " + zis.getNextEntry().getSize());
        
        br = new BufferedReader(new InputStreamReader(zis));
    }
    
    public void readLine() throws IOException{
        char[] cbuf = new char[157];
        br.read(cbuf);
        System.out.print(cbuf);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            File file = new File("D:\\singleEnd.fastq");
            System.out.println("file raw size " + file.length());
            CompressFileReader reader = new CompressFileReader(new File("D:\\singleEnd.zip"));
            reader.readLine();
        } catch (IOException ex) {
            Logger.getLogger(CompressFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
