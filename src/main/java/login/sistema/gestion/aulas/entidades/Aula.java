package login.sistema.gestion.aulas.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "aulas",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uq_aula_nombre_edificio",
            columnNames = {"nombre", "edificio"}
        )
    }
)
public class Aula {

    public enum TipoAula {
        NORMAL,
        CENTRO_DE_COMPUTO,
        LABORATORIO,
        ESPECIAL
    }

    public enum Estado {
        ASIGNADA,
        ASIGNADA_TEMPORALMENTE,
        DISPONIBLE,
        EN_MANTENIMIENTO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @Column(name = "edificio", nullable = false, length = 100)
    private String edificio;

    @Column(name = "planta", nullable = false)
    private Integer planta;

    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_aula", nullable = false)
    private TipoAula tipoAula;

    @Column(name = "responsable", nullable = false, length = 150)
    private String responsable;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado = Estado.DISPONIBLE;

    // MySQL asigna la fecha al insertar el registro.
    @Column(
        name = "fecha_creacion",
        insertable = false,
        updatable = false
    )
    private LocalDateTime fechaCreacion;

    public Aula() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public Integer getPlanta() {
        return planta;
    }

    public void setPlanta(Integer planta) {
        this.planta = planta;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public TipoAula getTipoAula() {
        return tipoAula;
    }

    public void setTipoAula(TipoAula tipoAula) {
        this.tipoAula = tipoAula;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
}