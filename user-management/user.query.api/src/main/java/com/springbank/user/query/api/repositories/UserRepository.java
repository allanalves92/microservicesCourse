package com.springbank.user.query.api.repositories;

import com.springbank.user.core.models.*;
import org.springframework.data.mongodb.repository.*;

public interface UserRepository extends MongoRepository<User, String> {
}
