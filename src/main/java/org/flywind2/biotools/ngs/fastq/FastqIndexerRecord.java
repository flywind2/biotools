/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.flywind2.biotools.ngs.fastq;

/**
 *
 * @author SuFeng <flywind2.su@gmail.com>
 */
public class FastqIndexerRecord {
    private String name;
    private int length;
    private long offset;
    
    public FastqIndexerRecord(){
    }
    
    public FastqIndexerRecord(String name,int length,long offset){
        this.name = name;
        this.length = length;
        this.offset = offset;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

}
