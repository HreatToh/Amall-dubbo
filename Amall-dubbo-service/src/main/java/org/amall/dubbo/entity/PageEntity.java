package org.amall.dubbo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageEntity<T> {

    private List<T> data;

    private Long total;

    private String msg;

    private Integer code;

    private boolean success;

}
