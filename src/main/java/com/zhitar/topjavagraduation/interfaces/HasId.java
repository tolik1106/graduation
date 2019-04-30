package com.zhitar.topjavagraduation.interfaces;

import java.io.Serializable;

public interface HasId<ID extends Serializable> {
    ID getId();

    void setId(ID id);

    default boolean isNew() {
        return getId() == null;
    }
}
