package com.trendit.db.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long companyId;

    @Column(length = 30)
    String companyName;

    @Column(length = 50)
    String companyCategory;

    @Column(length = 50)
    String companyMainItem;

    @Column(length = 500)
    String companyLink;
}
