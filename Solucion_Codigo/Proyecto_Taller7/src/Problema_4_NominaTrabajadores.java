
/**
 * Problema 4 - Sistema de nómina para trabajadores
 * Se desea desarrollar un sistema de nómina para los trabajadores de una empresa.
 * Los datos personales de los trabajadores son nombre y apellidos, dirección y DNI.
 * Además, existen diferentes tipos de trabajadores:
 * Fijos Mensuales: que cobran una cantidad fija al mes.
 * Comisionistas: cobran un porcentaje fijo por las ventas que han realizado
 * Por Horas: cobran un precio por cada una de las horas que han realizado
 * durante el mes. El precio es fijo para las primeras 40 horas y es otro
 * para las horas realizadas a partir de la 40 hora mensual.
 * Jefe: cobra un sueldo fijo (no hay que calcularlo). Cada empleado tiene
 * obligatoriamente un jefe (exceptuando los jefes que no tienen ninguno).
 * El programa debe permitir dar de alta a trabajadores, así como fijar horas
 * o ventas realizadas e imprimir la nómina correspondiente al final de mes.
 *
 * Note
 *
 * Diseñe la jerarquia de clases UML basado en herencia, que defina de mejor
 * forma el escenario planteado.
 * Para probar el diseño de clases, instancia en el clase de prueba Ejecutor
 * (la-s clase-s respectiva-s), con datos aleatorios.
 * En los escenarios de prueba verifique su solución con al menos 2 tipos de
 * trabajadores.
 *
 * @author paulo
 */
import java.util.ArrayList;

public class Problema_4_NominaTrabajadores {

    public static void main(String[] args) {
        Jefe jefePrincipal = new Jefe("Roberto", "Delgado", "Av. Amazonas 1101", "99887766X", 2800);
        jefePrincipal.jefe = jefePrincipal;

        Empresa empresa = new Empresa(jefePrincipal);

        FijosMensuales empleado1 = new FijosMensuales("Camila", "Espinoza", "Calle Bolívar 202", "11223344P", jefePrincipal, 1450);
        PorHoras empleado2 = new PorHoras("Esteban", "Vega", "Av. Colón 300", "55667788Q", jefePrincipal, 10.25, 44);
        Comisionistas empleado3 = new Comisionistas("Fernanda", "Zambrano", "Calle Sucre 908", "33445566R", jefePrincipal, 9200, 0.03);

        empresa.darAltaTrabajador(empleado1);
        empresa.darAltaTrabajador(empleado2);
        empresa.darAltaTrabajador(empleado3);

        empresa.listaNomina();
    }
    String nombres;
    String apellidos;
    String direccion;
    String DNI;
    Jefe jefe;

    public Problema_4_NominaTrabajadores(String nombres, String apellidos, String direccion, String DNI, Jefe jefe) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.DNI = DNI;
        this.jefe = jefe;
    }

    public double calcularSueldo() {
        return 0;
    }

    public String toString() {
        return "Trabajador: " + nombres + " " + apellidos + " (" + DNI + ")\n"
                + "Dirección: " + direccion + "\n"
                + "Jefe: " + jefe.nombres + " " + jefe.apellidos + "\n"
                + "Sueldo: " + calcularSueldo() + "\n";
    }
}

class Empresa {

    Jefe jefeGeneral;
    ArrayList<Problema_4_NominaTrabajadores> trabajadores;

    public Empresa(Jefe jefeGeneral) {
        this.jefeGeneral = jefeGeneral;
        trabajadores = new ArrayList<>();
        trabajadores.add(jefeGeneral);
    }

    public void darAltaTrabajador(Problema_4_NominaTrabajadores t) {
        trabajadores.add(t);
    }

    public void listaNomina() {
        System.out.println("NOMINA DE LA EMPRESA");
        for (Problema_4_NominaTrabajadores t : trabajadores) {
            System.out.println(t);
        }
    }
}

class Jefe extends Problema_4_NominaTrabajadores {

    double sueldo;

    public Jefe(String nombres, String apellidos, String direccion, String DNI, double sueldo) {
        super(nombres, apellidos, direccion, DNI, null);
        this.sueldo = sueldo;
    }

    public double calcularSueldo() {
        return sueldo;
    }
}

class FijosMensuales extends Problema_4_NominaTrabajadores {

    double sueldo;

    public FijosMensuales(String nombres, String apellidos, String direccion, String DNI, Jefe jefe, double sueldo) {
        super(nombres, apellidos, direccion, DNI, jefe);
        this.sueldo = sueldo;
    }

    public double calcularSueldo() {
        return sueldo;
    }
}

class PorHoras extends Problema_4_NominaTrabajadores {

    double valorHora;
    int horasTrabajadas;

    public PorHoras(String nombres, String apellidos, String direccion, String DNI, Jefe jefe, double valorHora, int horasTrabajadas) {
        super(nombres, apellidos, direccion, DNI, jefe);
        this.valorHora = valorHora;
        this.horasTrabajadas = horasTrabajadas;
    }

    public double calcularSueldo() {
        if (horasTrabajadas <= 40) {
            return horasTrabajadas * valorHora;
        } else {
            return 40 * valorHora + (horasTrabajadas - 40) * (valorHora * 1.5);
        }
    }
}

class Comisionistas extends Problema_4_NominaTrabajadores {

    int ventasMes;
    double comision;

    public Comisionistas(String nombres, String apellidos, String direccion, String DNI, Jefe jefe, int ventasMes, double comision) {
        super(nombres, apellidos, direccion, DNI, jefe);
        this.ventasMes = ventasMes;
        this.comision = comision;
    }

    public double calcularSueldo() {
        return ventasMes * comision;
    }
}
