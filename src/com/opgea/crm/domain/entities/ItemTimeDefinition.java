/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.domain.entities;

import com.opgea.crm.domain.entities.qualifier.DurationType;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "itemtimedefinition")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemTimeDefinition.findAll", query = "SELECT i FROM ItemTimeDefinition i"),
    @NamedQuery(name = "ItemTimeDefinition.findAllByItemId", query = "SELECT i FROM ItemTimeDefinition i WHERE i.item.id = :itemId"),
    @NamedQuery(name = "ItemTimeDefinition.findAllByCompanyId", query = "SELECT i FROM ItemTimeDefinition i WHERE i.item.company.id = :companyId"),
    @NamedQuery(name = "ItemTimeDefinition.findById", query = "SELECT i FROM ItemTimeDefinition i WHERE i.id = :id"),
    @NamedQuery(name = "ItemTimeDefinition.findByJob", query = "SELECT i FROM ItemTimeDefinition i WHERE i.job.id = :jobId")})
public class ItemTimeDefinition implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Item item;
    @OneToOne
    private Job job;
    private Long durationValue;
    private DurationType durationType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemTimeDefinition)) {
            return false;
        }
        ItemTimeDefinition other = (ItemTimeDefinition) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ItemTimeDefinition{" + "id=" + id + ", item=" + item + ", job=" + job + ", durationValue=" + durationValue + ", durationType=" + durationType + '}';
    }

    
    
}
