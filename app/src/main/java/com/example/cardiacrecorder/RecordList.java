package com.example.cardiacrecorder;

import java.util.ArrayList;
import java.util.List;

/**
 * this RecordList class will create a List using existing
 * records and show it to user and some methods to do some
 * operation on RecordList
 */
public class RecordList {
    private List<Record> records = new ArrayList<>();

    /**
     * add a record to records (a recordlist)
     * @param record datatype is Record type
     */
    public void add(Record record) {
        if (records.contains(record)) {
            throw new IllegalArgumentException();
        }
        records.add(record);
    }

    /**
     * this will return a List of records (a recordlist)
     * @return
     * List<Record>
     */
    public List<Record> getRecords() {
        List<Record> recordList = records;
        return recordList;
    }

    /**
     * delete a particular record from table if data is
     * exist on that particular table or else if not then
     * throw an exception
     * @param record Record type
     */
    public void delete(Record record) {
        if (records.contains(record)) {
            records.remove(record);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * update a particular record with new value
     * @param record_old
     * @param record_new
     */
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

    /**
     * this will show the size of RecordList
     * @return
     * size of RecordList
     */
    public int countRecords() {
        return records.size();
    }
}