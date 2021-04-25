package com.springbank.user.core.models;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
public class User {
  @Id private String id;
  private String firstname;
  private String lastname;
  private String emailAddress;
  private Account account;
}
