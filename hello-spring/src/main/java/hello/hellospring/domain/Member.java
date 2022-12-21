package hello.hellospring.domain;

import javax.persistence.*;

@Entity // JPA가 관리하는 엔티티
// JPA는 인터페이스, 구현은 여러 업체가함 ex)Hibernate
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
