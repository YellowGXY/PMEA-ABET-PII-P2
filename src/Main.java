import udla.jgjbmp.pmeaabet.*;
import udla.jgjbmp.pmeaabet.enums.Tipo;
import udla.jgjbmp.pmeaabet.enums.Caducidad;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;
import java.util.Locale;

void main() {
    Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    
    System.out.println("========================================");
    System.out.println("   SISTEMA DE GESTION DE INVENTARIOS");
    System.out.println("========================================");
    System.out.print("Ingrese el presupuesto inicial: $");
    double presupuestoInicial = scanner.nextDouble();
    scanner.nextLine();
    Administrador.setPresupuesto(presupuestoInicial);
    System.out.println("Presupuesto registrado: $" + String.format("%.2f", presupuestoInicial));
    
    System.out.print("Ingrese la capacidad de almacenamiento (litros): ");
    double capacidadInicial = scanner.nextDouble();
    scanner.nextLine();
    Administrador.setCapacidadAlmacen(capacidadInicial);
    System.out.println("Capacidad de almacenamiento registrada: " + String.format("%.2f", capacidadInicial) + " litros");
    
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
        System.out.println("5. Gestion de Proveedores");
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

            case 5:
                menuGestionProveedores(scanner);
                break;

            case 0:
                System.out.println("\nVolviendo al menú principal...");
                break;

            default:
                System.out.println("\nOpción inválida. Por favor intente nuevamente.");
        }

    } while (opcion != 0);
}

// Menú de Gestión de Inventario
void menuGestionInventario(Scanner scanner) {
    int opcion;

    do {
        System.out.println("\n========================================");
        System.out.println("      GESTION DE INVENTARIO");
        System.out.println("========================================");
        System.out.println("1. Ver Inventario Completo");
        System.out.println("2. Buscar Producto por Codigo");
        System.out.println("3. Buscar Producto por Nombre");
        System.out.println("4. Generar Reporte de Inventario");
        System.out.println("5. Ver Inventario Detallado");
        System.out.println("0. Volver al Menu Principal");
        System.out.println("========================================");
        System.out.print("Seleccione una opcion: ");
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
                Inventario.reporteInv();
                break;

            case 5:
                Inventario.verInventario();
                break;

            case 0:
                System.out.println("\nVolviendo al menu principal...");
                break;

            default:
                System.out.println("\nOpcion invalida. Por favor intente nuevamente.");
        }

    } while (opcion != 0);
}

// Menú de Administración
void menuAdministracion(Scanner scanner) {
    int opcion;

    do {
        System.out.println("\n========================================");
        System.out.println("         ADMINISTRACION");
        System.out.println("========================================");
        System.out.println("  Presupuesto actual: $" + String.format("%.2f", Administrador.getPresupuesto()));
        System.out.println("  Almacenamiento: " + String.format("%.2f", Administrador.getEspacioOcupado()) + " / " + String.format("%.2f", Administrador.getCapacidadAlmacen()) + " litros");
        System.out.println("  Espacio disponible: " + String.format("%.2f", Administrador.getEspacioDisponible()) + " litros");
        System.out.println("1. Vender Productos");
        System.out.println("2. Compra a Proveedor");
        System.out.println("3. Gestion de Proveedores");
        System.out.println("4. Editar Capacidad de Almacenamiento");
        System.out.println("0. Volver al Menu Principal");
        System.out.println("========================================");
        System.out.print("Seleccione una opcion: ");
        opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                Administrador.procesarVenta(scanner);
                break;

            case 2:
                compraAProveedor(scanner);
                break;

            case 3:
                menuGestionProveedores(scanner);
                break;
                
            case 4:
                editarCapacidadAlmacenamiento(scanner);
                break;

            case 0:
                System.out.println("\nVolviendo al menu principal...");
                break;

            default:
                System.out.println("\nOpcion invalida. Por favor intente nuevamente.");
        }

    } while (opcion != 0);
}

void editarCapacidadAlmacenamiento(Scanner scanner) {
    System.out.println("\n--- Editar Capacidad de Almacenamiento ---");
    System.out.println("Capacidad actual: " + String.format("%.2f", Administrador.getCapacidadAlmacen()) + " litros");
    System.out.println("Espacio ocupado: " + String.format("%.2f", Administrador.getEspacioOcupado()) + " litros");
    System.out.println("Espacio disponible: " + String.format("%.2f", Administrador.getEspacioDisponible()) + " litros");
    
    System.out.print("\nIngrese la nueva capacidad de almacenamiento (litros, 0 para cancelar): ");
    double nuevaCapacidad = scanner.nextDouble();
    scanner.nextLine();
    
    if (nuevaCapacidad == 0) {
        System.out.println("Operacion cancelada.");
        return;
    }
    
    if (nuevaCapacidad < Administrador.getEspacioOcupado()) {
        System.out.println("\nError: La nueva capacidad no puede ser menor al espacio actualmente ocupado.");
        System.out.println("Espacio ocupado: " + String.format("%.2f", Administrador.getEspacioOcupado()) + " litros");
        return;
    }
    
    Administrador.setCapacidadAlmacen(nuevaCapacidad);
    System.out.println("\nCapacidad de almacenamiento actualizada exitosamente.");
    System.out.println("Nueva capacidad: " + String.format("%.2f", nuevaCapacidad) + " litros");
    System.out.println("Espacio disponible: " + String.format("%.2f", Administrador.getEspacioDisponible()) + " litros");
}

// Métodos de Gestión de Productos
void agregarProducto(Scanner scanner) {
    System.out.println("\n--- Agregar Nuevo Producto ---");

    System.out.print("Codigo del producto (0 para cancelar): ");
    String codigo = scanner.nextLine();
    
    if (codigo.equals("0")) {
        System.out.println("Operacion cancelada.");
        return;
    }

    System.out.print("Nombre del producto: ");
    String nombre = scanner.nextLine();

    System.out.print("Precio del producto (use punto como separador decimal, ej: 10.50): ");
    double precio = scanner.nextDouble();
    scanner.nextLine();

    System.out.print("Stock inicial: ");
    int stock = scanner.nextInt();
    scanner.nextLine();

    System.out.print("Costo del producto (use punto como separador decimal, ej: 8.50): ");
    double costo = scanner.nextDouble();
    scanner.nextLine(); 

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
    
    System.out.print("Espacio de almacenamiento por unidad (litros): ");
    double espacioAlmacenamiento = scanner.nextDouble();
    scanner.nextLine();

    // Gestión de proveedor
    Proveedor proveedorSeleccionado = seleccionarOCrearProveedor(scanner);

    if (proveedorSeleccionado == null) {
        System.out.println("Operación cancelada. No se agregó el producto.");
        return;
    }

    // Crear y agregar el producto
    Producto nuevoProducto = new Producto(nombre, precio, stock, codigo, tipo, caducidad, fechaCaducidad, costo, proveedorSeleccionado, espacioAlmacenamiento);
    Producto.addProductos(nuevoProducto);
    
    // Actualizar espacio ocupado
    Administrador.actualizarEspacioOcupado();
}

void eliminarProducto(Scanner scanner) {
    System.out.println("\n--- Eliminar Producto ---");
    System.out.print("Ingrese el codigo del producto a eliminar (0 para cancelar): ");
    String codigo = scanner.nextLine();
    
    if (codigo.equals("0")) {
        System.out.println("Operacion cancelada.");
        return;
    }

    Producto.deleteProducto(codigo);
    Administrador.actualizarEspacioOcupado();
}

void editarProducto(Scanner scanner) {
    System.out.println("\n--- Editar Producto ---");
    System.out.print("Ingrese el codigo del producto a editar (0 para cancelar): ");
    String codigo = scanner.nextLine();
    
    if (codigo.equals("0")) {
        System.out.println("Operacion cancelada.");
        return;
    }

    System.out.println("\nIngrese los nuevos datos del producto:");

    System.out.print("Nuevo nombre del producto: ");
    String nombre = scanner.nextLine();

    System.out.print("Nuevo precio del producto (use punto como separador, ej: 10.50): ");
    double precio = scanner.nextDouble();
    scanner.nextLine();

    System.out.print("Nuevo stock: ");
    int stock = scanner.nextInt();
    scanner.nextLine();

    System.out.print("Nuevo costo del producto (use punto como separador, ej: 8.50): ");
    double costo = scanner.nextDouble();
    scanner.nextLine();

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
    
    System.out.print("Nuevo espacio de almacenamiento por unidad (litros): ");
    double espacioAlmacenamiento = scanner.nextDouble();
    scanner.nextLine();

    // Gestión de proveedor
    Proveedor proveedorSeleccionado = seleccionarOCrearProveedor(scanner);

    if (proveedorSeleccionado == null) {
        System.out.println("Operación cancelada. No se editó el producto.");
        return;
    }

    // Crear el producto con los nuevos datos y editar
    Producto productoEditado = new Producto(nombre, precio, stock, codigo, tipo, caducidad, fechaCaducidad, costo, proveedorSeleccionado, espacioAlmacenamiento);
    Producto.editarProducto(codigo, productoEditado);
    
    // Actualizar espacio ocupado
    Administrador.actualizarEspacioOcupado();
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
        System.out.println("Espacio por unidad: " + String.format("%.2f", p.getEspacioAlmacenamiento()) + " litros");
        System.out.println("Espacio total ocupado: " + String.format("%.2f", p.getEspacioAlmacenamiento() * p.getStockProducto()) + " litros");
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
 * Metodo para buscar un producto por código
 */
void buscarProductoPorCodigo(Scanner scanner) {
    System.out.println("\n--- Buscar Producto por Código ---");
    System.out.print("Ingrese el código del producto: ");
    String codigo = scanner.nextLine();

    Inventario.buscarYMostrarPorCodigo(codigo);
}

/**
 * Metodo para buscar un producto por nombre
 */
void buscarProductoPorNombre(Scanner scanner) {
    System.out.println("\n--- Buscar Producto por Nombre ---");
    System.out.print("Ingrese el nombre del producto (o parte del nombre): ");
    String nombre = scanner.nextLine();

    Inventario.buscarYMostrarPorNombre(nombre);
}

/**
 * Metodo para comprar productos a un proveedor
 */
void compraAProveedor(Scanner scanner) {
    if (Proveedor.getListaProveedores().isEmpty()) {
        System.out.println("\nNo hay proveedores registrados.");
        return;
    }

    System.out.println("\n========================================");
    System.out.println("      COMPRA A PROVEEDOR");
    System.out.println("========================================");
    System.out.println("Presupuesto disponible: $" + String.format("%.2f", Administrador.getPresupuesto()));
    System.out.println("Espacio disponible: " + String.format("%.2f", Administrador.getEspacioDisponible()) + " litros");

    // Mostrar proveedores
    System.out.println("\nProveedores disponibles:");
    int numero = 1;
    for (Proveedor p : Proveedor.getListaProveedores()) {
        System.out.println(numero + ". " + p.getNombreProveedor() + " (" + p.getCodigoProveedor() + ")");
        numero++;
    }
    System.out.println("0. Cancelar");

    System.out.print("\nSeleccione un proveedor: ");
    int opcion = scanner.nextInt();
    scanner.nextLine();

    if (opcion == 0) {
        return;
    }

    if (opcion > 0 && opcion < numero) {
        Proveedor proveedorSeleccionado = Proveedor.getListaProveedores().get(opcion - 1);
        realizarCompra(scanner, proveedorSeleccionado);
    } else {
        System.out.println("Opcion invalida.");
    }
}

/**
 * Metodo para realizar la compra de productos a un proveedor
 */
void realizarCompra(Scanner scanner, Proveedor proveedor) {
    List<Producto> productosProveedor = Proveedor.obtenerProductosDeProveedor(proveedor);

    if (productosProveedor.isEmpty()) {
        System.out.println("\nEste proveedor no tiene productos registrados.");
        return;
    }

    System.out.println("\n--- Productos de " + proveedor.getNombreProveedor() + " ---");
    int numero = 1;
    for (Producto p : productosProveedor) {
        System.out.println(numero + ". " + p.getNombreProducto() + 
            " - Costo: $" + p.getCostoProducto() + 
            " - Stock actual: " + p.getStockProducto() +
            " - Espacio/unidad: " + String.format("%.2f", p.getEspacioAlmacenamiento()) + " L");
        numero++;
    }
    System.out.println("0. Cancelar");

    System.out.print("\nSeleccione un producto para comprar: ");
    int opcion = scanner.nextInt();
    scanner.nextLine();

    if (opcion == 0) {
        return;
    }

    if (opcion > 0 && opcion < numero) {
        Producto productoSeleccionado = productosProveedor.get(opcion - 1);
        
        System.out.print("Ingrese la cantidad a comprar: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor a 0.");
            return;
        }

        double costoTotal = productoSeleccionado.getCostoProducto() * cantidad;
        double espacioNecesario = productoSeleccionado.getEspacioAlmacenamiento() * cantidad;

        if (costoTotal > Administrador.getPresupuesto()) {
            System.out.println("\nPresupuesto insuficiente.");
            System.out.println("Costo total: $" + String.format("%.2f", costoTotal));
            System.out.println("Presupuesto disponible: $" + String.format("%.2f", Administrador.getPresupuesto()));
            return;
        }
        
        if (espacioNecesario > Administrador.getEspacioDisponible()) {
            System.out.println("\nEspacio de almacenamiento insuficiente.");
            System.out.println("Espacio necesario: " + String.format("%.2f", espacioNecesario) + " litros");
            System.out.println("Espacio disponible: " + String.format("%.2f", Administrador.getEspacioDisponible()) + " litros");
            return;
        }

        // Realizar compra
        int nuevoStock = productoSeleccionado.getStockProducto() + cantidad;
        productoSeleccionado.setStockProducto(nuevoStock);
        Administrador.setPresupuesto(Administrador.getPresupuesto() - costoTotal);
        Administrador.actualizarEspacioOcupado();

        System.out.println("\n=== COMPRA REALIZADA ===");
        System.out.println("Producto: " + productoSeleccionado.getNombreProducto());
        System.out.println("Cantidad comprada: " + cantidad);
        System.out.println("Costo total: $" + String.format("%.2f", costoTotal));
        System.out.println("Stock anterior: " + (nuevoStock - cantidad));
        System.out.println("Stock actual: " + nuevoStock);
        System.out.println("Espacio utilizado: " + String.format("%.2f", espacioNecesario) + " litros");
        System.out.println("Espacio disponible: " + String.format("%.2f", Administrador.getEspacioDisponible()) + " litros");
        System.out.println("Presupuesto restante: $" + String.format("%.2f", Administrador.getPresupuesto()));
        System.out.println("========================");
    } else {
        System.out.println("Opcion invalida.");
    }
}

// Menu de Gestion de Proveedores
void menuGestionProveedores(Scanner scanner) {
    int opcion;

    do {
        System.out.println("\n========================================");
        System.out.println("      GESTION DE PROVEEDORES");
        System.out.println("========================================");
        System.out.println("1. Agregar Proveedor");
        System.out.println("2. Editar Proveedor");
        System.out.println("3. Eliminar Proveedor");
        System.out.println("4. Ver Todos los Proveedores");
        System.out.println("0. Volver al Menu Anterior");
        System.out.println("========================================");
        System.out.print("Seleccione una opcion: ");
        opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                agregarNuevoProveedor(scanner);
                break;

            case 2:
                editarProveedorExistente(scanner);
                break;

            case 3:
                eliminarProveedorExistente(scanner);
                break;

            case 4:
                verTodosLosProveedores();
                break;

            case 0:
                System.out.println("\nVolviendo al menu anterior...");
                break;

            default:
                System.out.println("\nOpcion invalida. Por favor intente nuevamente.");
        }

    } while (opcion != 0);
}

void agregarNuevoProveedor(Scanner scanner) {
    System.out.println("\n--- Agregar Nuevo Proveedor ---");

    System.out.print("Codigo del proveedor (0 para cancelar): ");
    String codigo = scanner.nextLine();
    
    if (codigo.equals("0")) {
        System.out.println("Operacion cancelada.");
        return;
    }

    System.out.print("Nombre del proveedor: ");
    String nombre = scanner.nextLine();

    System.out.print("Telefono del proveedor: ");
    String telefono = scanner.nextLine();

    System.out.print("Direccion del proveedor: ");
    String direccion = scanner.nextLine();

    Proveedor nuevoProveedor = new Proveedor(codigo, nombre, telefono, direccion);
    Proveedor.addProveedor(nuevoProveedor);
}

void editarProveedorExistente(Scanner scanner) {
    System.out.println("\n--- Editar Proveedor ---");
    
    if (Proveedor.getListaProveedores().isEmpty()) {
        System.out.println("No hay proveedores registrados.");
        return;
    }

    System.out.print("Ingrese el codigo del proveedor a editar (0 para cancelar): ");
    String codigo = scanner.nextLine();
    
    if (codigo.equals("0")) {
        System.out.println("Operacion cancelada.");
        return;
    }

    Proveedor proveedorExistente = Proveedor.buscarPorCodigo(codigo);
    if (proveedorExistente == null) {
        System.out.println("No se encontro un proveedor con ese codigo.");
        return;
    }

    System.out.println("\nProveedor actual:");
    System.out.println("Nombre: " + proveedorExistente.getNombreProveedor());
    System.out.println("Telefono: " + proveedorExistente.getTelefonoProveedor());
    System.out.println("Direccion: " + proveedorExistente.getDireccionProveedor());

    System.out.println("\nIngrese los nuevos datos del proveedor:");

    System.out.print("Nuevo nombre del proveedor: ");
    String nombre = scanner.nextLine();

    System.out.print("Nuevo telefono del proveedor: ");
    String telefono = scanner.nextLine();

    System.out.print("Nueva direccion del proveedor: ");
    String direccion = scanner.nextLine();

    Proveedor proveedorEditado = new Proveedor(codigo, nombre, telefono, direccion);
    Proveedor.editarProveedor(codigo, proveedorEditado);
}

void eliminarProveedorExistente(Scanner scanner) {
    System.out.println("\n--- Eliminar Proveedor ---");
    
    if (Proveedor.getListaProveedores().isEmpty()) {
        System.out.println("No hay proveedores registrados.");
        return;
    }

    System.out.print("Ingrese el codigo del proveedor a eliminar (0 para cancelar): ");
    String codigo = scanner.nextLine();
    
    if (codigo.equals("0")) {
        System.out.println("Operacion cancelada.");
        return;
    }

    Proveedor proveedorExistente = Proveedor.buscarPorCodigo(codigo);
    if (proveedorExistente == null) {
        System.out.println("No se encontro un proveedor con ese codigo.");
        return;
    }

    List<Producto> productosProveedor = Proveedor.obtenerProductosDeProveedor(proveedorExistente);
    if (!productosProveedor.isEmpty()) {
        System.out.println("\nAdvertencia: Este proveedor tiene " + productosProveedor.size() + " producto(s) asociado(s).");
        System.out.print("Esta seguro que desea eliminar el proveedor? (S/N): ");
        String confirmacion = scanner.nextLine().trim().toUpperCase();
        
        if (!confirmacion.equals("S")) {
            System.out.println("Operacion cancelada.");
            return;
        }
    }

    Proveedor.deleteProveedor(codigo);
}

void verTodosLosProveedores() {
    System.out.println("\n--- LISTA DE PROVEEDORES ---");

    if (Proveedor.getListaProveedores().isEmpty()) {
        System.out.println("No hay proveedores registrados.");
        return;
    }

    for (Proveedor p : Proveedor.getListaProveedores()) {
        System.out.println("\nCodigo: " + p.getCodigoProveedor());
        System.out.println("Nombre: " + p.getNombreProveedor());
        System.out.println("Telefono: " + p.getTelefonoProveedor());
        System.out.println("Direccion: " + p.getDireccionProveedor());
        
        List<Producto> productosProveedor = Proveedor.obtenerProductosDeProveedor(p);
        System.out.println("Productos asociados: " + productosProveedor.size());
        System.out.println("---");
    }

    System.out.println("\nTotal de proveedores: " + Proveedor.getListaProveedores().size());
}