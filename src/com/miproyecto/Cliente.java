package devplus;

public class Cliente {
    private String nombre;
    private String documento;
    private String telefono;
    private String correo;
    private String pais;

    public Cliente(String nombre, String documento, String telefono, String correo, String pais) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.correo = correo;
        this.pais = pais;
    }
        public String getNombre() {
            return nombre;
        }

        public String getDocumento() {
            return documento;
        }

        public String getTelefono() {
            return telefono;
        }

        public String getCorreo() {
            return correo;
        }

        public String getPais() {
            return pais;


        }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    }

