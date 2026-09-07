package com.gamezone.persistence;

import com.gamezone.model.Client;
import com.gamezone.model.Person;
import com.gamezone.model.Seller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class PersonRepository {

    private final Path filePath;

    public PersonRepository(String fileName) {
        this.filePath = Path.of(fileName);
    }

    public void save(Person person) throws IOException {
        createParentFolder();

        try (BufferedWriter writer = Files.newBufferedWriter(
                filePath,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {

            writer.write(convertToFileLine(person));
            writer.newLine();
        }
    }

    private String convertToFileLine(Person person) {
        if (person instanceof Client) {
            Client client = (Client) person;

            return String.join("|",
                    "CLIENT",
                    client.getId(),
                    client.getName(),
                    client.getPhone(),
                    client.getEmail());
        }

        if (person instanceof Seller) {
            Seller seller = (Seller) person;

            return String.join("|",
                    "SELLER",
                    seller.getId(),
                    seller.getName(),
                    seller.getPhone(),
                    seller.getEmployeeCode(),
                    seller.getWorkShift());
        }

        throw new IllegalArgumentException("Unsupported person type");
    }

    private void createParentFolder() throws IOException {
        Path parentFolder = filePath.getParent();

        if (parentFolder != null) {
            Files.createDirectories(parentFolder);
        }
    }
}
`