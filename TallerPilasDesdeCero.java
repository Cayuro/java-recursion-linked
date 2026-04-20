import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TallerPilasDesdeCero {

    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("EJERCICIO 1 - GESTION DE NIVELES");
        System.out.println("=====================================");

        GestorNiveles gestor = new GestorNiveles();
        gestor.entrarNivel(new Nivel("Bosque", 150, 100));
        gestor.entrarNivel(new Nivel("Cueva", 320, 85));
        gestor.entrarNivel(new Nivel("Castillo", 500, 60));

        System.out.println("Nivel actual: " + gestor.nivelActual());
        System.out.println("Profundidad actual: " + gestor.profundidad());
        System.out.println("Ruta del jugador:");
        gestor.mostrarRuta();

        Nivel salido = gestor.salirNivel();
        System.out.println("Salio del nivel: " + salido);
        System.out.println("Nueva profundidad: " + gestor.profundidad());

        System.out.println();
        System.out.println("=====================================");
        System.out.println("EJERCICIO 2 - VALIDADOR DE HTML");
        System.out.println("=====================================");

        ValidadorHTML validador = new ValidadorHTML();
        String htmlPrueba = "<html><body><h1>Titulo</h2><br><p>Hola</p></body></html>";

        List<String> erroresHTML = validador.validarHTML(htmlPrueba);
        if (erroresHTML.isEmpty()) {
            System.out.println("HTML correcto: no se encontraron errores.");
        } else {
            System.out.println("Errores encontrados:");
            for (String error : erroresHTML) {
                System.out.println("- " + error);
            }
        }

        System.out.println();
        System.out.println("=====================================");
        System.out.println("EJERCICIO 3 - TRANSACCIONES BANCARIAS");
        System.out.println("=====================================");

        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.operar(new Transaccion("T-001", TipoTransaccion.DEPOSITO, 1000, "2026-04-19"));
        cuenta.operar(new Transaccion("T-002", TipoTransaccion.RETIRO, 200, "2026-04-19"));
        cuenta.operar(new Transaccion("T-003", TipoTransaccion.TRANSFERENCIA, 150, "2026-04-19"));
        cuenta.operar(new Transaccion("T-004", TipoTransaccion.RETIRO, 900, "2026-04-19"));

        System.out.println("Saldo actual antes de revertir: " + cuenta.saldoActual());
        List<Transaccion> revertidas = cuenta.revertirUltimas(2);
        System.out.println("Transacciones revertidas: " + revertidas.size());
        System.out.println("Saldo actual despues de revertir: " + cuenta.saldoActual());

        System.out.println();
        System.out.println("=====================================");
        System.out.println("EJERCICIO 4 - REGISTRO DE ERRORES");
        System.out.println("=====================================");

        RegistradorErrores registrador = new RegistradorErrores();
        registrador.registrar(new Evento(NivelEvento.INFO, "Sistema iniciado", LocalDateTime.now().toString()));
        registrador.registrar(new Evento(NivelEvento.WARN, "Uso alto de memoria", LocalDateTime.now().toString()));
        registrador.registrar(new Evento(NivelEvento.ERROR, "Fallo al conectar DB", LocalDateTime.now().toString()));
        registrador.registrar(new Evento(NivelEvento.CRITICAL, "Servicio principal caido", LocalDateTime.now().toString()));

        System.out.println("Ultimos 3 eventos:");
        registrador.mostrarUltimos(3);

        List<Evento> soloErrores = registrador.filtrarPorNivel("ERROR");
        System.out.println("Eventos filtrados por nivel ERROR: " + soloErrores.size());
        for (Evento e : soloErrores) {
            System.out.println("- " + e);
        }

        registrador.exportarCSV("eventos.csv");

        System.out.println();
        System.out.println("=====================================");
        System.out.println("EJERCICIO 5 - LISTA DE ESPERA VUELO");
        System.out.println("=====================================");

        ListaEsperaVuelo listaEspera = new ListaEsperaVuelo();
        listaEspera.unirseListaEspera(new Pasajero("Ana", "PP-001", "ECONOMICA", "08:00"));
        listaEspera.unirseListaEspera(new Pasajero("Luis", "PP-002", "EJECUTIVA", "08:03"));
        listaEspera.unirseListaEspera(new Pasajero("Marta", "PP-003", "ECONOMICA", "08:05"));
        listaEspera.unirseListaEspera(new Pasajero("Pedro", "PP-004", "ECONOMICA", "08:06"));
        listaEspera.unirseListaEspera(new Pasajero("Sara", "PP-005", "EJECUTIVA", "08:10"));

        Pasajero asignado = listaEspera.asignarAsientoLibre();
        System.out.println("Asiento asignado a: " + asignado);
        System.out.println("Posicion actual PP-002: " + listaEspera.posicionActual("PP-002"));
        System.out.println("Espera estimada PP-003 (min): " + listaEspera.tiempoEsperaEstimado("PP-003"));

        System.out.println();
        System.out.println("=====================================");
        System.out.println("EJERCICIO 6 - SIMULACION SUPERMERCADO");
        System.out.println("=====================================");

        SimulacionSupermercado simulacion = new SimulacionSupermercado();
        simulacion.simularLlegadaClientes(20);
        simulacion.mostrarEstadisticas();

        System.out.println();
        System.out.println("=====================================");
        System.out.println("EJERCICIO 7 - COLA DE DESCARGAS");
        System.out.println("=====================================");

        GestorDescargas gestorDescargas = new GestorDescargas();
        gestorDescargas.agregarDescarga(new Descarga("https://archivo1.com", "video.mp4", 40));
        gestorDescargas.agregarDescarga(new Descarga("https://archivo2.com", "curso.zip", 25));
        gestorDescargas.agregarDescarga(new Descarga("https://archivo3.com", "manual.pdf", 8));
        gestorDescargas.agregarDescarga(new Descarga("https://archivo4.com", "dataset.csv", 18));
        gestorDescargas.agregarDescarga(new Descarga("https://archivo5.com", "musica.mp3", 12));
        gestorDescargas.procesarTodas();
        gestorDescargas.estadisticas();

        System.out.println();
        System.out.println("=====================================");
        System.out.println("EJERCICIO 8 - COLA PRIORIDAD MIN-HEAP");
        System.out.println("=====================================");

        ColaPrioridad<Integer> colaMinima = new ColaPrioridad<>();
        colaMinima.insertar(5);
        colaMinima.insertar(3);
        colaMinima.insertar(8);
        colaMinima.insertar(1);
        colaMinima.insertar(4);
        System.out.println("Extraer 1: " + colaMinima.extraerMinimo());
        System.out.println("Extraer 2: " + colaMinima.extraerMinimo());
        System.out.println("Extraer 3: " + colaMinima.extraerMinimo());

        Integer[] arregloPrueba = {5, 3, 8, 1, 4};
        Integer[] ordenado = ColaPrioridad.heapSort(arregloPrueba);
        System.out.print("HeapSort: ");
        for (int i = 0; i < ordenado.length; i++) {
            System.out.print(ordenado[i] + " ");
        }
        System.out.println();

        System.out.println();
        System.out.println("=====================================");
        System.out.println("EJERCICIO 9 - PLANIFICADOR DE TAREAS SO");
        System.out.println("=====================================");

        PlanificadorTareasSO planificador = new PlanificadorTareasSO();
        planificador.admitirProceso(new ProcesoSO("P1", "Proceso 1", 1, 0, 0));
        planificador.admitirProceso(new ProcesoSO("P2", "Proceso 2", 5, 0, 0));
        planificador.admitirProceso(new ProcesoSO("P3", "Proceso 3", 3, 0, 0));
        planificador.admitirProceso(new ProcesoSO("P4", "Proceso 4", 2, 0, 0));
        planificador.admitirProceso(new ProcesoSO("P5", "Proceso 5", 4, 0, 0));
        planificador.simularQuantums(20, 1);
    }
}

// Nodo generico para cualquier tipo de dato.
class NodoGenerico<T> {
    T dato;
    NodoGenerico<T> siguiente;

    NodoGenerico(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

// Pila estatica: usa un arreglo fijo de nodos.
class PilaEstatica<T> {
    private NodoGenerico<T>[] elementos;
    private int tope;

    @SuppressWarnings("unchecked")
    public PilaEstatica(int capacidad) {
        this.elementos = (NodoGenerico<T>[]) new NodoGenerico[capacidad];
        this.tope = 0;
    }

    public boolean apilar(T dato) {
        if (estaLlena()) {
            return false;
        }

        elementos[tope] = new NodoGenerico<>(dato);
        tope = tope + 1;
        return true;
    }

    public T desapilar() {
        if (estaVacia()) {
            return null;
        }

        tope = tope - 1;
        T dato = elementos[tope].dato;
        elementos[tope] = null;
        return dato;
    }

    public T cima() {
        if (estaVacia()) {
            return null;
        }

        return elementos[tope - 1].dato;
    }

    public int tamano() {
        return tope;
    }

    public boolean estaVacia() {
        return tope == 0;
    }

    public boolean estaLlena() {
        return tope == elementos.length;
    }

    public T obtenerEnIndice(int indice) {
        if (indice < 0 || indice >= tope) {
            return null;
        }

        return elementos[indice].dato;
    }
}

// Pila dinamica: usa nodos enlazados y crece segun necesidad.
class PilaDinamica<T> {
    private NodoGenerico<T> cima;
    private int tamano;

    public PilaDinamica() {
        this.cima = null;
        this.tamano = 0;
    }

    public void apilar(T dato) {
        NodoGenerico<T> nuevo = new NodoGenerico<>(dato);
        nuevo.siguiente = cima;
        cima = nuevo;
        tamano = tamano + 1;
    }

    public T desapilar() {
        if (estaVacia()) {
            return null;
        }

        T dato = cima.dato;
        cima = cima.siguiente;
        tamano = tamano - 1;
        return dato;
    }

    public T verCima() {
        if (estaVacia()) {
            return null;
        }

        return cima.dato;
    }

    public int tamano() {
        return tamano;
    }

    public boolean estaVacia() {
        return cima == null;
    }
}

// ==================================================
// EJERCICIO 1: GESTION DE NIVELES (PILA ESTATICA)
// ==================================================

class Nivel {
    private String nombre;
    private int puntuacion;
    private int vida;

    public Nivel(String nombre, int puntuacion, int vida) {
        this.nombre = nombre;
        this.puntuacion = puntuacion;
        this.vida = vida;
    }

    @Override
    public String toString() {
        return "Nivel{nombre='" + nombre + "', puntuacion=" + puntuacion + ", vida=" + vida + "}";
    }
}

class GestorNiveles {
    private PilaEstatica<Nivel> pilaNiveles;

    public GestorNiveles() {
        // Capacidad fija de 15 segun el enunciado.
        this.pilaNiveles = new PilaEstatica<>(15);
    }

    public void entrarNivel(Nivel n) {
        boolean agregado = pilaNiveles.apilar(n);
        if (!agregado) {
            System.out.println("No se puede entrar: la pila de niveles esta llena.");
        }
    }

    public Nivel salirNivel() {
        return pilaNiveles.desapilar();
    }

    public Nivel nivelActual() {
        return pilaNiveles.cima();
    }

    public int profundidad() {
        return pilaNiveles.tamano();
    }

    public void mostrarRuta() {
        if (pilaNiveles.estaVacia()) {
            System.out.println("(sin niveles)");
            return;
        }

        // Se recorre de abajo hacia arriba para mostrar desde el primer nivel.
        for (int i = 0; i < pilaNiveles.tamano(); i++) {
            System.out.println("Paso " + (i + 1) + ": " + pilaNiveles.obtenerEnIndice(i));
        }
    }
}

// ==============================================
// EJERCICIO 2: VALIDADOR DE HTML (PILA ESTATICA)
// ==============================================

class ValidadorHTML {

    public List<String> validarHTML(String html) {
        List<String> errores = new ArrayList<>();
        PilaEstatica<String> pilaEtiquetas = new PilaEstatica<>(1000);

        Set<String> etiquetasAutoCierre = new HashSet<>();
        etiquetasAutoCierre.add("br");
        etiquetasAutoCierre.add("img");
        etiquetasAutoCierre.add("hr");
        etiquetasAutoCierre.add("input");
        etiquetasAutoCierre.add("meta");
        etiquetasAutoCierre.add("link");

        int i = 0;
        while (i < html.length()) {
            if (html.charAt(i) == '<') {
                int cierre = html.indexOf('>', i);

                if (cierre == -1) {
                    errores.add("Etiqueta incompleta desde posicion " + i);
                    break;
                }

                String contenido = html.substring(i + 1, cierre).trim().toLowerCase();
                i = cierre + 1;

                if (contenido.isEmpty()) {
                    continue;
                }

                // Ignora comentarios, doctype o tags de procesamiento.
                if (contenido.startsWith("!") || contenido.startsWith("?")) {
                    continue;
                }

                boolean esCierre = false;
                if (contenido.startsWith("/")) {
                    esCierre = true;
                    contenido = contenido.substring(1).trim();
                }

                boolean esAutoCierreExplicito = false;
                if (contenido.endsWith("/")) {
                    esAutoCierreExplicito = true;
                    contenido = contenido.substring(0, contenido.length() - 1).trim();
                }

                String nombreEtiqueta = extraerNombreEtiqueta(contenido);
                if (nombreEtiqueta.isEmpty()) {
                    continue;
                }

                if (etiquetasAutoCierre.contains(nombreEtiqueta) || esAutoCierreExplicito) {
                    continue;
                }

                if (!esCierre) {
                    pilaEtiquetas.apilar(nombreEtiqueta);
                } else {
                    if (pilaEtiquetas.estaVacia()) {
                        errores.add("Etiqueta cerrada sin apertura: </" + nombreEtiqueta + ">");
                    } else {
                        String ultimaAbierta = pilaEtiquetas.desapilar();
                        if (!ultimaAbierta.equals(nombreEtiqueta)) {
                            errores.add("Mal anidamiento: se esperaba </" + ultimaAbierta
                                    + "> pero llego </" + nombreEtiqueta + ">");
                        }
                    }
                }
            } else {
                i = i + 1;
            }
        }

        while (!pilaEtiquetas.estaVacia()) {
            String pendiente = pilaEtiquetas.desapilar();
            errores.add("Etiqueta no cerrada: <" + pendiente + ">");
        }

        return errores;
    }

    private String extraerNombreEtiqueta(String contenido) {
        StringBuilder nombre = new StringBuilder();

        for (int i = 0; i < contenido.length(); i++) {
            char c = contenido.charAt(i);
            if (c == ' ' || c == '\t' || c == '\n' || c == '/') {
                break;
            }
            nombre.append(c);
        }

        return nombre.toString();
    }
}

// =========================================================
// EJERCICIO 3: TRANSACCIONES BANCARIAS (PILA DINAMICA)
// =========================================================

enum TipoTransaccion {
    DEPOSITO,
    RETIRO,
    TRANSFERENCIA
}

class Transaccion {
    private String id;
    private TipoTransaccion tipo;
    private double monto;
    private String fecha;

    public Transaccion(String id, TipoTransaccion tipo, double monto, String fecha) {
        this.id = id;
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = fecha;
    }

    public TipoTransaccion getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    @Override
    public String toString() {
        return "Transaccion{id='" + id + "', tipo=" + tipo + ", monto=" + monto + ", fecha='" + fecha + "'}";
    }
}

class CuentaBancaria {
    private PilaDinamica<Transaccion> historial;
    private double saldo;

    public CuentaBancaria() {
        this.historial = new PilaDinamica<>();
        this.saldo = 0;
    }

    public boolean operar(Transaccion t) {
        if (t.getMonto() <= 0) {
            System.out.println("Transaccion rechazada: el monto debe ser positivo.");
            return false;
        }

        if (t.getTipo() == TipoTransaccion.DEPOSITO) {
            saldo = saldo + t.getMonto();
            historial.apilar(t);
            System.out.println("Transaccion aplicada: " + t);
            return true;
        }

        if (t.getTipo() == TipoTransaccion.RETIRO || t.getTipo() == TipoTransaccion.TRANSFERENCIA) {
            if (saldo - t.getMonto() < 0) {
                System.out.println("Transaccion rechazada por saldo insuficiente: " + t);
                return false;
            }

            saldo = saldo - t.getMonto();
            historial.apilar(t);
            System.out.println("Transaccion aplicada: " + t);
            return true;
        }

        return false;
    }

    public List<Transaccion> revertirUltimas(int n) {
        List<Transaccion> revertidas = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Transaccion t = historial.desapilar();
            if (t == null) {
                break;
            }
            revertidas.add(t);
        }

        // Se recalcula el saldo desde cero, como pide el enunciado.
        recalcularSaldoDesdeCero();
        return revertidas;
    }

    private void recalcularSaldoDesdeCero() {
        saldo = 0;

        // Primero invertimos para recuperar orden cronologico (viejas a nuevas).
        PilaDinamica<Transaccion> auxiliar = new PilaDinamica<>();
        while (!historial.estaVacia()) {
            auxiliar.apilar(historial.desapilar());
        }

        // Luego reconstruimos historial y saldo de forma simple.
        while (!auxiliar.estaVacia()) {
            Transaccion t = auxiliar.desapilar();
            aplicarSinValidacion(t);
            historial.apilar(t);
        }
    }

    private void aplicarSinValidacion(Transaccion t) {
        if (t.getTipo() == TipoTransaccion.DEPOSITO) {
            saldo = saldo + t.getMonto();
        } else {
            saldo = saldo - t.getMonto();
        }
    }

    public double saldoActual() {
        return saldo;
    }
}

// ======================================================
// EJERCICIO 4: REGISTRO DE ERRORES (PILA DINAMICA)
// ======================================================

enum NivelEvento {
    INFO,
    WARN,
    ERROR,
    CRITICAL
}

class Evento {
    private NivelEvento nivel;
    private String mensaje;
    private String timestamp;

    public Evento(NivelEvento nivel, String mensaje, String timestamp) {
        this.nivel = nivel;
        this.mensaje = mensaje;
        this.timestamp = timestamp;
    }

    public NivelEvento getNivel() {
        return nivel;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Evento{nivel=" + nivel + ", mensaje='" + mensaje + "', timestamp='" + timestamp + "'}";
    }
}

class RegistradorErrores {
    private PilaDinamica<Evento> pilaEventos;

    public RegistradorErrores() {
        this.pilaEventos = new PilaDinamica<>();
    }

    public void registrar(Evento e) {
        pilaEventos.apilar(e);
    }

    public void mostrarUltimos(int n) {
        if (n <= 0) {
            System.out.println("Cantidad invalida.");
            return;
        }

        PilaDinamica<Evento> auxiliar = new PilaDinamica<>();
        int contador = 0;

        while (!pilaEventos.estaVacia() && contador < n) {
            Evento e = pilaEventos.desapilar();
            System.out.println(e);
            auxiliar.apilar(e);
            contador = contador + 1;
        }

        // Restauramos para no perder eventos.
        while (!auxiliar.estaVacia()) {
            pilaEventos.apilar(auxiliar.desapilar());
        }
    }

    public List<Evento> filtrarPorNivel(String nivel) {
        List<Evento> filtrados = new ArrayList<>();
        PilaDinamica<Evento> auxiliar = new PilaDinamica<>();

        while (!pilaEventos.estaVacia()) {
            Evento e = pilaEventos.desapilar();
            if (e.getNivel().name().equalsIgnoreCase(nivel)) {
                filtrados.add(e);
            }
            auxiliar.apilar(e);
        }

        // Restauramos para dejar la pila igual que al inicio.
        while (!auxiliar.estaVacia()) {
            pilaEventos.apilar(auxiliar.desapilar());
        }

        return filtrados;
    }

    public void exportarCSV(String archivo) {
        PilaDinamica<Evento> auxiliar = new PilaDinamica<>();

        // Pasamos todo a una pila auxiliar para escribir del mas antiguo al mas nuevo.
        while (!pilaEventos.estaVacia()) {
            auxiliar.apilar(pilaEventos.desapilar());
        }

        try (PrintWriter escritor = new PrintWriter(new FileWriter(archivo))) {
            escritor.println("nivel,mensaje,timestamp");

            while (!auxiliar.estaVacia()) {
                Evento e = auxiliar.desapilar();
                String mensajeLimpio = e.getMensaje().replace(",", " ");
                escritor.println(e.getNivel() + "," + mensajeLimpio + "," + e.getTimestamp());

                // Reinsertamos para dejar la pila como estaba.
                pilaEventos.apilar(e);
            }

            System.out.println("CSV exportado en: " + archivo);
        } catch (IOException ex) {
            System.out.println("Error al exportar CSV: " + ex.getMessage());
        }
    }
}

// ==========================================
// BASE COLAS DESDE CERO (ESTATICA Y DINAMICA)
// ==========================================

class ColaEstatica<T> {
    private NodoGenerico<T>[] datos;
    private int frente;
    private int fin;
    private int tamano;

    @SuppressWarnings("unchecked")
    public ColaEstatica(int capacidad) {
        this.datos = (NodoGenerico<T>[]) new NodoGenerico[capacidad];
        this.frente = 0;
        this.fin = 0;
        this.tamano = 0;
    }

    public boolean encolar(T dato) {
        if (estaLlena()) {
            return false;
        }

        datos[fin] = new NodoGenerico<>(dato);
        fin = (fin + 1) % datos.length;
        tamano = tamano + 1;
        return true;
    }

    public T desencolar() {
        if (estaVacia()) {
            return null;
        }

        T dato = datos[frente].dato;
        datos[frente] = null;
        frente = (frente + 1) % datos.length;
        tamano = tamano - 1;
        return dato;
    }

    public T verFrente() {
        if (estaVacia()) {
            return null;
        }

        return datos[frente].dato;
    }

    public int tamano() {
        return tamano;
    }

    public boolean estaVacia() {
        return tamano == 0;
    }

    public boolean estaLlena() {
        return tamano == datos.length;
    }
}

class ColaDinamica<T> {
    private NodoGenerico<T> frente;
    private NodoGenerico<T> fin;
    private int tamano;

    public ColaDinamica() {
        this.frente = null;
        this.fin = null;
        this.tamano = 0;
    }

    public void encolar(T dato) {
        NodoGenerico<T> nuevo = new NodoGenerico<>(dato);

        if (estaVacia()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }

        tamano = tamano + 1;
    }

    public T desencolar() {
        if (estaVacia()) {
            return null;
        }

        T dato = frente.dato;
        frente = frente.siguiente;
        tamano = tamano - 1;

        if (frente == null) {
            fin = null;
        }

        return dato;
    }

    public T verFrente() {
        if (estaVacia()) {
            return null;
        }

        return frente.dato;
    }

    public int tamano() {
        return tamano;
    }

    public boolean estaVacia() {
        return frente == null;
    }
}

// ====================================================
// EJERCICIO 5: LISTA DE ESPERA VUELO (COLA DINAMICA)
// ====================================================

class Pasajero {
    private String nombre;
    private String pasaporte;
    private String clase;
    private String horaRegistro;

    public Pasajero(String nombre, String pasaporte, String clase, String horaRegistro) {
        this.nombre = nombre;
        this.pasaporte = pasaporte;
        this.clase = clase;
        this.horaRegistro = horaRegistro;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    @Override
    public String toString() {
        return "Pasajero{nombre='" + nombre + "', pasaporte='" + pasaporte + "', clase='" + clase
                + "', horaRegistro='" + horaRegistro + "'}";
    }
}

class ListaEsperaVuelo {
    private static final int TIEMPO_ROTACION_ASIENTO_MIN = 5;
    private ColaDinamica<Pasajero> cola;

    public ListaEsperaVuelo() {
        this.cola = new ColaDinamica<>();
    }

    public int unirseListaEspera(Pasajero p) {
        cola.encolar(p);
        return cola.tamano();
    }

    public Pasajero asignarAsientoLibre() {
        return cola.desencolar();
    }

    public int posicionActual(String pasaporte) {
        int tam = cola.tamano();
        int posicion = -1;

        for (int i = 1; i <= tam; i++) {
            Pasajero actual = cola.desencolar();
            if (actual.getPasaporte().equalsIgnoreCase(pasaporte) && posicion == -1) {
                posicion = i;
            }
            cola.encolar(actual);
        }

        return posicion;
    }

    public int tiempoEsperaEstimado(String pasaporte) {
        int posicion = posicionActual(pasaporte);
        if (posicion == -1) {
            return -1;
        }

        return posicion * TIEMPO_ROTACION_ASIENTO_MIN;
    }
}

// =====================================================
// EJERCICIO 6: SIMULACION CAJA SUPERMERCADO (ESTATICA)
// =====================================================

class Cliente {
    private String id;
    private int tiempoCompraMin;
    private int minutoLlegada;

    public Cliente(String id, int tiempoCompraMin, int minutoLlegada) {
        this.id = id;
        this.tiempoCompraMin = tiempoCompraMin;
        this.minutoLlegada = minutoLlegada;
    }

    public int getTiempoCompraMin() {
        return tiempoCompraMin;
    }

    public int getMinutoLlegada() {
        return minutoLlegada;
    }

    @Override
    public String toString() {
        return "Cliente{id='" + id + "', tiempoCompra=" + tiempoCompraMin + ", llegada=" + minutoLlegada + "}";
    }
}

class CajaSupermercado {
    private String nombre;
    private ColaEstatica<Cliente> cola;
    private int tiempoFinUltimoServicio;
    private int sumaEspera;
    private int atendidosEn60;

    public CajaSupermercado(String nombre, int capacidad) {
        this.nombre = nombre;
        this.cola = new ColaEstatica<>(capacidad);
        this.tiempoFinUltimoServicio = 0;
        this.sumaEspera = 0;
        this.atendidosEn60 = 0;
    }

    public boolean recibirCliente(Cliente c) {
        return cola.encolar(c);
    }

    public void procesarCola() {
        while (!cola.estaVacia()) {
            Cliente c = cola.desencolar();

            int inicioAtencion = tiempoFinUltimoServicio;
            if (inicioAtencion < c.getMinutoLlegada()) {
                inicioAtencion = c.getMinutoLlegada();
            }

            int espera = inicioAtencion - c.getMinutoLlegada();
            sumaEspera = sumaEspera + espera;

            tiempoFinUltimoServicio = inicioAtencion + c.getTiempoCompraMin();
            if (tiempoFinUltimoServicio <= 60) {
                atendidosEn60 = atendidosEn60 + 1;
            }
        }
    }

    public int tamanoCola() {
        return cola.tamano();
    }

    public double esperaPromedio() {
        if (atendidosEn60 == 0) {
            return 0;
        }
        return (double) sumaEspera / atendidosEn60;
    }

    public int getAtendidosEn60() {
        return atendidosEn60;
    }

    public String getNombre() {
        return nombre;
    }
}

class SimulacionSupermercado {
    private CajaSupermercado[] cajas;

    public SimulacionSupermercado() {
        this.cajas = new CajaSupermercado[3];
        cajas[0] = new CajaSupermercado("Caja 1", 15);
        cajas[1] = new CajaSupermercado("Caja 2", 15);
        cajas[2] = new CajaSupermercado("Caja 3", 15);
    }

    public void simularLlegadaClientes(int cantidad) {
        for (int i = 1; i <= cantidad; i++) {
            int tiempoCompra = (int) (Math.random() * 10) + 1;
            int llegada = i - 1;
            Cliente cliente = new Cliente("C-" + i, tiempoCompra, llegada);

            CajaSupermercado caja = obtenerCajaConMenosPersonas();
            boolean asignado = caja.recibirCliente(cliente);
            if (!asignado) {
                System.out.println("No se pudo asignar " + cliente + " porque " + caja.getNombre() + " esta llena.");
            }
        }

        for (int i = 0; i < cajas.length; i++) {
            cajas[i].procesarCola();
        }
    }

    private CajaSupermercado obtenerCajaConMenosPersonas() {
        CajaSupermercado menor = cajas[0];

        for (int i = 1; i < cajas.length; i++) {
            if (cajas[i].tamanoCola() < menor.tamanoCola()) {
                menor = cajas[i];
            }
        }

        return menor;
    }

    public void mostrarEstadisticas() {
        double sumaPromedios = 0;
        int totalAtendidos = 0;
        CajaSupermercado mejorCaja = cajas[0];

        for (int i = 0; i < cajas.length; i++) {
            System.out.println(cajas[i].getNombre() + " -> atendidos en 60 min: " + cajas[i].getAtendidosEn60()
                    + ", espera promedio: " + cajas[i].esperaPromedio());

            sumaPromedios = sumaPromedios + cajas[i].esperaPromedio();
            totalAtendidos = totalAtendidos + cajas[i].getAtendidosEn60();

            if (cajas[i].esperaPromedio() < mejorCaja.esperaPromedio()) {
                mejorCaja = cajas[i];
            }
        }

        System.out.println("Tiempo de espera promedio total: " + (sumaPromedios / cajas.length));
        System.out.println("Total de clientes atendidos en 60 min: " + totalAtendidos);
        System.out.println("Caja mas eficiente: " + mejorCaja.getNombre());
    }
}

// ====================================================
// EJERCICIO 7: COLA DE DESCARGAS CON REINTENTOS
// ====================================================

enum EstadoDescarga {
    PENDIENTE,
    COMPLETADA,
    FALLIDA
}

class Descarga {
    private String url;
    private String nombreArchivo;
    private int intentos;
    private int tamanoMB;
    private EstadoDescarga estado;

    public Descarga(String url, String nombreArchivo, int tamanoMB) {
        this.url = url;
        this.nombreArchivo = nombreArchivo;
        this.intentos = 0;
        this.tamanoMB = tamanoMB;
        this.estado = EstadoDescarga.PENDIENTE;
    }

    public int getIntentos() {
        return intentos;
    }

    public void aumentarIntento() {
        intentos = intentos + 1;
    }

    public int getTamanoMB() {
        return tamanoMB;
    }

    public void setEstado(EstadoDescarga estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Descarga{url='" + url + "', archivo='" + nombreArchivo + "', intentos=" + intentos + ", tamanoMB="
                + tamanoMB + ", estado=" + estado + "}";
    }
}

class GestorDescargas {
    private static final double PROBABILIDAD_FALLO = 0.30;

    private ColaDinamica<Descarga> cola;
    private int completadas;
    private int fallidas;
    private int reintentadas;
    private int tiempoTotalSimulado;

    public GestorDescargas() {
        this.cola = new ColaDinamica<>();
        this.completadas = 0;
        this.fallidas = 0;
        this.reintentadas = 0;
        this.tiempoTotalSimulado = 0;
    }

    public void agregarDescarga(Descarga d) {
        cola.encolar(d);
    }

    public boolean procesarSiguiente() {
        Descarga actual = cola.desencolar();
        if (actual == null) {
            return false;
        }

        actual.aumentarIntento();
        tiempoTotalSimulado = tiempoTotalSimulado + actual.getTamanoMB();

        boolean fallo = Math.random() < PROBABILIDAD_FALLO;
        if (!fallo) {
            actual.setEstado(EstadoDescarga.COMPLETADA);
            completadas = completadas + 1;
            System.out.println("Descarga completada: " + actual);
            return true;
        }

        if (actual.getIntentos() < 3) {
            reintentadas = reintentadas + 1;
            actual.setEstado(EstadoDescarga.PENDIENTE);
            cola.encolar(actual);
            System.out.println("Descarga fallo, se reencola: " + actual);
        } else {
            actual.setEstado(EstadoDescarga.FALLIDA);
            fallidas = fallidas + 1;
            System.out.println("Descarga fallida definitiva: " + actual);
        }

        return true;
    }

    public void procesarTodas() {
        while (procesarSiguiente()) {
            // Se procesa hasta vaciar cola.
        }
    }

    public void estadisticas() {
        System.out.println("Completadas: " + completadas);
        System.out.println("Fallidas: " + fallidas);
        System.out.println("Reintentadas: " + reintentadas);
        System.out.println("Tiempo total simulado (unidad simple): " + tiempoTotalSimulado);
    }
}

// ======================================================
// EJERCICIO 8: COLA CON PRIORIDAD (MIN-HEAP MANUAL)
// ======================================================

interface AccionColaPrioridad<T> {
    void aplicar(T elemento);
}

class ColaPrioridad<T extends Comparable<T>> {
    private Object[] heap;
    private int tamano;

    public ColaPrioridad() {
        this.heap = new Object[20];
        this.tamano = 0;
    }

    public void insertar(T dato) {
        if (tamano == heap.length) {
            redimensionar();
        }

        heap[tamano] = dato;
        subir(tamano);
        tamano = tamano + 1;
    }

    public T extraerMinimo() {
        if (isEmpty()) {
            return null;
        }

        T minimo = obtener(0);
        heap[0] = heap[tamano - 1];
        heap[tamano - 1] = null;
        tamano = tamano - 1;
        bajar(0);
        return minimo;
    }

    public T verMinimo() {
        if (isEmpty()) {
            return null;
        }
        return obtener(0);
    }

    public boolean isEmpty() {
        return tamano == 0;
    }

    public int size() {
        return tamano;
    }

    public void aplicarATodos(AccionColaPrioridad<T> accion) {
        for (int i = 0; i < tamano; i++) {
            accion.aplicar(obtener(i));
        }

        reconstruirHeap();
    }

    public static <E extends Comparable<E>> E[] heapSort(E[] datos) {
        ColaPrioridad<E> cp = new ColaPrioridad<>();
        for (int i = 0; i < datos.length; i++) {
            cp.insertar(datos[i]);
        }

        @SuppressWarnings("unchecked")
        E[] salida = (E[]) java.lang.reflect.Array.newInstance(datos.getClass().getComponentType(), datos.length);
        for (int i = 0; i < salida.length; i++) {
            salida[i] = cp.extraerMinimo();
        }
        return salida;
    }

    @SuppressWarnings("unchecked")
    private T obtener(int indice) {
        return (T) heap[indice];
    }

    private void subir(int indice) {
        int actual = indice;

        while (actual > 0) {
            int padre = (actual - 1) / 2;
            T valorActual = obtener(actual);
            T valorPadre = obtener(padre);

            if (valorActual.compareTo(valorPadre) < 0) {
                intercambiar(actual, padre);
                actual = padre;
            } else {
                break;
            }
        }
    }

    private void bajar(int indice) {
        int actual = indice;

        while (true) {
            int hijoIzq = (2 * actual) + 1;
            int hijoDer = (2 * actual) + 2;
            int menor = actual;

            if (hijoIzq < tamano && obtener(hijoIzq).compareTo(obtener(menor)) < 0) {
                menor = hijoIzq;
            }

            if (hijoDer < tamano && obtener(hijoDer).compareTo(obtener(menor)) < 0) {
                menor = hijoDer;
            }

            if (menor != actual) {
                intercambiar(actual, menor);
                actual = menor;
            } else {
                break;
            }
        }
    }

    private void intercambiar(int i, int j) {
        Object temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void redimensionar() {
        Object[] nuevo = new Object[heap.length * 2];
        for (int i = 0; i < heap.length; i++) {
            nuevo[i] = heap[i];
        }
        heap = nuevo;
    }

    private void reconstruirHeap() {
        for (int i = (tamano / 2) - 1; i >= 0; i--) {
            bajar(i);
        }
    }
}

// =======================================================
// EJERCICIO 9: PLANIFICADOR TAREAS SO (CON PRIORIDADES)
// =======================================================

class ProcesoSO implements Comparable<ProcesoSO> {
    private String pid;
    private String nombre;
    private int prioridad;
    private int tiempoCPUAcumulado;
    private int tiempoLlegada;

    public ProcesoSO(String pid, String nombre, int prioridad, int tiempoCPUAcumulado, int tiempoLlegada) {
        this.pid = pid;
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.tiempoCPUAcumulado = tiempoCPUAcumulado;
        this.tiempoLlegada = tiempoLlegada;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public void sumarCPU(int quantum) {
        tiempoCPUAcumulado = tiempoCPUAcumulado + quantum;
    }

    @Override
    public int compareTo(ProcesoSO otro) {
        if (this.prioridad != otro.prioridad) {
            return Integer.compare(this.prioridad, otro.prioridad);
        }

        return Integer.compare(this.tiempoLlegada, otro.tiempoLlegada);
    }

    @Override
    public String toString() {
        return "Proceso{pid='" + pid + "', nombre='" + nombre + "', prioridad=" + prioridad + ", cpu="
                + tiempoCPUAcumulado + ", llegada=" + tiempoLlegada + "}";
    }
}

class PlanificadorTareasSO {
    private ColaPrioridad<ProcesoSO> colaProcesos;

    public PlanificadorTareasSO() {
        this.colaProcesos = new ColaPrioridad<>();
    }

    public void admitirProceso(ProcesoSO p) {
        colaProcesos.insertar(p);
    }

    public ProcesoSO ejecutarSiguiente(int quantum) {
        ProcesoSO actual = colaProcesos.extraerMinimo();
        if (actual == null) {
            return null;
        }

        actual.sumarCPU(quantum);

        // El que ejecuta baja un poco su urgencia para repartir CPU.
        actual.setPrioridad(actual.getPrioridad() + 1);

        // Aging: todos los demas mejoran su prioridad para evitar starvation.
        colaProcesos.aplicarATodos(new AccionColaPrioridad<ProcesoSO>() {
            @Override
            public void aplicar(ProcesoSO p) {
                int nueva = p.getPrioridad() - 1;
                if (nueva < 1) {
                    nueva = 1;
                }
                p.setPrioridad(nueva);
            }
        });

        colaProcesos.insertar(actual);
        return actual;
    }

    public void simularQuantums(int cantidad, int quantum) {
        for (int i = 1; i <= cantidad; i++) {
            ProcesoSO ejecutado = ejecutarSiguiente(quantum);
            if (ejecutado == null) {
                System.out.println("No hay procesos en cola.");
                break;
            }

            System.out.println("Quantum " + i + " -> " + ejecutado);
        }
    }
}
