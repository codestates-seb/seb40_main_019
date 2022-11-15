package com.backend.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PageInfo {
    private int page;
    private int size=15;
    private long totalElements;
    private int totalPages;
}
