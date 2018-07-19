package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Promotion {
  
  private String promotionId = null;
  private String aid = null;
  private String name = null;
  private String status = null;
  private String fixedPromotionCode = null;
  private Boolean unlimitedUses = null;
  private String promotionCodePrefix = null;
  private Boolean newCustomersOnly = null;
  private Double discountAmount = null;
  private String discountCurrency = null;
  private String discount = null;
  private Double percentageDiscount = null;
  private String discountType = null;
  private Integer usesAllowed = null;
  private Integer uses = null;
  private Boolean neverAllowZero = null;
  private String termDependencyType = null;
  private Date startDate = null;
  private Date endDate = null;
  private Date createDate = null;
  private String createBy = null;
  private Date updateDate = null;
  private String updateBy = null;
  private Boolean deleted = null;
  private List<PromotionFixedDiscountt> fixedDiscountList = null;
  private Boolean applyToAllBillingPeriods = null;
  private Boolean canBeAppliedOnRenewal = null;
  
  /**
   * Promotion id
   **/
  public String getPromotionId() {
    return promotionId;
  }

  public void setPromotionId(String promotionId) {
    this.promotionId = promotionId;
  }
  
  /**
   * Application aid
   **/
  public String getAid() {
    return aid;
  }

  public void setAid(String aid) {
    this.aid = aid;
  }
  
  /**
   * Promotion name
   **/
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * Status
   **/
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
  
  /**
   * Fixed value for all promotion codes
   **/
  public String getFixedPromotionCode() {
    return fixedPromotionCode;
  }

  public void setFixedPromotionCode(String fixedPromotionCode) {
    this.fixedPromotionCode = fixedPromotionCode;
  }
  
  /**
   * Whether to allow unlimited uses
   **/
  public Boolean getUnlimitedUses() {
    return unlimitedUses;
  }

  public void setUnlimitedUses(Boolean unlimitedUses) {
    this.unlimitedUses = unlimitedUses;
  }
  
  /**
   * Prefix for all codes
   **/
  public String getPromotionCodePrefix() {
    return promotionCodePrefix;
  }

  public void setPromotionCodePrefix(String promotionCodePrefix) {
    this.promotionCodePrefix = promotionCodePrefix;
  }
  
  /**
   * whether promotion allows new customers only
   **/
  public Boolean getNewCustomersOnly() {
    return newCustomersOnly;
  }

  public void setNewCustomersOnly(Boolean newCustomersOnly) {
    this.newCustomersOnly = newCustomersOnly;
  }
  
  /**
   * Promotion discount
   **/
  public Double getDiscountAmount() {
    return discountAmount;
  }

  public void setDiscountAmount(Double discountAmount) {
    this.discountAmount = discountAmount;
  }
  
  /**
   * Promotion discount currency
   **/
  public String getDiscountCurrency() {
    return discountCurrency;
  }

  public void setDiscountCurrency(String discountCurrency) {
    this.discountCurrency = discountCurrency;
  }
  
  /**
   * Formated promotion discount
   **/
  public String getDiscount() {
    return discount;
  }

  public void setDiscount(String discount) {
    this.discount = discount;
  }
  
  /**
   * Promotion percentage discount
   **/
  public Double getPercentageDiscount() {
    return percentageDiscount;
  }

  public void setPercentageDiscount(Double percentageDiscount) {
    this.percentageDiscount = percentageDiscount;
  }
  
  /**
   * Type of promotion discount 
   **/
  public String getDiscountType() {
    return discountType;
  }

  public void setDiscountType(String discountType) {
    this.discountType = discountType;
  }
  
  /**
   * Number of uses allowed by promotion
   **/
  public Integer getUsesAllowed() {
    return usesAllowed;
  }

  public void setUsesAllowed(Integer usesAllowed) {
    this.usesAllowed = usesAllowed;
  }
  
  /**
   * Defines how many times promotion has been used
   **/
  public Integer getUses() {
    return uses;
  }

  public void setUses(Integer uses) {
    this.uses = uses;
  }
  
  /**
   * Never allow the value of checkout to be zero
   **/
  public Boolean getNeverAllowZero() {
    return neverAllowZero;
  }

  public void setNeverAllowZero(Boolean neverAllowZero) {
    this.neverAllowZero = neverAllowZero;
  }
  
  /**
   * Type of dependency to terms
   **/
  public String getTermDependencyType() {
    return termDependencyType;
  }

  public void setTermDependencyType(String termDependencyType) {
    this.termDependencyType = termDependencyType;
  }
  
  /**
   * The start date.
   **/
  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }
  
  /**
   * The end date
   **/
  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
  
  /**
   * The creation date
   **/
  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }
  
  /**
   * The user who created the object
   **/
  public String getCreateBy() {
    return createBy;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }
  
  /**
   * The update date
   **/
  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }
  
  /**
   * The last user to update the object
   **/
  public String getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(String updateBy) {
    this.updateBy = updateBy;
  }
  
  /**
   * If the object is deleted
   **/
  public Boolean getDeleted() {
    return deleted;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }
  
  /**
   **/
  public List<PromotionFixedDiscountt> getFixedDiscountList() {
    return fixedDiscountList;
  }

  public void setFixedDiscountList(List<PromotionFixedDiscountt> fixedDiscountList) {
    this.fixedDiscountList = fixedDiscountList;
  }
  
  /**
   * Promotion discount applies to first billing period or all billing periods
   **/
  public Boolean getApplyToAllBillingPeriods() {
    return applyToAllBillingPeriods;
  }

  public void setApplyToAllBillingPeriods(Boolean applyToAllBillingPeriods) {
    this.applyToAllBillingPeriods = applyToAllBillingPeriods;
  }
  
  /**
   * Promotion can be applied on renewal
   **/
  public Boolean getCanBeAppliedOnRenewal() {
    return canBeAppliedOnRenewal;
  }

  public void setCanBeAppliedOnRenewal(Boolean canBeAppliedOnRenewal) {
    this.canBeAppliedOnRenewal = canBeAppliedOnRenewal;
  }
  
  public static Promotion fromJson(JSONObject json) throws JSONException {
    Promotion promotion = new Promotion();

    promotion.promotionId = json.optString("promotion_id");
    promotion.aid = json.optString("aid");
    promotion.name = json.optString("name");
    promotion.status = json.optString("status");
    promotion.fixedPromotionCode = json.optString("fixed_promotion_code");
    promotion.unlimitedUses = json.optBoolean("unlimited_uses");
    promotion.promotionCodePrefix = json.optString("promotion_code_prefix");
    promotion.newCustomersOnly = json.optBoolean("new_customers_only");
    promotion.discountAmount = json.optDouble("discount_amount");
    promotion.discountCurrency = json.optString("discount_currency");
    promotion.discount = json.optString("discount");
    promotion.percentageDiscount = json.optDouble("percentage_discount");
    promotion.discountType = json.optString("discount_type");
    promotion.usesAllowed = json.optInt("uses_allowed");
    promotion.uses = json.optInt("uses");
    promotion.neverAllowZero = json.optBoolean("never_allow_zero");
    promotion.termDependencyType = json.optString("term_dependency_type");
    promotion.startDate = new Date(json.optLong("start_date") * 1000);
    promotion.endDate = new Date(json.optLong("end_date") * 1000);
    promotion.createDate = new Date(json.optLong("create_date") * 1000);
    promotion.createBy = json.optString("create_by");
    promotion.updateDate = new Date(json.optLong("update_date") * 1000);
    promotion.updateBy = json.optString("update_by");
    promotion.deleted = json.optBoolean("deleted");
    promotion.fixedDiscountList = new ArrayList<>();
    JSONArray fixedDiscountListJsonArray = json.optJSONArray("fixed_discount_list");
    int fixedDiscountListLength = fixedDiscountListJsonArray.length();
    for (int ii = 0; ii < fixedDiscountListLength; ii++) {
      promotion.fixedDiscountList.add(PromotionFixedDiscountt.fromJson(fixedDiscountListJsonArray.optJSONObject(ii)));
    }
    promotion.applyToAllBillingPeriods = json.optBoolean("apply_to_all_billing_periods");
    promotion.canBeAppliedOnRenewal = json.optBoolean("can_be_applied_on_renewal");
    
    return promotion;
  }
}
