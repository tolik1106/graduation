package com.zhitar.topjavagraduation.to;

import com.zhitar.topjavagraduation.domain.AbstractBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class RestaurantTo extends AbstractBaseEntity {

    public RestaurantTo(Long id, String name, Long votesCount, boolean meVoted) {
        super(id);
        this.name = name;
        this.votesCount = votesCount;
        this.meVoted = meVoted;
    }

    private String name;

    private Long votesCount;

    private boolean meVoted;
}
