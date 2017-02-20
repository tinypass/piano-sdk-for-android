package io.piano.android.composer.model;

import org.json.JSONObject;

public class Access {

    public String rid;
    public String resourceName;
    public int expireDate;
    public int daysUntilExpiration;

    static Access fromJson(JSONObject json) {
        Access access = new Access();

        access.rid = json.optString("rid");
        access.resourceName = json.optString("resourceName");
        access.expireDate = json.optInt("expireDate");
        access.daysUntilExpiration = json.optInt("daysUntilExpiration");

        return access;
    }
}
