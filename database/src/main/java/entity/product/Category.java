package entity.product;

import entity.util.IdentifiableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * @author a.shestovsky
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends IdentifiableEntity {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "category")
    private Set<Product> products = new HashSet<>();

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
