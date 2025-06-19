package com.brucecompiler.result;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Generic Result representing paginated results
 * It includes the total number of records and a list of the records on the current page
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {

    private long total;

    private List<T> records;
}
