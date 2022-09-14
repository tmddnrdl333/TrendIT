package com.trendit.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long companyId;

    @Column(length = 30, nullable = false)
    String companyName;

    @Column(length = 50)
    String companyCategory;

    @Column(length = 50)
    String companyMainItem;

    @Column(length = 500)
    String companyLink;

    @OneToOne(mappedBy = "company")
    Keyword keyword;
}
