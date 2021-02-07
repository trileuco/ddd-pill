package com.santiagovl.dddpill.legacy;

import java.time.Instant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

  private String id;

  private String email;

  private String password;

  private Instant createdAt;

}
