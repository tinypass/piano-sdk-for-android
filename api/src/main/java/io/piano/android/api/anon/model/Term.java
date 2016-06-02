package io.piano.android.api.anon.model;

import io.piano.android.api.anon.model.Resource;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Term {
  
  private String termId = null;
  private String aid = null;
  private Resource resource = null;
  private String type = null;
  private String name = null;
  private String description = null;
  private Date createDate = null;

  
  /**
   * Term ID
   **/
  public String getTermId() {
    return termId;
  }

  public void setTermId(String termId) {
    this.termId = termId;
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
   * The resource
   **/
  public Resource getResource() {
    return resource;
  }

  public void setResource(Resource resource) {
    this.resource = resource;
  }
  /**
   * Term type
   **/
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
  /**
   * Term name
   **/
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  /**
   * Term description
   **/
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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

  public static Term fromJson(JSONObject json) throws JSONException {
    Term term = new Term();

    term.termId = json.optString("term_id");
    term.aid = json.optString("aid");
    term.resource = Resource.fromJson(json.optJSONObject("resource"));
    term.type = json.optString("type");
    term.name = json.optString("name");
    term.description = json.optString("description");
    term.createDate = new Date(json.optLong("create_date") * 1000);
    
    return term;
  }
}
