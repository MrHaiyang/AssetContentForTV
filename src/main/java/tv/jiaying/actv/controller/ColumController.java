package tv.jiaying.actv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tv.jiaying.actv.controller.pojo.ResultBean;
import tv.jiaying.actv.entity.slave.Colum;
import tv.jiaying.actv.entity.slave.Item;
import tv.jiaying.actv.entity.slave.Relevance;
import tv.jiaying.actv.entity.slave.ColumRepository;
import tv.jiaying.actv.entity.slave.ItemRepository;
import tv.jiaying.actv.entity.slave.RelevanceRepository;
import tv.jiaying.actv.service.ServiceErrorCode;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;


@RestController
@RequestMapping("/colum")
@CrossOrigin
@Transactional(transactionManager = "slaveTransactionManager", rollbackFor = Exception.class, readOnly = true)
@ConfigurationProperties("actv.img")
public class ColumController {

    private static Logger logger = LoggerFactory.getLogger(ColumController.class);

    @Resource
    ColumRepository columRepository;

    @Resource
    RelevanceRepository relevanceRepository;

    @Resource
    ItemRepository itemRepository;

    private String location;

    /**
     * 获取子栏目内容(id,title,posterSmall,posterMid,posterLarge)
     *
     * @param id
     * @param smartCardId
     * @param checkcode
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/child")
    @CrossOrigin
    @Transactional(transactionManager = "slaveTransactionManager", rollbackFor = Exception.class, readOnly = true)
    public ResultBean getColum(long id, String smartCardId, String checkcode, String type,
                               @RequestParam(required = false, defaultValue = "0") int page,
                               @RequestParam(required = false, defaultValue = "0") int size) {


        //获取根目录id
        if (id == 0) {
            Colum colum = columRepository.findFirstByIsRootIsTrue();
            id = colum.getId();
        }
        //获取该栏目下所有关联
        if ("all".equals(type)) {
            //让节目排在栏目前面
            List<Relevance> relevances = relevanceRepository.findAllByParentColumIdAndOnlineOrderByChildItemIdDesc(id, true);
            List<ContentBean> contentBeans = new ArrayList<>();
            for (Relevance relevance : relevances) {
                if (relevance.getChildColumId() != 0) {
                    Colum childColum = columRepository.findFirstById(relevance.getChildColumId());
                    ContentBean contentBean = new ContentBean();
                    contentBean.setId(childColum.getId());
                    contentBean.setTitle(childColum.getName());
                    if (childColum.getPosterSmall() != null)
                        contentBean.setPosterSmall(childColum.getPosterSmall());
                    if (childColum.getPosterMid() != null)
                        contentBean.setPosterMid(childColum.getPosterMid());
                    if (childColum.getPosterLarge() != null)
                        contentBean.setPosterLarge(childColum.getPosterLarge());
                    contentBeans.add(contentBean);
                } else if (relevance.getChildItemId() != 0) {
                    Item item = itemRepository.getOne(relevance.getChildItemId());
                    ContentBean contentBean = new ContentBean();
                    contentBean.setId(item.getId());
                    contentBean.setTitle(item.getTitle());
                    if (item.getPosterSmall() != null) contentBean.setPosterSmall(item.getPosterSmall());
                    if (item.getPosterMid() != null) contentBean.setPosterMid(item.getPosterMid());
                    if (item.getPosterLarge() != null) contentBean.setPosterLarge(item.getPosterLarge());
                    contentBeans.add(contentBean);
                }
            }

            ColumResultBean columResultBean = new ColumResultBean();

            columResultBean.setContent(contentBeans);
            columResultBean.setNumber(0);
            columResultBean.setNumberOfElements(contentBeans.size());
            columResultBean.setTotalElements(contentBeans.size());
            columResultBean.setTotalPage(1);
            return new ResultBean(ServiceErrorCode.OK, columResultBean);
        }

        Pageable pageable = PageRequest.of(page, size);
        ColumResultBean columResultBean = new ColumResultBean();
        List<ContentBean> contentBeans = new ArrayList<>();
        if ("item".equals(type)) {
            Page<Relevance> relevancePage = relevanceRepository.findByParentColumIdAndChildColumIdAndOnline(id, 0, true, pageable);
            Page<Item> itemPage = new Page<Item>() {
                @Override
                public int getTotalPages() {
                    return relevancePage.getTotalPages();
                }

                @Override
                public long getTotalElements() {
                    return relevancePage.getTotalElements();
                }

                @Override
                public <U> Page<U> map(Function<? super Item, ? extends U> function) {
                    return null;
                }

                @Override
                public int getNumber() {
                    return relevancePage.getNumber();
                }

                @Override
                public int getSize() {
                    return relevancePage.getSize();
                }

                @Override
                public int getNumberOfElements() {
                    return relevancePage.getNumberOfElements();
                }

                @Override
                public List<Item> getContent() {
                    List<Item> items = new ArrayList<>();
                    for (Relevance relevance : relevancePage.getContent()) {
                        if (relevance.getChildItemId() != 0) {
                            items.add(itemRepository.getOne(relevance.getChildItemId()));
                        }
                    }
                    return items;
                }

                @Override
                public boolean hasContent() {
                    return relevancePage.hasContent();
                }

                @Override
                public Sort getSort() {
                    return null;
                }

                @Override
                public boolean isFirst() {
                    return relevancePage.isFirst();
                }

                @Override
                public boolean isLast() {
                    return relevancePage.isLast();
                }

                @Override
                public boolean hasNext() {
                    return relevancePage.hasNext();
                }

                @Override
                public boolean hasPrevious() {
                    return relevancePage.hasPrevious();
                }

                @Override
                public Pageable nextPageable() {
                    return relevancePage.nextPageable();
                }

                @Override
                public Pageable previousPageable() {
                    return relevancePage.previousPageable();
                }

                @Override
                public Iterator<Item> iterator() {
                    return null;
                }
            };

            for (Item item : itemPage.getContent()) {
                ContentBean contentBean = new ContentBean();
                contentBean.setId(item.getId());
                contentBean.setTitle(item.getTitle());
                if (item.getPosterSmall() != null) contentBean.setPosterSmall(item.getPosterSmall());
                if (item.getPosterMid() != null) contentBean.setPosterMid(item.getPosterMid());
                if (item.getPosterLarge() != null) contentBean.setPosterLarge(item.getPosterLarge());

                contentBeans.add(contentBean);
            }

            columResultBean.setContent(contentBeans);
            columResultBean.setTotalPage(itemPage.getTotalPages());
            columResultBean.setTotalElements(itemPage.getTotalElements());
            columResultBean.setNumberOfElements(itemPage.getNumberOfElements());
            columResultBean.setNumber(itemPage.getNumber());


        } else if ("column".equals(type)) {
            Page<Relevance> relevancePage = relevanceRepository.findByParentColumIdAndChildItemIdAndOnline(id, 0, true, pageable);
            Page<Colum> columPage = new Page<Colum>() {
                @Override
                public int getTotalPages() {
                    return relevancePage.getTotalPages();
                }

                @Override
                public long getTotalElements() {
                    return relevancePage.getTotalElements();
                }

                @Override
                public <U> Page<U> map(Function<? super Colum, ? extends U> function) {
                    return null;
                }

                @Override
                public int getNumber() {
                    return relevancePage.getNumber();
                }

                @Override
                public int getSize() {
                    return relevancePage.getSize();
                }

                @Override
                public int getNumberOfElements() {
                    return relevancePage.getNumberOfElements();
                }

                @Override
                public List<Colum> getContent() {
                    List<Colum> colums = new ArrayList<>();
                    for (Relevance relevance : relevancePage.getContent()) {
                        if (relevance.getChildColumId() != 0) {
                            colums.add(columRepository.getOne(relevance.getChildColumId()));
                        }
                    }
                    return colums;
                }

                @Override
                public boolean hasContent() {
                    return false;
                }

                @Override
                public Sort getSort() {
                    return null;
                }

                @Override
                public boolean isFirst() {
                    return false;
                }

                @Override
                public boolean isLast() {
                    return false;
                }

                @Override
                public boolean hasNext() {
                    return false;
                }

                @Override
                public boolean hasPrevious() {
                    return false;
                }

                @Override
                public Pageable nextPageable() {
                    return null;
                }

                @Override
                public Pageable previousPageable() {
                    return null;
                }

                @Override
                public Iterator<Colum> iterator() {
                    return null;
                }
            };

            for (Colum colum : columPage.getContent()) {
                ContentBean contentBean = new ContentBean();
                contentBean.setId(colum.getId());
                contentBean.setTitle(colum.getName());
                if (colum.getPosterSmall() != null) contentBean.setPosterSmall(colum.getPosterSmall());
                if (colum.getPosterMid() != null) contentBean.setPosterMid(colum.getPosterMid());
                if (colum.getPosterLarge() != null) contentBean.setPosterLarge(colum.getPosterLarge());

                contentBeans.add(contentBean);
            }

            columResultBean.setContent(contentBeans);
            columResultBean.setTotalPage(columPage.getTotalPages());
            columResultBean.setTotalElements(columPage.getTotalElements());
            columResultBean.setNumberOfElements(columPage.getNumberOfElements());
            columResultBean.setNumber(columPage.getNumber());

        }

        return new ResultBean(ServiceErrorCode.OK, columResultBean);

    }

    /**
     * 获取栏目详情
     *
     * @param id
     * @return
     */
    @GetMapping("/detail")
    @Transactional(transactionManager = "slaveTransactionManager", rollbackFor = Exception.class, readOnly = true)
    public ResultBean getColum(long id) {
        Colum colum = columRepository.findFirstById(id);

        if (colum == null) {
            return new ResultBean(ServiceErrorCode.UNKNOWN_COLUM);
        }
        if (colum.getPosterSmall() != null) colum.setPosterSmall(colum.getPosterSmall());
        if (colum.getPosterMid() != null) colum.setPosterMid( colum.getPosterMid());
        if (colum.getPosterLarge() != null) colum.setPosterLarge(colum.getPosterLarge());
        return new ResultBean(ServiceErrorCode.OK, colum);
    }

    public class ContentBean {

        private long id;

        private String title;

        private String posterSmall;

        private String posterMid;

        private String posterLarge;

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

    public class ColumResultBean {

        private List<ContentBean> content;

        private int totalPage;

        private long totalElements;

        private int numberOfElements;

        private int number;

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
