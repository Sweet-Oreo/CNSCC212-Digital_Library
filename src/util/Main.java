package util;

import com.google.zxing.WriterException;
import domain.Paper;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, WriterException {

        Paper paper = new Paper();
        paper.setId(1);
        paper.setTitle("Adapting to Artificial Intelligence - Radiologists and Pathologists as Information Specialists");
        paper.setAuthor("Saurabh Jha");
        paper.setUniversity("University of Pennsylvania");
        paper.setKeyword("Radiologists, Pathologists");
        paper.setMajor("Artificial Intelligence");
        paper.setPublish_date("2021-04-12");

        File pdf = new File("./test/1.pdf");
        File img = new File("./test/1.png");
        QRUtils.AddInfoToPDF(paper, pdf, img);

    }

}
