package tv.jiaying.actv.entity.master;


import javax.persistence.*;
import java.util.Date;

/**
 * 用户收藏信息
 */
@Entity
@Table(name = "collection_record")
public class CollectionRecordMaster {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "smart_card_id")
    private String smartCardId; //智能卡号

    @Column(name = "collection_id")
    private long collectionId; //收藏的媒资id itemId 或者栏目ID

    public enum Type {
        movie, //节目
        series //电视剧专栏
    }

    private Type type; //收藏类型

    private String watch; //备注

    @Column(name = "last_update_time")
    private Date lastUpdateTime; //记录修改时间

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSmartCardId() {
        return smartCardId;
    }

    public void setSmartCardId(String smartCardId) {
        this.smartCardId = smartCardId;
    }

    public long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(long collectionId) {
        this.collectionId = collectionId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getWatch() {
        return watch;
    }

    public void setWatch(String watch) {
        this.watch = watch;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
