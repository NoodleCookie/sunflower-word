package sunflower.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class BaseEntity {

    private String createdBy;

    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdTime;
}
