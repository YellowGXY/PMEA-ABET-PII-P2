package udla.jgjbmp.pmeaabet;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Inventario {
    // Atributos
    private static int totalProductos;
    private static int espacioDisponible;
    private static double gananciasTotales;
    private static double costosInv;

    // Constructor vacío
    public Inventario() {
        totalProductos = 0;
        espacioDisponible = 0;
        gananciasTotales = 0.0;
        costosInv = 0.0;
        actualizarEstadisticas();
    }

    // Getters y Setters
    public static int getTotalProductos() {
        return totalProductos;
    }

    public static void setTotalProductos(int totalProductos) {
        Inventario.totalProductos = totalProductos;
    }

    public static int getEspacioDisponible() {
        return espacioDisponible;
    }

    public static void setEspacioDisponible(int espacioDisponible) {
        Inventario.espacioDisponible = espacioDisponible;
    }

    public static double getGananciasTotales() {
        return gananciasTotales;
    }

    public static void setGananciasTotales(double gananciasTotales) {
        Inventario.gananciasTotales = gananciasTotales;
    }

    public static double getCostosInv() {
        return costosInv;
    }

    public static void setCostosInv(double costosInv) {
        Inventario.costosInv = costosInv;
    }

    /**
     * Método para buscar un producto por código
     */
    public static Producto buscarProducto(String codigoProducto) {
        for (Producto p : Producto.getListaProductos()) {
            if (p.getCodigoProducto().equals(codigoProducto)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Busca un producto por nombre sin distinguir mayusculas y minusculas
     * Permite busquedas parciales del nombre del producto
     */
    public static Producto buscarProductoPorNombre(String nombreProducto) {
        // Convertir a minúsculas para búsqueda case-insensitive
        String nombreBusqueda = nombreProducto.toLowerCase();

        for (Producto p : Producto.getListaProductos()) {
            // Si el nombre del producto contiene el texto buscado
            if (p.getNombreProducto().toLowerCase().contains(nombreBusqueda)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Método para buscar y mostrar un producto por código
     */
    public static void buscarYMostrarPorCodigo(String codigoProducto) {
        Producto producto = buscarProducto(codigoProducto);

        if (producto == null) {
            System.out.println("\nError: No se encontró un producto con el código " + codigoProducto);
            return;
        }

        mostrarDetalleProducto(producto);
    }

    /**
     * Método para buscar y mostrar un producto por nombre
     */
    public static void buscarYMostrarPorNombre(String nombreProducto) {
        Producto producto = buscarProductoPorNombre(nombreProducto);

        if (producto == null) {
            System.out.println("\nError: No se encontró un producto con el nombre '" + nombreProducto + "'");
            return;
        }

        mostrarDetalleProducto(producto);
    }

    /**
     * Método para mostrar información detallada de un producto
     */
    private static void mostrarDetalleProducto(Producto producto) {
        System.out.println("\n===================================================================");
        System.out.println("                   PRODUCTO ENCONTRADO                          ");
        System.out.println("===================================================================");
        System.out.println("\n  Código: " + producto.getCodigoProducto());
        System.out.println("  Nombre: " + producto.getNombreProducto());
        System.out.println("  Precio: $" + String.format("%.2f", producto.getPrecioProducto()));
        System.out.println("  Stock: " + producto.getStockProducto() + " unidades");
        System.out.println("  Costo: $" + String.format("%.2f", producto.getCostoProducto()));
        System.out.println("  Tipo: " + producto.getTipoProducto());
        System.out.println("  Caducidad: " + producto.getCaducidadProducto());
        System.out.println("  Fecha de caducidad: " + producto.getFechaCaducidad());

        if (producto.getProveedor() != null) {
            System.out.println("  Proveedor: " + producto.getProveedor().getNombreProveedor());
        } else {
            System.out.println("  Proveedor: N/A");
        }

        System.out.println("===================================================================");
    }

    /**
     * Muestra informacion resumida de un producto
     */
    public static void mostrarInfoProducto(Producto producto) {
        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.println("\n--- INFORMACIÓN DEL PRODUCTO ---");
        System.out.println("Código: " + producto.getCodigoProducto());
        System.out.println("Nombre: " + producto.getNombreProducto());
        System.out.println("Precio: $" + producto.getPrecioProducto());
        System.out.println("Stock disponible: " + producto.getStockProducto());
        System.out.println("--------------------------------");
    }

    /**
     * Muestra el inventario completo en formato tabla resumida
     */
    public static void verInventarioCompleto() {
        if (Producto.getListaProductos().isEmpty()) {
            System.out.println("\nNo hay productos en el inventario.");
            return;
        }

        System.out.println("\n=================================================================");
        System.out.println("                    INVENTARIO COMPLETO                         ");
        System.out.println("=================================================================");

        System.out.printf("\n  %-10s %-25s %-10s %-10s %-10s%n",
                "Código", "Nombre", "Precio", "Stock", "Costo");
        System.out.println("  ──────────────────────────────────────────────────────────────────");

        for (Producto p : Producto.getListaProductos()) {
            String nombreCorto = p.getNombreProducto().length() > 25 ?
                    p.getNombreProducto().substring(0, 22) + "..." :
                    p.getNombreProducto();

            System.out.printf("  %-10s %-25s $%-9.2f %-10d $%-9.2f%n",
                    p.getCodigoProducto(),
                    nombreCorto,
                    p.getPrecioProducto(),
                    p.getStockProducto(),
                    p.getCostoProducto());
        }

        System.out.println("  ──────────────────────────────────────────────────────────────────");
        System.out.println("\n  Total de productos: " + Producto.getListaProductos().size());
        System.out.println("=======================================================================\n");
    }

    /**
     * Actualiza las estadísticas generales del inventario
     */
    private static void actualizarEstadisticas() {
        List<Producto> productos = Producto.getListaProductos();
        totalProductos = productos.size();

        // Calcular espacio disponible (suma total del stock)
        int stockTotal = 0;
        double costosTotal = 0.0;

        for (Producto p : productos) {
            stockTotal += p.getStockProducto();
            costosTotal += p.getCostoProducto() * p.getStockProducto();
        }

        espacioDisponible = stockTotal;
        costosInv = costosTotal;
    }

    /**
     * Método para registrar una venta y actualizar el inventario
     */
    public static void invVenta(String codigoProducto, int cantidad) {
        if (cantidad <= 0) {
            System.out.println("Error: La cantidad debe ser mayor a 0");
            return;
        }

        Producto producto = buscarProducto(codigoProducto);

        if (producto == null) {
            System.out.println("Error: No se encontró el producto con código " + codigoProducto);
            return;
        }

        // Verificar que hay suficiente stock
        if (producto.getStockProducto() < cantidad) {
            System.out.println("Error: Stock insuficiente");
            System.out.println("Stock disponible: " + producto.getStockProducto());
            System.out.println("Cantidad solicitada: " + cantidad);
            return;
        }

        // Calcular ganancia de la venta
        double gananciaVenta = (producto.getPrecioProducto() - producto.getCostoProducto()) * cantidad;
        double totalVenta = producto.getPrecioProducto() * cantidad;

        // Actualizar el stock del producto
        int nuevoStock = producto.getStockProducto() - cantidad;
        producto.setStockProducto(nuevoStock);

        // Actualizar ganancias totales
        gananciasTotales += gananciaVenta;

        System.out.println("=== VENTA REALIZADA ===");
        System.out.println("Producto: " + producto.getNombreProducto());
        System.out.println("Cantidad vendida: " + cantidad);
        System.out.println("Precio unitario: $" + String.format("%.2f", producto.getPrecioProducto()));
        System.out.println("Total venta: $" + String.format("%.2f", totalVenta));
        System.out.println("Ganancia: $" + String.format("%.2f", gananciaVenta));
        System.out.println("Stock restante: " + nuevoStock);
        System.out.println("=======================");

        // Actualizar estadísticas del inventario
        actualizarEstadisticas();

        // Alerta si el stock es bajo
        if (nuevoStock < 2) {
            System.out.println("ALERTA: Stock bajo para " + producto.getNombreProducto() + " - ¡Reabastecer pronto!");
        }
    }

    /**
     * Genera un reporte completo del inventario en formato tabla
     */
    public static void reporteInv() {
        List<Producto> productos = Producto.getListaProductos();

        if (productos.isEmpty()) {
            System.out.println("El inventario está vacío");
            return;
        }

        // Actualizar estadisticas antes de generar el reporte
        actualizarEstadisticas();

        System.out.println("\n============================================================================================================================");
        System.out.println("                                            REPORTE DE INVENTARIO                                                           ");
        System.out.println("============================================================================================================================");
        System.out.println(" Codigo    | Nombre Producto              | Tipo             | Precio   | Stock | Caducidad  | Dias Rest. | Reabastecer ");
        System.out.println("-----------|------------------------------|------------------|----------|-------|------------|------------|-------------");

        for (Producto p : productos) {
            String codigo = ajustarAncho(p.getCodigoProducto(), 9);
            String nombre = ajustarAncho(p.getNombreProducto(), 28);
            String tipo = ajustarAncho(p.getTipoProducto().toString(), 16);
            String precio = ajustarAncho(String.format("$%.2f", p.getPrecioProducto()), 8);
            String stock = ajustarAncho(String.valueOf(p.getStockProducto()), 5);

            // Calcular días restantes hasta la caducidad
            String diasRestantes = "N/A";
            if (p.getFechaCaducidad() != null) {
                long dias = ChronoUnit.DAYS.between(LocalDate.now(), p.getFechaCaducidad());
                diasRestantes = dias >= 0 ? String.valueOf(dias) : "CADUCADO";
            }
            String diasRest = ajustarAncho(diasRestantes, 10);

            String caducidad = ajustarAncho(p.getCaducidadProducto().toString(), 10);
            String reabastecer = ajustarAncho(p.getStockProducto() < 2 ? "SI" : "NO", 11);

            System.out.println(" " + codigo + " | " + nombre + " | " + tipo + " | " + precio + " | " + stock + " | " + caducidad + " | " + diasRest + " | " + reabastecer + " ");
        }

        System.out.println("============================================================================================================================");
        System.out.println(" ESTADISTICAS DEL INVENTARIO                                                                                               ");
        System.out.println("============================================================================================================================");
        System.out.println(" Total de productos diferentes: " + ajustarAncho(String.valueOf(totalProductos), 89) + "");
        System.out.println(" Total de unidades en stock: " + ajustarAncho(String.valueOf(espacioDisponible), 92) + "");
        System.out.println(" Costo total del inventario: $" + ajustarAncho(String.format("%.2f", costosInv), 90) + "");
        System.out.println(" Ganancias totales acumuladas: $" + ajustarAncho(String.format("%.2f", gananciasTotales), 87) + "");
        System.out.println("============================================================================================================================");

        // Alertas de productos con stock bajo
        System.out.println("\nPRODUCTOS CON STOCK BAJO (< 2 unidades):");
        boolean hayStockBajo = false;
        for (Producto p : productos) {
            if (p.getStockProducto() < 2) {
                System.out.println("   - " + p.getNombreProducto() + " (Código: " + p.getCodigoProducto() + ") - Stock: " + p.getStockProducto());
                hayStockBajo = true;
            }
        }
        if (!hayStockBajo) {
            System.out.println("   [OK] No hay productos con stock bajo");
        }

        // Alertas de productos proximos a caducar (menos de 7 dias)
        System.out.println("\n[!] PRODUCTOS PROXIMOS A CADUCAR (menos de 7 dias):");
        boolean hayCaducando = false;
        for (Producto p : productos) {
            if (p.getFechaCaducidad() != null) {
                long diasRestantes = ChronoUnit.DAYS.between(LocalDate.now(), p.getFechaCaducidad());
                if (diasRestantes >= 0 && diasRestantes < 7) {
                    System.out.println("   - " + p.getNombreProducto() + " (Código: " + p.getCodigoProducto() + ") - Caduca en: " + diasRestantes + " días");
                    hayCaducando = true;
                } else if (diasRestantes < 0) {
                    System.out.println("   - " + p.getNombreProducto() + " (Código: " + p.getCodigoProducto() + ") - ¡CADUCADO!");
                    hayCaducando = true;
                }
            }
        }
        if (!hayCaducando) {
            System.out.println("   [OK] No hay productos proximos a caducar");
        }
        System.out.println();
    }

    /**
     * Muestra el inventario con todos los detalles de cada producto
     */
    public static void verInventario() {
        List<Producto> productos = Producto.getListaProductos();

        if (productos.isEmpty()) {
            System.out.println("El inventario esta vacio");
            return;
        }

        System.out.println("\n================================================================");
        System.out.println("               LISTADO COMPLETO DE PRODUCTOS                    ");
        System.out.println("================================================================\n");

        int contador = 1;
        for (Producto p : productos) {
            System.out.println("-------------------------------------------------------------");
            System.out.println(" PRODUCTO #" + contador);
            System.out.println("-------------------------------------------------------------");
            System.out.println(" Codigo:           " + p.getCodigoProducto());
            System.out.println(" Nombre:           " + p.getNombreProducto());
            System.out.println(" Tipo:             " + p.getTipoProducto());
            System.out.println(" Precio:           $" + String.format("%.2f", p.getPrecioProducto()));
            System.out.println(" Costo:            $" + String.format("%.2f", p.getCostoProducto()));
            System.out.println(" Stock:            " + p.getStockProducto() + " unidades");
            System.out.println(" Caducidad:        " + p.getCaducidadProducto());

            if (p.getFechaCaducidad() != null) {
                System.out.println(" Fecha Caducidad:  " + p.getFechaCaducidad());
                long diasRestantes = ChronoUnit.DAYS.between(LocalDate.now(), p.getFechaCaducidad());
                if (diasRestantes >= 0) {
                    System.out.println(" Dias restantes:   " + diasRestantes + " dias");
                } else {
                    System.out.println(" Estado:           CADUCADO!");
                }
            }

            if (p.getProveedor() != null) {
                System.out.println(" Proveedor:        " + p.getProveedor().getNombreProveedor());
                System.out.println(" Tel. Proveedor:   " + p.getProveedor().getTelefonoProveedor());
            }

            System.out.println("-------------------------------------------------------------\n");
            contador++;
        }

        System.out.println("Total de productos: " + productos.size());
    }

    /**
     * Busca un producto por su codigo
     */
    public static Producto busquedaCode(String codigoProducto) {
        return buscarProducto(codigoProducto);
    }

    /**
     * Busca y muestra todos los productos que coincidan con el nombre ingresado
     */
    public static void busquedaNombre(String nombreProducto) {
        List<Producto> productos = Producto.getListaProductos();
        boolean encontrado = false;

        System.out.println("\n================================================================");
        System.out.println("           RESULTADOS DE BUSQUEDA POR NOMBRE                    ");
        System.out.println(" Busqueda: " + ajustarAncho(nombreProducto, 53) + "");
        System.out.println("================================================================\n");

        for (Producto p : productos) {
            if (p.getNombreProducto().toLowerCase().contains(nombreProducto.toLowerCase())) {
                System.out.println("-------------------------------------------------------------");
                System.out.println(" Codigo:    " + p.getCodigoProducto());
                System.out.println(" Nombre:    " + p.getNombreProducto());
                System.out.println(" Tipo:      " + p.getTipoProducto());
                System.out.println(" Precio:    $" + String.format("%.2f", p.getPrecioProducto()));
                System.out.println(" Stock:     " + p.getStockProducto() + " unidades");
                System.out.println("-------------------------------------------------------------\n");
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron productos con el nombre: " + nombreProducto);
        }
    }

    /**
     * Ajusta el ancho de texto para formato de tablas
     */
    private static String ajustarAncho(String texto, int ancho) {
        if (texto.length() > ancho) {
            return texto.substring(0, ancho);
        }
        return texto + " ".repeat(ancho - texto.length());
    }
}