package udla.jgjbmp.pmeaabet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que maneja la administración de ventas y presupuesto
 */
public class Administrador {
    // Atributo para almacenar el presupuesto
    private static double presupuesto = 0.0;
    
    // Atributos para gestión de almacenamiento
    private static double capacidadAlmacen = 0.0;
    private static double espacioOcupado = 0.0;

    // Getters y Setters
    public static double getPresupuesto() {
        return presupuesto;
    }

    public static void setPresupuesto(double presupuesto) {
        Administrador.presupuesto = presupuesto;
    }
    
    public static double getCapacidadAlmacen() {
        return capacidadAlmacen;
    }
    
    public static void setCapacidadAlmacen(double capacidadAlmacen) {
        Administrador.capacidadAlmacen = capacidadAlmacen;
    }
    
    public static double getEspacioOcupado() {
        return espacioOcupado;
    }
    
    public static void setEspacioOcupado(double espacioOcupado) {
        Administrador.espacioOcupado = espacioOcupado;
    }
    
    public static double getEspacioDisponible() {
        return capacidadAlmacen - espacioOcupado;
    }
    
    /**
     * Actualiza el espacio ocupado calculando el total del inventario
     */
    public static void actualizarEspacioOcupado() {
        espacioOcupado = 0.0;
        for (Producto p : Producto.getListaProductos()) {
            espacioOcupado += p.getEspacioAlmacenamiento() * p.getStockProducto();
        }
    }

    /**
     * Clase interna que representa una factura
     */
    public static class Factura {
        private List<ItemVenta> items;
        private double subtotal;
        private double iva;
        private double total;

        public Factura(List<ItemVenta> items) {
            this.items = new ArrayList<>(items);
            calcularTotales();
        }

        /**
         * Calcula los totales de la factura
         */
        private void calcularTotales() {
            this.subtotal = 0;
            for (ItemVenta item : items) {
                this.subtotal += item.getSubtotal();
            }
            this.iva = this.subtotal * 0.15; // 15% de IVA
            this.total = this.subtotal + this.iva;
        }

        // Getters
        public List<ItemVenta> getItems() { return items; }
        public double getSubtotal() { return subtotal; }
        public double getIva() { return iva; }
        public double getTotal() { return total; }
    }

    /**
     * Procesa la venta de productos
     */
    public static void procesarVenta(Scanner scanner) {
        System.out.println("\n========================================");
        System.out.println("         PROCESO DE VENTA");
        System.out.println("========================================");

        // Verificar que hay productos
        if (Producto.getListaProductos().isEmpty()) {
            System.out.println("No hay productos disponibles para la venta.");
            return;
        }

        List<ItemVenta> carrito = new ArrayList<>();
        boolean continuarVenta = true;

        while (continuarVenta) {
            // Solicitar ID del producto
            System.out.print("\nIngrese el ID del producto a vender: ");
            String idProducto = scanner.nextLine();

            // Buscar el producto
            Producto producto = Inventario.buscarProducto(idProducto);

            if (producto == null) {
                System.out.println("Error: Producto no encontrado con el ID " + idProducto);
                System.out.print("¿Desea intentar con otro ID? (S/N): ");
                String respuesta = scanner.nextLine().trim().toUpperCase();
                if (!respuesta.equals("S")) {
                    break;
                }
                continue;
            }

            // Mostrar información del producto
            Inventario.mostrarInfoProducto(producto);

            // Verificar que hay stock disponible
            if (producto.getStockProducto() <= 0) {
                System.out.println("Error: Este producto no tiene stock disponible.");
                System.out.print("¿Desea escoger otro producto? (S/N): ");
                String respuesta = scanner.nextLine().trim().toUpperCase();
                if (respuesta.equals("S")) {
                    continue;
                } else {
                    break;
                }
            }

            // Solicitar cantidad
            int cantidad = 0;
            boolean cantidadValida = false;

            while (!cantidadValida) {
                System.out.print("¿Cuántos desea comprar?: ");
                cantidad = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                // Validar cantidad
                if (cantidad <= 0) {
                    System.out.println("Error: La cantidad debe ser mayor a 0.");
                } else if (cantidad > producto.getStockProducto()) {
                    System.out.println("Error: Stock insuficiente. Solo hay " +
                            producto.getStockProducto() + " unidades disponibles.");
                    System.out.print("¿Desea ingresar otra cantidad? (S/N): ");
                    String respuesta = scanner.nextLine().trim().toUpperCase();
                    if (!respuesta.equals("S")) {
                        break;
                    }
                } else {
                    cantidadValida = true;
                }
            }

            if (!cantidadValida) {
                System.out.print("¿Desea escoger otro producto? (S/N): ");
                String respuesta = scanner.nextLine().trim().toUpperCase();
                if (respuesta.equals("S")) {
                    continue;
                } else {
                    break;
                }
            }

            // Agregar al carrito
            ItemVenta item = new ItemVenta(producto, cantidad);
            carrito.add(item);

            System.out.println("\n Producto agregado al carrito:");
            System.out.println("  " + producto.getNombreProducto() + " x" + cantidad +
                    " = $" + String.format("%.2f", item.getSubtotal()));

            // Preguntar si desea agregar más productos
            System.out.print("\n¿Desea agregar otro producto? (S/N): ");
            String respuesta = scanner.nextLine().trim().toUpperCase();

            if (!respuesta.equals("S")) {
                continuarVenta = false;
            }
        }

        // Verificar si hay productos en el carrito
        if (carrito.isEmpty()) {
            System.out.println("\nVenta cancelada. No se agregaron productos.");
            return;
        }

        // Realizar la venta
        realizarVenta(carrito);
    }

    /**
     * Método que procesa la transacción de venta
     */
    private static void realizarVenta(List<ItemVenta> items) {
        // Validar que hay items
        if (items == null || items.isEmpty()) {
            System.out.println("Error: No hay productos para vender.");
            return;
        }

        // Validar stock y actualizar
        for (ItemVenta item : items) {
            Producto producto = item.getProducto();
            int stockActual = producto.getStockProducto();
            int cantidadVenta = item.getCantidad();

            // Verificar stock suficiente
            if (stockActual < cantidadVenta) {
                System.out.println("Error: Stock insuficiente para " +
                        producto.getNombreProducto());
                return;
            }

            // Actualizar stock
            producto.setStockProducto(stockActual - cantidadVenta);
        }

        // Generar factura
        Factura factura = new Factura(items);

        // Calcular ganancia y actualizar presupuesto
        double totalVenta = factura.getTotal();
        double ganancia = 0;
        for (ItemVenta item : items) {
            Producto p = item.getProducto();
            double precioVenta = p.getPrecioProducto() * item.getCantidad();
            double costoTotal = p.getCostoProducto() * item.getCantidad();
            ganancia += (precioVenta - costoTotal);
        }
        presupuesto += totalVenta;
        
        // Actualizar espacio ocupado tras la venta
        actualizarEspacioOcupado();

        System.out.println("\nVenta realizada exitosamente!");
        System.out.println("  Total de la venta: $" + String.format("%.2f", totalVenta));
        System.out.println("  Ganancia neta: $" + String.format("%.2f", ganancia));
        System.out.println("  Presupuesto actual: $" + String.format("%.2f", presupuesto));
        System.out.println("  Espacio liberado en almacen");
        System.out.println("  Espacio disponible: " + String.format("%.2f", getEspacioDisponible()) + " litros");

        // Imprimir factura automáticamente
        imprimirFactura(factura);
    }

    /**
     * Imprime una factura formateada
     */
    private static void imprimirFactura(Factura factura) {
        if (factura == null) {
            System.out.println("Error: Factura no válida.");
            return;
        }

        System.out.println("\n===============================================================");
        System.out.println("                      FACTURA DE VENTA                          ");
        System.out.println("==================================================================");
        System.out.println("\n-----------------------------------------------------------------------");
        System.out.println("  PRODUCTOS");
        System.out.println("-------------------------------------------------------------------------");

        System.out.printf("  %-10s %-25s %-8s %-10s %-12s%n",
                "Código", "Nombre", "Cant.", "P. Unit.", "Subtotal");
        System.out.println("--------------------------------------------------------------------------");

        for (ItemVenta item : factura.getItems()) {
            Producto p = item.getProducto();
            String nombreCorto = p.getNombreProducto().length() > 25 ?
                    p.getNombreProducto().substring(0, 22) + "..." :
                    p.getNombreProducto();

            System.out.printf("  %-10s %-25s %-8d $%-9.2f $%-11.2f%n",
                    p.getCodigoProducto(),
                    nombreCorto,
                    item.getCantidad(),
                    p.getPrecioProducto(),
                    item.getSubtotal());
        }

        System.out.println("------------------------------------------------------------------------");
        System.out.println("\n----------------------------------------------------------------------");
        System.out.println("  RESUMEN");
        System.out.println("------------------------------------------------------------------------");
        System.out.printf("  Subtotal:           $%10.2f%n", factura.getSubtotal());
        System.out.printf("  IVA (15%%)           $%10.2f%n", factura.getIva());
        System.out.println("------------------------------------------------------------------------");
        System.out.printf("  TOTAL:              $%10.2f%n", factura.getTotal());
        System.out.println("========================================================================");
        System.out.println("                ¡Gracias por su compra!");
        System.out.println("======================================================================\n");
    }
}