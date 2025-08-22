package romeu.jesus.cadastro_usuarios.infrastructury.entitys;

import jakarta.persistence.*;
import lombok.Builder;


@Builder
@Entity
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="email", unique = true)
    private String email;

    @Column(name="nome")
    private String name;

    public Usuario() {
    }

    public Usuario(Integer id, String email, String nome) {
        this.id = id;
        this.email = email;
        this.name = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setNome(String nome) {
        this.name = nome;
    }
}
