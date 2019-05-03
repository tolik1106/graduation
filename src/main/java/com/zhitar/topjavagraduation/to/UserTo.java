package com.zhitar.topjavagraduation.to;

import com.zhitar.topjavagraduation.interfaces.HasId;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserTo implements HasId<Long> {

    private Long id;

    private String name;

    private String email;

    private String password;

}
