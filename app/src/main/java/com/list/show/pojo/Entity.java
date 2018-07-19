package com.list.show.pojo;

public class Entity
{

  public boolean isActive=false;
  public Entity()
  {

  }
  private int panicId;

  public int getPanicId() { return this.panicId; }

  public void setPanicId(int panicId) { this.panicId = panicId; }

  private String notes;

  public String getNotes() { return this.notes; }

  public void setNotes(String notes) { this.notes = notes; }

  private UserId userId;

  public UserId getUserId() { return this.userId; }

  public void setUserId(UserId userId) { this.userId = userId; }

  private String created_On;

  public String getCreatedOn() { return this.created_On; }

  public void setCreatedOn(String created_On) { this.created_On = created_On; }

  private String latitude;

  public String getLatitude() { return this.latitude; }

  public void setLatitude(String latitude) { this.latitude = latitude; }

  private String longitude;

  public String getLongitude() { return this.longitude; }

  public void setLongitude(String longitude) { this.longitude = longitude; }

  private String address;

  public String getAddress() { return this.address; }

  public void setAddress(String address) { this.address = address; }

  private boolean active;

  public boolean getActive() { return this.active; }

  public void setActive(boolean active) { this.active = active; }

  private boolean watchlist;

  public boolean getWatchlist() { return this.watchlist; }

  public void setWatchlist(boolean watchlist) { this.watchlist = watchlist; }

  private boolean read;

  public boolean getRead() { return this.read; }

  public void setRead(boolean read) { this.read = read; }

  private String fileType;

  public String getFileType() { return this.fileType; }

  public void setFileType(String fileType) { this.fileType = fileType; }

  private String fileUrl;

  public String getFileUrl() { return this.fileUrl; }

  public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }

  private boolean deleted;

  public boolean getDeleted() { return this.deleted; }

  public void setDeleted(boolean deleted) { this.deleted = deleted; }

  private String ratio;

  public String getRatio() { return this.ratio; }

  public void setRatio(String ratio) { this.ratio = ratio; }

  private String thumbnailUrl;

  public String getThumbnailUrl() { return this.thumbnailUrl; }

  public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }

  private boolean mywatchlist;

  public boolean getMywatchlist() { return this.mywatchlist; }

  public void setMywatchlist(boolean mywatchlist) { this.mywatchlist = mywatchlist; }

  private int commentCount;

  public int getCommentCount() { return this.commentCount; }

  public void setCommentCount(int commentCount) { this.commentCount = commentCount; }

  private String smallUrl;

  public String getSmallUrl() { return this.smallUrl; }

  public void setSmallUrl(String smallUrl) { this.smallUrl = smallUrl; }

  private String largeUrl;

  public String getLargeUrl() { return this.largeUrl; }

  public void setLargeUrl(String largeUrl) { this.largeUrl = largeUrl; }
}