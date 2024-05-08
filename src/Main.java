import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
/*Desarrolla un programa que lea datos de 2 arrays, uno con nombres de productos y el otro
con sus precios. Almacenar dicho contenido en un fichero, donde cada línea constará de
nombre de producto y precio.
*/
        String ruta = "C:\\Users\\a23andreacc\\Desktop\\Andrea\\Programacion\\Tema 11\\Boletin_Ficheros_2_3\\productos.txt";
        String[] nombreproducto = {"Fresa", "Uva", "Sandia", "Manzana"};
        double[] precioProducto = {3.2, 2.3, 1.4, 2.5};

        verProductosArray(nombreproducto, precioProducto);
        crearFichero(ruta,nombreproducto,precioProducto);
        leerFichero(ruta);

        /*A partir del ejercicio anterior, implementa un método que solicite un nombre de producto al
        usuario y lo elimine del listado (del fichero). Deberá mostrar también su información por pantalla (nombre y precio)*/



        eliminarProducto( ruta); // Eliminar el producto del archivo y mostrar su información
    }
    public static void crearFichero(String ruta,String[] nombreproducto, double[] precioProducto){
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                FileWriter fw = new FileWriter(archivo);
                for (int i = 0; i < nombreproducto.length; i++) {
                    fw.write(nombreproducto[i] + " " + precioProducto[i] + " €");
                    fw.write('\n');
                }
                fw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void leerFichero(String ruta){
        File archivo = new File(ruta);
        if (archivo.exists()) {
            FileReader fr = null;
            try {
                fr = new FileReader(archivo);// Crear FileReader para leer el archivo
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            BufferedReader br = new BufferedReader(fr);// Crear BufferedReader para leer el archivo línea por línea
            String linea;
            try {
                System.out.println("-------- Productos en fichero ---------");
                System.out.println(" ");
                while ((linea = br.readLine()) != null) { // Leer y mostrar cada línea del archivo hasta que no haya más líneas
                    System.out.println(linea);// Mostrar la línea en la consola
                }
                br.close();// Cerrar BufferedReader para liberar recursos
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void verProductosArray(String[] nombres, double[] precios) {
        String linea;
        System.out.println("---- Productos en array ----");
        System.out.println(" ");
        for (int i = 0; i < nombres.length; i++) {
            linea = nombres[i] + " " + precios[i] + " €";
            System.out.println(linea);
        }
        System.out.println(" ");
    }
    public static void eliminarProducto (String ruta){
        // Solicitar al usuario el nombre del producto a eliminar
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el nombre del producto a eliminar: ");
        String productoAEliminar = sc.nextLine();
        File archivo = new File(ruta);
        List<String> lineas = new ArrayList<>();
         try
          {
           BufferedReader reader = new BufferedReader(new FileReader(archivo));
           String linea;
           // Leer el contenido del archivo y almacenarlo en una lista
           while ((linea = reader.readLine()) != null) {
             lineas.add(linea);
           }
            } catch (IOException e) {
                throw new RuntimeException("Error al leer el archivo", e);
            }

            // Identificar y eliminar el producto de la lista
            for (int i = 0; i < lineas.size(); i++) {
                if (lineas.get(i).startsWith(productoAEliminar)) {
                    System.out.println("Producto eliminado del archivo: " + lineas.get(i).toString());
                    lineas.remove(i);
                    break; // Detener la iteración una vez que se ha eliminado el producto
                }
            }

            // Escribir el contenido actualizado de vuelta al archivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
                for (String linea : lineas) {
                    writer.write(linea);
                    writer.newLine();
                }
            } catch (IOException e) {
                throw new RuntimeException("Error al escribir en el archivo", e);
            }
        }
}
