package ru.practicum.ewm.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * custom Pagination
 */
public class EwmPageRequest extends PageRequest {

    private final int from;

    public EwmPageRequest(int from, int size, Sort sort) {
        super(from / size, size, sort);
        this.from = from;
    }

    @Override
    public long getOffset() {
        return from;
    }
}
