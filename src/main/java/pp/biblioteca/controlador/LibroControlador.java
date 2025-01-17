package pp.biblioteca.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        libros.forEach(libro -> {
            logger.info(String.valueOf(libro));
        });
        return libros;
    }
}
