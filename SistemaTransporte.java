/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistematransporte;

import javax.swing.JOptionPane;

/**
 *
 * @author alons
 */
public class SistemaTransporte {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Crear conductor
        Conductor conductor = new Conductor();
        conductor.setNombre(JOptionPane.showInputDialog("Nombre del conductor: "));
        conductor.setCedula(JOptionPane.showInputDialog("Cedula del conductor: "));
        conductor.setCodigoEmpleado(JOptionPane.showInputDialog("Codigo del empleado: "));
        conductor.setRutaAsignada(JOptionPane.showInputDialog("Ruta asignada: "));
        conductor.setNombreEmpresa(JOptionPane.showInputDialog("Nombre de la empresa: "));
        
        String licencia = JOptionPane.showInputDialog("Tiene licencia profesional? (Si/No)");
        
        //Variables acumuladoras
        double totalComisiones = 0;
        double totalKm = 0;
        int puntos = 0;
        int cantidadViajes = 0;
        
        while (true) {
            Viaje viaje = new Viaje();

            viaje.setNombrePasajero(JOptionPane.showInputDialog("Nombre del pasajero:"));
            viaje.setCedulaPasajero(JOptionPane.showInputDialog("Cédula del pasajero:"));
            viaje.setCodigoViaje(JOptionPane.showInputDialog("Código del viaje:"));
            viaje.setDistanciaKm(Double.parseDouble(JOptionPane.showInputDialog("Distancia (km):")));

            int mes = Integer.parseInt(JOptionPane.showInputDialog("Número de mes (1-12):"));
            while (mes < 1 || mes > 12) {
                mes = Integer.parseInt(JOptionPane.showInputDialog("Número inválido. Ingrese mes entre 1 y 12:"));
            }
            viaje.setMes(mes);

            viaje.setInternacional(JOptionPane.showInputDialog("¿Es internacional? (Sí/No):").equalsIgnoreCase("sí"));
            viaje.setNocturno(JOptionPane.showInputDialog("¿Es nocturno? (Sí/No):").equalsIgnoreCase("sí"));
            viaje.setCargaPesada(JOptionPane.showInputDialog("¿Es de carga pesada? (Sí/No):").equalsIgnoreCase("sí"));
            viaje.setEstado(JOptionPane.showInputDialog("Estado del viaje (completado/cancelado/en proceso):").toLowerCase());

            // Solo si el viaje fue completado
            if (viaje.getEstado().equals("completado")) {
                double base = viaje.getDistanciaKm() * 10;
                double comision = base;

                if (viaje.isInternacional()) {
                    comision += base * 0.15;
                }
                if (viaje.isNocturno()) {
                    comision += base * 0.10;
                    puntos += 2;
                }
                if (viaje.isCargaPesada()) {
                    comision += base * 0.20;
                    puntos += 3;
                }
                if (viaje.getDistanciaKm() > 200) {
                    comision += base * 0.05;
                    puntos += 1;
                }

                totalComisiones += comision;
                totalKm += viaje.getDistanciaKm();
                cantidadViajes++;
            }

            // Preguntar si desea registrar otro viaje
            int continuar = JOptionPane.showConfirmDialog(null, "¿Desea registrar otro viaje?", "Continuar", JOptionPane.YES_NO_OPTION);
            if (continuar != JOptionPane.YES_OPTION) {
                break;
            }
        }

        boolean bonoExtra = (cantidadViajes > 5 || totalKm > 1000);
        if (bonoExtra) {
            totalComisiones += 5000;
            puntos += 5;
        }

        // Mostrar resultados
        String resumen = "El Conductor \"" + conductor.getNombre() + "\" código: " + conductor.getCodigoEmpleado() + "\n"
                + "Empresa: " + conductor.getNombreEmpresa() + "\n"
                + "Ruta asignada: " + conductor.getRutaAsignada() + "\n"
                + "Recorrió un total de " + totalKm + " km.\n"
                + "Obtuvo un total en comisiones de $" + totalComisiones + ".\n"
                + (bonoExtra ? "El conductor logró el objetivo de obtener el BONO EXTRA.\n" : "El conductor NO obtuvo el bono extra.\n")
                + "Puntos obtenidos: " + puntos + "\n"
                + (conductor.isLicenciaProfesional() ? "Cuenta con licencia profesional." : "NO tiene licencia profesional.");

        JOptionPane.showMessageDialog(null, resumen);
        
        
        
        
        
       
       
    }
    
}
