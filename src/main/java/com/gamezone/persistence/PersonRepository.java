package com.gamezone.persistence;

import com.gamezone.model.Client;
import com.gamezone.model.Person;
import com.gamezone.model.Seller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
/**
 * Handles file persistence operations for clients and sellers.
 */
public class PersonRepository {

    private final Path filePath;
/**
 * Creates a repository using the specified data file.
 *
 * @param fileName path of the file used to store people
 */
    public PersonRepository(String fileName) {
        this.filePath = Path.of(fileName);
    }
/**
 * Saves a person in the data file.
 *
 * @param person person to save
 * @throws IOException if the file cannot be written
 */
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
    /**
 * Loads all people stored in the data file.
 *
 * @return list of stored people
 * @throws IOException if the file cannot be read
 */
    public List<Person> load() throws IOException {
    List<Person> people = new ArrayList<>();

    if (!Files.exists(filePath)) {
        return people;
    }

    try (BufferedReader reader = Files.newBufferedReader(filePath)) {
        String line;

        while ((line = reader.readLine()) != null) {
            if (!line.isBlank()) {
                Person person = convertFromFileLine(line);

                if (person != null) {
                    people.add(person);
                }
            }
        }
    }

    return people;
}
private Person convertFromFileLine(String line) {
    String[] data = line.split("\\|", -1);

    if (data.length == 5 && data[0].equals("CLIENT")) {
        return new Client(
                data[1],
                data[2],
                data[3],
                data[4]);
    }

    if (data.length == 6 && data[0].equals("SELLER")) {
        return new Seller(
                data[1],
                data[2],
                data[3],
                data[4],
                data[5]);
    }

    return null;
}
}