/**   
 * @Title: FastqIndexer.java 
 * @Package fastq 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author SuFeng
 * @date 2014年1月6日 下午5:21:58 
 * @version V1.0   
 */
package org.flywind2.biotools.ngs.fastq;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/**
 * @author SuFeng
 * 
 */
public class FastqIndexer  implements Closeable{
	//private File fastqFile;
	
	private FileInputStream fis;
	
	private BufferedReader br;
	
	private FileChannel fc;
	
	private String indexLocation;


	public FastqIndexer(File fastqFile) throws FileNotFoundException {
		//this.fastqFile = fastqFile;
		this.fis  = new FileInputStream(fastqFile);
		this.fc = this.fis.getChannel();
		this.br = new BufferedReader(new InputStreamReader(fis));
		this.indexLocation = getIndexLocation(fastqFile);
	}

	/**
	 * @param fastqFile
	 * @return
	 */
	private String getIndexLocation(File fastqFile) {
		// TODO Auto-generated method stub
		String full = fastqFile.getAbsolutePath();
		String name = full.substring(0,full.lastIndexOf("."));
		return name+".idx";
	}

	/**
	 * index the fastq file
	 * @throws IOException
	 */
	public void index() throws IOException{
		
		int lineNum = 1;
		String line = null;
		int offset = 0;
		int length =0; 
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(indexLocation));
		
		int sepLength =System.getProperty("line.separator").getBytes().length;
		
		while((line=br.readLine())!=null){
			//length = line.getBytes("UTF-8").length;
			length = line.getBytes().length;
			//System.out.println((lineNum)+"\t"+offset+"\t"+(length));
			bw.write(offset+"\t"+(length));
			bw.newLine();
			offset=offset + length+sepLength;//+sepLength
			lineNum++;
		}
		bw.flush();
		bw.close();

		
	}
	
	
	public String read(int offset,int length) throws IOException{

		MappedByteBuffer buffer = fc.map(MapMode.READ_ONLY, 0, fc.size());
		//System.out.println("file length is " + fis.available()+"\t channel length is " + fc.size());
		buffer.position(offset);
		byte[] bytes = new byte[length];
		
		buffer.get(bytes);
		
		//System.out.println("bytes length is "+bytes.length+"\n"+new String(bytes));
		return new String(bytes);
	}
	
	

	/* (non-Javadoc)
	 * @see java.io.Closeable#close()
	 */
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		if(fis!=null){
			br.close();
			fc.close();
			fis.close();
		}
	}
	
	
	public static void main(String args[]) throws UnsupportedEncodingException{
		
		System.out.println("line.separator length is "+System.getProperty("line.separator").getBytes().length);
		
		System.out.println("@SOLEXA11:1:1:6925:12862#0/1".getBytes().length);
		try {
			FastqIndexer idx = new FastqIndexer(new File("D:\\Nonin\\20120821\\zhuqi-11_20120821_GTCCGC_L003_R1_001.fastq"));
			
			idx.index();

//			BufferedReader br = new BufferedReader(new FileReader("D:\\test_idx.txt"));
//			String line = null;
//			while((line=br.readLine())!=null){
//				String[] temp = line.split("\t",-1);
//				System.out.println(temp[0]+":\t"+idx.read(Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
//				
//				
//			}
//			br.close();
			idx.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
