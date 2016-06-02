package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResourceStats {
  
  private String rid = null;
  private Integer numBundles = null;
  private Integer numCustomers = null;
  private Integer numTerms = null;
  private String tags = null;

  
  /**
   * Unique id for resource
   **/
  public String getRid() {
    return rid;
  }

  public void setRid(String rid) {
    this.rid = rid;
  }
  /**
   * Number of bundles the resource has
   **/
  public Integer getNumBundles() {
    return numBundles;
  }

  public void setNumBundles(Integer numBundles) {
    this.numBundles = numBundles;
  }
  /**
   * Number of customers the resource has
   **/
  public Integer getNumCustomers() {
    return numCustomers;
  }

  public void setNumCustomers(Integer numCustomers) {
    this.numCustomers = numCustomers;
  }
  /**
   * Number of terms the resource has
   **/
  public Integer getNumTerms() {
    return numTerms;
  }

  public void setNumTerms(Integer numTerms) {
    this.numTerms = numTerms;
  }
  /**
   * The tags of the page
   **/
  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public static ResourceStats fromJson(JSONObject json) throws JSONException {
    ResourceStats resourceStats = new ResourceStats();

    resourceStats.rid = json.optString("rid");
    resourceStats.numBundles = json.optInt("num_bundles");
    resourceStats.numCustomers = json.optInt("num_customers");
    resourceStats.numTerms = json.optInt("num_terms");
    resourceStats.tags = json.optString("tags");
    
    return resourceStats;
  }
}
