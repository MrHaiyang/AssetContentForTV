//package tv.jiaying.actv.entity.master;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Date;
//
//public interface OrderMasterRepository extends JpaRepository<OrderMaster,Long> {
//
//    Page<OrderMaster> findAllByCardIDAndOrderTypeAndStatusAndExpireDateLessThanEqual(String smartCardId, int orderType, int status, Date currentDate, Pageable pageable);
//
//}
