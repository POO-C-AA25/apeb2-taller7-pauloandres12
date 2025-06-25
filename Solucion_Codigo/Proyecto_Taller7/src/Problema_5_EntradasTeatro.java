
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
public class Problema_5_EntradasTeatro {

}
