package com.example.cardiacrecorder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * HistoryActivityUnitTest class for testing on List insertion, update or delete
 */
public class HistoryActivityUnitTest {
    /**
     * create a Mock RecordList with one Record data
     * @return
     * returns a RecordList
     */
    private  RecordList mockRecordList() {
        RecordList recordList = new RecordList();
        recordList.add(mockRecord());
        return recordList;
    }

    /**
     * create a Mock Record
     * @return
     * returns created Mock Record
     */
    private Record mockRecord() {
        return new Record("95", "75", "68", "Tuesday,July 12", "08:02 PM", "after walk", "Normal", "Normal");
    }

    /**
     * checks if a record is added successfully on RecordList
     */
    @Test
    public void testAdd() {
        RecordList recordList = mockRecordList();
        assertEquals(1, recordList.getRecords().size());

        Record record = new Record("98", "78", "68", "Tuesday,July 12", "08:06 PM", "after drink", "Normal", "Normal");
        recordList.add(record);

        assertEquals(2, recordList.getRecords().size());
        assertTrue(recordList.getRecords().contains(record));
    }

    /**
     * checks if add function handle exception successfully
     */
    @Test
    public void testAddException() {
        RecordList recordList = new RecordList();
        Record record = mockRecord();
        recordList.add(record);

        assertThrows(IllegalArgumentException.class, () -> {
            recordList.add(record);
        });
    }

    /**
     * checks if getRecords return a List successfully
     */
    @Test
    public void testGetRecords() {
        RecordList recordList = mockRecordList();
        assertEquals(0, mockRecord().compareTo(recordList.getRecords().get(0)));

        Record record = new Record("98", "78", "68", "Tuesday,July 12", "08:06 PM", "after drink", "Normal", "Normal");
        recordList.add(record);

        assertEquals(0, record.compareTo(recordList.getRecords().get(1)));
        assertEquals(0, mockRecord().compareTo(recordList.getRecords().get(0)));
    }

    /**
     * checks if record is deleted successfully from RecordList
     */
    @Test
    public void testDelete() {
        RecordList temp = new RecordList();

        Record a = new Record("98", "78", "68", "Tuesday,July 12", "08:06 PM", "after drink", "Normal", "Normal");

        temp.add(a);
        temp.delete(a);

        assertFalse(temp.getRecords().contains(a));
    }

    /**
     * checks if delete function handle exception successfully
     */
    @Test
    public void testDeleteException() {
        RecordList temp = mockRecordList();

        Record record = new Record("98", "78", "68", "Tuesday,July 12", "08:06 PM", "after drink", "Normal", "Normal");

        assertThrows(IllegalArgumentException.class, () -> {
            temp.delete(record);
        });
    }

    /**
     * checks if countRecords function can count records on RecordList successfully
     */
    @Test
    public void testcountRecords() {
        RecordList recordList = mockRecordList();

        Record record = new Record("98", "78", "68", "Tuesday,July 12", "08:06 PM", "after drink", "Normal", "Normal");

        recordList.add(record);
        assertEquals(2, recordList.countRecords());
    }

    /**
     * checks if record is updated successfully on RecordList
     */
    @Test
    public void testupdate() {
        RecordList recordList = new RecordList();

        Record record1 = new Record("95", "75", "68", "Tuesday,July 12", "08:02 PM", "after walk", "Normal", "Normal");
        Record record2 = new Record("98", "78", "68", "Tuesday,July 12", "08:06 PM", "after drink", "Normal", "Normal");

        recordList.add(record1);

        recordList.update(record1, record2);
        assertEquals(0, record2.compareTo(recordList.getRecords().get(0)));
    }

    /**
     * checks if update function handle exception in deleting successfully
     */
    @Test
    public void testUpdateExceptionDelete() {
        RecordList recordList = new RecordList();

        Record record1 = new Record("95", "75", "68", "Tuesday,July 12", "08:02 PM", "after walk", "Normal", "Normal");
        Record record2 = new Record("98", "78", "68", "Tuesday,July 12", "08:06 PM", "after drink", "Normal", "Normal");

        assertThrows(IllegalArgumentException.class, () -> {
            recordList.update(record1, record2);
        });
    }

    /**
     * checks if update function handle exception in adding successfully
     */
    @Test
    public void testUpdateExceptionAdd() {
        RecordList recordList = new RecordList();

        Record record1 = new Record("95", "75", "68", "Tuesday,July 12", "08:02 PM", "after walk", "Normal", "Normal");
        Record record2 = new Record("98", "78", "68", "Tuesday,July 12", "08:06 PM", "after drink", "Normal", "Normal");

        recordList.add(record2);
        recordList.add(record1);
        assertThrows(IllegalArgumentException.class, () -> {
            recordList.update(record1, record2);
        });
    }

}