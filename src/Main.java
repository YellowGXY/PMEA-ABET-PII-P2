import udla.jgjbmp.pmeaabet.*;
import udla.jgjbmp.pmeaabet.enums.Tipo;
import udla.jgjbmp.pmeaabet.enums.Caducidad;
import java.time.LocalDate;
import java.util.Scanner;

void main() {
    Scanner scanner = new Scanner(System.in);
    int opcionPrincipal;

    do {
        System.out.println("\n========================================");
        System.out.println("   SISTEMA DE GESTIÓN DE INVENTARIOS");
        System.out.println("========================================");
        System.out.println("1. Gestión de Productos");
        System.out.println("2. Gestión de Inventario");
        System.out.println("3. Administración");
        System.out.println("0. Salir");
        System.out.println("========================================");
        System.out.print("Seleccione una opción: ");
        opcionPrincipal = scanner.nextInt();
        scanner.nextLine();

        switch (opcionPrincipal) {
            case 1:
                menuGestionProductos(scanner);
                break;

            case 2:
                menuGestionInventario(scanner);
                break;

            case 3:
                menuAdministracion(scanner);
                break;

            case 0:
                System.out.println("\nSaliendo del sistema...");
                break;

            default:
                System.out.println("\nOpción inválida. Por favor intente nuevamente.");
        }

    } while (opcionPrincipal != 0);

    scanner.close();
}

// Menú de Gestión de Productos
void menuGestionProductos(Scanner scanner) {
    int opcion;

    do {
        System.out.println("\n========================================");
        System.out.println("      GESTIÓN DE PRODUCTOS");
        System.out.println("========================================");
        System.out.println("1. Agregar Producto");
        System.out.println("2. Eliminar Producto");
        System.out.println("3. Editar Producto");
        System.out.println("4. Ver Todos los Productos");
        System.out.println("0. Volver al Menú Principal");
        System.out.println("========================================");
        System.out.print("Seleccione una opción: ");
        opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                agregarProducto(scanner);
                break;

            case 2:
                eliminarProducto(scanner);
                break;

            case 3:
                editarProducto(scanner);
                break;

            case 4:
                verTodosLosProductos();
                break;

            case 0:
                System.out.println("\nVolviendo al menú principal...");
                break;

            default:
                System.out.println("\nOpción inválida. Por favor intente nuevamente.");
        }

    } while (opcion != 0);
}

// Menú de Gestión de Inventario - ACTUALIZADO CON TUS MÉTODOS
void menuGestionInventario(Scanner scanner) {
    int opcion;

    do {
        System.out.println("\n========================================");
        System.out.println("      GESTIÓN DE INVENTARIO");
        System.out.println("========================================");
        System.out.println("1. Ver Inventario Completo");
        System.out.println("2. Buscar Producto por Código");
        System.out.println("3. Buscar Producto por Nombre");
        System.out.println("4. Reabastecimiento de Producto");
        System.out.println("5. Registrar Venta");
        System.out.println("6. Generar Reporte de Inventario");
        System.out.println("7. Ver Inventario Detallado"); // Nuevo
        System.out.println("0. Volver al Menú Principal");
        System.out.println("========================================");
        System.out.print("Seleccione una opción: ");
        opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                Inventario.verInventarioCompleto();
                break;

            case 2:
                buscarProductoPorCodigo(scanner);
                break;

            case 3:
                buscarProductoPorNombre(scanner);
                break;

            case 4:
                reabastecerProducto(scanner);
                break;

            case 5:
                registrarVenta(scanner);
                break;

            case 6:
                Inventario.reporteInv();
                break;

            case 7:
                Inventario.verInventario();
                break;

            case 0:
                System.out.println("\nVolviendo al menú principal...");
                break;

            default:
                System.out.println("\nOpción inválida. Por favor intente nuevamente.");
        }

    } while (opcion != 0);
}

// Menú de Administración
void menuAdministracion(Scanner scanner) {
    int opcion;

    do {
        System.out.println("\n========================================");
        System.out.println("         ADMINISTRACIÓN");
        System.out.println("========================================");
        System.out.println("  Presupuesto actual: $" + String.format("%.2f", Administrador.getPresupuesto()));
        System.out.println("1. Vender Productos");
        System.out.println("0. Volver al Menú Principal");
        System.out.println("========================================");
        System.out.print("Seleccione una opción: ");
        opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                Administrador.procesarVenta(scanner);
                break;

            case 0:
                System.out.println("\nVolviendo al menú principal...");
                break;

            default:
                System.out.println("\nOpción inválida. Por favor intente nuevamente.");
        }

    } while (opcion != 0);
}

// Métodos de Gestión de Productos
void agregarProducto(Scanner scanner) {
    System.out.println("\n--- Agregar Nuevo Producto ---");

    System.out.print("Código del producto: ");
    String codigo = scanner.nextLine();

    System.out.print("Nombre del producto: ");
    String nombre = scanner.nextLine();

    double precio = 0;
    boolean precioValido = false;
    while (!precioValido) {
        try {
            System.out.print("Precio del producto (use punto como separador decimal, ej: 10.50): ");
            precio = scanner.nextDouble();
            if (precio <= 0) {
                System.out.println("Error: El precio debe ser mayor a 0");
            } else {
                precioValido = true;
            }
        } catch (Exception e) {
            System.out.println("Error: Ingrese un número válido (use punto como separador decimal)");
            scanner.nextLine(); // Limpiar buffer
        }
    }

    int stock = 0;
    boolean stockValido = false;
    while (!stockValido) {
        try {
            System.out.print("Stock inicial: ");
            stock = scanner.nextInt();
            if (stock < 0) {
                System.out.println("Error: El stock no puede ser negativo");
            } else {
                stockValido = true;
            }
        } catch (Exception e) {
            System.out.println("Error: Ingrese un número entero válido");
            scanner.nextLine(); // Limpiar buffer
        }
    }

    double costo = 0;
    boolean costoValido = false;
    while (!costoValido) {
        try {
            System.out.print("Costo del producto (use punto como separador decimal, ej: 8.50): ");
            costo = scanner.nextDouble();
            if (costo <= 0) {
                System.out.println("Error: El costo debe ser mayor a 0");
            } else {
                costoValido = true;
            }
        } catch (Exception e) {
            System.out.println("Error: Ingrese un número válido (use punto como separador decimal)");
            scanner.nextLine(); // Limpiar buffer
        }
    }
    scanner.nextLine(); // Limpiar buffer final

    // Mostrar tipos disponibles
    System.out.println("\nTipos de producto disponibles:");
    Tipo[] tipos = Tipo.values();
    for (int i = 0; i < tipos.length; i++) {
        System.out.println((i + 1) + ". " + tipos[i]);
    }
    System.out.print("Seleccione el tipo (1-" + tipos.length + "): ");
    int tipoSeleccionado = scanner.nextInt() - 1;
    Tipo tipo = tipos[tipoSeleccionado];

    // Mostrar caducidades disponibles
    System.out.println("\nTipo de caducidad:");
    Caducidad[] caducidades = Caducidad.values();
    for (int i = 0; i < caducidades.length; i++) {
        System.out.println((i + 1) + ". " + caducidades[i]);
    }
    System.out.print("Seleccione la caducidad (1-" + caducidades.length + "): ");
    int caducidadSeleccionada = scanner.nextInt() - 1;
    Caducidad caducidad = caducidades[caducidadSeleccionada];
    scanner.nextLine();

    System.out.print("Fecha de caducidad (YYYY-MM-DD): ");
    String fechaStr = scanner.nextLine();
    LocalDate fechaCaducidad = LocalDate.parse(fechaStr);

    // Gestión de proveedor
    Proveedor proveedorSeleccionado = seleccionarOCrearProveedor(scanner);

    if (proveedorSeleccionado == null) {
        System.out.println("Operación cancelada. No se agregó el producto.");
        return;
    }

    // Crear y agregar el producto
    Producto nuevoProducto = new Producto(nombre, precio, stock, codigo, tipo, caducidad, fechaCaducidad, costo, proveedorSeleccionado);
    Producto.addProductos(nuevoProducto);
}

void eliminarProducto(Scanner scanner) {
    System.out.println("\n--- Eliminar Producto ---");
    System.out.print("Ingrese el código del producto a eliminar: ");
    String codigo = scanner.nextLine();

    Producto.deleteProducto(codigo);
}

void editarProducto(Scanner scanner) {
    System.out.println("\n--- Editar Producto ---");
    System.out.print("Ingrese el código del producto a editar: ");
    String codigo = scanner.nextLine();

    System.out.println("\nIngrese los nuevos datos del producto:");

    System.out.print("Nuevo nombre del producto: ");
    String nombre = scanner.nextLine();

    double precio = 0;
    boolean precioValido = false;
    while (!precioValido) {
        try {
            System.out.print("Nuevo precio del producto (use punto como separador, ej: 10.50): ");
            precio = scanner.nextDouble();
            if (precio <= 0) {
                System.out.println("Error: El precio debe ser mayor a 0");
            } else {
                precioValido = true;
            }
        } catch (Exception e) {
            System.out.println("Error: Ingrese un número válido (use punto como separador decimal)");
            scanner.nextLine();
        }
    }

    int stock = 0;
    boolean stockValido = false;
    while (!stockValido) {
        try {
            System.out.print("Nuevo stock: ");
            stock = scanner.nextInt();
            if (stock < 0) {
                System.out.println("Error: El stock no puede ser negativo");
            } else {
                stockValido = true;
            }
        } catch (Exception e) {
            System.out.println("Error: Ingrese un número entero válido");
            scanner.nextLine();
        }
    }

    double costo = 0;
    boolean costoValido = false;
    while (!costoValido) {
        try {
            System.out.print("Nuevo costo del producto (use punto como separador, ej: 8.50): ");
            costo = scanner.nextDouble();
            if (costo <= 0) {
                System.out.println("Error: El costo debe ser mayor a 0");
            } else {
                costoValido = true;
            }
        } catch (Exception e) {
            System.out.println("Error: Ingrese un número válido (use punto como separador decimal)");
            scanner.nextLine();
        }
    }
    scanner.nextLine(); // Limpiar buffer

    // Mostrar tipos disponibles
    System.out.println("\nTipos de producto disponibles:");
    Tipo[] tipos = Tipo.values();
    for (int i = 0; i < tipos.length; i++) {
        System.out.println((i + 1) + ". " + tipos[i]);
    }
    System.out.print("Seleccione el tipo (1-" + tipos.length + "): ");
    int tipoSeleccionado = scanner.nextInt() - 1;
    Tipo tipo = tipos[tipoSeleccionado];

    // Mostrar caducidades disponibles
    System.out.println("\nTipo de caducidad:");
    Caducidad[] caducidades = Caducidad.values();
    for (int i = 0; i < caducidades.length; i++) {
        System.out.println((i + 1) + ". " + caducidades[i]);
    }
    System.out.print("Seleccione la caducidad (1-" + caducidades.length + "): ");
    int caducidadSeleccionada = scanner.nextInt() - 1;
    Caducidad caducidad = caducidades[caducidadSeleccionada];
    scanner.nextLine();

    System.out.print("Nueva fecha de caducidad (YYYY-MM-DD): ");
    String fechaStr = scanner.nextLine();
    LocalDate fechaCaducidad = LocalDate.parse(fechaStr);

    // Gestión de proveedor
    Proveedor proveedorSeleccionado = seleccionarOCrearProveedor(scanner);

    if (proveedorSeleccionado == null) {
        System.out.println("Operación cancelada. No se editó el producto.");
        return;
    }

    // Crear el producto con los nuevos datos y editar
    Producto productoEditado = new Producto(nombre, precio, stock, codigo, tipo, caducidad, fechaCaducidad, costo, proveedorSeleccionado);
    Producto.editarProducto(codigo, productoEditado);
}

void verTodosLosProductos() {
    System.out.println("\n--- LISTA DE PRODUCTOS ---");

    if (Producto.getListaProductos().isEmpty()) {
        System.out.println("No hay productos registrados.");
        return;
    }

    for (Producto p : Producto.getListaProductos()) {
        System.out.println("\nCódigo: " + p.getCodigoProducto());
        System.out.println("Nombre: " + p.getNombreProducto());
        System.out.println("Precio: $" + p.getPrecioProducto());
        System.out.println("Stock: " + p.getStockProducto());
        System.out.println("Tipo: " + p.getTipoProducto());
        System.out.println("Caducidad: " + p.getCaducidadProducto());
        System.out.println("Fecha de caducidad: " + p.getFechaCaducidad());
        System.out.println("Costo: $" + p.getCostoProducto());
        if (p.getProveedor() != null) {
            System.out.println("Proveedor: " + p.getProveedor().getNombreProveedor());
        } else {
            System.out.println("Proveedor: N/A");
        }
        System.out.println("---");
    }

    System.out.println("\nTotal de productos: " + Producto.getListaProductos().size());
}

// Métodos de Gestión de Proveedores
Proveedor seleccionarOCrearProveedor(Scanner scanner) {
    if (Proveedor.getListaProveedores().isEmpty()) {
        System.out.println("\nNo hay proveedores registrados.");
        System.out.println("1. Agregar nuevo proveedor");
        System.out.println("2. Cancelar");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion == 1) {
            return crearNuevoProveedor(scanner);
        } else {
            return null;
        }
    } else {
        System.out.println("\nProveedores disponibles:");

        int numero = 1;
        for (Proveedor p : Proveedor.getListaProveedores()) {
            System.out.println(numero + ". " + p.getNombreProveedor() + " (" + p.getCodigoProveedor() + ")");
            numero++;
        }
        System.out.println(numero + ". Agregar nuevo proveedor");
        System.out.println("0. Cancelar");

        System.out.print("\nSeleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion == 0) {
            return null;
        } else if (opcion == numero) {
            return crearNuevoProveedor(scanner);
        } else if (opcion > 0 && opcion < numero) {
            return Proveedor.getListaProveedores().get(opcion - 1);
        } else {
            System.out.println("Opción inválida.");
            return null;
        }
    }
}

Proveedor crearNuevoProveedor(Scanner scanner) {
    System.out.println("\n--- Agregar Nuevo Proveedor ---");

    System.out.print("Código del proveedor: ");
    String codigo = scanner.nextLine();

    System.out.print("Nombre del proveedor: ");
    String nombre = scanner.nextLine();

    System.out.print("Teléfono del proveedor: ");
    String telefono = scanner.nextLine();

    System.out.print("Dirección del proveedor: ");
    String direccion = scanner.nextLine();

    Proveedor nuevoProveedor = new Proveedor(codigo, nombre, telefono, direccion);
    Proveedor.addProveedor(nuevoProveedor);

    return nuevoProveedor;
}

// Métodos de Gestión de Inventario
/**
 * Método para buscar un producto por código
 */
void buscarProductoPorCodigo(Scanner scanner) {
    System.out.println("\n--- Buscar Producto por Código ---");
    System.out.print("Ingrese el código del producto: ");
    String codigo = scanner.nextLine();

    Inventario.buscarYMostrarPorCodigo(codigo);
}

/**
 * Método para buscar un producto por nombre
 */
void buscarProductoPorNombre(Scanner scanner) {
    System.out.println("\n--- Buscar Producto por Nombre ---");
    System.out.print("Ingrese el nombre del producto (o parte del nombre): ");
    String nombre = scanner.nextLine();

    Inventario.buscarYMostrarPorNombre(nombre);
}

/**
 * Método para reabastecer un producto
 * NUEVO - Integra tu método invReabastecimiento()
 */
void reabastecerProducto(Scanner scanner) {
    System.out.println("\n--- Reabastecimiento de Producto ---");

    // Primero mostrar el inventario resumido
    Inventario.verInventarioCompleto();

    System.out.print("\nIngrese el código del producto a reabastecer: ");
    String codigo = scanner.nextLine();

    System.out.print("Ingrese la cantidad a agregar al stock: ");
    int cantidad = scanner.nextInt();
    scanner.nextLine();

    // Llamar al método de tu clase Inventario
    Inventario.invReabastecimiento(codigo, cantidad);
}

/**
 * Método para registrar una venta
 * NUEVO - Integra tu método invVenta()
 */
void registrarVenta(Scanner scanner) {
    System.out.println("\n--- Registrar Venta ---");

    // Primero mostrar el inventario resumido
    Inventario.verInventarioCompleto();

    System.out.print("\nIngrese el código del producto a vender: ");
    String codigo = scanner.nextLine();

    // Mostrar información del producto antes de vender
    Producto producto = Inventario.buscarProducto(codigo);
    if (producto != null) {
        Inventario.mostrarInfoProducto(producto);

        System.out.print("\nIngrese la cantidad a vender: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        // Llamar al método de tu clase Inventario
        Inventario.invVenta(codigo, cantidad);
    } else {
        System.out.println("Producto no encontrado.");
    }
}