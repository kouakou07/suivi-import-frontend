package com.example.suivie_importBackend.models;

import java.util.Date;

import com.example.suivie_importBackend.Enum.Deletion;
import com.example.suivie_importBackend.Enum.Status;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@MappedSuperclass
public class BaseM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    protected Integer status;
    protected Boolean deleted;
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dateCreation;
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dateEdition;

    public BaseM(Integer status) {
        this.deleted = Deletion.NO;
        this.dateCreation = new Date();
        this.dateEdition = new Date();
        this.status = Status.DEFAULT_ENABLE;
    }
    public BaseM() {
        this(1);
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Boolean getDeleted() {
        return deleted;
    }
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
        this.dateEdition = new Date();
    }
    public Date getDateCreation() {
        return dateCreation;
    }
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
    public Date getDateEdition() {
        return dateEdition;
    }
    public void setDateEdition(Date dateEdition) {
        this.dateEdition = dateEdition;
    }

    public void update(){
        this.dateEdition = new Date();
    }
    
}
