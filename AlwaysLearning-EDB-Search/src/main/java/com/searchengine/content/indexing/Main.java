package com.searchengine.content.indexing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.searchengine.indexing.IndexItem;

public class Main {

	    private final String sourceFilePath = "/home/amila/Lucene/sourceFiles";    //give the location of the source files location here
	    private final String indexFilePath = "/home/amila/Lucene/Always Learning localhost repository/index";   //give the location where you guys want to create index
	    private IndexWriter writer = null;
	    private File indexDirectory = null;
	    private String fileContent;  //temporary storer of all the text parsed from doc and pdf 

	    /**
	     *
	     * @throws FileNotFoundException
	     * @throws CorruptIndexException
	     * @throws IOException
	     */
	    private Main() throws FileNotFoundException, CorruptIndexException, IOException {
	        try {
	            long start = System.currentTimeMillis();
	            createIndexWriter();
	            checkFileValidity();
	            closeIndexWriter();
	            long end = System.currentTimeMillis();
	            System.out.println("Total Document Indexed : " + TotalDocumentsIndexed());
	            System.out.println("Total time" + (end - start) / (100 * 60));
	        } catch (Exception e) {
	            System.out.println("Sorry task cannot be completed");
	        }
	    }

	    /**
	     * IndexWriter writes the data to the index. Its provided by Lucene
	     *
	     * @param analyzer : its a standard analyzer, in this case it filters out
	     *  englishStopWords and also analyses TFIDF
	     */
	    private void createIndexWriter() {
	        try {
	            indexDirectory = new File(indexFilePath);
	            if (!indexDirectory.exists()) {
	                indexDirectory.mkdir();
	            }
	            FSDirectory dir = FSDirectory.open(indexDirectory);
	            StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_34);
	            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_34, analyzer);
	            writer = new IndexWriter(dir, config);
	        } catch (Exception ex) {
	            System.out.println("Sorry cannot get the index writer");
	        }
	    }

	    /**
	     * This function checks whenther the file passed is valid or not
	     */
	    private void checkFileValidity() {
	        File[] filesToIndex = new File[100]; // suppose there are 100 files at max
	        filesToIndex = new File(sourceFilePath).listFiles();
	        for (File file : filesToIndex) {
	            try {
	                //to check whenther the file is a readable file or not.
	                if (!file.isDirectory()
	                        && !file.isHidden()
	                        && file.exists()
	                        && file.canRead()
	                        && file.length() > 0.0
	                        && file.isFile() ) {
	                    if(file.getName().endsWith(".txt")){
	                        indexTextFiles(file);//if the file text file no need to parse text. 
	                    System.out.println("INDEXED FILE " + file.getAbsolutePath() + " :-) ");
	                    }
	                    else if(file.getName().endsWith(".doc") || file.getName().endsWith(".pdf")){
	                        //different methof for indexing doc and pdf file.
	                       StartIndex(file);                    
	                    }
	                }
	            } catch (Exception e) {
	                System.out.println("Sorry cannot index " + file.getAbsolutePath());
	            }
	        }
	    }
	    
	    
	    /**
	     * This method is for indexing pdf file and doc file.
	     * The text parsed from them are indexed along with the filename and filepath
	     * @param file : the file which you want to index
	     * @throws FileNotFoundException
	     * @throws CorruptIndexException
	     * @throws IOException 
	     */
	    public void StartIndex(File file) throws FileNotFoundException, CorruptIndexException, IOException {
	         fileContent = null;
	        try {
	            Document doc = new Document();
	            if (file.getName().endsWith(".doc")) {
	                //call the doc file parser and get the content of doc file in txt format
	                //fileContent = new DocFileParser().DocFileContentParser(file.getAbsolutePath());
	            }
	            if (file.getName().endsWith(".pdf")) {
	                //call the pdf file parser and get the content of pdf file in txt format
	                fileContent = PdfFileParser(file.getAbsolutePath());
	            }
	           /* doc.add(new Field("content", fileContent,
	                    Field.Store.YES, Field.Index.ANALYZED,
	                    Field.TermVector.WITH_POSITIONS_OFFSETS));
	            doc.add(new Field("filename", file.getName(),
	                    Field.Store.YES, Field.Index.ANALYZED));
	            doc.add(new Field("fullpath", file.getAbsolutePath(),
	                    Field.Store.YES, Field.Index.ANALYZED));*/
	            
	            doc.add(new Field(IndexItem.ID, "test-id", Field.Store.YES, Field.Index.NOT_ANALYZED));
		        doc.add(new Field(IndexItem.TITLE, file.getName(), Field.Store.YES, Field.Index.ANALYZED));
		        doc.add(new Field(IndexItem.CONTENT, fileContent,Field.Store.YES, Field.Index.ANALYZED,Field.TermVector.WITH_POSITIONS_OFFSETS));       
		        
	            if (doc != null) {
	                writer.addDocument(doc);
	            }
	            System.out.println("Indexed" + file.getAbsolutePath());
	        } catch (Exception e) {
	            System.out.println("error in indexing" + (file.getAbsolutePath()));
	        }
	    }

	    /**
	     * This method indexed text files.
	     * @param file
	     * @throws CorruptIndexException
	     * @throws IOException
	     */
	    private void indexTextFiles(File file) throws CorruptIndexException, IOException {
	        Document doc = new Document();
	        
	       /* doc.add(new Field("content", new FileReader(file)));
	        doc.add(new Field("filename", file.getName(),
	                Field.Store.YES, Field.Index.ANALYZED));
	        doc.add(new Field("fullpath", file.getAbsolutePath(),
	                Field.Store.YES, Field.Index.ANALYZED));*/
	        
	        
	        doc.add(new Field(IndexItem.ID, "test-id", Field.Store.YES, Field.Index.NOT_ANALYZED));
	        doc.add(new Field(IndexItem.TITLE, file.getName(), Field.Store.YES, Field.Index.ANALYZED));
	        doc.add(new Field(IndexItem.CONTENT, new FileReader(file)));
	        
	        
	        
	        if (doc != null) {
	            writer.addDocument(doc);
	        }
	    }
	    public String PdfFileParser(String pdffilePath) throws FileNotFoundException, IOException
	    {
	        String content;
	        FileInputStream fi = new FileInputStream(new File(pdffilePath));
	        PDFParser parser = new PDFParser(fi);
	        parser.parse();
	        COSDocument cd = parser.getDocument();
	        PDFTextStripper stripper = new PDFTextStripper();
	        content = stripper.getText(new PDDocument(cd));
	        cd.close();
	        System.out.println("content " + content);
	        return content;
	    }
	    
	   	
	   
	    
	    public String DocFileContentParser(String fileName) {
	        POIFSFileSystem fs = null;
	        try {
	           
	            if (fileName.endsWith(".xls")) { //if the file is excel file
	                ExcelExtractor ex = new ExcelExtractor(fs);
	                return ex.getText(); //returns text of the excel file
	            } else if (fileName.endsWith(".ppt")) { //if the file is power point file
	                PowerPointExtractor extractor = new PowerPointExtractor(fs);
	                return extractor.getText(); //returns text of the power point file

	            }
	            
	            //else for .doc file
	            fs = new POIFSFileSystem(new FileInputStream(fileName));
	            HWPFDocument doc = new HWPFDocument(fs);
	            WordExtractor we = new WordExtractor(doc);
	            return we.getText();//if the extension is .doc
	        } catch (Exception e) {
	            System.out.println("document file cant be indexed");
	        }
	        return "";
	    }
	    /**
	     * This method returns the total number of documents indexed.
	     * @return total number of documents indexed.
	     */
	    private int TotalDocumentsIndexed() {
	        try {
	            IndexReader reader = IndexReader.open(FSDirectory.open(indexDirectory));
	            return reader.maxDoc();
	        } catch (Exception ex) {
	            System.out.println("Sorry no index found");
	        }
	        return 0;
	    }

	    /**
	     *  closes the IndexWriter
	     */
	    private void closeIndexWriter() {
	        try {
	            writer.optimize();
	            writer.close();
	        } catch (Exception e) {
	            System.out.println("Indexer Cannot be closed");
	        }
	    }

	   /**
	     *  Main method.
	     */

	    public static void main(String arg[]) {
	        try {
	            new Main();
	        } catch (Exception ex) {
	            System.out.println("Cannot Start :(");
	        }
	    }
	}

