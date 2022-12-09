package com.example.medicalonlinebookingservice.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class FreeVisit extends AbstractEntity{

  @ManyToMany
  private List<Visit> visitList;


  public FreeVisit(List<Visit> visitList) {
    this.visitList = visitList;
  }

  public FreeVisit() {
  }


}
