package cz.cuni.mff.java.project.sykora;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ImportException {
        if (args.length < 2) {
            System.out.println("Exiting, both folder with excel files and output folders need to be provided.");
            System.exit(1);
        }

        File inputFolder = new File(args[0]);
        File outputFolder = new File(args[1]);

        if (!inputFolder.exists() || !inputFolder.isDirectory()) {
            System.out.println("Input folder does not exist or is not a directory.");
            System.exit(1);
        }

        if (!outputFolder.exists() || !outputFolder.isDirectory()) {
            System.out.println("Output folder does not exist or is not a directory.");
            System.exit(1);
        }

        File[] listOfFiles = inputFolder.listFiles();

        if (listOfFiles == null) {
            return;
        }

        for (File listOfFile : listOfFiles) {

            if (listOfFile.isFile()) {
                File file = new File(args[0] + '/' + listOfFile.getName());
                var jsonWriter = new JsonTrainWriter();
                var parser = new SzdcTrainParser(jsonWriter, new StdOutParserLogger());

                parser.parseTrains(file);

                File jsonTargetFile = new File(outputFolder, file.getName().replaceAll("xlsx", "json"));
                try (var outputFile = new FileWriter(jsonTargetFile)) {
                    outputFile.write(jsonWriter.createRootJsonObject().toString(4));
                } catch (IOException e) {
                    System.out.println("Unable to write JSON output: " + e);
                }
            }
        }
    }

}
