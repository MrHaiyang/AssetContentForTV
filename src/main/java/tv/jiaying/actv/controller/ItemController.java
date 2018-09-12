package tv.jiaying.actv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tv.jiaying.actv.controller.pojo.ResultBean;
import tv.jiaying.actv.entity.slave.Colum;
import tv.jiaying.actv.entity.slave.Item;
import tv.jiaying.actv.entity.slave.ColumRepository;
import tv.jiaying.actv.entity.slave.ItemRepository;
import tv.jiaying.actv.service.ServiceErrorCode;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/item")
@CrossOrigin
@ConfigurationProperties("actv.img")
@Transactional(transactionManager = "slaveTransactionManager", rollbackFor = Exception.class, readOnly = true)
public class ItemController {

    private static Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Resource
    ItemRepository itemRepository;

    @Resource
    ColumRepository columRepository;

    private String location;


    /**
     * 获取节目详情
     *
     * @param checkcode
     * @param id
     * @return
     */
    @GetMapping("/detail")
    @Transactional(transactionManager = "slaveTransactionManager", rollbackFor = Exception.class, readOnly = true)
    public ResultBean getItemInfo(String checkcode, long id) {

        Item item = itemRepository.findFirstById(id);
        if (item == null) {
            return new ResultBean(ServiceErrorCode.UNKNOWN_ASSET);
        }
        if (item.getPosterSmall() != null) item.setPosterSmall(item.getPosterSmall());
        if (item.getPosterMid() != null) item.setPosterMid(item.getPosterMid());
        if (item.getPosterLarge() != null) item.setPosterLarge(item.getPosterLarge());

        return new ResultBean(ServiceErrorCode.OK, item);
    }

    /**
     * 搜索
     *
     * @param keyword
     * @param size
     * @param page
     * @param checkcode
     * @return
     */
    @GetMapping("/keyword")
    @CrossOrigin
    @Transactional(transactionManager = "slaveTransactionManager", rollbackFor = Exception.class, readOnly = true)
    public ResultBean getSearchResult(String keyword, String dependence, int size, int page, String checkcode, String smartCardId) {

        Pageable pageable = PageRequest.of(page, size);

        String dp = this.getSearchTypeByDependence(dependence);
        Page<Colum> columPage = this.getColumPageResultBySearchString(dp, keyword, pageable);

        //Page<Colum> columPage = columRepository.findAllByTypeAndNameSpellContainingOrTypeAndActorsSpellContainingOrTypeAndDirectorSpellContaining(Colum.Type.series, keyword, Colum.Type.series, keyword, Colum.Type.series, keyword, pageable);

        if (columPage.getNumberOfElements() < size) {
            Pageable pageable1 = PageRequest.of(page, size - columPage.getNumberOfElements());
            Page<Item> itemPage = this.getItemPageResultBySearchString(dp,keyword,pageable1);
            //Page<Item> itemPage = itemRepository.findAllByShowTypeAndTitleSpellContainingOrShowTypeAndActorsSpellContainingOrShowTypeAndDirectorSpellContaining(Item.ShowType.movie, keyword, Item.ShowType.movie, keyword, Item.ShowType.movie, keyword, pageable1);
            Page<SearchBean> searchBeanPage = new Page<SearchBean>() {
                @Override
                public int getTotalPages() {
                    return itemPage.getTotalPages();
                }

                @Override
                public long getTotalElements() {
                    return columPage.getTotalElements() + itemPage.getTotalElements();
                }

                @Override
                public <U> Page<U> map(Function<? super SearchBean, ? extends U> function) {
                    return null;
                }

                @Override
                public int getNumber() {
                    return itemPage.getNumber();
                }

                @Override
                public int getSize() {
                    return size;
                }

                @Override
                public int getNumberOfElements() {
                    return itemPage.getNumberOfElements() + columPage.getNumberOfElements();
                }

                @Override
                public List<SearchBean> getContent() {
                    List<SearchBean> searchBeans = new ArrayList<>();
                    for (Colum colum : columPage.getContent()) {
                        SearchBean searchBean = new SearchBean();
                        searchBean.setId(colum.getId());
                        searchBean.setTitle(colum.getName());
                        searchBean.setType("series");
                        searchBeans.add(searchBean);
                    }
                    for (Item item : itemPage.getContent()) {
                        SearchBean searchBean = new SearchBean();
                        searchBean.setId(item.getId());
                        searchBean.setTitle(item.getTitle());
                        searchBean.setType("item");
                        searchBeans.add(searchBean);
                    }
                    return searchBeans;
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
                public Iterator<SearchBean> iterator() {
                    return null;
                }
            };

            SearchResultBean searchResultBean = new SearchResultBean();
            searchResultBean.setContent(searchBeanPage.getContent());
            searchResultBean.setNumber(searchBeanPage.getNumber());
            searchResultBean.setNumberOfElements(searchBeanPage.getNumberOfElements());
            searchResultBean.setTotalElements(searchBeanPage.getTotalElements());
            searchResultBean.setTotalPage(searchBeanPage.getTotalPages());

            return new ResultBean(ServiceErrorCode.OK, searchResultBean);

        } else {
            Page<SearchBean> searchBeanPage = new Page<SearchBean>() {
                @Override
                public int getTotalPages() {
                    return columPage.getTotalPages();
                }

                @Override
                public long getTotalElements() {
                    return columPage.getTotalElements();
                }

                @Override
                public <U> Page<U> map(Function<? super SearchBean, ? extends U> function) {
                    return null;
                }

                @Override
                public int getNumber() {
                    return columPage.getNumber();
                }

                @Override
                public int getSize() {
                    return columPage.getSize();
                }

                @Override
                public int getNumberOfElements() {
                    return columPage.getNumberOfElements();
                }

                @Override
                public List<SearchBean> getContent() {
                    List<SearchBean> searchBeans = new ArrayList<>();
                    for (Colum colum : columPage.getContent()) {
                        SearchBean searchBean = new SearchBean();
                        searchBean.setId(colum.getId());
                        searchBean.setTitle(colum.getName());
                        searchBean.setType("series");
                        searchBeans.add(searchBean);
                    }
                    return searchBeans;
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
                public Iterator<SearchBean> iterator() {
                    return null;
                }
            };

            SearchResultBean searchResultBean = new SearchResultBean();
            searchResultBean.setContent(searchBeanPage.getContent());
            searchResultBean.setNumber(searchBeanPage.getNumber());
            searchResultBean.setNumberOfElements(searchBeanPage.getNumberOfElements());
            searchResultBean.setTotalElements(searchBeanPage.getTotalElements());
            searchResultBean.setTotalPage(searchBeanPage.getTotalPages());

            return new ResultBean(ServiceErrorCode.OK, searchResultBean);
        }

    }

    public String getSearchTypeByDependence(String dp) {
        if (dp.contains("name") & dp.contains("actor") & dp.contains("director")) {
            return "name;actor;director";
        }
        if (dp.contains("name") & !dp.contains("actor") & !dp.contains("director")) {
            return "name";
        }
        if (!dp.contains("name") & dp.contains("actor") & !dp.contains("director")) {
            return "actor";
        }
        if (!dp.contains("name") & !dp.contains("actor") & dp.contains("director")) {
            return "director";
        }
        if (dp.contains("name") & dp.contains("actor") & !dp.contains("director")) {
            return "name;actor";
        }
        if (!dp.contains("name") & dp.contains("actor") & dp.contains("director")) {
            return "actor;director";
        }
        if (dp.contains("name") & !dp.contains("actor") & dp.contains("director")) {
            return "name;director";
        }
        return "name;actor;director";

    }

    public Page<Colum> getColumPageResultBySearchString(String searchStr, String keyword, Pageable pageable) {

        Page<Colum> columPage = null;

        switch (searchStr) {
            case "name":
                columPage = columRepository.findAllByTypeAndNameSpellContaining(Colum.Type.series, keyword, pageable);
                break;
            case "actor":
                columPage = columRepository.findAllByTypeAndActorsSpellContaining(Colum.Type.series, keyword, pageable);
                break;
            case "director":
                columPage = columRepository.findAllByTypeAndDirectorSpellContaining(Colum.Type.series, keyword, pageable);
                break;
            case "name;actor":
                columPage = columRepository.findAllByTypeAndNameSpellContainingOrTypeAndActorsSpellContaining(Colum.Type.series, keyword, Colum.Type.series, keyword, pageable);
                break;
            case "name;director":
                columPage = columRepository.findAllByTypeAndNameSpellContainingOrTypeAndDirectorSpellContaining(Colum.Type.series, keyword, Colum.Type.series, keyword, pageable);
                break;
            case "actor;director":
                columPage = columRepository.findAllByTypeAndActorsSpellContainingOrTypeAndDirectorSpellContaining(Colum.Type.series, keyword, Colum.Type.series, keyword, pageable);
                break;
            case "name;actor;director":
                columPage = columRepository.findAllByTypeAndNameSpellContainingOrTypeAndActorsSpellContainingOrTypeAndDirectorSpellContaining(Colum.Type.series, keyword, Colum.Type.series, keyword, Colum.Type.series, keyword, pageable);
                break;
        }

        return columPage;
    }

    public Page<Item> getItemPageResultBySearchString(String searchStr, String keyword, Pageable pageable) {

        Page<Item> itemPage = null;

        switch (searchStr) {
            case "name":
                itemPage = itemRepository.findAllByShowTypeAndTitleSpellContaining(Item.ShowType.movie, keyword, pageable);
                break;
            case "actor":
                itemPage = itemRepository.findAllByShowTypeAndActorsSpellContaining(Item.ShowType.movie, keyword, pageable);
                break;
            case "director":
                itemPage = itemRepository.findAllByShowTypeAndDirectorSpellContaining(Item.ShowType.movie, keyword, pageable);
                break;
            case "name;actor":
                itemPage = itemRepository.findAllByShowTypeAndTitleSpellContainingOrShowTypeAndActorsSpellContaining(Item.ShowType.movie, keyword, Item.ShowType.movie, keyword, pageable);
                break;
            case "name;director":
                itemPage = itemRepository.findAllByShowTypeAndTitleSpellContainingOrShowTypeAndDirectorSpellContaining(Item.ShowType.movie, keyword, Item.ShowType.movie, keyword, pageable);
                break;
            case "actor;director":
                itemPage = itemRepository.findAllByShowTypeAndActorsSpellContainingOrShowTypeAndDirectorSpellContaining(Item.ShowType.movie, keyword, Item.ShowType.movie, keyword, pageable);
                break;
            case "name;actor;director;":
                itemPage = itemRepository.findAllByShowTypeAndTitleSpellContainingOrShowTypeAndActorsSpellContainingOrShowTypeAndDirectorSpellContaining(Item.ShowType.movie, keyword, Item.ShowType.movie, keyword, Item.ShowType.movie, keyword, pageable);
                break;
        }

        return itemPage;

    }

    /**
     * 根据属性取值返回String
     *
     * @param object
     * @param objectClass
     * @param property
     * @return
     */
    public String getStringByParam(Object object, Class<?> objectClass, String property) {
        StringBuffer result = new StringBuffer();
        String[] temp = property.split(",");
        List<String> propertyList = Arrays.asList(temp);
        Field[] fields = objectClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (propertyList.contains(fields[i].getName())) {
                try {
                    fields[i].setAccessible(true);
                    result.append(fields[i].getName() + ":" + fields[i].get(object)).append(",");
                } catch (IllegalAccessException e) {
                    logger.info(e.getMessage(), e);
                }
            }
        }
        return result.toString();
    }


    public class SearchBean {
        long id;
        String title;
        String type;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public class SearchResultBean {
        private List<SearchBean> content;

        private int totalPage;

        private long totalElements;

        private int numberOfElements;

        private int number;

        public List<SearchBean> getContent() {
            return content;
        }

        public void setContent(List<SearchBean> content) {
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
