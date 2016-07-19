package io.piano.android.api.publisher.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TaxProviderConfiguration {
  
  private String taxProviderConfigurationId = null;
  private String type = null;
  private String taxJarProviderToken = null;
  
  /**
   * Tax provider configuration id
   **/
  public String getTaxProviderConfigurationId() {
    return taxProviderConfigurationId;
  }

  public void setTaxProviderConfigurationId(String taxProviderConfigurationId) {
    this.taxProviderConfigurationId = taxProviderConfigurationId;
  }
  
  /**
   * Tax provider type
   **/
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
  
  /**
   * TaxJar configuration auth token
   **/
  public String getTaxJarProviderToken() {
    return taxJarProviderToken;
  }

  public void setTaxJarProviderToken(String taxJarProviderToken) {
    this.taxJarProviderToken = taxJarProviderToken;
  }
  
  public static TaxProviderConfiguration fromJson(JSONObject json) throws JSONException {
    TaxProviderConfiguration taxProviderConfiguration = new TaxProviderConfiguration();

    taxProviderConfiguration.taxProviderConfigurationId = json.optString("tax_provider_configuration_id");
    taxProviderConfiguration.type = json.optString("type");
    taxProviderConfiguration.taxJarProviderToken = json.optString("tax_jar_provider_token");
    
    return taxProviderConfiguration;
  }
}
