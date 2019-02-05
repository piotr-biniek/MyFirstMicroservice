/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.biniek.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Uzer")
//@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "type")
//@DiscriminatorValue("UZER")
//@NamedQueries({
//        @NamedQuery(name = "Uzer.findByEmail", query = "SELECT d FROM Uzer d WHERE d.email = :email"),
//     @NamedQuery(name = "Uzer.doesEmailExists", query = "SELECT d FROM Uzer d WHERE d.email = :email"),
//})
 
public class User extends AbstractEntity implements Serializable {//user

    @Id
    @Column(name = "id", updatable = false)
    @TableGenerator(name = "UzerGen", table = "GENERATOR", initialValue = 400, allocationSize =  6053 )
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "UzerGen")
    private Long id;

    @Column(name = "username", length = 64, nullable = false, unique = true, updatable = true)
    private String username;

    @Column(name = "password", length = 256, nullable = false)
    private String password;

    @Column(name = "email", length = 64, nullable = false, unique = true, updatable = true)
    @Pattern(regexp = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$", message = "{constraint.string.incorrectemail}")
    private String email;

    
   
    @Override
    protected String getBusinessKey() {
        return "emal: "+username+", id: "+id;
    }

    
    public String getName()      
    {
      return username;
    }

    @Override
    public String toString() {
        return "Uzer{" + "id=" + id + ", name=" + username + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public Object getId() {
        return id;
    }
    
    
}
