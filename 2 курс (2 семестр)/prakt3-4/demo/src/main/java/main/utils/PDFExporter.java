package main.utils;

import main.model.Voting;
import main.model.Candidate;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.Map;

public class PDFExporter {
    public static void exportResults(Voting voting, String filePath) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream content = new PDPageContentStream(document, page)) {
                content.beginText();
                content.setFont(PDType1Font.HELVETICA_BOLD, 16);
                content.newLineAtOffset(100, 700);
                content.showText("Результаты голосования: " + voting.getTitle());
                content.endText();

                // Таблица результатов
                content.beginText();
                content.setFont(PDType1Font.HELVETICA, 12);
                int y = 650;
                for (Map.Entry<Candidate, Integer> entry : voting.getResults().entrySet()) {
                    content.newLineAtOffset(100, y);
                    content.showText(entry.getKey().getName() + ": " + entry.getValue() + " голосов");
                    y -= 20;
                }
                content.endText();
            }

            document.save(filePath);
        }
    }
    public static void addVotingToDocument(PDDocument document, Voting voting) throws IOException {
        PDPage page = new PDPage();
        document.addPage(page);
        
        try (PDPageContentStream content = new PDPageContentStream(document, page)) {
            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 12);
            content.newLineAtOffset(100, 700);
            content.showText("Результаты голосования: " + voting.getTitle());
            content.newLineAtOffset(0, -20);
            content.showText("Всего голосов: " + voting.getTotalVotes());
            content.newLineAtOffset(0, -20);
            
            for (Candidate candidate : voting.getCandidates()) {
                content.showText(candidate.getName() + ": " + candidate.getVotesCount() + " голосов");
                content.newLineAtOffset(0, -15);
            }
            
            content.endText();
        }
    }
}