package uz.versatile.handbook_demo.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class BookDto implements Serializable {

    @ApiModelProperty(hidden = true)
    private String id;
    @ApiModelProperty(hidden = true)
    private String createdDate;
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private Boolean isActive;
    @ApiModelProperty(hidden = true)
    private BookDto parent;
    @ApiModelProperty(hidden = true)
    private List<BookDto> children;


    @ApiModelProperty(required = true, value = "Title",
            notes = "Title for booking entity",
            allowableValues = "Book Title (1 - 2048 chars)")
    @JsonProperty(required = true)
    private String title;

    @ApiModelProperty(value = "Parent Id",
            notes = "Write exist parent id other case throw except",
            allowableValues = "1")
    private String parentIdStr;


}
