package com.brucecompiler.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginVO implements Serializable {

    private Long id;

    private String openid;

    private String token;
}
