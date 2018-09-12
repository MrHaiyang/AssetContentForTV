package tv.jiaying.actv.entity.slave;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

/**
 * 媒资基本单位 用来表示影片或者电视剧
 */
@Entity
@Table(name = "item")
@JsonIgnoreProperties({"hibernateLazyInitializer", "itemExtend"})
@Proxy(lazy = false)
public class Item {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Provider provider; //提供商

    @Column(name = "asset_id")
    private String assetId; //媒资ID

    private String product; //媒资属性 VOD

    private String title; //影片名称

    @Column(name = "title_spell")
    private String titleSpell;

    @Column(name = "run_time")
    private String runTime; //时长

    public enum ShowType {
        movie,  //电影
        series  //电视剧
    }

    @Column(name = "show_type")
    private ShowType showType; //媒资类型

    private String genre; //影片类型

    @Column(name = "licensing_window_start")
    private Date licensingWindowStart; //版权开始时间

    @Column(name = "licensing_window_end")
    private Date licensingWindowEnd; //版权结束时间

    private String year; //影片上映时间

    private String language; //语言

    private String director; //导演

    @Column(name = "director_spell")
    private String directorSpell;

    private String actors; //演员

    @Column(name = "actors_spell")
    private String actorsSpell;

    @Column(name = "summary_short")
    private String summaryShort; //影片简介

    @Column(name = "suggested_price")
    private Double suggestedPrice; //建议售价

    @Column(name = "country_of_origin")
    private String countryOfOrigin; //片源产地

    @Column(name = "poster_small")
    private String posterSmall; //小海报

    @Column(name = "poster_mid")
    private String posterMid; //中海报

    @Column(name = "poster_large")
    private String posterLarge; //大海报

    private Boolean online; //上下架

    @Column(name = "transfer_url")
    private String transferUrl; //内容注入地址

    @Column(name = "transfer_state")
    private Boolean transferState;

    @Column(name = "asset_service")
    private String assetService; //媒资存放服务器地址

    @Column(name = "play_url")
    private String playUrl; //点播地址  和assetService拼起来合成点播地址

    @OneToOne
    private ItemExtend item_extend; //扩展字段

    @Column(name = "modify_date")
    private Date modifyDate;  //修改时间

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getAssetService() {
        return assetService;
    }

    public void setAssetService(String assetService) {
        this.assetService = assetService;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public ShowType getShowType() {
        return showType;
    }

    public void setShowType(ShowType showType) {
        this.showType = showType;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getLicensingWindowStart() {
        return licensingWindowStart;
    }

    public void setLicensingWindowStart(Date licensingWindowStart) {
        this.licensingWindowStart = licensingWindowStart;
    }

    public Date getLicensingWindowEnd() {
        return licensingWindowEnd;
    }

    public void setLicensingWindowEnd(Date licensingWindowEnd) {
        this.licensingWindowEnd = licensingWindowEnd;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getSummaryShort() {
        return summaryShort;
    }

    public void setSummaryShort(String summaryShort) {
        this.summaryShort = summaryShort;
    }

    public Double getSuggestedPrice() {
        return suggestedPrice;
    }

    public void setSuggestedPrice(Double suggestedPrice) {
        this.suggestedPrice = suggestedPrice;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
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

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public ItemExtend getItem_extend() {
        return item_extend;
    }

    public void setItem_extend(ItemExtend item_extend) {
        this.item_extend = item_extend;
    }

    public String getTransferUrl() {
        return transferUrl;
    }

    public void setTransferUrl(String transferUrl) {
        this.transferUrl = transferUrl;
    }

    public Boolean getTransferState() {
        return transferState;
    }

    public void setTransferState(Boolean transferState) {
        this.transferState = transferState;
    }

    public String getTitleSpell() {
        return titleSpell;
    }

    public void setTitleSpell(String titleSpell) {
        this.titleSpell = titleSpell;
    }

    public String getDirectorSpell() {
        return directorSpell;
    }

    public void setDirectorSpell(String directorSpell) {
        this.directorSpell = directorSpell;
    }

    public String getActorsSpell() {
        return actorsSpell;
    }

    public void setActorsSpell(String actorsSpell) {
        this.actorsSpell = actorsSpell;
    }
}
