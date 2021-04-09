package uz.versatile.handbook_demo.dtos.queries;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public interface BookQuery {
    Long getId();
    String getTitle();
    Date getCreatedDate();
    String getParentId();
    Boolean getIsActive();
}
