package com.github.swainc.result;

import lombok.Data;

/**
 * @author swaince
 * @date 2019/12/15 4:30 下午
 */
@Data
public class SimpleResponse {

    public SimpleResponse() {
    }

    public SimpleResponse(Object content) {
        this.content = content;
    }

    private Object content;
}
