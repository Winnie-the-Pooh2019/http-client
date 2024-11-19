package ru.kemsu;

import lombok.Data;

import java.util.List;

@Data
public class UserListResponse {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<User> data;
}