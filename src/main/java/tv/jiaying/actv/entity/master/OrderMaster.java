//package tv.jiaying.actv.entity.master;
//
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import java.util.Date;
//
//@Entity
//@Table(name = "orderRecord")
//public class OrderMaster {
//
//    @Id
//    @GeneratedValue
//    private long id;
//
//    private String orderNo; //订单号，由系统产生
//
//    private int orderType; //订单类型，产品包0 或者 单片1
//
//    private int payType; //支付方式
//
//    private String payAmount; //支付金额，缴费通知接口返回
//
//    private int payChannelType; //支付方式，缴费通知接口返回
//
//    private Date tranDate; //支付交易时间，缴费通知接口返回
//
//    private String billKey; //缴费号码，缴费通知接口返回
//
//    private int status; //缴费状态 3表示支付成功
//
//    private String payOrderNo; //支付订单号
//
//    private String paymentItemName; //缴费项目名称
//
//    private String assetID; //单片的assetId
//
//    private String providerID; //单片的providerId
//
//    private String ppvID; //商品的ppvid
//
//    private long dataID; //内容ID itemId
//
//    private String cardID; //智能卡号
//
//    private String title; //商品名称
//
//    private Double pirce;//商品价格
//
//    private Date orderDate;  //订单时间
//
//    private Date expireDate;//订单过期时间
//
//    private Date modifyDate; //订单修改时间
//
//    private int duration;//包月时长
//
//    public int getDuration() {
//        return duration;
//    }
//
//    public void setDuration(int duration) {
//        this.duration = duration;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getOrderNo() {
//        return orderNo;
//    }
//
//    public void setOrderNo(String orderNo) {
//        this.orderNo = orderNo;
//    }
//
//    public int getOrderType() {
//        return orderType;
//    }
//
//    public void setOrderType(int orderType) {
//        this.orderType = orderType;
//    }
//
//    public int getPayType() {
//        return payType;
//    }
//
//    public void setPayType(int payType) {
//        this.payType = payType;
//    }
//
//    public String getPayAmount() {
//        return payAmount;
//    }
//
//    public void setPayAmount(String payAmount) {
//        this.payAmount = payAmount;
//    }
//
//    public int getPayChannelType() {
//        return payChannelType;
//    }
//
//    public void setPayChannelType(int payChannelType) {
//        this.payChannelType = payChannelType;
//    }
//
//    public Date getTranDate() {
//        return tranDate;
//    }
//
//    public void setTranDate(Date tranDate) {
//        this.tranDate = tranDate;
//    }
//
//    public String getBillKey() {
//        return billKey;
//    }
//
//    public void setBillKey(String billKey) {
//        this.billKey = billKey;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public String getPayOrderNo() {
//        return payOrderNo;
//    }
//
//    public void setPayOrderNo(String payOrderNo) {
//        this.payOrderNo = payOrderNo;
//    }
//
//    public String getPaymentItemName() {
//        return paymentItemName;
//    }
//
//    public void setPaymentItemName(String paymentItemName) {
//        this.paymentItemName = paymentItemName;
//    }
//
//    public String getAssetID() {
//        return assetID;
//    }
//
//    public void setAssetID(String assetID) {
//        this.assetID = assetID;
//    }
//
//    public String getProviderID() {
//        return providerID;
//    }
//
//    public void setProviderID(String providerID) {
//        this.providerID = providerID;
//    }
//
//    public String getPpvID() {
//        return ppvID;
//    }
//
//    public void setPpvID(String ppvID) {
//        this.ppvID = ppvID;
//    }
//
//    public long getDataID() {
//        return dataID;
//    }
//
//    public void setDataID(long dataID) {
//        this.dataID = dataID;
//    }
//
//    public String getCardID() {
//        return cardID;
//    }
//
//    public void setCardID(String cardID) {
//        this.cardID = cardID;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public Double getPirce() {
//        return pirce;
//    }
//
//    public void setPirce(Double pirce) {
//        this.pirce = pirce;
//    }
//
//    public Date getOrderDate() {
//        return orderDate;
//    }
//
//    public void setOrderDate(Date orderDate) {
//        this.orderDate = orderDate;
//    }
//
//    public Date getModifyDate() {
//        return modifyDate;
//    }
//
//    public void setModifyDate(Date modifyDate) {
//        this.modifyDate = modifyDate;
//    }
//
//    public Date getExpireDate() {
//        return expireDate;
//    }
//
//    public void setExpireDate(Date expireDate) {
//        this.expireDate = expireDate;
//    }
//}
