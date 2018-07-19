package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OfferTemplateCategories {
  
  private String categoryId = null;
  private Integer countTemplates = null;
  private List<OfferTemplate> offerTemplateModels = null;
  
  /**
   * The id of Category
   **/
  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }
  
  /**
   * Count of templates in the category
   **/
  public Integer getCountTemplates() {
    return countTemplates;
  }

  public void setCountTemplates(Integer countTemplates) {
    this.countTemplates = countTemplates;
  }
  
  /**
   **/
  public List<OfferTemplate> getOfferTemplateModels() {
    return offerTemplateModels;
  }

  public void setOfferTemplateModels(List<OfferTemplate> offerTemplateModels) {
    this.offerTemplateModels = offerTemplateModels;
  }
  
  public static OfferTemplateCategories fromJson(JSONObject json) throws JSONException {
    OfferTemplateCategories offerTemplateCategories = new OfferTemplateCategories();

    offerTemplateCategories.categoryId = json.optString("category_id");
    offerTemplateCategories.countTemplates = json.optInt("count_templates");
    offerTemplateCategories.offerTemplateModels = new ArrayList<>();
    JSONArray offerTemplateModelsJsonArray = json.optJSONArray("offer_template_models");
    int offerTemplateModelsLength = offerTemplateModelsJsonArray.length();
    for (int ii = 0; ii < offerTemplateModelsLength; ii++) {
      offerTemplateCategories.offerTemplateModels.add(OfferTemplate.fromJson(offerTemplateModelsJsonArray.optJSONObject(ii)));
    }
    
    return offerTemplateCategories;
  }
}
