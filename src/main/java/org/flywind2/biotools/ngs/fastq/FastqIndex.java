/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.flywind2.biotools.ngs.fastq;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SuFeng <flywind2.su@gmail.com>
 */
public class FastqIndex {
    private final File fastqFile;
    private FileInputStream fis;
    private BufferedReader br;
    
    private List<FastqIndexerRecord> indexRecords = new LinkedList<>();
    
    public FastqIndex(File fastqFile){
        this.fastqFile = fastqFile;
        try {
            fis = new FileInputStream(this.fastqFile);
            br = new BufferedReader(new InputStreamReader(fis));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FastqIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void index() throws IOException{
        List<FastqIndexerRecord> indexRecords  = new ArrayList<>();
        String line = null;
        int offset = 0;
        int recordLength = 0;
        FastqIndexerRecord record;
        int count = 0;
        while((line=br.readLine())!=null){
            //fastq 
            if(line.startsWith("@")){
                count++;
                String name = line+System.getProperty("line.separator").toCharArray().length;
                String seq = br.readLine()+System.getProperty("line.separator").toCharArray().length;
                String other = br.readLine()+System.getProperty("line.separator").toCharArray().length;
                String qual = br.readLine();
                recordLength = name.toCharArray().length+seq.toCharArray().length+other.toCharArray().length+qual.toCharArray().length;
                
                record = new FastqIndexerRecord(name,recordLength,offset);
                //+System.getProperty("line.separator").toCharArray().length
                offset=offset+ recordLength+1;
                indexRecords.add(record);
                
                if(indexRecords.size()%10000==0){
                    
                    writeIndex(indexRecords,true);
                    indexRecords.clear();
                }
                    
                
            }
        }
        
         writeIndex(indexRecords,true);
        System.out.println("total reads is " + count);
    }
    
    public List<FastqIndexerRecord> getIndex(){
        return Collections.unmodifiableList(indexRecords);
    }
    
    public void writeIndex(boolean append){
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("D:\\index.txt",append));
            for(FastqIndexerRecord r : indexRecords){
                //if(i++>10)
                //   break;
                //System.out.println(r.getName()+"\t"+r.getLength()+"\t"+r.getOffset());
                bw.write(r.getName()+"\t"+r.getLength()+"\t"+r.getOffset());
                bw.newLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(FastqIndex.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bw.close();
                indexRecords.clear();
            } catch (IOException ex) {
                Logger.getLogger(FastqIndex.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void main(String args[]){
        FastqIndex indexer = new FastqIndex(new File("d:\\WGC14039_304_combined_filtered_R1.fastq"));
        try {
            indexer.index();
            //indexer.writeIndex(true);
        } catch (IOException ex) {
            Logger.getLogger(FastqIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void writeIndex(List<FastqIndexerRecord> indexRecords, boolean b) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\index.txt",b))){
            for(FastqIndexerRecord r : indexRecords){
                //if(i++>10)
                //   break;
                //System.out.println(r.getName()+"\t"+r.getLength()+"\t"+r.getOffset());
                bw.write(r.getName()+"\t"+r.getLength()+"\t"+r.getOffset());
                bw.newLine();
            }
            bw.flush();
        }catch(IOException ex){
            Logger.getLogger(FastqIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
