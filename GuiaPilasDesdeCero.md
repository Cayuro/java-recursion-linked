# Guia completa: Pilas estaticas y dinamicas (desde cero)

Esta guia esta hecha para aprender paso a paso, como si fuera una clase.  
Todo esta pensado con logica simple de principiante:

- Sin usar librerias de pilas de Java.
- Con nodos genericos.
- Con nombres claros.
- Con comentarios sencillos.

El codigo completo esta en el archivo:

- `TallerPilasDesdeCero.java`

---

## Base comun para todos los ejercicios

Antes de los 4 ejercicios, se usan 3 clases base:

1. `NodoGenerico<T>`
2. `PilaEstatica<T>`
3. `PilaDinamica<T>`

### Codigo base

```java
class NodoGenerico<T> {
    T dato;
    NodoGenerico<T> siguiente;

    NodoGenerico(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

class PilaEstatica<T> {
    private NodoGenerico<T>[] elementos;
    private int tope;

    @SuppressWarnings("unchecked")
    public PilaEstatica(int capacidad) {
        this.elementos = (NodoGenerico<T>[]) new NodoGenerico[capacidad];
        this.tope = 0;
    }

    public boolean apilar(T dato) {
        if (estaLlena()) return false;
        elementos[tope] = new NodoGenerico<>(dato);
        tope = tope + 1;
        return true;
    }

    public T desapilar() {
        if (estaVacia()) return null;
        tope = tope - 1;
        T dato = elementos[tope].dato;
        elementos[tope] = null;
        return dato;
    }

    public T cima() {
        if (estaVacia()) return null;
        return elementos[tope - 1].dato;
    }

    public int tamano() { return tope; }
    public boolean estaVacia() { return tope == 0; }
    public boolean estaLlena() { return tope == elementos.length; }

    public T obtenerEnIndice(int indice) {
        if (indice < 0 || indice >= tope) return null;
        return elementos[indice].dato;
    }
}

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
        if (estaVacia()) return null;
        T dato = cima.dato;
        cima = cima.siguiente;
        tamano = tamano - 1;
        return dato;
    }

    public T verCima() {
        if (estaVacia()) return null;
        return cima.dato;
    }

    public int tamano() { return tamano; }
    public boolean estaVacia() { return cima == null; }
}
```

### Explicacion linea por linea (base)

- `class NodoGenerico<T>`: crea un nodo para cualquier tipo de dato (`T`).
- `T dato`: aqui se guarda el valor real.
- `NodoGenerico<T> siguiente`: puntero al siguiente nodo (para pila dinamica).
- Constructor: recibe el dato y deja `siguiente` en `null`.

- `PilaEstatica<T>`: pila con capacidad fija (arreglo).
- `elementos`: arreglo de nodos.
- `tope`: cantidad actual de elementos y posicion de insercion.
- `apilar`: si no esta llena, guarda un nodo y sube `tope`.
- `desapilar`: baja `tope`, toma el dato y limpia la casilla.
- `cima`: devuelve el ultimo agregado sin sacarlo.
- `obtenerEnIndice`: ayuda a recorrer desde el inicio, util para mostrar rutas.

- `PilaDinamica<T>`: no tiene limite fijo; usa nodos enlazados.
- `cima`: nodo de arriba.
- `apilar`: inserta nuevo nodo al inicio.
- `desapilar`: quita la cima y avanza al siguiente nodo.

---

## Ejercicio 1: Gestion de niveles (Pila estatica)

### 1) Explicacion sencilla

En un videojuego, cuando entras a un nivel, ese nivel queda en la parte de arriba de la pila.  
Si sales del nivel, debes volver al anterior. Eso es LIFO:

- Last In: ultimo en entrar
- First Out: primero en salir

### 2) Estructura de clases

- `Nivel` (modelo)
- `GestorNiveles` (usa `PilaEstatica<Nivel>`)

### 3) Codigo completo

```java
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

        for (int i = 0; i < pilaNiveles.tamano(); i++) {
            System.out.println("Paso " + (i + 1) + ": " + pilaNiveles.obtenerEnIndice(i));
        }
    }
}
```

### 4) Explicacion linea por linea

- `Nivel`: guarda informacion del nivel.
- Constructor de `Nivel`: llena nombre, puntuacion y vida.
- `toString`: permite imprimir bonito en consola.

- `GestorNiveles`: clase que maneja la pila.
- Constructor: crea la pila con capacidad fija 15.
- `entrarNivel`: intenta apilar; si no cabe, avisa.
- `salirNivel`: desapila y devuelve el nivel que se quita.
- `nivelActual`: ve la cima.
- `profundidad`: cantidad de niveles cargados.
- `mostrarRuta`: recorre desde indice 0 (primer nivel) hasta el actual.

### 5) Ejemplo de uso (main)

```java
GestorNiveles gestor = new GestorNiveles();
gestor.entrarNivel(new Nivel("Bosque", 150, 100));
gestor.entrarNivel(new Nivel("Cueva", 320, 85));
gestor.entrarNivel(new Nivel("Castillo", 500, 60));

System.out.println("Nivel actual: " + gestor.nivelActual());
System.out.println("Profundidad actual: " + gestor.profundidad());
gestor.mostrarRuta();

Nivel salido = gestor.salirNivel();
System.out.println("Salio del nivel: " + salido);
```

---

## Ejercicio 2: Validador de HTML (Pila estatica)

### 1) Explicacion sencilla

Cada etiqueta abierta se mete en pila.  
Cuando llega una etiqueta de cierre, debe cerrar exactamente la ultima que se abrio.

Si no coincide:

- cierre sin apertura
- mal anidamiento
- etiqueta no cerrada

### 2) Estructura de clases

- `ValidadorHTML` (usa `PilaEstatica<String>`)

### 3) Codigo completo

```java
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

                if (contenido.isEmpty()) continue;
                if (contenido.startsWith("!") || contenido.startsWith("?")) continue;

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
                if (nombreEtiqueta.isEmpty()) continue;

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
```

### 4) Explicacion linea por linea

- `errores`: lista de mensajes para reportar problemas.
- `pilaEtiquetas`: guarda etiquetas abiertas.
- `etiquetasAutoCierre`: tags que no necesitan cierre manual.
- Bucle `while`: recorre el texto completo.
- Si ve `<`, busca el siguiente `>`.
- Si no encuentra `>`, reporta etiqueta incompleta.
- Convierte contenido a minuscula para comparar facil.
- Ignora comentarios/doctype (`!`, `?`).
- Si comienza con `/`, es etiqueta de cierre.
- Si termina con `/`, es autocierre explicito.
- `extraerNombreEtiqueta`: toma solo el nombre principal (ignora atributos).
- Si es apertura, hace `apilar`.
- Si es cierre:
  - pila vacia -> cierre sin apertura.
  - pila con datos -> compara con la ultima abierta.
- Al final, si quedan etiquetas en pila, son no cerradas.

### 5) Ejemplo de uso (main)

```java
ValidadorHTML validador = new ValidadorHTML();
String htmlPrueba = "<html><body><h1>Titulo</h2><br><p>Hola</p></body></html>";

List<String> erroresHTML = validador.validarHTML(htmlPrueba);
for (String error : erroresHTML) {
    System.out.println(error);
}
```

---

## Ejercicio 3: Transacciones bancarias (Pila dinamica)

### 1) Explicacion sencilla

Cada transaccion valida se guarda en la pila.  
Si quieres revertir las ultimas `n`, simplemente desapilas `n` elementos.

Luego recalculas saldo desde cero con lo que quedo en historial.

### 2) Estructura de clases

- `TipoTransaccion` (enum)
- `Transaccion` (modelo)
- `CuentaBancaria` (usa `PilaDinamica<Transaccion>`)

### 3) Codigo completo

```java
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

    public TipoTransaccion getTipo() { return tipo; }
    public double getMonto() { return monto; }

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
            return true;
        }

        if (t.getTipo() == TipoTransaccion.RETIRO || t.getTipo() == TipoTransaccion.TRANSFERENCIA) {
            if (saldo - t.getMonto() < 0) {
                System.out.println("Transaccion rechazada por saldo insuficiente: " + t);
                return false;
            }
            saldo = saldo - t.getMonto();
            historial.apilar(t);
            return true;
        }

        return false;
    }

    public List<Transaccion> revertirUltimas(int n) {
        List<Transaccion> revertidas = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Transaccion t = historial.desapilar();
            if (t == null) break;
            revertidas.add(t);
        }

        recalcularSaldoDesdeCero();
        return revertidas;
    }

    private void recalcularSaldoDesdeCero() {
        saldo = 0;
        PilaDinamica<Transaccion> auxiliar = new PilaDinamica<>();

        while (!historial.estaVacia()) {
            auxiliar.apilar(historial.desapilar());
        }

        while (!auxiliar.estaVacia()) {
            Transaccion t = auxiliar.desapilar();
            if (t.getTipo() == TipoTransaccion.DEPOSITO) {
                saldo = saldo + t.getMonto();
            } else {
                saldo = saldo - t.getMonto();
            }
            historial.apilar(t);
        }
    }

    public double saldoActual() {
        return saldo;
    }
}
```

### 4) Explicacion linea por linea

- `enum TipoTransaccion`: limita los tipos posibles.
- `Transaccion`: guarda datos de cada operacion.
- `historial`: pila dinamica con transacciones.
- `saldo`: saldo vivo de la cuenta.
- `operar`:
  - rechaza montos no positivos.
  - deposito suma.
  - retiro/transferencia restan solo si alcanza el saldo.
  - cada operacion valida se apila.
- `revertirUltimas`:
  - desapila `n` transacciones recientes.
  - luego recalcula saldo desde cero.
- `recalcularSaldoDesdeCero`:
  - vacia historial a auxiliar.
  - vuelve a pasar transaccion por transaccion para reconstruir saldo e historial.

### 5) Ejemplo de uso (main)

```java
CuentaBancaria cuenta = new CuentaBancaria();
cuenta.operar(new Transaccion("T-001", TipoTransaccion.DEPOSITO, 1000, "2026-04-19"));
cuenta.operar(new Transaccion("T-002", TipoTransaccion.RETIRO, 200, "2026-04-19"));
cuenta.operar(new Transaccion("T-003", TipoTransaccion.TRANSFERENCIA, 150, "2026-04-19"));

System.out.println("Saldo antes: " + cuenta.saldoActual());
cuenta.revertirUltimas(2);
System.out.println("Saldo despues: " + cuenta.saldoActual());
```

---

## Ejercicio 4: Registro de errores (Pila dinamica)

### 1) Explicacion sencilla

Piensa en una pila de logs como un stack trace:

- lo ultimo registrado es lo primero que ves
- pero al filtrar o mostrar, no quieres destruir la pila

Entonces se usa una pila auxiliar para recorrer y luego restaurar.

### 2) Estructura de clases

- `NivelEvento` (enum)
- `Evento` (modelo)
- `RegistradorErrores` (usa `PilaDinamica<Evento>`)

### 3) Codigo completo

```java
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

    public NivelEvento getNivel() { return nivel; }
    public String getMensaje() { return mensaje; }
    public String getTimestamp() { return timestamp; }

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

        while (!auxiliar.estaVacia()) {
            pilaEventos.apilar(auxiliar.desapilar());
        }

        return filtrados;
    }

    public void exportarCSV(String archivo) {
        PilaDinamica<Evento> auxiliar = new PilaDinamica<>();

        while (!pilaEventos.estaVacia()) {
            auxiliar.apilar(pilaEventos.desapilar());
        }

        try (PrintWriter escritor = new PrintWriter(new FileWriter(archivo))) {
            escritor.println("nivel,mensaje,timestamp");

            while (!auxiliar.estaVacia()) {
                Evento e = auxiliar.desapilar();
                String mensajeLimpio = e.getMensaje().replace(",", " ");
                escritor.println(e.getNivel() + "," + mensajeLimpio + "," + e.getTimestamp());
                pilaEventos.apilar(e);
            }

            System.out.println("CSV exportado en: " + archivo);
        } catch (IOException ex) {
            System.out.println("Error al exportar CSV: " + ex.getMessage());
        }
    }
}
```

### 4) Explicacion linea por linea

- `NivelEvento`: define la severidad.
- `Evento`: modelo con nivel, mensaje y fecha/hora.
- `registrar`: apila cada evento nuevo.
- `mostrarUltimos`:
  - desapila temporalmente para mostrar.
  - guarda en auxiliar.
  - vuelve a apilar para restaurar estado.
- `filtrarPorNivel`:
  - recorre toda la pila con auxiliar.
  - guarda coincidencias en lista.
  - restaura pila original.
- `exportarCSV`:
  - usa auxiliar para recorrer del mas viejo al mas nuevo.
  - escribe encabezado y filas.
  - repone la pila al final.

### 5) Ejemplo de uso (main)

```java
RegistradorErrores registrador = new RegistradorErrores();
registrador.registrar(new Evento(NivelEvento.INFO, "Sistema iniciado", LocalDateTime.now().toString()));
registrador.registrar(new Evento(NivelEvento.ERROR, "Fallo al conectar DB", LocalDateTime.now().toString()));
registrador.registrar(new Evento(NivelEvento.CRITICAL, "Servicio principal caido", LocalDateTime.now().toString()));

registrador.mostrarUltimos(2);
List<Evento> errores = registrador.filtrarPorNivel("ERROR");
System.out.println("Errores: " + errores.size());
registrador.exportarCSV("eventos.csv");
```

---

## Colas desde cero (nuevos ejercicios)

Ahora el mismo enfoque, pero para colas.

Regla clave de cola:

- FIFO = First In, First Out
- El primero que entra es el primero que sale

### Base comun para colas

Se agregaron dos implementaciones manuales usando `NodoGenerico<T>`:

1. `ColaEstatica<T>` (arreglo circular)
2. `ColaDinamica<T>` (nodos enlazados)

```java
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
        if (estaLlena()) return false;
        datos[fin] = new NodoGenerico<>(dato);
        fin = (fin + 1) % datos.length;
        tamano = tamano + 1;
        return true;
    }

    public T desencolar() {
        if (estaVacia()) return null;
        T dato = datos[frente].dato;
        datos[frente] = null;
        frente = (frente + 1) % datos.length;
        tamano = tamano - 1;
        return dato;
    }
}

class ColaDinamica<T> {
    private NodoGenerico<T> frente;
    private NodoGenerico<T> fin;
    private int tamano;

    public void encolar(T dato) {
        NodoGenerico<T> nuevo = new NodoGenerico<>(dato);
        if (frente == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
        tamano = tamano + 1;
    }

    public T desencolar() {
        if (frente == null) return null;
        T dato = frente.dato;
        frente = frente.siguiente;
        tamano = tamano - 1;
        if (frente == null) fin = null;
        return dato;
    }
}
```

Explicacion simple:

- En `ColaEstatica`, `frente` marca quien sale y `fin` donde entra el nuevo.
- Se usa `%` para que el arreglo se comporte como circular.
- En `ColaDinamica`, `frente` y `fin` son nodos enlazados.

---

## Ejercicio 5: Lista de espera de vuelo (Cola dinamica)

### 1) Explicacion sencilla

Los pasajeros entran a una lista de espera.  
Cuando hay asiento libre, se atiende al primero de la cola.

### 2) Estructura de clases

- `Pasajero`
- `ListaEsperaVuelo` con `ColaDinamica<Pasajero>`

### 3) Codigo completo

```java
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
        if (posicion == -1) return -1;
        return posicion * TIEMPO_ROTACION_ASIENTO_MIN;
    }
}
```

### 4) Explicacion linea por linea

- `unirseListaEspera`: encola y devuelve la posicion.
- `asignarAsientoLibre`: desencola el primero.
- `posicionActual`: rota la cola sin destruirla para buscar pasaporte.
- `tiempoEsperaEstimado`: usa la formula `posicion * tiempo_rotacion`.

### 5) Ejemplo de uso (main)

```java
ListaEsperaVuelo listaEspera = new ListaEsperaVuelo();
listaEspera.unirseListaEspera(new Pasajero("Ana", "PP-001", "ECONOMICA", "08:00"));
listaEspera.unirseListaEspera(new Pasajero("Luis", "PP-002", "EJECUTIVA", "08:03"));

Pasajero asignado = listaEspera.asignarAsientoLibre();
System.out.println(asignado);
System.out.println(listaEspera.posicionActual("PP-002"));
```

---

## Ejercicio 6: Simulacion de cajas (Cola estatica)

### 1) Explicacion sencilla

Hay 3 cajas, cada una con su cola estatica (capacidad 15).  
Cada cliente va a la cola con menos personas.

### 2) Estructura de clases

- `Cliente`
- `CajaSupermercado`
- `SimulacionSupermercado`

### 3) Codigo completo

```java
class Cliente {
    private String id;
    private int tiempoCompraMin;
    private int minutoLlegada;
}

class CajaSupermercado {
    private String nombre;
    private ColaEstatica<Cliente> cola;
    private int tiempoFinUltimoServicio;
    private int sumaEspera;
    private int atendidosEn60;

    public boolean recibirCliente(Cliente c) {
        return cola.encolar(c);
    }

    public void procesarCola() {
        while (!cola.estaVacia()) {
            Cliente c = cola.desencolar();
            int inicioAtencion = Math.max(tiempoFinUltimoServicio, c.getMinutoLlegada());
            int espera = inicioAtencion - c.getMinutoLlegada();
            sumaEspera = sumaEspera + espera;
            tiempoFinUltimoServicio = inicioAtencion + c.getTiempoCompraMin();
            if (tiempoFinUltimoServicio <= 60) {
                atendidosEn60 = atendidosEn60 + 1;
            }
        }
    }
}
```

### 4) Explicacion linea por linea

- `tiempoFinUltimoServicio`: reloj virtual de cada caja.
- `inicioAtencion`: depende de llegada del cliente y disponibilidad de la caja.
- `espera`: diferencia entre inicio de atencion y llegada.
- Si el servicio termina en `<= 60`, cuenta como atendido en el rango pedido.

### 5) Ejemplo de uso (main)

```java
SimulacionSupermercado simulacion = new SimulacionSupermercado();
simulacion.simularLlegadaClientes(20);
simulacion.mostrarEstadisticas();
```

---

## Ejercicio 7: Cola de descargas con reintentos (Cola dinamica)

### 1) Explicacion sencilla

Cada descarga se procesa en orden FIFO.  
Si falla y tiene menos de 3 intentos, vuelve al final de la cola.

### 2) Estructura de clases

- `EstadoDescarga` (enum)
- `Descarga`
- `GestorDescargas`

### 3) Codigo completo

```java
enum EstadoDescarga {
    PENDIENTE,
    COMPLETADA,
    FALLIDA
}

class GestorDescargas {
    private static final double PROBABILIDAD_FALLO = 0.30;
    private ColaDinamica<Descarga> cola;
    private int completadas;
    private int fallidas;
    private int reintentadas;

    public boolean procesarSiguiente() {
        Descarga actual = cola.desencolar();
        if (actual == null) return false;

        actual.aumentarIntento();
        boolean fallo = Math.random() < PROBABILIDAD_FALLO;

        if (!fallo) {
            actual.setEstado(EstadoDescarga.COMPLETADA);
            completadas = completadas + 1;
            return true;
        }

        if (actual.getIntentos() < 3) {
            reintentadas = reintentadas + 1;
            actual.setEstado(EstadoDescarga.PENDIENTE);
            cola.encolar(actual);
        } else {
            actual.setEstado(EstadoDescarga.FALLIDA);
            fallidas = fallidas + 1;
        }

        return true;
    }
}
```

### 4) Explicacion linea por linea

- `PROBABILIDAD_FALLO`: 30% de fallo simulado.
- `procesarSiguiente`:
  - toma la descarga al frente.
  - si completa, suma `completadas`.
  - si falla y le quedan intentos, reencola.
  - si llega a 3 intentos, queda como `FALLIDA`.

### 5) Ejemplo de uso (main)

```java
GestorDescargas gestor = new GestorDescargas();
gestor.agregarDescarga(new Descarga("url", "archivo.zip", 25));
gestor.procesarTodas();
gestor.estadisticas();
```

---

## Ejercicio 8: Cola con prioridad (Min-Heap manual)

### 1) Explicacion sencilla

Una cola con prioridad no sale por llegada sino por prioridad.  
Con min-heap, el menor valor siempre queda arriba.

### 2) Estructura de clases

- `ColaPrioridad<T extends Comparable<T>>`
- `AccionColaPrioridad<T>` (para aging en ejercicio 9)

### 3) Codigo completo

```java
class ColaPrioridad<T extends Comparable<T>> {
    private Object[] heap;
    private int tamano;

    public void insertar(T dato) {
        heap[tamano] = dato;
        subir(tamano);
        tamano = tamano + 1;
    }

    public T extraerMinimo() {
        T minimo = obtener(0);
        heap[0] = heap[tamano - 1];
        tamano = tamano - 1;
        bajar(0);
        return minimo;
    }
}
```

### 4) Explicacion linea por linea

- `insertar`: pone al final y luego hace sift-up (`subir`).
- `extraerMinimo`: toma la raiz, mueve el ultimo a la raiz y baja (`bajar`).
- Padre de `i`: `(i - 1) / 2`.
- Hijos de `i`: `2*i + 1` y `2*i + 2`.

### 5) Ejemplo de uso (main)

```java
ColaPrioridad<Integer> cola = new ColaPrioridad<>();
cola.insertar(5);
cola.insertar(3);
cola.insertar(8);
cola.insertar(1);
cola.insertar(4);
System.out.println(cola.extraerMinimo());
```

---

## Ejercicio 9: Planificador de tareas SO (prioridad + aging)

### 1) Explicacion sencilla

El proceso con numero de prioridad menor sale primero.  
Para evitar starvation, se aplica aging a los que esperan.

### 2) Estructura de clases

- `ProcesoSO implements Comparable<ProcesoSO>`
- `PlanificadorTareasSO` con `ColaPrioridad<ProcesoSO>`

### 3) Codigo completo

```java
class PlanificadorTareasSO {
    private ColaPrioridad<ProcesoSO> colaProcesos;

    public ProcesoSO ejecutarSiguiente(int quantum) {
        ProcesoSO actual = colaProcesos.extraerMinimo();
        if (actual == null) return null;

        actual.sumarCPU(quantum);
        actual.setPrioridad(actual.getPrioridad() + 1);

        colaProcesos.aplicarATodos(new AccionColaPrioridad<ProcesoSO>() {
            @Override
            public void aplicar(ProcesoSO p) {
                int nueva = p.getPrioridad() - 1;
                if (nueva < 1) nueva = 1;
                p.setPrioridad(nueva);
            }
        });

        colaProcesos.insertar(actual);
        return actual;
    }
}
```

### 4) Explicacion linea por linea

- Saca el mas urgente (`extraerMinimo`).
- Le suma CPU por un quantum.
- El ejecutado pierde urgencia (`+1`) para repartir CPU.
- A los demas se les mejora prioridad (`-1`) con limite en 1.
- Se reinsertan para seguir la simulacion.

### 5) Ejemplo de uso (main)

```java
PlanificadorTareasSO planificador = new PlanificadorTareasSO();
planificador.admitirProceso(new ProcesoSO("P1", "Proceso 1", 1, 0, 0));
planificador.admitirProceso(new ProcesoSO("P2", "Proceso 2", 5, 0, 0));
planificador.simularQuantums(20, 1);
```

---

## Main completo del taller

Tu main ya esta incluido en `TallerPilasDesdeCero.java` y ahora prueba 9 ejercicios:

1. 4 de pilas
2. 5 de colas

---

## Resumen final para estudiar

1. Pila: LIFO (ultimo en entrar, primero en salir).
2. Cola: FIFO (primero en entrar, primero en salir).
3. Estaticas: usan arreglo con capacidad fija.
4. Dinamicas: usan nodos enlazados y crecen segun necesidad.
5. Para consultar sin destruir estructura, usa auxiliar y restaura.
6. Prioridad con heap: no manda la llegada, manda el menor valor de prioridad.
