package tv.jiaying.actv.entity.slave;


import javax.persistence.*;

/**
 * 提供商信息
 */
@Entity
@Table(name="provider")
public class Provider {

    @Id
    @GeneratedValue
    private long id;

    private String name; //提供商名称

    @Column(name = "provider_id")
    private String providerId; //提供商ID

    private String logo; //提供商logo

    @Column(name = "logo_tag")
    private Boolean logoTag; //是否显示logo在影片角标上

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Boolean getLogoTag() {
        return logoTag;
    }

    public void setLogoTag(Boolean logoTag) {
        this.logoTag = logoTag;
    }
}
