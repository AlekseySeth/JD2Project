package entity.product;

import entity.util.IdentifiableEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author a.shestovsky
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "brands")
public class Brand extends IdentifiableEntity {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "logo_url", unique = true,nullable = false)
    private String logoURL;

    public Brand(String name, String logoURL) {
        this.name = name;
        this.logoURL = logoURL;
    }
}
