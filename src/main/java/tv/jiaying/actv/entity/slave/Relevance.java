package tv.jiaying.actv.entity.slave;

import org.hibernate.mapping.ToOne;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 栏目与栏目，节目的关联关系
 */
@Entity
@Table(name = "project_relevance")
public class Relevance {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "parent_colum_id")
    private long parentColumId;  //父栏目

    @Column(name = "child_colum_id")
    private long childColumId;  //子栏目

    @Column(name = "child_item_id")
    private long childItemId; //栏目下的节目

    @GeneratedValue
    private int position; //节目或栏目在父栏目中的位置

    private Boolean online; //子栏目或节目在父栏目的上下架

    @Column(name = "last_update_time")
    private Date lastUpdateTime; //最后一次修改记录时间

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParentColumId() {
        return parentColumId;
    }

    public void setParentColumId(long parentColumId) {
        this.parentColumId = parentColumId;
    }

    public long getChildColumId() {
        return childColumId;
    }

    public void setChildColumId(long childColumId) {
        this.childColumId = childColumId;
    }

    public long getChildItemId() {
        return childItemId;
    }

    public void setChildItemId(long childItemId) {
        this.childItemId = childItemId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }


}
