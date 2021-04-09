package uz.versatile.handbook_demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import uz.versatile.handbook_demo.dtos.BookDto;
import uz.versatile.handbook_demo.utils.Utils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class BookEntity implements Serializable {

    @Id
    @Column(nullable = false)
    @GeneratedValue(generator = "optimized-sequence")
    private Long id;

    @Column(nullable = false, columnDefinition = "text", length = 2048)
    private String title;
    private Boolean isActive = Boolean.TRUE;

    @Column(name = "created_date")
    private Date createdDate = new Date();

    @Column(name = "parent_id")
    private String parentId;

    public BookDto dto() {
        BookDto dto = new BookDto();
        BeanUtils.copyProperties(this, dto, "id", "createdDate");
        if (parentId != null) {
            dto.setId(this.parentId + "." + this.id);
        } else dto.setId(this.id.toString());
        dto.setCreatedDate(Utils.getSimpleDateFormat(this.createdDate));
        return dto;
    }
}
