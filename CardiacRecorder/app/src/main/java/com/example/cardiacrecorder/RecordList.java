package com.example.cardiacrecorder;

import java.util.ArrayList;
import java.util.List;

public class RecordList {
    private List<Record> records = new ArrayList<>();

    public void add(Record record) {
        if (records.contains(record)) {
            throw new IllegalArgumentException();
        }
        records.add(record);
    }

    public List<Record> getRecords() {
        List<Record> recordList = records;
        return recordList;
    }

    public void delete(Record record) {
        if (records.contains(record)) {
            records.remove(record);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public void update(Record record_old, Record record_new) {
        if (records.contains(record_old)) {
            records.remove(record_old);
            if (records.contains(record_new)) {
                throw new IllegalArgumentException();
            }
            records.add(record_new);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public int countRecords() {
        return records.size();
    }
}
