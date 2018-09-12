package tv.jiaying.actv.entity.slave;


import javax.persistence.*;

/**
 * 对item的扩展字段
 */
@Entity
@Table(name="item_extend")
public class ItemExtend {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "fs_content_format")
    private String fsContentFormat;

    @Column(name = "content_file_size")
    private String contentFileSize;

    @Column(name = "hdcontent")
    private String HDContent;

    @Column(name = "subtitle_language")
    private String subtitleLanguage;

    @Column(name = "bit_rate")
    private int bitRate;

    @Column(name = "audio_type")
    private String audioType;

    private String codec;

    @Column(name = "frame_rate")
    private String frameRate;

    private String resolution;

    private String encryption;

    private String extend1;

    private String extend2;

    private String extend3;

    private String extend4;

    private String extend5;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFsContentFormat() {
        return fsContentFormat;
    }

    public void setFsContentFormat(String fsContentFormat) {
        this.fsContentFormat = fsContentFormat;
    }

    public String getContentFileSize() {
        return contentFileSize;
    }

    public void setContentFileSize(String contentFileSize) {
        this.contentFileSize = contentFileSize;
    }

    public String getHDContent() {
        return HDContent;
    }

    public void setHDContent(String HDContent) {
        this.HDContent = HDContent;
    }

    public String getSubtitleLanguage() {
        return subtitleLanguage;
    }

    public void setSubtitleLanguage(String subtitleLanguage) {
        this.subtitleLanguage = subtitleLanguage;
    }

    public int getBitRate() {
        return bitRate;
    }

    public void setBitRate(int bitRate) {
        this.bitRate = bitRate;
    }

    public String getAudioType() {
        return audioType;
    }

    public void setAudioType(String audioType) {
        this.audioType = audioType;
    }

    public String getCodec() {
        return codec;
    }

    public void setCodec(String codec) {
        this.codec = codec;
    }

    public String getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(String frameRate) {
        this.frameRate = frameRate;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getEncryption() {
        return encryption;
    }

    public void setEncryption(String encryption) {
        this.encryption = encryption;
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }

    public String getExtend2() {
        return extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2;
    }

    public String getExtend3() {
        return extend3;
    }

    public void setExtend3(String extend3) {
        this.extend3 = extend3;
    }

    public String getExtend4() {
        return extend4;
    }

    public void setExtend4(String extend4) {
        this.extend4 = extend4;
    }

    public String getExtend5() {
        return extend5;
    }

    public void setExtend5(String extend5) {
        this.extend5 = extend5;
    }
}
