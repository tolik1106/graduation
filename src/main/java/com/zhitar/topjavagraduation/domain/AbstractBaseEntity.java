package com.zhitar.topjavagraduation.domain;

import com.zhitar.topjavagraduation.interfaces.HasId;
import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public abstract class AbstractBaseEntity implements HasId<Long> {

    public AbstractBaseEntity() {
    }

    public AbstractBaseEntity(Long id) {
        this.id = id;
    }

    public static final int START_SEQ = 10000;
    public static final Long LONG_START_SEQ = (long) START_SEQ;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @Column(nullable = false, columnDefinition = "bigint default nextval('global_seq')")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    protected Long id;

    @Override
    public boolean isNew() {
        return this.id == null;
    }
}
