package com.example.licenseapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Copyright implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDate expireDt;

    private Long cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    @ToString.Exclude
    @JsonIgnoreProperties(value = {"copyrights"})
    private Company company;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "recording_copyright",
            joinColumns = @JoinColumn(name = "copyright_id"),
            inverseJoinColumns = @JoinColumn(name = "recording_id"))
    @ToString.Exclude
    @JsonIgnoreProperties(value = {"copyrights"})
    private List<Recording> recordings;

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Copyright copyright = (Copyright) o;
        return id != null && Objects.equals(id, copyright.id);
    }

    @Override
    public int hashCode() {

        return getClass().hashCode();
    }
}
