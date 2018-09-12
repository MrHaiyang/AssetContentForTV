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
import tv.jiaying.actv.entity.master.CollectionRecordMaster;
import tv.jiaying.actv.entity.master.CollectionRecordMasterRepository;
import tv.jiaying.actv.entity.slave.*;
import tv.jiaying.actv.service.ServiceErrorCode;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    CollectionRecordRepository collectionRecordRepository;

    @Resource
    CollectionRecordMasterRepository collectionRecordMasterRepository;


    @Resource
    ItemRepository itemRepository;

    @Resource
    ColumRepository columRepository;


    /**
     * 用户是否收藏某一项目
     *
     * @param smartCardId
     * @param checkcode
     * @param collectionId
     * @param collectionType
     * @return
     */
    @GetMapping("/collection/exist")
    @CrossOrigin
    @Transactional(transactionManager = "slaveTransactionManager", rollbackFor = Exception.class,readOnly = true)
    public ResultBean findOnefromUser(String smartCardId, String checkcode, long collectionId, CollectionRecord.Type collectionType) {

        Boolean isExist = collectionRecordRepository.existsBySmartCardIdAndCollectionIdAndType(smartCardId, collectionId, collectionType);
        return new ResultBean(ServiceErrorCode.OK, isExist);
    }

    /**
     * 获取用户收藏的节目
     *
     * @param smartCardId
     * @param checkcode
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/collection")
    @CrossOrigin
    @Transactional(transactionManager = "slaveTransactionManager", rollbackFor = Exception.class,readOnly = true)
    public ResultBean getCollections(String smartCardId, String checkcode, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<CollectionRecord> collectionRecordPage = collectionRecordRepository.findBySmartCardIdOrderByLastUpdateTime(smartCardId, pageable);

        CollectionPageBean collectionPageBean = new CollectionPageBean();

        List<CollectionBean> collectionBeans = new ArrayList<>();
        for (CollectionRecord collectionRecord : collectionRecordPage.getContent()) {
            CollectionBean collectionBean = new CollectionBean();
            if (CollectionRecord.Type.movie.equals(collectionRecord.getType())) {
                Item item = itemRepository.getOne(collectionRecord.getCollectionId());
                collectionBean.setId(item.getId());
                collectionBean.setTitle(item.getTitle());
                collectionBean.setPosterSmall(item.getPosterSmall());
                collectionBean.setPosterMid(item.getPosterMid());
                collectionBean.setPosterLarge(item.getPosterLarge());
            } else if (CollectionRecord.Type.series.equals(collectionRecord.getType())) {
                Colum colum = columRepository.getOne(collectionRecord.getCollectionId());
                collectionBean.setId(colum.getId());
                collectionBean.setTitle(colum.getName());
                collectionBean.setPosterSmall(colum.getPosterSmall());
                collectionBean.setPosterMid(colum.getPosterMid());
                collectionBean.setPosterLarge(colum.getPosterLarge());
            }
            collectionBeans.add(collectionBean);
        }
        collectionPageBean.setContent(collectionBeans);
        collectionPageBean.setNumber(collectionRecordPage.getNumber());
        collectionPageBean.setNumberOfElements(collectionRecordPage.getNumberOfElements());
        collectionPageBean.setTotalElements(collectionRecordPage.getTotalElements());
        collectionPageBean.setTotalPage(collectionRecordPage.getTotalPages());

        return new ResultBean(ServiceErrorCode.OK, collectionPageBean);

    }

    /**
     * 删除用户收藏列表中的节目
     *
     * @param smartCardId
     * @param checkcode
     * @param collectionId
     * @param collectionType
     * @return
     */
    @GetMapping("/collection/delete")
    @CrossOrigin
    @Transactional(transactionManager = "masterTransactionManager", rollbackFor = Exception.class)
    public ResultBean deleteCollection(String smartCardId, String checkcode, long collectionId, CollectionRecordMaster.Type collectionType) {

        CollectionRecordMaster collectionRecordMaster = collectionRecordMasterRepository.findFirstBySmartCardIdAndCollectionIdAndType(smartCardId, collectionId, collectionType);
        if (collectionRecordMaster == null) {
            return new ResultBean(ServiceErrorCode.UNKNOW_COLLECTION);
        }
        collectionRecordMasterRepository.delete(collectionRecordMaster);
        return new ResultBean(ServiceErrorCode.OK);
    }

    /**
     * 上传用户收藏节目信息
     *
     * @param smartCardId
     * @param checkcode
     * @param collectionId
     * @param collectionType
     * @return
     */
    @GetMapping("/collection/add")
    @CrossOrigin
    @Transactional(transactionManager = "masterTransactionManager", rollbackFor = Exception.class)
    public ResultBean saveCollection(String smartCardId, String checkcode, long collectionId, CollectionRecordMaster.Type collectionType) {

        Boolean exist = collectionRecordMasterRepository.existsBySmartCardIdAndCollectionIdAndType(smartCardId, collectionId, collectionType);
        if (exist) {
            return new ResultBean(ServiceErrorCode.UNKNOW_COLLECTION_REPEAT);
        }
        CollectionRecordMaster collection = new CollectionRecordMaster();
        collection.setSmartCardId(smartCardId);
        collection.setCollectionId(collectionId);
        collection.setType(collectionType);
        collection.setLastUpdateTime(new Date());
        collectionRecordMasterRepository.save(collection);

        return new ResultBean(ServiceErrorCode.OK);
    }

    public class CollectionBean {

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

    public class CollectionPageBean {
        private List<CollectionBean> content;

        private int totalPage;

        private long totalElements;

        private int numberOfElements;

        private int number;

        public List<CollectionBean> getContent() {
            return content;
        }

        public void setContent(List<CollectionBean> content) {
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
