package romeu.jesus.cadastro_usuarios.business;

import org.springframework.stereotype.Service;
import romeu.jesus.cadastro_usuarios.infrastructury.entitys.Usuario;
import romeu.jesus.cadastro_usuarios.infrastructury.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository){
        this.repository = repository;
    }

    public void salvarUsuario(Usuario usuario){
        repository.saveAndFlush(usuario);
    }

    public Usuario buscarUsuarioPorEmail(String email){
        return repository.findByEmail(email).orElseThrow(
                ()-> new RuntimeException("Usuário não encontrado!")
        );
    }

    public void apagarUsuarioPorEmail(String email){
        repository.deleteByEmail(email);
    }

    public void apagarUsuarioPorId(Integer id){
        repository.deleteById(id);
    }

    public void atualizarUsuarioPorId(Integer id, Usuario usuario){
        Usuario usuarioEntity = repository.findById(id).orElseThrow(()-> new RuntimeException("Usuário não encontrado!"));
        Usuario usuarioAtualizado = Usuario.builder()
                .email(usuario.getEmail() != null ? usuario.getEmail() : usuarioEntity.getEmail())
                .name(usuario.getName() != null ? usuario.getName() : usuarioEntity.getName())
                .id(usuarioEntity.getId())
                .build();
        //repository.saveAndFlush(usuarioAtualizado);
        repository.save(usuarioAtualizado);
    }
    public List<Usuario> buscarTodos(){
        return repository.findAll();
    }
}
