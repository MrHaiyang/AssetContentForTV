package tv.jiaying.actv.entity.slave;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import tv.jiaying.actv.entity.slave.Order;

import java.util.Date;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByCardIDAndOrderTypeAndStatusAndExpireDateLessThanEqual(String smartCardId, int orderType, int status, Date currentDate, Pageable pageable);
}
