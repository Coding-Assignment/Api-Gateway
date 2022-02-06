package com.coding.assignment.apigateway.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class BankUser extends BaseEntity{

    private String username;
    private String password;
    private String accessToken;

}
