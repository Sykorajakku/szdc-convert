package cz.cuni.mff.java.project.sykora;

import java.io.File;

public class Main {

    public static void main(String[] args) throws ImportException {

        File inputFolder = new File(args[0]);
        File outputFolder = new File(args[1]);

        File[] listOfFiles = inputFolder.listFiles();

        int tablesCount = 0;

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {

                File file = new File(args[0] + '/' + listOfFiles[i].getName());
                System.out.println("Processing file: " + file.getName());
                tablesCount+= SzdcTrainParser.ParseTrain(file, outputFolder);
            }
        }

        System.out.println("Number of parsed tables: " + tablesCount);
    }
}
