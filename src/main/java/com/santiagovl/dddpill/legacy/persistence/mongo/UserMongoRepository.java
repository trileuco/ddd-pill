package com.santiagovl.dddpill.legacy.persistence.mongo;

import com.santiagovl.dddpill.legacy.domain.model.user.User;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserMongoRepository {

  private static final String USERS_COLLECTION_NAME = "users";

  private final MongoTemplate mongoTemplate;

  private final UserMongoConverter userMongoConverter;

  public User save(final User user) {
    final MongoUser mongoUser = userMongoConverter.convert(user);
    assert mongoUser != null;
    final MongoUser createdMongoUser = mongoTemplate.insert(mongoUser, USERS_COLLECTION_NAME);
    return userMongoConverter.reverse().convert(createdMongoUser);
  }

  public Optional<User> findByEmail(final String email) {
    Criteria emailCriteria = Criteria.where("email").is(email);
    final Query findUserByEmailQuery = new Query().addCriteria(emailCriteria);
    final Optional<MongoUser> foundLegacyMongoUser =
        mongoTemplate.find(findUserByEmailQuery, MongoUser.class).stream().findAny();
    if (foundLegacyMongoUser.isEmpty()) {
      return Optional.empty();
    }
    return Optional.ofNullable(userMongoConverter.reverse().convert(foundLegacyMongoUser.get()));
  }
}
