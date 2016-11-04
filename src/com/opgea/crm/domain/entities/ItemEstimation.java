/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.domain.entities;

import com.opgea.crm.common.constant.ApplicationConstants;
import com.opgea.crm.domain.entities.qualifier.DurationType;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ramesh
 */
@Entity
@Table(name = "itemEstimation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemEstimation.findAll", query = "SELECT i FROM ItemEstimation i"),
    @NamedQuery(name = "ItemEstimation.findAllByCompanyId", query = "SELECT i FROM ItemEstimation i WHERE i.estimation.client.company.id = :companyId"),
    @NamedQuery(name = "ItemEstimation.findById", query = "SELECT i FROM ItemEstimation i WHERE i.id = :id"),
    @NamedQuery(name = "ItemEstimation.findByEstimation", query = "SELECT i FROM ItemEstimation i WHERE i.estimation.id = :estimationId"),
    @NamedQuery(name = "ItemEstimation.findByEstimationAndItem", query = "SELECT i FROM ItemEstimation i WHERE i.estimation.id = :estimationId AND i.item.id = :itemId"),
    @NamedQuery(name = "ItemEstimation.findExisting", query = "SELECT i FROM ItemEstimation i WHERE i.estimation.id = :estimationId AND i.job.id = :jobId AND i.item.id =:itemId")
})
public class ItemEstimation implements Serializable{
    
    private static final long serialVersionUID = -4331933367938317767L;
    
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Item item;
    @OneToOne
    private Job job;
    private DurationType durationType;
    private Long durationValue;
    private Integer quantity;
    private Long totalTime;
    private Long totalTimeInHrs;
    @OneToOne
    private Estimation estimation;

    public DurationType getDurationType() {
        return durationType;
    }

    public void setDurationType(DurationType durationType) {
        this.durationType = durationType;
    }

    public Long getDurationValue() {
        return durationValue;
    }

    public void setDurationValue(Long durationValue) {
        this.durationValue = durationValue;
    }

    public Estimation getEstimation() {
        return estimation;
    }

    public void setEstimation(Estimation estimation) {
        this.estimation = estimation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getTotalTime() {
       if (this.durationValue != null && this.quantity != null) {
            this.totalTime = this.durationValue * this.quantity;
        }
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public Long getTotalTimeInHrs() {
        if(this.durationType == DurationType.Day){
            this.totalTimeInHrs = totalTime*ApplicationConstants.WORKING_HRS_IN_A_DAY;
        }else{
            this.totalTimeInHrs = totalTime;
        }
        return totalTimeInHrs;
    }

    public void setTotalTimeInHrs(Long totalTimeInHrs) {
        this.totalTimeInHrs = totalTimeInHrs;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemEstimation other = (ItemEstimation) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "ItemEstimation{" + "id=" + id + ", item=" + item + ", job=" + job + ", durationType=" + durationType + ", durationValue=" + durationValue + ", quantity=" + quantity + ", totalTime=" + totalTime + ", totalTimeInHrs=" + totalTimeInHrs + ", estimation=" + estimation + '}';
    }
}
