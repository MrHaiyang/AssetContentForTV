package tv.jiaying.actv.entity.slave;


import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

/**
 * 栏目信息
 */
@Entity
@Table(name = "colum")
@Proxy(lazy = false)
public class Colum {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "is_root")
    private Boolean isRoot; //是否是根栏目

    public enum Type {
        normal, //普通栏目
        series  //电视剧
    }

    @Column(unique = true)
    private String name; //栏目名称

    @Column(name = "name_spell")
    private String nameSpell;

    private Type type;  //栏目类型

    @Column(name = "poster_small")
    private String posterSmall; //小海报

    @Column(name = "poster_mid")
    private String posterMid; //中海报

    @Column(name = "poster_large")
    private String posterLarge; //大海报

    private String brief; //简介

    private String director; //导演

    @Column(name = "director_spell")
    private String directorSpell;

    private String actors; //演员

    @Column(name = "actors_spell")
    private String actorsSpell;

    private String year; //年份

    private String genre; //电视剧类型 若是普通栏目可为空

    @Column(name = "licensing_window_start")
    private Date licensingWindowStart; //版权开始时间

    @Column(name = "licensing_window_end")
    private Date licensingWindowEnd; //版权结束时间

    private Boolean online; //栏目上下架

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getRoot() {
        return isRoot;
    }

    public void setRoot(Boolean root) {
        isRoot = root;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getNameSpell() {
        return nameSpell;
    }

    public void setNameSpell(String nameSpell) {
        this.nameSpell = nameSpell;
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
