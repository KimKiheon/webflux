package com.kkh.user.owner.model.entity;

import com.kkh.user._support.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "owners")
@CompoundIndexes({
})
public class Owner {
    @Id
    private String id;
    private String name;
    private String phoneNumber;
    private List<String> restaurantList;
    @Indexed(unique = true)
    private String email;
    private Long settlement;
    private Role role;
}
