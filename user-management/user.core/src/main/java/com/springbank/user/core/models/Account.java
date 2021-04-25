package com.springbank.user.core.models;

import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
  private String username;
  private String password;
  private List<Role> roles;
}