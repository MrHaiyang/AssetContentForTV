package tv.jiaying.actv.entity.master;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import tv.jiaying.actv.entity.slave.CollectionRecord;

public interface CollectionRecordMasterRepository extends JpaRepository<CollectionRecordMaster,Long> {
    CollectionRecordMaster findFirstBySmartCardIdAndCollectionIdAndType(String smartCardId, long collectionId, CollectionRecordMaster.Type type);

    boolean existsBySmartCardIdAndCollectionIdAndType(String smartCardId,long collectionId,CollectionRecordMaster.Type type);

}
