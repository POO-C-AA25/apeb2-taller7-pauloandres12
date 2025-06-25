
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
public class Problema_4_NominaTrabajadores {

    public static void main(String[] args) {
        ArrayList<Trabajador> listaEmpleados = new ArrayList<>();
        Nomina nomina = new Nomina(listaEmpleados);
        Jefe jefe1 = new Jefe("Rene", "Morales", "Av. Amazonas", "001");
        Jefe jefe2 = new Jefe("Luis", "Quito", "Av. El Inca", "002");
        Jefe jefe3 = new Jefe("Maria", "Valdivieso", "Av. República", "003");
        listaEmpleados.add(jefe1);
        listaEmpleados.add(jefe2);
        listaEmpleados.add(jefe3);

        FijoMensual fijo1 = new FijoMensual(jefe1, "Carlos", "Correa", "Calle Sucre", "101");
        listaEmpleados.add(fijo1);

        Comisionista com1 = new Comisionista(jefe2, 8, "Daniela", "Jaramillo", "Barrio El Bosque", "202");
        listaEmpleados.add(com1);

        PorHoras ph1 = new PorHoras(jefe3, 50, "Esteban", "Chamba", "Conjunto Los Alamos", "303");
        listaEmpleados.add(ph1);
        listaEmpleados.add(ph1);

        for (Trabajador trab : nomina.listaEmpleados) {
            trab.calcularSueldo();
        }

        nomina.CalcularHoras();
        nomina.CalcularVentas();
        System.out.println(nomina.dibujarnomina());
        nomina.despedir("456");
        System.out.println("\n\n================!!!!! DNI: 456 - ESTAS DESPEDIDO!!!!!================\n\n");
        System.out.println(nomina.dibujarnomina());
    }
}

class Nomina {

    public ArrayList<Trabajador> listaEmpleados;
    public int horas;
    public int ventas;

    public Nomina(ArrayList<Trabajador> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public void despedir(String DNI) {
        for (int i = 0; i < listaEmpleados.size(); i++) {
            if (listaEmpleados.get(i).dni.equalsIgnoreCase(DNI)) {
                listaEmpleados.remove(i);
            }
        }
    }

    public void CalcularHoras() {
        for (Trabajador trabajador : listaEmpleados) {
            if (trabajador instanceof PorHoras) {
                this.horas += ((PorHoras) trabajador).horas;
            }
        }
    }

    public void CalcularVentas() {
        for (Trabajador trabajador : listaEmpleados) {
            if (trabajador instanceof Comisionista) {
                this.ventas += ((Comisionista) trabajador).ventas;
            }
        }
    }

    public String dibujarnomina() {
        String nomina = "";
        for (Trabajador trabajador : listaEmpleados) {
            nomina += trabajador + "\n";
        }
        nomina += "\n\nSe trabajaron: " + horas + "      -      Se hicieron " + ventas + " ventas";
        return nomina;
    }
}

class Trabajador {

    public String nombre;
    public String apellido;
    public String direccion;
    public String dni;
    public double sueldo;

    public Trabajador(String nombre, String apellido, String direccion, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.dni = dni;
    }

    public void calcularSueldo() {
    }

    @Override
    public String toString() {
        return "nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion + ", DNI=" + dni + ", sueldo=" + sueldo;
    }
}

class FijoMensual extends Trabajador {

    public Jefe jefe;

    public FijoMensual(Jefe jefe, String nombre, String apellido, String direccion, String dni) {
        super(nombre, apellido, direccion, dni);
        this.jefe = jefe;
    }

    @Override
    public void calcularSueldo() {
        this.sueldo = 350;
    }

    @Override
    public String toString() {
        return super.toString() + " jefe=" + jefe.nombre;
    }
}

class Comisionista extends Trabajador {

    public Jefe jefe;
    public int ventas;

    public Comisionista(Jefe jefe, int ventasRealizadas, String nombre, String apellido, String direccion, String dni) {
        super(nombre, apellido, direccion, dni);
        this.jefe = jefe;
        this.ventas = ventasRealizadas;
    }

    @Override
    public void calcularSueldo() {
        this.sueldo = (this.ventas * 100) * 0.15;
    }

    @Override
    public String toString() {
        return super.toString() + " jefe=" + jefe.nombre + ", ventasRealiadas=" + ventas;
    }
}

class PorHoras extends Trabajador {

    public Jefe jefe;
    public int horas;

    public PorHoras(Jefe jefe, int horasTrabajadas, String nombre, String apellido, String direccion, String dni) {
        super(nombre, apellido, direccion, dni);
        this.jefe = jefe;
        this.horas = horasTrabajadas;
    }

    @Override
    public void calcularSueldo() {
        this.sueldo = this.horas * 7;
        if (this.horas > 40) {
            this.sueldo = (40 * 7) + (this.horas - 40) * 14;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " jefe=" + jefe.nombre + ", horasTrabajadas=" + horas;
    }
}

class Jefe extends Trabajador {

    public Jefe(String nombre, String apellido, String direccion, String DNI) {
        super(nombre, apellido, direccion, DNI);
    }

    @Override
    public void calcularSueldo() {
        this.sueldo = 10000;
    }
}
