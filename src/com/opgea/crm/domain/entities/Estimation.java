/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.crm.domain.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "estimation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estimation.findAll", query = "SELECT e FROM Estimation e"),
    @NamedQuery(name = "Estimation.findAllByClientId", query = "SELECT e FROM Estimation e WHERE e.client.id = :clientId"),
    @NamedQuery(name = "Estimation.findAllByCompanyId", query = "SELECT e FROM Estimation e WHERE e.client.company.id = :companyId"),
    @NamedQuery(name = "Estimation.findById", query = "SELECT e FROM Estimation e WHERE e.id = :id"),
    @NamedQuery(name = "Estimation.findByName", query = "SELECT e FROM Estimation e WHERE e.name = :name")})
public class Estimation implements Serializable {

    private static final long serialVersionUID = -4331930067222317767L;
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne
    private Client client;
    @OneToOne
    private JobStatus jobStatus;
    @OneToOne(mappedBy="estimation")
    private List<ItemEstimation> itemEstimations;
    @OneToOne
    private Project project;

    public void add(ItemEstimation itemEstimation){
        this.itemEstimations.add(itemEstimation);
    }
    
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemEstimation> getItemEstimations() {
        return itemEstimations;
    }

    public void setItemEstimations(List<ItemEstimation> itemEstimations) {
        this.itemEstimations = itemEstimations;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Estimation other = (Estimation) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Estimation{" + "id=" + id + ", name=" + name + ", client=" + client + ", jobStatus=" + jobStatus + '}';
    }
}
