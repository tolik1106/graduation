package com.zhitar.topjavagraduation.to;

import com.zhitar.topjavagraduation.interfaces.HasId;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserTo implements HasId<Long> {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 64)
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 5, max = 128)
    private String password;
}
