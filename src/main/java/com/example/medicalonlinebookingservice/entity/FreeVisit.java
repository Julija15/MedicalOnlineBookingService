package com.example.medicalonlinebookingservice.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "free_visits")
public class FreeVisit extends AbstractEntity{
  @ManyToMany
  private List<Visit> visitList;


  public FreeVisit(List<Visit> visitList) {
    this.visitList = visitList;
  }

  public FreeVisit() {
  }


}
