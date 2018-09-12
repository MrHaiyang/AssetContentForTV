package tv.jiaying.actv.entity.slave;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRecordRepository extends JpaRepository<CollectionRecord,Long> {

    Page<CollectionRecord> findBySmartCardIdOrderByLastUpdateTime(String smartCardId, Pageable pageable);

    int deleteBySmartCardIdAndCollectionIdAndType(String smartCardId, long collectionId, CollectionRecord.Type type);

    CollectionRecord findFirstBySmartCardIdAndCollectionIdAndType(String smartCardId, long collectionId, CollectionRecord.Type type);

    boolean existsBySmartCardIdAndCollectionIdAndType(String smartCardId,long collectionId,CollectionRecord.Type type);
}
