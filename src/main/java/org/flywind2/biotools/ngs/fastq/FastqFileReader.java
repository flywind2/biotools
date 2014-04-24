/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.flywind2.biotools.ngs.fastq;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SuFeng <flywind2.su@gmail.com>
 */
public class FastqFileReader {
    private final File fastqFile;
    private FileInputStream fis;
    private BufferedInputStream bis;
    

    
    public FastqFileReader(File fastqFile){
        this.fastqFile = fastqFile;
        try {
            fis = new FileInputStream(this.fastqFile);
            bis = new BufferedInputStream(fis);
            bis.skip(20640192);
            byte[] b = new byte[164];
            bis.read(b);
            System.out.println(new String(b));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FastqFileReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FastqFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String read(String name,int length,int offset) throws IOException{
        fis.getChannel().position(offset);

        byte[] bytes = new byte[length];
        ByteBuffer buf = ByteBuffer.wrap(bytes);
        fis.getChannel().read(buf);
        //buf.flip();
        //System.out.println(new String(bytes));
        return new String(bytes);
    }
    
    public static void main(String args[]){
        FastqIndex indexer = new FastqIndex(new File("d:\\singleEnd.fastq"));
        FastqFileReader reader = new FastqFileReader(new File("d:\\singleEnd.fastq"));
        
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\singleEnd_reader.fq"));
            indexer.index();
            List<FastqIndexerRecord> idx = indexer.getIndex();
            System.out.println("records size is " + idx.size());
//            System.exit(0);
            int i=0;
//            for(FastqIndexerRecord r: idx){
//                i++;
//                //System.out.println(r.getName()+"\t"+r.getLength()+"\t"+r.getOffset());
//                String f = reader.read(r.getName(),r.getLength(),r.getOffset());
//                System.out.println(f);
//                bw.write(f);
//                //bw.newLine();
//                if(i>2){
//                    break;
//                }
//            }
            
            
            
            bw.flush();
           bw.close();
            
           String s = "@SOLEXA11:1:1:997:5979#0/1\n" +
"...................................................\n" +
"+SOLEXA11:1:1:997:5979#0/1\n" +
"BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB";
           
           System.out.println(s.length()+"\t"+s.getBytes("utf-8").length);
           // System.out.println(reader.read(idx.get(idx.size()-1).getName(), idx.get(idx.size()-1).getLength(), idx.get(idx.size()-1).getOffset()));
            
        } catch (IOException ex) {
            Logger.getLogger(FastqFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
