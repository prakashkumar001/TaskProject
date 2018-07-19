package com.list.show.pojo;

import java.util.ArrayList;

public class RootObject
{
  private Status status;

  public Status getStatus() { return this.status; }

  public void setStatus(Status status) { this.status = status; }

  private ArrayList<Entity> _entity;

  public ArrayList<Entity> getEntity() { return this._entity; }

  public void setEntity(ArrayList<Entity> _entity) { this._entity = _entity; }
}
