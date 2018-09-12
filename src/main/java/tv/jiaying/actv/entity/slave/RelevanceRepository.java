package tv.jiaying.actv.entity.slave;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import tv.jiaying.actv.entity.slave.Relevance;

import java.util.List;

public interface RelevanceRepository extends JpaRepository<Relevance,Long> {

    Page<Relevance> findByParentColumId(long parentColumId, Pageable pageable);

    Page<Relevance> findByParentColumIdAndChildColumIdAndOnline(long parentColumId,long childColumId,Boolean online, Pageable pageable);

    Page<Relevance> findByParentColumIdAndChildItemIdAndOnline(long parentColumId, long childItemId, Boolean online, Pageable pageable);

    List<Relevance> findAllByParentColumIdAndOnlineOrderByChildItemIdDesc(long parentColumId,Boolean online);
}
