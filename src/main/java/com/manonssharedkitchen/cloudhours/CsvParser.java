package com.manonssharedkitchen.cloudhours;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.StrNotNullOrEmpty;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvConstraintViolationException;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.manonssharedkitchen.cloudhours.model.Record;

public class CsvParser {
    static List<Record> parseBrivoReport(String fileName)
            throws IOException {
        List<Record> records = new ArrayList<>();
        try (ICsvBeanReader beanReader = new CsvBeanReader(new StringReader(fileName),
                CsvPreference.STANDARD_PREFERENCE)) {
            // the header elements are used to map the values to the bean
            final String[] headers = new String[] { "Date", "Event", null, "Credential", null, "Site", "Door", null };
            final CellProcessor[] processors = getProcessors();

            // Skip the first 7 lines of the csv report.
            final String[] fakeHeader = new String[] { null, null, null, null, null, null, null, null };
            final CellProcessor[] fakeProcessor = new CellProcessor[] {
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional(),
            };
            for (int i = 0; i < 8; i++) {
                beanReader.read(Record.class, fakeHeader, fakeProcessor);
            }
            Record record;
            while ((record = beanReader.read(Record.class, headers, processors)) != null) {
                records.add(record);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SuperCsvConstraintViolationException e) {
            System.out.println("Unable to process line: " + e.toString());
            System.out.println("");
        }
        return records;
    }

    private static CellProcessor[] getProcessors() {
        final CellProcessor[] processors = new CellProcessor[] {
                new StrNotNullOrEmpty(), // Date/Time
                new StrNotNullOrEmpty(), // Event/User
                new Optional(),
                new Optional(), // Credential
                new Optional(),
                new StrNotNullOrEmpty(), // Site
                new StrNotNullOrEmpty(), // Door/Device
                new Optional()
        };
        return processors;
    }
}
