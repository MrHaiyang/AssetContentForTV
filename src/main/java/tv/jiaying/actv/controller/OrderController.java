package tv.jiaying.actv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tv.jiaying.actv.controller.pojo.ResultBean;
import tv.jiaying.actv.entity.slave.Item;
import tv.jiaying.actv.entity.slave.Order;
import tv.jiaying.actv.entity.slave.ItemRepository;
import tv.jiaying.actv.entity.slave.OrderRepository;
import tv.jiaying.actv.service.ServiceErrorCode;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin
@Transactional(transactionManager = "slaveTransactionManager",rollbackFor = Exception.class,readOnly = true)
public class OrderController {

    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Resource
    ItemRepository itemRepository;

    @Resource
    OrderRepository orderRepository;

    /**
     * 获取用户订购节目
     * @param smartCardId
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/user")
    @CrossOrigin
    @Transactional(transactionManager = "slaveTransactionManager",rollbackFor = Exception.class,readOnly = true)
    public ResultBean getOrders(String checkcode,String smartCardId, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orderPage = orderRepository.findAllByCardIDAndOrderTypeAndStatusAndExpireDateLessThanEqual(smartCardId, 1, 3, new Date(), pageable);
        OrderItemPageBean orderItemPageBean = new OrderItemPageBean();
        List<OrderItemBean> orderItemBeanList = new ArrayList<>();
        for (Order order : orderPage.getContent()) {
            OrderItemBean orderItemBean = new OrderItemBean();
            Item item = itemRepository.getOne(order.getDataID());
            if (item == null) {
                continue;
            }
            orderItemBean.setId(item.getId());
            orderItemBean.setTitle(item.getTitle());
            orderItemBean.setPosterSmall(item.getPosterSmall());
            orderItemBean.setPosterMid(item.getPosterMid());
            orderItemBean.setPosterLarge(item.getPosterLarge());
            orderItemBeanList.add(orderItemBean);
        }
        orderItemPageBean.setContent(orderItemBeanList);
        orderItemPageBean.setNumber(orderPage.getNumber());
        orderItemPageBean.setNumberOfElements(orderItemBeanList.size());
        orderItemPageBean.setTotalElements(orderPage.getTotalElements());
        orderItemPageBean.setTotalPage(orderPage.getTotalPages());
        return new ResultBean(ServiceErrorCode.OK, orderItemPageBean);
    }

    public class OrderItemBean {

        long id;
        String title;
        String posterSmall;
        String posterMid;
        String posterLarge;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPosterSmall() {
            return posterSmall;
        }

        public void setPosterSmall(String posterSmall) {
            this.posterSmall = posterSmall;
        }

        public String getPosterMid() {
            return posterMid;
        }

        public void setPosterMid(String posterMid) {
            this.posterMid = posterMid;
        }

        public String getPosterLarge() {
            return posterLarge;
        }

        public void setPosterLarge(String posterLarge) {
            this.posterLarge = posterLarge;
        }
    }

    public class OrderItemPageBean {

        List<OrderItemBean> content;

        private int totalPage;

        private long totalElements;

        private int numberOfElements;

        private int number;

        public List<OrderItemBean> getContent() {
            return content;
        }

        public void setContent(List<OrderItemBean> content) {
            this.content = content;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public long getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(long totalElements) {
            this.totalElements = totalElements;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }
}
