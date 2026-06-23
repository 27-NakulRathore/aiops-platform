package com.aiops.auth.dto;

import com.aiops.auth.entity.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileResponse {

    private Long id;

    private String name;

    private String email;

    private Role role;

    private Boolean active;
}