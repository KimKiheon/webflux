package com.kkh.user.user.model.entity;

import com.kkh.user.common.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "users")
@CompoundIndexes({
})
public class User {
    @Id
    private String id;
    private String name;
    private String phoneNumber;
    @Indexed(unique = true)
    private String email;
    private Role role;
}