package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResourceTag {
  
  private String resourceTagId = null;
  private String name = null;
  private String type = null;

  
  /**
   * Id for resource tag
   **/
  public String getResourceTagId() {
    return resourceTagId;
  }

  public void setResourceTagId(String resourceTagId) {
    this.resourceTagId = resourceTagId;
  }
  /**
   * The name
   **/
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  /**
   * The type
   **/
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public static ResourceTag fromJson(JSONObject json) throws JSONException {
    ResourceTag resourceTag = new ResourceTag();

    resourceTag.resourceTagId = json.optString("resource_tag_id");
    resourceTag.name = json.optString("name");
    resourceTag.type = json.optString("type");
    
    return resourceTag;
  }
}
