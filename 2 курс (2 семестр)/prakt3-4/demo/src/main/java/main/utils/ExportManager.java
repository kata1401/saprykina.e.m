package main.utils;

import main.model.Voting;
import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExportManager {

    public void exportResults(List<Voting> votings, boolean singleFile) throws IOException {
        String timestamp = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
        
        if (singleFile) {
            String filename = "export_" + timestamp + ".pdf";
            exportToSingleFile(votings, filename);
        } else {
            for (Voting voting : votings) {
                String filename = "export_" + voting.getId() + "_" + timestamp + ".pdf";
                PDFExporter.exportResults(voting, filename);
            }
        }
    }

    private void exportToSingleFile(List<Voting> votings, String filename) throws IOException {
        try (PDDocument document = new PDDocument()) {
            for (Voting voting : votings) {
                PDFExporter.addVotingToDocument(document, voting);
            }
            document.save(filename);
        }
    }

    public void chooseExportPath(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }
}