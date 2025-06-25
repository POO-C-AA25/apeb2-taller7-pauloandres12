
/**
 * Problema 5 - Venta de entradas al teatro
 * Dadas las siguientes clases, que expresan una relación de herencia entre las entidades:
 * Se desea gestionar la venta de entradas para un espectáculo en un teatro.
 * El patio de butacas del teatro se divide en varias zonas, cada una identificada
 * por su nombre. Los datos de las zonas son los mostrados en la siguiente tabla:
 *
 * NOMBRE ZONA	NÚMERO DE LOCALIDADES	PRECIO NORMA	PRECIO ABONADO
 * Principal	200	25$	17.5$
 * PalcoB	40	70$	40$
 * Central	400	20$	14$
 * Lateral	100	15.5$	10$
 *
 * Para realizar la compra de una entrada, un espectador debe indicar la zona
 * que desea y presentar al vendedor el documento que justifique que tiene algún
 * tipo de descuento (estudiante, abonado o pensionista). El vendedor sacará la
 * entrada del tipo apropiado y de la zona indicada, en el momento de la compra
 * se asignará a la entrada un identificador (un número entero) que permitirá la
 * identificación de la entrada en todas las operaciones que posteriormente
 * se desee realizar con ella.
 * Una entrada tiene como datos asociados su identificador, la zona a la que
 * pertenece y el nombre del comprador.
 * Los precios de las entradas dependen de la zona y del tipo de entrada según
 * lo explicado a continuación:
 *
 * Entradas normales: su precio es el precio normal de la zona elegida sin
 * ningún tipo de descuento
 * Entradas reducidas (para estudiantes o pensionistas): su precio tiene una
 * rebaja del 15% sobre el precio normal de la zona elegida.
 * Entradas abonado: su precio es el precio para abonados de la zona elegida.
 * La interacción entre el vendedor y la aplicación es la descrita en los
 * siguientes casos de usos.
 *
 * Note
 *
 * Caso de uso “Vende entrada”:
 * El vendedor elige la opción “vende entrada” e introduce la zona deseada,
 * el nombre del espectador y el tipo (normal, abonado o beneficiario de entrada reducida).
 * Si la zona elegida no está completa, la aplicación genera una nueva entrada
 * con los datos facilitados.
 * Si no existe ninguna zona con ese nombre, se notifica y finaliza el caso de
 * uso sin generar la entrada.
 * Si la zona elegida está completa lo notifica y finaliza el caso de uno sin
 * generar la entrada.
 * La aplicación muestra el identificador y el precio de la entrada.
 * Caso de uso “Consulta entrada”:
 *
 * El vendedor elige la opción “consulta entrada” e introduce el identificador de la entrada.
 * La aplicación muestra los datos de la entrada: nombre del espectador, precio
 * y nombre de la zona. Si no existe ninguna entrada con ese identificador,
 * lo notifica y finaliza el caso de uso
 *
 * @author paulo
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Problema_5_EntradasTeatro {

    public class SistemaEntradas {

        public static void main(String[] args) {
            ArrayList<Zona> areas = new ArrayList<>();
            Zona platinum = new Zona(30.0, 20.0, "Platinum", 150);
            areas.add(platinum);
            Zona palco = new Zona(65.0, 35.0, "Palco", 50);
            areas.add(palco);
            Zona preferencial = new Zona(22.0, 16.0, "Preferencial", 300);
            areas.add(preferencial);
            Zona general = new Zona(18.0, 12.0, "General", 120);
            areas.add(general);

            int seguir = 0;
            Scanner lector = new Scanner(System.in);
            ArrayList<Ticket> vendidos = new ArrayList<>();

            while (seguir == 0) {
                System.out.println("\nSISTEMA DE VENTA DE ENTRADAS");
                System.out.println("[1] Vender entrada");
                System.out.println("[2] Buscar entrada por código");
                System.out.println("[0] Salir");
                System.out.print("Ingrese opción: ");
                int opcion = lector.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Seleccione área:");
                        System.out.println("[1]Platinum\n[2]Palco\n[3]Preferencial\n[4]General");
                        int opcionArea = lector.nextInt();
                        Zona areaSeleccionada = areas.get(opcionArea - 1);

                        if (areaSeleccionada.tieneDisponibilidad()) {
                            System.out.print("Nombre del comprador: ");
                            String comprador = lector.next();

                            System.out.println("Tipo de entrada:");
                            System.out.println("[1]Normal\n[2]Socio\n[3]Con descuento");
                            int tipoEntrada = lector.nextInt();

                            Ticket ticket = null;
                            switch (tipoEntrada) {
                                case 1:
                                    ticket = new Normal(areaSeleccionada, comprador);
                                    break;
                                case 2:
                                    ticket = new Socio(areaSeleccionada, comprador);
                                    break;
                                case 3:
                                    ticket = new DescuentoEntrada(areaSeleccionada, comprador);
                                    break;
                                default:
                                    System.out.println("Tipo de entrada inválido.");
                                    continue;
                            }

                            areaSeleccionada.ocuparAsiento();
                            ticket.calcularPrecio();
                            ticket.generarCodigo();
                            vendidos.add(ticket);
                            System.out.println(ticket);
                        } else {
                            System.out.println("¡No hay asientos disponibles en esta área!");
                        }
                        break;
                    case 2:
                        System.out.print("Ingrese código a buscar: ");
                        int codigoBusqueda = lector.nextInt();
                        boolean hallado = false;
                        for (Ticket t : vendidos) {
                            if (t.codigo == codigoBusqueda) {
                                System.out.println("\n>> Comprador: " + t.nombreCliente);
                                System.out.println(">> Precio: " + t.precio);
                                System.out.println(">> Área: " + t.area.nombreArea + "\n");
                                hallado = true;
                                break;
                            }
                        }
                        if (!hallado) {
                            System.out.println("No se encontró la entrada con ese código.");
                        }
                        break;
                    case 0:
                        seguir = 1;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
            lector.close();
        }
    }
}

class Zona {

    public double precioNormal;
    public double precioSocio;
    public String nombreArea;
    public int asientosDisponibles;

    public Zona(double precioNormal, double precioSocio, String nombreArea, int asientosDisponibles) {
        this.precioNormal = precioNormal;
        this.precioSocio = precioSocio;
        this.nombreArea = nombreArea;
        this.asientosDisponibles = asientosDisponibles;
    }

    public boolean tieneDisponibilidad() {
        return this.asientosDisponibles > 0;
    }

    public void ocuparAsiento() {
        this.asientosDisponibles--;
    }
}

class Ticket {

    public Zona area;
    public int codigo;
    public String nombreCliente;
    public double precio;

    public Ticket(Zona area, String nombreCliente) {
        this.area = area;
        this.nombreCliente = nombreCliente;
    }

    public double calcularPrecio() {
        this.precio = this.area.precioNormal;
        return precio;
    }

    public void generarCodigo() {
        Random rand = new Random();
        this.codigo = 10000 + rand.nextInt(90000);
    }

    public String toString() {
        return "Ticket{" + "Código=" + codigo + ", Precio=" + precio + '}';
    }
}

class Normal extends Ticket {

    public Normal(Zona area, String nombreCliente) {
        super(area, nombreCliente);
    }
}

class DescuentoEntrada extends Ticket {

    public DescuentoEntrada(Zona area, String nombreCliente) {
        super(area, nombreCliente);
    }

    @Override
    public double calcularPrecio() {
        this.precio = super.calcularPrecio() * 0.80; // 20% de descuento
        return precio;
    }
}

class Socio extends Ticket {

    public Socio(Zona area, String nombreCliente) {
        super(area, nombreCliente);
    }

    @Override
    public double calcularPrecio() {
        this.precio = this.area.precioSocio;
        return precio;
    }

}
