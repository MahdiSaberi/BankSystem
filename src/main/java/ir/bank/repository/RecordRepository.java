package ir.bank.repository;

import ir.bank.base.repository.BaseRepository;
import ir.bank.domain.Record;

public interface RecordRepository  extends BaseRepository<Long, Record> {
    void initRecords();
}
