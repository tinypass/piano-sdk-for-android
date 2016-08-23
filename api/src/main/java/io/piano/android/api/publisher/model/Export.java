package io.piano.android.api.publisher.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Export {
  
  private String exportId = null;
  private String exportName = null;
  private Date exportCreated = null;
  private Date exportCompleted = null;
  private Integer exportPercentage = null;
  private Integer exportRecords = null;
  private String exportStatus = null;
  private String reportType = null;
  private String filterData = null;
  
  /**
   * Downloadable report ID
   **/
  public String getExportId() {
    return exportId;
  }

  public void setExportId(String exportId) {
    this.exportId = exportId;
  }
  
  /**
   * Downloadable report name
   **/
  public String getExportName() {
    return exportName;
  }

  public void setExportName(String exportName) {
    this.exportName = exportName;
  }
  
  /**
   * Time stamp of downloadable report creation
   **/
  public Date getExportCreated() {
    return exportCreated;
  }

  public void setExportCreated(Date exportCreated) {
    this.exportCreated = exportCreated;
  }
  
  /**
   * Time stamp of downloadable report completeon
   **/
  public Date getExportCompleted() {
    return exportCompleted;
  }

  public void setExportCompleted(Date exportCompleted) {
    this.exportCompleted = exportCompleted;
  }
  
  /**
   * Downloadable report completion percent
   **/
  public Integer getExportPercentage() {
    return exportPercentage;
  }

  public void setExportPercentage(Integer exportPercentage) {
    this.exportPercentage = exportPercentage;
  }
  
  /**
   * Number of records in a downloadable report
   **/
  public Integer getExportRecords() {
    return exportRecords;
  }

  public void setExportRecords(Integer exportRecords) {
    this.exportRecords = exportRecords;
  }
  
  /**
   * Downloadable report completion status
   **/
  public String getExportStatus() {
    return exportStatus;
  }

  public void setExportStatus(String exportStatus) {
    this.exportStatus = exportStatus;
  }
  
  /**
   * Report type
   **/
  public String getReportType() {
    return reportType;
  }

  public void setReportType(String reportType) {
    this.reportType = reportType;
  }
  
  /**
   * Filter data
   **/
  public String getFilterData() {
    return filterData;
  }

  public void setFilterData(String filterData) {
    this.filterData = filterData;
  }
  
  public static Export fromJson(JSONObject json) throws JSONException {
    Export export = new Export();

    export.exportId = json.optString("export_id");
    export.exportName = json.optString("export_name");
    export.exportCreated = new Date(json.optLong("export_created") * 1000);
    export.exportCompleted = new Date(json.optLong("export_completed") * 1000);
    export.exportPercentage = json.optInt("export_percentage");
    export.exportRecords = json.optInt("export_records");
    export.exportStatus = json.optString("export_status");
    export.reportType = json.optString("report_type");
    export.filterData = json.optString("filter_data");
    
    return export;
  }
}
