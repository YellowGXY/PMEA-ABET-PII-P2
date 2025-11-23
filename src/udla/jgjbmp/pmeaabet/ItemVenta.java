package udla.jgjbmp.pmeaabet;

/**
 * La clase es necesario ya que para crear la factura es necesario tener una clase que registre todos los productos
 * vendidos y su informacion correspondiente
 */
public class ItemVenta {
    private Producto producto;
    private int cantidad;

    /**
     * Constructor de ItemVenta
     */
    public ItemVenta(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el producto del item
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Obtiene la cantidad del item
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Calcula el subtotal del item (precio por cantidad)
     */
    public double getSubtotal() {
        return producto.getPrecioProducto() * cantidad;
    }
}
