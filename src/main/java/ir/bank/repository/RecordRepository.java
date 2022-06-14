package ir.bank.repository;

import ir.bank.base.repository.BaseRepository;
import ir.bank.domain.Records;

import java.util.List;

public interface RecordRepository  extends BaseRepository<Long, Records> {
    void initRecords();

    List<Records> findByCardId(Long id);
}
