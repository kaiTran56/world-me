package com.tranquyet.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class CommentEntity extends BasedEntity{
    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "commentEntity", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<PublicVideoEntity> publicVideoEntityList = new ArrayList<>();
}
