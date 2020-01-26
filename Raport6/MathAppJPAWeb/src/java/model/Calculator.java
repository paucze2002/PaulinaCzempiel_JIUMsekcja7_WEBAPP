/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Default entity class for database
 *
 * @author Paulina Czempiel
 * @version 1.0
 */
@Entity
@Table(name = "Calculator")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Converttable.findAll", query = "SELECT c FROM Calculator c")
    , @NamedQuery(name = "Converttable.findById", query = "SELECT c FROM Calculator c WHERE c.id = :id")
    , @NamedQuery(name = "Converttable.findByNumber", query = "SELECT c FROM Calculator c WHERE c.number = :number")
    , @NamedQuery(name = "Converttable.findByResult", query = "SELECT c FROM Calculator c WHERE c.result = :result")})
public class Calculator implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "number")
    private String number;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "result")
    private String result;
    
    /**
    * Calculator
    **/
    public Calculator() {
    }
    
    /**
    * Calculator id
     * @param id
    **/
    public Calculator(Integer id) {
        this.id = id;
    }
    /**
    * Calculator id, number and result
     * @param id
     * @param number
     * @param result
    **/
    public Calculator(Integer id, String number, String result) {
        this.id = id;
        this.number = number;
        this.result = result;
    }

    /**
     * Get iD
     * @return id
     */
    public Integer getId() {
        return id;
    }
    /**
     * Set id
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * Get number
     * @return number
     */
    public String getNumber() {
        return number;
    }
    
    /**
     * Set number
     * @param number 
     */
    public void setNumber(String number) {
        this.number = number;
    }
    
    /**
     * Get Result
     * @return result
     */
    public String getResult() {
        return result;
    }
    
    /**
     * Set result
     * @param result 
     */
    public void setResult(String result) {
        this.result = result;
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
        if (!(object instanceof Calculator)) {
            return false;
        }
        Calculator other = (Calculator) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "model.Calculator[ id=" + id + " ]";
    }
    
}
