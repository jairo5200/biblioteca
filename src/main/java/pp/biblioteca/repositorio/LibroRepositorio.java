package pp.biblioteca.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import pp.biblioteca.modelo.Libro;

public interface LibroRepositorio  extends JpaRepository<Libro,Integer> {
}
