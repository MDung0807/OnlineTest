package com.iotstar.onlinetest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"topicId", "wishListId"}))
public class WishItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "topicId")
    private Topic topic;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "wishListId")
    private WishList wishList;
}
