package udla.jgjbmp.pmeaabet;

import udla.jgjbmp.pmeaabet.enums.Caducidad;
import udla.jgjbmp.pmeaabet.enums.Tipo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Producto {
    // Atributos
    private String nombreProducto;
    private double precioProducto;
    private int stockProducto;
    private String codigoProducto;
    private Tipo tipoProducto;
    private Caducidad caducidadProducto;
    private LocalDate fechaCaducidad;
    private double costoProducto;
    private Proveedor proveedor;
    private double espacioAlmacenamiento;
    
    // Lista estática para almacenar todos los productos
    private static final List<Producto> listaProductos = new ArrayList<>();
    
    // Constructor vacío
    public Producto() {
    }
    
    // Constructor con parámetros
    public Producto(String nombreProducto, double precioProducto, int stockProducto, 
                   String codigoProducto, Tipo tipoProducto, Caducidad caducidadProducto, 
                   LocalDate fechaCaducidad, double costoProducto, Proveedor proveedor, double espacioAlmacenamiento) {
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.stockProducto = stockProducto;
        this.codigoProducto = codigoProducto;
        this.tipoProducto = tipoProducto;
        this.caducidadProducto = caducidadProducto;
        this.fechaCaducidad = fechaCaducidad;
        this.costoProducto = costoProducto;
        this.proveedor = proveedor;
        this.espacioAlmacenamiento = espacioAlmacenamiento;
    }
    
    // Getters y Setters
    public String getNombreProducto() {
        return nombreProducto;
    }
    
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    
    public double getPrecioProducto() {
        return precioProducto;
    }
    
    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }
    
    public int getStockProducto() {
        return stockProducto;
    }
    
    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }
    
    public String getCodigoProducto() {
        return codigoProducto;
    }
    
    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }
    
    public Tipo getTipoProducto() {
        return tipoProducto;
    }
    
    public void setTipoProducto(Tipo tipoProducto) {
        this.tipoProducto = tipoProducto;
    }
    
    public Caducidad getCaducidadProducto() {
        return caducidadProducto;
    }
    
    public void setCaducidadProducto(Caducidad caducidadProducto) {
        this.caducidadProducto = caducidadProducto;
    }
    
    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }
    
    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
    
    public double getCostoProducto() {
        return costoProducto;
    }
    
    public void setCostoProducto(double costoProducto) {
        this.costoProducto = costoProducto;
    }
    
    public Proveedor getProveedor() {
        return proveedor;
    }
    
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
    public double getEspacioAlmacenamiento() {
        return espacioAlmacenamiento;
    }
    
    public void setEspacioAlmacenamiento(double espacioAlmacenamiento) {
        this.espacioAlmacenamiento = espacioAlmacenamiento;
    }
    
    public static List<Producto> getListaProductos() {
        return listaProductos;
    }
    
    // Métodos de gestión
    
    /**
     * Metodo para agregar un nuevo producto a la lista de productos
     */
    public static void addProductos(Producto producto) {
        // Verificar si el producto es nulo
        if (producto == null) {
            System.out.println("Error: El producto no puede ser nulo");
            return;
        }
        
        // Verificar si ya existe un producto con el mismo código
        for (Producto p : listaProductos) {
            if (p.getCodigoProducto().equals(producto.getCodigoProducto())) {
                System.out.println("Error: Ya existe un producto con ese código");
                return;
            }
        }
        
        listaProductos.add(producto);
        System.out.println("Producto agregado: " + producto.getNombreProducto());
    }
    
    /**
     * Metodo para eliminar un producto de la lista por su código
     */
    public static void deleteProducto(String codigoProducto) {
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getCodigoProducto().equals(codigoProducto)) {
                Producto productoEliminado = listaProductos.remove(i);
                System.out.println("Producto eliminado: " + productoEliminado.getNombreProducto());
                return;
            }
        }
        System.out.println("Error: No se encontró un producto con el código " + codigoProducto);
    }
    
    /**
     * Metodo para editar un producto existente
     */
    public static void editarProducto(String codigoProducto, Producto nuevoProducto) {
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getCodigoProducto().equals(codigoProducto)) {
                nuevoProducto.setCodigoProducto(codigoProducto);
                listaProductos.set(i, nuevoProducto);
                System.out.println("Producto actualizado: " + nuevoProducto.getNombreProducto());
                return;
            }
        }
        System.out.println("Error: No se encontró un producto con el código " + codigoProducto);
    }
}
