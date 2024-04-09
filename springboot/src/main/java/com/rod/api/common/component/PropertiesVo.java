package com.rod.api.common.component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PropertiesVo {

    private Boolean enabled;
    private String location;
    private String maxFileSize;
    private String maxRequestSize;
    private String fileSizeThreshold;
}
