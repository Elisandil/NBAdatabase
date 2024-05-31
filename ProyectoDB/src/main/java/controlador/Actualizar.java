package controlador;

import java.util.List;
import vista.Vista;
import vista.VistaTablaUnica;

public interface Actualizar<T> {
    List<T> obtener();
    
    default void actualizarTabla(Vista vista, Object[][] datos, String[] nombreColumnas) {
        vista.getTableModel().setDataVector(datos, nombreColumnas);
    }    
    
    default void actualizarTablaUnica(VistaTablaUnica vista, Object[][] datos, String[] nombreColumnas) {
        vista.getTableModel().setDataVector(datos, nombreColumnas);
    }    
}
