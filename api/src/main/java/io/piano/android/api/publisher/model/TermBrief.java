package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TermBrief {
  
  private String termId = null;
  private String name = null;
  
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
   * Term name
   **/
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  public static TermBrief fromJson(JSONObject json) throws JSONException {
    TermBrief termBrief = new TermBrief();

    termBrief.termId = json.optString("term_id");
    termBrief.name = json.optString("name");
    
    return termBrief;
  }
}
