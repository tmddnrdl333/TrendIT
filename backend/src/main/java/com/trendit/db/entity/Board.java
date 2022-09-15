package com.trendit.db.entity;

import com.trendit.api.response.data.BoardData;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long boardId;

    @Column(length = 15, nullable = false)
    String userName;

    @Column(length = 150, nullable = false)
    String password;

    @Column(length = 300, nullable = false)
    String boardContent;

    @Column(nullable = false)
    @CreatedDate
    LocalDateTime createdDate;

    @LastModifiedDate
    LocalDateTime updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id")
    Keyword keyword;

    public BoardData entityToDto() {
        return BoardData.builder()
                .boardId(this.getBoardId())
                .userName(this.getUserName())
                .boardContent(this.getBoardContent())
                .createdDate(this.getCreatedDate())
                .updatedDate(this.getUpdatedDate())
                .build();
    }
}