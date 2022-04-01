package com.example.licenseapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@ApiModel("Песня")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Recording implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String version;

    private ZonedDateTime releaseTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Singer singer;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "recordings")
    @ToString.Exclude
    @JsonIgnoreProperties(value = {"recordings"})
    private List<Copyright> copyrights;

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Recording recording = (Recording) o;
        return id != null && Objects.equals(id, recording.id);
    }

    @Override
    public int hashCode() {

        return getClass().hashCode();
    }
}
