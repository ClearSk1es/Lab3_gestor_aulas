module login.sistema.gestion.aulas {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    
    requires java.naming;
    
    opens login.sistema.gestion.aulas.entidades to org.hibernate.orm.core;
    
    opens login.sistema.gestion.aulas to javafx.fxml;
    exports login.sistema.gestion.aulas;
}
