package com.ecommerce.filterprices.infrastructure.adapter.db.migration;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Pattern;


public class V2__insert_data extends BaseJavaMigration {
    private static final String COMMA = ",";
    private static final String OPEN_BRACKET = "(";
    private static final String CLOSE_BRACKET = ")";

    // regex pattern of the example given date 2020-01-01-00.00.00
    private static final Pattern EXAMPLE_REGEX_PATTERN = Pattern.compile("([0-9]{4})-([0-9]{2})-([0-9]{2})-([0-9]{2}).([0-9]{2}).([0-9]{2})");
    private static final String EXAMPLE_DATE_PATTERN = "yyyy-MM-dd-HH.mm.ss";
    @Override
    public void migrate(Context context) throws Exception {
        migrateFromCsv(context, "price.csv", "price", new String[]{"brand_id", "start_date", "end_date", "price_list", "product_id", "priority", "price","currency"});
    }

    /**
     * Builds and execute migration from CSV file into database
     * @param context
     * @param fileName
     * @param table
     * @param fields
     * @throws IOException
     */
    private void migrateFromCsv(Context context, String fileName, String table, String[] fields) throws IOException {
        final String INSERT = "INSERT INTO";
        final String VALUES = "VALUES";
        final String SPACE = " ";
        FileReader csvFile = new FileReader(getClass().getClassLoader().getResource(fileName).getPath());
        Iterable<CSVRecord> csvRecords = CSVFormat.EXCEL.parse(csvFile);
        csvRecords.forEach(record -> {
            try {
                context.getConnection().createStatement().execute( String.join( SPACE,INSERT, table,
                        getFields(fields), VALUES, getValuesFromRecord(record,fields.length)));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     *
     * @param fields
     * @return comma separated fields
     */
    private String getFields(String[] fields) {
        return OPEN_BRACKET + String.join(COMMA,fields) + CLOSE_BRACKET;
    }

    /**
     *
     * @param record
     * @param length
     * @return comma separated values
     */
    private String getValuesFromRecord(CSVRecord record, int length) {
        final String SINGLE_QUOTE = "'";
        StringBuilder sb = new StringBuilder();
        sb.append(OPEN_BRACKET);
        for (int i = 0; i < length; i++) {
            String currentField = record.get(i);
            currentField = Optional.ofNullable(currentField)
                    .filter(current -> EXAMPLE_REGEX_PATTERN.matcher(current).matches())
                    .map(field -> {
                        LocalDateTime localDateTime = LocalDateTime.parse(field, DateTimeFormatter
                                .ofPattern(EXAMPLE_DATE_PATTERN));
                        return localDateTime.toString();
                    })
                    .orElse(currentField);

            sb.append(SINGLE_QUOTE)
                    .append(currentField)
                    .append(SINGLE_QUOTE)
                    .append(COMMA);
        }
        if (sb.length() > 0) {
            // remove last comma
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(CLOSE_BRACKET);
        return sb.toString();
    }
}
