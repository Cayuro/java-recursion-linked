public class TallerListasRecursividad {

    public static void main(String[] args) {
        // Punto de entrada para probar, de forma simple, las estructuras y algoritmos.
        // Pruebas rapidas del Bloque 1 (simple y doble)
        ListaSimpleBasica ls = new ListaSimpleBasica();
        ls.insertarInicio(10);
        ls.insertarFinal(20);
        ls.insertarFinal(5);
        System.out.println("Lista simple:");
        ls.mostrar();
        System.out.println("Total nodos: " + ls.contarNodos());
        System.out.println("Existe 20: " + ls.buscarValor(20));
        System.out.println("Mayor: " + ls.obtenerMayor());
        ls.eliminarPrimero();
        ls.mostrar();

        ListaDobleBasica ld = new ListaDobleBasica();
        ld.insertarInicio(3);
        ld.insertarFinal(7);
        ld.insertarFinal(9);
        System.out.println("Doble adelante:");
        ld.mostrarAdelante();
        System.out.println("Doble atras:");
        ld.mostrarAtras();
        ld.modificarDatos(2);
        ld.mostrarAdelante();

        // Prueba recursividad
        System.out.println("Factorial 5: " + RecursividadBasica.factorial(5));
        System.out.println("Fibonacci 7: " + RecursividadBasica.fibonacci(7));
    }
}

// =========================
// BLOQUE 1 - LISTA SIMPLE
// =========================



class NodoSimple {
    // Valor almacenado en el nodo.
    int dato;
    // Referencia al siguiente nodo en la lista.
    NodoSimple sig;

    NodoSimple(int dato) {
        // Inicializa el nodo con su dato y sin enlace siguiente.
        this.dato = dato;
        this.sig = null;
    }
}

class ListaSimpleBasica {
    // Primer nodo de la lista (si es null, la lista esta vacia).
    NodoSimple cabeza;

    // 1) Insertar Inicio (Simple)
    public void insertarInicio(int valor) {
        NodoSimple nuevoNodo = new NodoSimple(valor);
        nuevoNodo.sig = cabeza;
        cabeza = nuevoNodo;
    }

    // 2) Insertar Final (Simple)
    public void insertarFinal(int valor) {
        NodoSimple nuevoNodo = new NodoSimple(valor);

        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoSimple aux = cabeza;
            while (aux.sig != null) {
                aux = aux.sig;
            }
            aux.sig = nuevoNodo;
        }
    }

    // 3) Mostrar (Simple)
    public void mostrar() {
        NodoSimple puntero = cabeza;

        if (puntero == null) {
            System.out.println("(vacia)");
        } else {
            while (puntero != null) {
                System.out.print(puntero.dato + " ");
                // Aqui muevo el puntero al siguiente para no perder la lista
                puntero = puntero.sig;
            }
            System.out.println();
        }
    }

    // 4) Contar Nodos
    public int contarNodos() {
        int contador = 0;
        NodoSimple temporal = cabeza;

        while (temporal != null) {
            contador = contador + 1;
            temporal = temporal.sig;
        }

        return contador;
    }

    // 5) Buscar Valor
    public boolean buscarValor(int valor) {
        boolean encontrado = false;
        NodoSimple nodoActual = cabeza;

        while (nodoActual != null) {
            if (nodoActual.dato == valor) {
                encontrado = true;
                // Error comun de principiante: encontrar y aun asi seguir recorriendo
                // No afecta el resultado final, solo hace trabajo de mas.
            }
            nodoActual = nodoActual.sig;
        }

        return encontrado;
    }

    // 6) Eliminar Primero
    public void eliminarPrimero() {
        if (cabeza == null) {
            return;
        } else {
            cabeza = cabeza.sig;
        }
    }

    // 7) Vaciar Lista
    public void vaciarLista() {
        cabeza = null;
    }

    // 8) Obtener Mayor
    public int obtenerMayor() {
        if (cabeza == null) {
            return -1;
        }

        int mayor = cabeza.dato;
        NodoSimple n = cabeza.sig;

        while (n != null) {
            if (n.dato > mayor) {
                mayor = n.dato;
            }
            n = n.sig;
        }

        return mayor;
    }
}

// =========================
// BLOQUE 1 - LISTA DOBLE
// =========================

class NodoDoble {
    // Dato almacenado.
    int dato;
    // Enlace al siguiente nodo.
    NodoDoble sig;
    // Enlace al nodo anterior.
    NodoDoble ant;

    NodoDoble(int dato) {
        // Crea un nodo aislado (sin vecinos).
        this.dato = dato;
        this.sig = null;
        this.ant = null;
    }
}

class ListaDobleBasica {
    // Inicio de la lista doble.
    NodoDoble cabeza;

    // 9) Insertar Inicio (Doble)
    public void insertarInicio(int valor) {
        NodoDoble nuevoNodo = new NodoDoble(valor);

        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            nuevoNodo.sig = cabeza;
            cabeza.ant = nuevoNodo;
            cabeza = nuevoNodo;
        }
    }

    // 10) Insertar Final (Doble)
    public void insertarFinal(int valor) {
        NodoDoble nuevoNodo = new NodoDoble(valor);

        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoDoble aux = cabeza;
            while (aux.sig != null) {
                aux = aux.sig;
            }
            aux.sig = nuevoNodo;
            nuevoNodo.ant = aux;
        }
    }

    // 11) Mostrar Adelante
    public void mostrarAdelante() {
        NodoDoble puntero = cabeza;

        while (puntero != null) {
            System.out.print(puntero.dato + " ");
            puntero = puntero.sig;
        }
        System.out.println();
    }

    // 12) Mostrar Atras
    public void mostrarAtras() {
        if (cabeza == null) {
            System.out.println("(vacia)");
            return;
        }

        NodoDoble finalLista = cabeza;
        while (finalLista.sig != null) {
            finalLista = finalLista.sig;
        }

        while (finalLista != null) {
            System.out.print(finalLista.dato + " ");
            finalLista = finalLista.ant;
        }
        System.out.println();
    }

    // 13) Eliminar Ultimo
    public void eliminarUltimo() {
        if (cabeza == null) {
            return;
        }

        if (cabeza.sig == null) {
            cabeza = null;
        } else {
            NodoDoble aux = cabeza;
            while (aux.sig != null) {
                aux = aux.sig;
            }

            NodoDoble penultimo = aux.ant;
            penultimo.sig = null;
            aux.ant = null;
        }
    }

    // 14) Modificar Datos
    public void modificarDatos(int valorSuma) {
        NodoDoble nodoActual = cabeza;

        while (nodoActual != null) {
            nodoActual.dato = nodoActual.dato + valorSuma;
            nodoActual = nodoActual.sig;
        }
    }

    // 15) Verificar Estado
    public boolean estaVacia() {
        if (cabeza == null) {
            return true;
        } else {
            return false;
        }
    }
}

// =========================
// BLOQUE 2 - CASOS REALES
// =========================

class NodoTexto {
    // Texto almacenado (URL, nombre de archivo, accion, etc.).
    String dato;
    // Siguiente nodo.
    NodoTexto sig;
    // Nodo anterior (se usa en estructuras dobles).
    NodoTexto ant;

    NodoTexto(String dato) {
        // Inicializa el nodo de texto sin conexiones.
        this.dato = dato;
        this.sig = null;
        this.ant = null;
    }
}

// 1) Historial de Navegacion con limite
class HistorialNavegacion {
    // Sitio mas reciente visitado.
    NodoTexto cabeza;
    // Maximo de paginas que se guardan en historial.
    int limite;

    HistorialNavegacion(int limite) {
        this.limite = limite;
        this.cabeza = null;
    }

    public void visitar(String url) {
        // Inserta al inicio porque representa la visita mas reciente.
        NodoTexto nuevoNodo = new NodoTexto(url);
        nuevoNodo.sig = cabeza;
        if (cabeza != null) {
            cabeza.ant = nuevoNodo;
        }
        cabeza = nuevoNodo;

        // Si sobrepasa el limite, corto el ultimo
        int total = contar();
        if (total > limite) {
            eliminarUltimo();
        }
    }

    private int contar() {
        // Recorre todo el historial para contar elementos.
        int c = 0;
        NodoTexto aux = cabeza;
        while (aux != null) {
            c = c + 1;
            aux = aux.sig;
        }
        return c;
    }

    private void eliminarUltimo() {
        // Elimina la visita mas antigua (cola de la lista).
        if (cabeza == null) {
            return;
        }

        if (cabeza.sig == null) {
            cabeza = null;
            return;
        }

        NodoTexto aux = cabeza;
        while (aux.sig != null) {
            aux = aux.sig;
        }

        NodoTexto penultimo = aux.ant;
        penultimo.sig = null;
        aux.ant = null;
    }
}

// 2) Cola de impresion con prioridad
class ColaImpresionPrioridad {
    // Frente de la cola de impresion.
    NodoTexto frente;

    public void insertarDocumento(String nombre, boolean urgente) {
        // Urgente entra al frente; normal se encola al final.
        NodoTexto nuevoNodo = new NodoTexto(nombre);

        if (frente == null) {
            frente = nuevoNodo;
        } else {
            if (urgente) {
                nuevoNodo.sig = frente;
                frente = nuevoNodo;
            } else {
                NodoTexto aux = frente;
                while (aux.sig != null) {
                    aux = aux.sig;
                }
                aux.sig = nuevoNodo;
            }
        }
    }

    public String imprimirSiguiente() {
        // Atiende y retira el documento del frente.
        if (frente == null) {
            return null;
        }

        String doc = frente.dato;
        frente = frente.sig;
        return doc;
    }
}

// 3) Sistema de Undo
class SistemaUndo {
    // Tope de la pila de acciones.
    NodoTexto tope;

    public void guardarAccion(String accion) {
        // Push: agrega accion al inicio.
        NodoTexto nuevoNodo = new NodoTexto(accion);
        nuevoNodo.sig = tope;
        tope = nuevoNodo;
    }

    public String undo() {
        // Pop: devuelve la ultima accion y la retira.
        if (tope == null) {
            return null;
        }

        String accion = tope.dato;
        tope = tope.sig;
        return accion;
    }
}

// 4) Detector de correos duplicados
class DetectorCorreosDuplicados {
    // Lista de IDs de correos ya vistos.
    NodoTexto cabeza;

    public boolean insertarSiNoExiste(String idCorreo) {
        // Busca si ya existe para evitar duplicados.
        NodoTexto aux = cabeza;

        while (aux != null) {
            if (aux.dato.equals(idCorreo)) {
                return false;
            }
            aux = aux.sig;
        }

        NodoTexto nuevoNodo = new NodoTexto(idCorreo);
        nuevoNodo.sig = cabeza;
        cabeza = nuevoNodo;
        return true;
    }
}

// 5) Inversion de playlist (reverse)
class PlaylistSimple {
    // Primera cancion de la playlist.
    NodoTexto cabeza;

    public void agregarCancion(String nombre) {
        // Inserta al final para conservar orden de reproduccion.
        NodoTexto nuevoNodo = new NodoTexto(nombre);

        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoTexto aux = cabeza;
            while (aux.sig != null) {
                aux = aux.sig;
            }
            aux.sig = nuevoNodo;
        }
    }

    public void invertir() {
        // Reverse iterativo de lista simple con tres punteros.
        NodoTexto anterior = null;
        NodoTexto actual = cabeza;
        NodoTexto siguiente;

        while (actual != null) {
            siguiente = actual.sig;
            actual.sig = anterior;
            anterior = actual;
            actual = siguiente;
        }

        cabeza = anterior;
    }
}

// 6) Navegador de galeria de fotos
class GaleriaFotos {
    // Inicio de la lista de fotos.
    NodoTexto inicio;
    // Puntero a la foto actualmente mostrada.
    NodoTexto fotoActual;

    public void agregarFoto(String nombreFoto) {
        // Agrega la foto al final y enlaza en doble sentido.
        NodoTexto nuevoNodo = new NodoTexto(nombreFoto);

        if (inicio == null) {
            inicio = nuevoNodo;
            fotoActual = nuevoNodo;
        } else {
            NodoTexto aux = inicio;
            while (aux.sig != null) {
                aux = aux.sig;
            }
            aux.sig = nuevoNodo;
            nuevoNodo.ant = aux;
        }
    }

    public String siguiente() {
        // Avanza si hay siguiente, si no, se queda en la actual.
        if (fotoActual == null) {
            return null;
        }

        if (fotoActual.sig != null) {
            fotoActual = fotoActual.sig;
        }

        return fotoActual.dato;
    }

    public String anterior() {
        // Retrocede si hay anterior, si no, se queda en la actual.
        if (fotoActual == null) {
            return null;
        }

        if (fotoActual.ant != null) {
            fotoActual = fotoActual.ant;
        }

        return fotoActual.dato;
    }
}

// 7) Reproductor de musica circular
class ReproductorCircular {
    // Cabeza de lista circular de canciones.
    NodoTexto cabeza;

    public void agregar(String cancion) {
        // Inserta al final y mantiene la circularidad al apuntar a cabeza.
        NodoTexto nuevoNodo = new NodoTexto(cancion);

        if (cabeza == null) {
            cabeza = nuevoNodo;
            nuevoNodo.sig = cabeza;
        } else {
            NodoTexto aux = cabeza;
            while (aux.sig != cabeza) {
                aux = aux.sig;
            }
            aux.sig = nuevoNodo;
            nuevoNodo.sig = cabeza;
        }
    }

    public String siguienteCancion(String actual) {
        // Busca la cancion actual y retorna la siguiente en el ciclo.
        if (cabeza == null) {
            return null;
        }

        NodoTexto aux = cabeza;
        do {
            if (aux.dato.equals(actual)) {
                return aux.sig.dato;
            }
            aux = aux.sig;
        } while (aux != cabeza);

        return cabeza.dato;
    }
}

// 8) Editor de texto (cursor)
class EditorTextoCursor {
    // Representa el texto como lista de caracteres.
    NodoChar cabeza;

    public void insertarDespuesDePos(int pos, char letra) {
        // Inserta despues de una posicion; si no existe, agrega al final.
        NodoChar nuevoNodo = new NodoChar(letra);

        if (pos < 0 || cabeza == null) {
            nuevoNodo.sig = cabeza;
            cabeza = nuevoNodo;
            return;
        }

        int i = 0;
        NodoChar aux = cabeza;

        while (aux != null && i < pos) {
            aux = aux.sig;
            i = i + 1;
        }

        if (aux == null) {
            NodoChar ult = cabeza;
            while (ult.sig != null) {
                ult = ult.sig;
            }
            ult.sig = nuevoNodo;
        } else {
            nuevoNodo.sig = aux.sig;
            aux.sig = nuevoNodo;
        }
    }
}

class NodoChar {
    // Caracter almacenado.
    char dato;
    // Siguiente caracter.
    NodoChar sig;

    NodoChar(char dato) {
        // Crea un nodo de caracter suelto.
        this.dato = dato;
        this.sig = null;
    }
}

// 9) Buscador de punto medio (tortuga y liebre)
class BuscadorPuntoMedio {
    public NodoSimple obtenerMedio(NodoSimple cabeza) {
        // Estrategia tortuga-liebre: uno avanza 1 y otro 2.
        if (cabeza == null) {
            return null;
        }

        NodoSimple tortuga = cabeza;
        NodoSimple liebre = cabeza;

        while (liebre != null && liebre.sig != null) {
            tortuga = tortuga.sig;
            liebre = liebre.sig.sig;
        }

        return tortuga;
    }
}

// 10) Gestion de turnos bancarios
class TurnosBancarios {
    // Frente de la fila de clientes.
    NodoTexto frente;

    public void agregarCliente(String nombre) {
        // Encola cliente al final de la fila.
        NodoTexto nuevoNodo = new NodoTexto(nombre);

        if (frente == null) {
            frente = nuevoNodo;
        } else {
            NodoTexto aux = frente;
            while (aux.sig != null) {
                aux = aux.sig;
            }
            aux.sig = nuevoNodo;
            nuevoNodo.ant = aux;
        }
    }

    public boolean retirarCliente(String nombre) {
        // Elimina por nombre ajustando enlaces anterior/siguiente.
        NodoTexto aux = frente;

        while (aux != null) {
            if (aux.dato.equals(nombre)) {
                if (aux.ant == null) {
                    // Es el primero
                    frente = aux.sig;
                    if (frente != null) {
                        frente.ant = null;
                    }
                } else {
                    aux.ant.sig = aux.sig;
                    if (aux.sig != null) {
                        aux.sig.ant = aux.ant;
                    }
                }
                return true;
            }
            aux = aux.sig;
        }

        return false;
    }
}

// =========================
// BLOQUE 3 - RECURSIVIDAD
// =========================

class RecursividadBasica {

    // 1) Factorial
    public static long factorial(int n) {
        // Caso base: 0! y 1! valen 1.
        if (n <= 1) {
            return 1;
        }
        // Caso recursivo: n! = n * (n-1)!
        return n * factorial(n - 1);
    }

    // 2) Suma de digitos
    public static int sumaDigitos(int n) {
        // Trabaja con valor absoluto para soportar negativos.
        n = Math.abs(n);
        if (n < 10) {
            return n;
        }
        return (n % 10) + sumaDigitos(n / 10);
    }

    // 3) Potencia a^b
    public static long potencia(int a, int b) {
        // Caso base: todo numero elevado a 0 es 1.
        if (b == 0) {
            return 1;
        }
        return a * potencia(a, b - 1);
    }

    // 4) Invertir cadena
    public static String invertirCadena(String txt) {
        // Caso base: null, vacia o un caracter ya estan invertidos.
        if (txt == null || txt.length() <= 1) {
            return txt;
        }
        return invertirCadena(txt.substring(1)) + txt.charAt(0);
    }

    // 5) Fibonacci n-esimo
    public static int fibonacci(int n) {
        // Casos base de la serie.
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // 6) Conteo regresivo
    public static void conteoRegresivo(int n) {
        // Se detiene al pasar por debajo de 0.
        if (n < 0) {
            return;
        }
        System.out.print(n + " ");
        conteoRegresivo(n - 1);
    }

    // 7) Suma de arreglo
    public static int sumaArreglo(int[] vec, int i) {
        // Si no hay arreglo o se llego al final, suma 0.
        if (vec == null || i >= vec.length) {
            return 0;
        }
        return vec[i] + sumaArreglo(vec, i + 1);
    }

    // 8) Palindromo
    public static boolean esPalindromo(String palabra) {
        // Se compara en minuscula para ignorar mayusculas.
        if (palabra == null) {
            return false;
        }
        return revisarPalabra(palabra.toLowerCase(), 0, palabra.length() - 1);
    }

    private static boolean revisarPalabra(String txt, int ini, int fin) {
        // Si los indices se cruzan, todo coincidio.
        if (ini >= fin) {
            return true;
        }

        if (txt.charAt(ini) != txt.charAt(fin)) {
            return false;
        }

        return revisarPalabra(txt, ini + 1, fin - 1);
    }

    // 9) Decimal a binario
    public static String decimalABinario(int n) {
        // Caso base para 0 y 1.
        if (n < 2) {
            return String.valueOf(n);
        }
        return decimalABinario(n / 2) + (n % 2);
    }

    // 10) MCD (Euclides)
    public static int mcd(int a, int b) {
        // Normaliza signos para evitar efectos de negativos.
        a = Math.abs(a);
        b = Math.abs(b);

        if (b == 0) {
            return a;
        }

        return mcd(b, a % b);
    }
}

// =========================
// BLOQUE 4 - DESAFIOS
// =========================

class DesafiosListas {

    // 1) Fusion de listas ordenadas sin crear nodos nuevos
    public NodoSimple fusionarOrdenadas(NodoSimple a, NodoSimple b) {
        // Si alguna lista es vacia, la otra ya es el resultado.
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }

        NodoSimple inicio;
        if (a.dato <= b.dato) {
            inicio = a;
            a = a.sig;
        } else {
            inicio = b;
            b = b.sig;
        }

        NodoSimple cola = inicio;

        // Va uniendo el menor nodo disponible de cada lista.
        while (a != null && b != null) {
            if (a.dato <= b.dato) {
                cola.sig = a;
                a = a.sig;
            } else {
                cola.sig = b;
                b = b.sig;
            }
            cola = cola.sig;
        }

        if (a != null) {
            cola.sig = a;
        } else {
            cola.sig = b;
        }

        return inicio;
    }

    // 2) Deteccion de ciclos (Floyd)
    public boolean tieneCiclo(NodoSimple cabeza) {
        // Si lento y rapido se encuentran, hay ciclo.
        NodoSimple lento = cabeza;
        NodoSimple rapido = cabeza;

        while (rapido != null && rapido.sig != null) {
            lento = lento.sig;
            rapido = rapido.sig.sig;

            if (lento == rapido) {
                return true;
            }
        }

        return false;
    }

    // 3) Eliminar n-esimo desde el final en un recorrido
    public NodoSimple eliminarDesdeFinal(NodoSimple cabeza, int n) {
        // Nodo fantasma para simplificar eliminacion del primer nodo real.
        NodoSimple fantasma = new NodoSimple(0);
        fantasma.sig = cabeza;

        NodoSimple rapido = fantasma;
        NodoSimple lento = fantasma;

        int i = 0;
        while (i <= n && rapido != null) {
            rapido = rapido.sig;
            i = i + 1;
        }

        if (i <= n) {
            return cabeza;
        }

        while (rapido != null) {
            rapido = rapido.sig;
            lento = lento.sig;
        }

        if (lento.sig != null) {
            lento.sig = lento.sig.sig;
        }

        return fantasma.sig;
    }

    // 4) Insertion sort sobre lista enlazada
    public NodoSimple insertionSortLista(NodoSimple cabeza) {
        // Construye una nueva lista ordenada reinsertando cada nodo.
        NodoSimple ordenada = null;
        NodoSimple aux = cabeza;

        while (aux != null) {
            NodoSimple siguiente = aux.sig;
            ordenada = insertarEnOrden(ordenada, aux);
            aux = siguiente;
        }

        return ordenada;
    }

    private NodoSimple insertarEnOrden(NodoSimple inicio, NodoSimple nuevoNodo) {
        // Inserta el nodo en su posicion manteniendo orden ascendente.
        nuevoNodo.sig = null;

        if (inicio == null || nuevoNodo.dato < inicio.dato) {
            nuevoNodo.sig = inicio;
            return nuevoNodo;
        }

        NodoSimple aux = inicio;
        while (aux.sig != null && aux.sig.dato <= nuevoNodo.dato) {
            aux = aux.sig;
        }

        nuevoNodo.sig = aux.sig;
        aux.sig = nuevoNodo;
        return inicio;
    }

    // 5) Intercambio de nodos en parejas
    public NodoSimple intercambiarParejas(NodoSimple cabeza) {
        // Intercambia enlaces de a pares: (a,b) pasa a (b,a).
        NodoSimple fantasma = new NodoSimple(0);
        fantasma.sig = cabeza;
        NodoSimple puntero = fantasma;

        while (puntero.sig != null && puntero.sig.sig != null) {
            NodoSimple a = puntero.sig;
            NodoSimple b = puntero.sig.sig;

            a.sig = b.sig;
            b.sig = a;
            puntero.sig = b;

            puntero = a;
        }

        return fantasma.sig;
    }

    // 6) Lista doble circular multicanal
    public ListaDobleCircularMulticanal crearMulticanal() {
        // Fabrica una estructura multicanal vacia.
        return new ListaDobleCircularMulticanal();
    }

    // 7) Particion de lista alrededor de x
    public NodoSimple particionar(NodoSimple cabeza, int x) {
        // Separa en dos listas: menores y mayores/iguales a x.
        NodoSimple menoresIni = null;
        NodoSimple menoresFin = null;
        NodoSimple mayoresIni = null;
        NodoSimple mayoresFin = null;

        NodoSimple aux = cabeza;

        while (aux != null) {
            NodoSimple sig = aux.sig;
            aux.sig = null;

            if (aux.dato < x) {
                if (menoresIni == null) {
                    menoresIni = aux;
                    menoresFin = aux;
                } else {
                    menoresFin.sig = aux;
                    menoresFin = aux;
                }
            } else {
                if (mayoresIni == null) {
                    mayoresIni = aux;
                    mayoresFin = aux;
                } else {
                    mayoresFin.sig = aux;
                    mayoresFin = aux;
                }
            }

            aux = sig;
        }

        if (menoresIni == null) {
            return mayoresIni;
        }

        menoresFin.sig = mayoresIni;
        return menoresIni;
    }

    // 8) Suma de listas que representan numeros
    public NodoSimple sumarListasNumeros(NodoSimple a, NodoSimple b) {
        // Suma digito a digito con acarreo, como suma manual.
        NodoSimple cabezaRes = null;
        NodoSimple colaRes = null;
        int acarreo = 0;

        while (a != null || b != null || acarreo != 0) {
            int va = 0;
            int vb = 0;

            if (a != null) {
                va = a.dato;
                a = a.sig;
            }

            if (b != null) {
                vb = b.dato;
                b = b.sig;
            }

            int suma = va + vb + acarreo;
            acarreo = suma / 10;
            int dig = suma % 10;

            NodoSimple nuevoNodo = new NodoSimple(dig);
            if (cabezaRes == null) {
                cabezaRes = nuevoNodo;
                colaRes = nuevoNodo;
            } else {
                colaRes.sig = nuevoNodo;
                colaRes = nuevoNodo;
            }
        }

        return cabezaRes;
    }

    // 9) Clonacion con puntero random
    public NodoRandom clonarConRandom(NodoRandom cabeza) {
        // Tecnica en 3 pasos: intercalar copias, copiar random, separar listas.
        if (cabeza == null) {
            return null;
        }

        NodoRandom aux = cabeza;
        while (aux != null) {
            NodoRandom copia = new NodoRandom(aux.dato);
            copia.sig = aux.sig;
            aux.sig = copia;
            aux = copia.sig;
        }

        aux = cabeza;
        while (aux != null) {
            if (aux.random != null) {
                aux.sig.random = aux.random.sig;
            }
            aux = aux.sig.sig;
        }

        aux = cabeza;
        NodoRandom inicioCopia = cabeza.sig;

        while (aux != null) {
            NodoRandom copia = aux.sig;
            aux.sig = copia.sig;

            if (copia.sig != null) {
                copia.sig = copia.sig.sig;
            }

            aux = aux.sig;
        }

        return inicioCopia;
    }

    // 10) Flattening de lista con hijos
    public NodoMulti flatten(NodoMulti cabeza) {
        // Aplana recursivamente cada sublista child dentro de next.
        if (cabeza == null) {
            return null;
        }

        NodoMulti aux = cabeza;

        while (aux != null) {
            if (aux.child != null) {
                NodoMulti siguiente = aux.next;
                NodoMulti hijo = flatten(aux.child);
                aux.next = hijo;
                aux.child = null;

                NodoMulti colaHijo = aux;
                while (colaHijo.next != null) {
                    colaHijo = colaHijo.next;
                }

                colaHijo.next = siguiente;
            }
            aux = aux.next;
        }

        return cabeza;
    }
}

class NodoCanal {
    // Nombre identificador del canal.
    String nombreCanal;
    // Enlaces para lista doble circular de canales.
    NodoCanal sig;
    NodoCanal ant;
    // Sublista de datos asociados al canal.
    NodoTexto subLista;

    NodoCanal(String nombreCanal) {
        this.nombreCanal = nombreCanal;
        this.sig = null;
        this.ant = null;
        this.subLista = null;
    }
}

class ListaDobleCircularMulticanal {
    // Cabeza de la lista circular de canales.
    NodoCanal cabeza;

    public void agregarCanal(String nombre) {
        // Inserta canal al final manteniendo enlaces circulares.
        NodoCanal nuevoNodo = new NodoCanal(nombre);

        if (cabeza == null) {
            cabeza = nuevoNodo;
            cabeza.sig = cabeza;
            cabeza.ant = cabeza;
        } else {
            NodoCanal ultimo = cabeza.ant;

            ultimo.sig = nuevoNodo;
            nuevoNodo.ant = ultimo;
            nuevoNodo.sig = cabeza;
            cabeza.ant = nuevoNodo;
        }
    }

    public void agregarDatoACanal(String canal, String dato) {
        // Busca el canal y agrega su dato en la sublista de ese canal.
        NodoCanal n = buscarCanal(canal);

        if (n == null) {
            return;
        }

        NodoTexto nuevoNodo = new NodoTexto(dato);
        if (n.subLista == null) {
            n.subLista = nuevoNodo;
        } else {
            NodoTexto aux = n.subLista;
            while (aux.sig != null) {
                aux = aux.sig;
            }
            aux.sig = nuevoNodo;
        }
    }

    private NodoCanal buscarCanal(String nombre) {
        // Recorre circularmente hasta volver a la cabeza.
        if (cabeza == null) {
            return null;
        }

        NodoCanal aux = cabeza;
        do {
            if (aux.nombreCanal.equals(nombre)) {
                return aux;
            }
            aux = aux.sig;
        } while (aux != cabeza);

        return null;
    }
}

class NodoRandom {
    // Dato del nodo.
    int dato;
    // Siguiente nodo lineal.
    NodoRandom sig;
    // Referencia aleatoria a cualquier nodo.
    NodoRandom random;

    NodoRandom(int dato) {
        this.dato = dato;
        this.sig = null;
        this.random = null;
    }
}

class NodoMulti {
    // Dato principal.
    int dato;
    // Siguiente nodo en el mismo nivel.
    NodoMulti next;
    // Inicio de sublista hija.
    NodoMulti child;

    NodoMulti(int dato) {
        this.dato = dato;
        this.next = null;
        this.child = null;
    }
}
