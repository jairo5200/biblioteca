package pp.biblioteca.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pp.biblioteca.excepcion.RecursoNoEncontradoExcepcion;
import pp.biblioteca.modelo.Libro;
import pp.biblioteca.servicio.LibroServicio;

import java.util.List;

@RestController
//http://localhost:8080/biblioteca-app
@RequestMapping("biblioteca-app")
@CrossOrigin(value = "http://localhost:4200")
public class LibroControlador {

    private  static final Logger logger = LoggerFactory.getLogger(LibroControlador.class);

    @Autowired
    private LibroServicio libroServicio;

    //http://localhost:8080/biblioteca-app/libros
    @GetMapping("/libros")
    public List<Libro> obtenerLibros(){
        List<Libro> libros = this.libroServicio.listarLibros();
        return libros;
    }

    //http://localhost:8080/biblioteca-app/libros
    @PostMapping("/libros")
    public Libro agregarLibro(@RequestBody Libro libro){
        Libro elLibro = this.libroServicio.guardarLibro(libro);
        return elLibro;
    }

    //http://localhost:8080/biblioteca-app/libros/id
    @GetMapping("/libros/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable int id) {
        Libro libro = this.libroServicio.buscarLibroPorId(id);
        if (libro != null) {
            return ResponseEntity.ok(libro);
        } else {
             throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        }
    }
}
