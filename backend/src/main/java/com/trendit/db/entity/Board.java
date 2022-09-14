package com.trendit.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long boardId;

    @Column(length = 15, nullable = false)
    String userName;

    @Column(length = 15, nullable = false)
    String password;

    @Column(length = 300, nullable = false)
    String boardContent;

    @Column(nullable = false)
    LocalDateTime createdDate;

    LocalDateTime updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id")
    Keyword keyword;
}
