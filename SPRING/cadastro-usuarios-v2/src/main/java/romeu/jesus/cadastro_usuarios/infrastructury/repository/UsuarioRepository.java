package romeu.jesus.cadastro_usuarios.infrastructury.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import romeu.jesus.cadastro_usuarios.infrastructury.entitys.Usuario;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
    @Transactional
    void deleteByEmail(String email);
}
