package pp.biblioteca.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pp.biblioteca.modelo.Libro;
import pp.biblioteca.repositorio.LibroRepositorio;

import java.util.List;

@Service
public class LibroServicio implements  ILibroServicio{

    @Autowired
    private LibroRepositorio libroRepositorio;

    @Override
    public List<Libro> listarLibros() {
        List<Libro> libros =this.libroRepositorio.findAll();
        return libros;
    }

    @Override
    public Libro buscarLibroPorId(Integer idLibro) {
        Libro elLibro = this.libroRepositorio.findById(idLibro).orElse(null);
        return elLibro;
    }

    @Override
    public void guardarLibro(Libro libro) {
        this.libroRepositorio.save(libro);
    }

    @Override
    public void eliminarLibroPorId(Integer idLibro) {
        this.libroRepositorio.deleteById(idLibro);
    }
}
