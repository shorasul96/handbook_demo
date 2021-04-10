package uz.versatile.handbook_demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import uz.versatile.handbook_demo.dtos.BookDto;
import uz.versatile.handbook_demo.utils.Utils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class BookEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "optimized-sequence")
    private Long id;

    @Column(nullable = false, columnDefinition = "text", length = 2048)
    private String title;
    private Boolean isActive = Boolean.TRUE;

    @Column(name = "created_date")
    private Date createdDate = new Date();

    @Column(name = "parent_id_str")
    private String parentIdStr;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private BookEntity parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<BookEntity> children = new ArrayList<>();

    public BookDto dto() {
        BookDto dto = new BookDto();
        BeanUtils.copyProperties(this, dto, "id", "createdDate");
        dto.setCreatedDate(Utils.getSimpleDateFormat(this.createdDate));

        if (parentIdStr != null)
            dto.setId(this.parentIdStr + "." + this.id);
        else
            dto.setId(this.id.toString());

        if (this.children != null)
            dto.setChildren(this.children.stream().map(BookEntity::dto).collect(Collectors.toList()));

        return dto;
    }
}
