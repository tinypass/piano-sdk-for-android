package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CommentAction {
  
  private String id = null;
  private String caption = null;
  
  /**
   * Id of inquiry action
   **/
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
  
  /**
   * Caption of inquiry action
   **/
  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }
  
  public static CommentAction fromJson(JSONObject json) throws JSONException {
    CommentAction commentAction = new CommentAction();

    commentAction.id = json.optString("id");
    commentAction.caption = json.optString("caption");
    
    return commentAction;
  }
}
