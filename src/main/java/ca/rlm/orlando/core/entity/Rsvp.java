package ca.rlm.orlando.core.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.google.gson.annotations.Expose;
import static config.SystemDefaultProperties.SCHEMA;

@Data
@Entity
@Table(schema = SCHEMA, name = "rsvp")
public class Rsvp {

  private static final String SEQUENCE_NAME = "rsvp_id_seq";

  @Basic(optional = false)
  @Column(name = "id")
  @Expose
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
  @Id
  @SequenceGenerator(name = SEQUENCE_NAME, allocationSize = 1,
    sequenceName = SCHEMA + "." + SEQUENCE_NAME)
  private Integer id;

  @Size(max = 50)
  @Column(length = 50)
  private String name;

  private Integer come;
  private Integer people;

  @Size(max = 300)
  @Column(length = 300)
  private String message;

  @Column(name = "created_at")
  private Long createdAt;

  @Column(name = "modified_at")
  private Long modifiedAt;

  @PrePersist
  public void prePersist() {
    this.createdAt = System.currentTimeMillis();
  }

  @PreUpdate
  public void preUpdate() {
    this.modifiedAt = System.currentTimeMillis();
  }
}
