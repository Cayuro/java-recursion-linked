# 📚 Taller Listas Enlazadas y Recursividad — Guía de Estudio Profunda

> Archivo: `TallerListasRecursividad.java`  
> Nivel: Fundamentos de Estructuras de Datos en Java  
> Objetivo: Entender **cada línea** del código, no solo memorizarla.

---

## 🧭 Mapa del Archivo

```
TallerListasRecursividad.java
│
├── main()                          → Punto de entrada. Prueba todo.
│
├── BLOQUE 1 — Operaciones básicas
│   ├── NodoSimple                  → Clase nodo para lista simple
│   ├── ListaSimpleBasica           → 8 métodos básicos de lista simple
│   ├── NodoDoble                   → Clase nodo para lista doble
│   └── ListaDobleBasica            → 7 métodos básicos de lista doble
│
├── BLOQUE 2 — Casos de uso reales
│   ├── NodoTexto                   → Nodo doble que guarda String
│   ├── HistorialNavegacion         → Lista doble con límite de tamaño
│   ├── ColaImpresionPrioridad      → Cola con inserción prioritaria
│   ├── SistemaUndo                 → Pila (LIFO) para deshacer acciones
│   ├── DetectorCorreosDuplicados   → Lista simple con validación previa
│   ├── PlaylistSimple              → Lista simple con inversión (reverse)
│   ├── GaleriaFotos                → Lista doble con cursor de posición
│   ├── ReproductorCircular         → Lista circular simple
│   ├── EditorTextoCursor           → Lista de chars con inserción por posición
│   ├── NodoChar                    → Nodo de caracteres
│   ├── BuscadorPuntoMedio          → Técnica tortuga y liebre
│   └── TurnosBancarios             → Eliminación por nombre en lista doble
│
├── BLOQUE 3 — Recursividad
│   └── RecursividadBasica          → 10 funciones recursivas clásicas
│
└── BLOQUE 4 — Desafíos avanzados
    ├── DesafiosListas              → 10 algoritmos avanzados
    ├── NodoCanal                   → Nodo con sublista embebida
    ├── ListaDobleCircularMulticanal→ Lista de listas circular doble
    ├── NodoRandom                  → Nodo con puntero aleatorio
    └── NodoMulti                   → Nodo con hijo (estructura jerárquica)
```

---

## 🔑 Conceptos Mentales Clave ANTES de leer el código

### ¿Qué es un nodo?
Un nodo es un objeto que guarda **dos cosas**:
1. Un **dato** (el valor)
2. Una **referencia** al siguiente nodo (o al anterior también, en lista doble)

```
Lista simple:    [10] → [20] → [5] → null
                  ↑
                cabeza
```

```
Lista doble:     null ← [3] ⇄ [7] ⇄ [9] → null
                          ↑
                        cabeza
```

### La regla de oro de los punteros
> **Nunca pierdas una referencia antes de reasignarla.**

Si haces `cabeza = cabeza.sig` sin guardar nada, perdiste el nodo anterior para siempre. El GC de Java lo eliminará.

---

## 📦 BLOQUE 1 — Operaciones Fundamentales

### `NodoSimple` — El átomo de la lista simple

```java
class NodoSimple {
    int dato;          // El valor que guarda el nodo
    NodoSimple sig;    // Referencia al SIGUIENTE nodo. null = "no hay siguiente"

    NodoSimple(int dato) {
        this.dato = dato;  // Asigna el valor recibido
        this.sig = null;   // Por defecto, el nuevo nodo no apunta a nadie
    }
}
```

**Diagrama al crear `new NodoSimple(10)`:**
```
[dato=10 | sig=null]
```

---

### `ListaSimpleBasica` — La lista en sí

```java
class ListaSimpleBasica {
    NodoSimple cabeza;  // Referencia al PRIMER nodo. Si es null, lista vacía.
```

> `cabeza` es tu único punto de acceso a toda la lista. Si lo pierdes, pierdes todo.

---

#### Método 1: `insertarInicio(int valor)`

```java
public void insertarInicio(int valor) {
    NodoSimple nuevoNodo = new NodoSimple(valor);
    // 1. Creamos el nuevo nodo. Aún no está conectado.
    //    Estado: nuevoNodo → null  |  cabeza → [vieja lista]

    nuevoNodo.sig = cabeza;
    // 2. El nuevo nodo apunta a lo que era la cabeza.
    //    Estado: nuevoNodo → [vieja lista]

    cabeza = nuevoNodo;
    // 3. La cabeza ahora ES el nuevo nodo.
    //    Estado: cabeza → nuevoNodo → [vieja lista]
}
```

**Visualización — insertar 10 en lista vacía, luego 20 al inicio:**
```
Inicial:   cabeza = null

insertarInicio(10):
  nuevoNodo = [10|null]
  nuevoNodo.sig = null  (cabeza era null)
  cabeza = [10|null]
  → cabeza → [10] → null

insertarInicio(20):
  nuevoNodo = [20|null]
  nuevoNodo.sig = cabeza  → nuevoNodo = [20 | → [10]]
  cabeza = [20 | → [10]]
  → cabeza → [20] → [10] → null
```

**Complejidad:** O(1) — siempre son 3 operaciones, sin importar el tamaño de la lista.

---

#### Método 2: `insertarFinal(int valor)`

```java
public void insertarFinal(int valor) {
    NodoSimple nuevoNodo = new NodoSimple(valor);
    // Creamos el nodo. sig = null (lógico: irá al final)

    if (cabeza == null) {
        // Caso especial: lista vacía. El nuevo nodo ES la cabeza.
        cabeza = nuevoNodo;
    } else {
        // Lista tiene al menos un nodo. Hay que llegar al último.
        NodoSimple aux = cabeza;
        // aux es un "cursor" que empieza en la cabeza.
        // NUNCA uses cabeza directamente para recorrer o la perderás.

        while (aux.sig != null) {
            // Mientras haya un siguiente, avanza.
            aux = aux.sig;
        }
        // Aquí aux.sig == null → aux ES el último nodo.

        aux.sig = nuevoNodo;
        // Conectamos: el último apunta al nuevo. 
        // El nuevo ya tiene sig=null (correcto para el final).
    }
}
```

**Visualización — insertar 5 al final de [10] → [20]:**
```
aux empieza en [10]
  ¿aux.sig (= [20]) != null? Sí → aux = [20]
  ¿aux.sig (= null) != null? No → salimos del while
aux ahora es [20]
aux.sig = [5]
Lista final: [10] → [20] → [5] → null
```

**Complejidad:** O(n) — hay que recorrer toda la lista.

---

#### Método 3: `mostrar()`

```java
public void mostrar() {
    NodoSimple puntero = cabeza;
    // Usamos "puntero" como cursor, NO tocamos cabeza directamente.

    if (puntero == null) {
        System.out.println("(vacia)");
    } else {
        while (puntero != null) {
            System.out.print(puntero.dato + " ");
            // Imprime el dato del nodo actual

            puntero = puntero.sig;
            // Mueve el cursor al siguiente. Comentario del autor:
            // "Aqui muevo el puntero al siguiente para no perder la lista"
            // → Correcto: movemos PUNTERO, no cabeza.
        }
        System.out.println(); // Salto de línea al final
    }
}
```

**Patrón fundamental:** `while (puntero != null)` + `puntero = puntero.sig`  
Este patrón aparece en CASI TODOS los métodos de listas enlazadas.

---

#### Método 4: `contarNodos()`

```java
public int contarNodos() {
    int contador = 0;
    NodoSimple temporal = cabeza;
    // temporal: cursor. contador: acumulador.

    while (temporal != null) {
        contador = contador + 1;   // Suma 1 por cada nodo visitado
        temporal = temporal.sig;   // Avanza al siguiente
    }

    return contador;
    // Cuando temporal == null, ya no hay más nodos.
}
```

**Complejidad:** O(n) — no hay otra forma, no hay `length` automático en listas enlazadas.

---

#### Método 5: `buscarValor(int valor)` ⚠️ BUG INTENCIONAL

```java
public boolean buscarValor(int valor) {
    boolean encontrado = false;
    NodoSimple nodoActual = cabeza;

    while (nodoActual != null) {
        if (nodoActual.dato == valor) {
            encontrado = true;
            // ⚠️ ERROR DE PRINCIPIANTE: encontró el valor pero NO para el loop.
            // Sigue recorriendo todos los nodos restantes innecesariamente.
            // No afecta el RESULTADO (encontrado sigue siendo true),
            // pero hace trabajo extra. La versión correcta usaría "break" o "return true".
        } else {
            encontrado = encontrado; // 🚨 Línea completamente inútil. 
            // Es redundante: asignarse a sí mismo no cambia nada.
            // El autor la dejó "por estilo básico" pero demuestra código innecesario.
        }
        nodoActual = nodoActual.sig;
    }

    return encontrado;
}
```

**Versión óptima (no está en el código, pero deberías saber cómo se vería):**
```java
public boolean buscarValorOptimo(int valor) {
    NodoSimple nodoActual = cabeza;
    while (nodoActual != null) {
        if (nodoActual.dato == valor) return true; // Para inmediatamente
        nodoActual = nodoActual.sig;
    }
    return false;
}
```

---

#### Método 6: `eliminarPrimero()`

```java
public void eliminarPrimero() {
    if (cabeza == null) {
        return; // Lista vacía: no hay nada que eliminar.
    } else {
        cabeza = cabeza.sig;
        // Movemos cabeza al segundo nodo.
        // El primer nodo queda sin referencias → el GC de Java lo elimina de memoria.
    }
}
```

**Visualización — eliminar primero de [10] → [20] → [5]:**
```
Antes:  cabeza → [10] → [20] → [5] → null
cabeza = cabeza.sig = [20]
Después: cabeza → [20] → [5] → null
El nodo [10] ya no tiene referencias → desaparece.
```

**Complejidad:** O(1) — siempre son 2 operaciones.

---

#### Método 7: `vaciarLista()`

```java
public void vaciarLista() {
    cabeza = null;
    // Al poner cabeza a null, ya no hay acceso al primer nodo.
    // Sin acceso al primero, tampoco al segundo, tercero, etc.
    // Java libera toda la memoria automáticamente (Garbage Collector).
}
```

> No necesitas iterar y eliminar nodo por nodo como en C/C++. Java lo hace por ti.

---

#### Método 8: `obtenerMayor()`

```java
public int obtenerMayor() {
    if (cabeza == null) {
        return -1;  // Convención: -1 indica lista vacía. 
                    // ⚠️ Problema: ¿qué si los datos son negativos?
    }

    int mayor = cabeza.dato;
    // Iniciamos el mayor con el primer elemento.
    // Así siempre tenemos un candidato válido desde el inicio.

    NodoSimple n = cabeza.sig;
    // Empezamos a comparar desde el SEGUNDO nodo (el primero ya lo guardamos).

    while (n != null) {
        if (n.dato > mayor) {
            mayor = n.dato;  // Actualizamos si encontramos uno más grande.
        }
        n = n.sig;
    }

    return mayor;
}
```

---

### `NodoDoble` — El átomo de la lista doble

```java
class NodoDoble {
    int dato;
    NodoDoble sig;   // Apunta al SIGUIENTE nodo
    NodoDoble ant;   // Apunta al ANTERIOR nodo  ← esta es la diferencia

    NodoDoble(int dato) {
        this.dato = dato;
        this.sig = null;
        this.ant = null;
    }
}
```

**Diagrama de un nodo doble:**
```
null ← [ant | dato | sig] → siguiente
         ↑ el nodo actual
```

---

#### Método 9: `insertarInicio(int valor)` — Lista Doble

```java
public void insertarInicio(int valor) {
    NodoDoble nuevoNodo = new NodoDoble(valor);

    if (cabeza == null) {
        cabeza = nuevoNodo;  // Lista vacía: el nuevo es el único.
    } else {
        nuevoNodo.sig = cabeza;
        // 1. Nuevo apunta al que era primero (hacia adelante).
        
        cabeza.ant = nuevoNodo;
        // 2. El que era primero apunta hacia atrás al nuevo.
        //    ⚠️ ORDEN IMPORTA: primero conectas sig, luego ant.
        //    Si hicieras cabeza = nuevoNodo primero, perderías la referencia a cabeza.
        
        cabeza = nuevoNodo;
        // 3. Ahora el nuevo es la cabeza.
    }
}
```

**Visualización — insertar 3 al inicio de [7]:**
```
Antes:   null ← [7|null] → null      cabeza = [7]

Paso 1: nuevoNodo.sig = cabeza → [3|sig→[7]] ← [7] → null
Paso 2: cabeza.ant = nuevoNodo → [3|sig→[7]] ← [7] → null  ← ENLACE ATRÁS
                                        ↑ ant del [7] ahora apunta a [3]
Paso 3: cabeza = nuevoNodo

Después: null ← [3] ⇄ [7] → null
```

---

#### Método 12: `mostrarAtras()` — Lista Doble (técnica interesante)

```java
public void mostrarAtras() {
    if (cabeza == null) {
        System.out.println("(vacia)");
        return;
    }

    // PASO 1: Llegar al último nodo
    NodoDoble finalLista = cabeza;
    while (finalLista.sig != null) {
        finalLista = finalLista.sig;
    }
    // finalLista ahora ES el último nodo (el que tiene sig == null).

    // PASO 2: Recorrer hacia atrás usando .ant
    while (finalLista != null) {
        System.out.print(finalLista.dato + " ");
        finalLista = finalLista.ant;
        // .ant va hacia el nodo anterior. Cuando llegamos a cabeza, ant == null → para.
    }
    System.out.println();
}
```

**Por qué necesitamos dos pasos:** En lista doble no hay un puntero directo al final. Tenemos que llegar caminando. Pero una vez ahí, podemos volver usando `.ant`.

---

#### Método 14: `modificarDatos(int valorSuma)`

```java
public void modificarDatos(int valorSuma) {
    NodoDoble nodoActual = cabeza;

    while (nodoActual != null) {
        nodoActual.dato = nodoActual.dato + valorSuma;
        // Modificamos el dato del nodo actual (suma valorSuma a cada elemento).
        nodoActual = nodoActual.sig;
    }
}
```

> Diferencia conceptual: este método modifica el **contenido** de los nodos, no la **estructura** de la lista. Los punteros `sig` y `ant` no cambian.

---

## 🏙️ BLOQUE 2 — Casos de Uso Reales

### `NodoTexto` — Nodo doble con dato `String`

```java
class NodoTexto {
    String dato;     // Ahora guarda texto en vez de int
    NodoTexto sig;
    NodoTexto ant;   // Doble enlace (aunque no todos los usos lo usan)
    // ...
}
```

---

### Caso 1: `HistorialNavegacion` — Lista doble con límite

```java
class HistorialNavegacion {
    NodoTexto cabeza;
    int limite;       // Máximo de URLs que guarda

    HistorialNavegacion(int limite) {
        this.limite = limite;
        this.cabeza = null;
    }

    public void visitar(String url) {
        NodoTexto nuevoNodo = new NodoTexto(url);
        
        // 1. Insertar al inicio (lo más reciente queda primero)
        nuevoNodo.sig = cabeza;
        if (cabeza != null) {
            cabeza.ant = nuevoNodo;
        }
        cabeza = nuevoNodo;

        // 2. Si nos pasamos del límite, eliminar el más antiguo (el último)
        int total = contar();
        if (total > limite) {
            eliminarUltimo();
        }
    }
```

**Patrón de diseño:** Lo más reciente va al frente, lo más viejo atrás. Si el historial se llena, cae el más antiguo. Como un buffer circular manual.

**Overhead del diseño:** Llama a `contar()` en cada `visitar()`. Eso es O(n) por inserción. Una implementación más eficiente mantendría un contador aparte. Aquí se sacrifica eficiencia por claridad.

---

### Caso 3: `SistemaUndo` — Pila con lista enlazada

```java
class SistemaUndo {
    NodoTexto tope;   // "tope" en vez de "cabeza" → semántica de pila

    public void guardarAccion(String accion) {
        NodoTexto nuevoNodo = new NodoTexto(accion);
        nuevoNodo.sig = tope;   // El nuevo va encima del tope actual
        tope = nuevoNodo;       // El nuevo ES el tope
    }

    public String undo() {
        if (tope == null) {
            return null;   // No hay nada que deshacer
        }

        String accion = tope.dato;  // Guardamos el dato del tope
        tope = tope.sig;            // Bajamos el tope (eliminamos el de arriba)
        return accion;              // Retornamos la acción que se deshace
    }
}
```

**Concepto:** Una lista enlazada con inserción y extracción solo por el inicio ES una pila (LIFO). No necesitas una clase Stack especial.

```
guardarAccion("escribir")  → tope → [escribir] → null
guardarAccion("pegar")     → tope → [pegar] → [escribir] → null
guardarAccion("borrar")    → tope → [borrar] → [pegar] → [escribir] → null

undo() → retorna "borrar"  → tope → [pegar] → [escribir] → null
undo() → retorna "pegar"   → tope → [escribir] → null
```

---

### Caso 5: `PlaylistSimple` — Inversión (algoritmo de 3 punteros)

Este es el algoritmo más importante del bloque 2. Memoriza la técnica.

```java
public void invertir() {
    NodoTexto anterior = null;    // El "que ya procesé"
    NodoTexto actual = cabeza;    // El nodo que estoy procesando ahora
    NodoTexto siguiente;          // El que viene (lo guardo ANTES de romper el enlace)

    while (actual != null) {
        siguiente = actual.sig;   // 1. Guardo el siguiente ANTES de perderlo
        actual.sig = anterior;    // 2. Invierto el enlace: apunto atrás en vez de adelante
        anterior = actual;        // 3. Avanzo "anterior" al nodo actual
        actual = siguiente;       // 4. Avanzo "actual" al que guardé
    }
    // Cuando actual == null, anterior es el último nodo (nuevo inicio)

    cabeza = anterior;            // El último nodo es ahora la cabeza
}
```

**Traza completa — invertir [A] → [B] → [C] → null:**

```
Inicio: anterior=null, actual=[A], cabeza=[A]

Iteración 1:
  siguiente = [B]
  actual.sig = null    → [A] → null
  anterior = [A]
  actual = [B]

Iteración 2:
  siguiente = [C]
  actual.sig = [A]     → [B] → [A] → null
  anterior = [B]
  actual = [C]

Iteración 3:
  siguiente = null
  actual.sig = [B]     → [C] → [B] → [A] → null
  anterior = [C]
  actual = null

Fin del while. cabeza = anterior = [C]
Lista final: [C] → [B] → [A] → null ✓
```

---

### Caso 7: `ReproductorCircular` — Lista circular

```java
public void agregar(String cancion) {
    NodoTexto nuevoNodo = new NodoTexto(cancion);

    if (cabeza == null) {
        cabeza = nuevoNodo;
        nuevoNodo.sig = cabeza;   // ⭐ El único nodo apunta A SÍ MISMO
    } else {
        // Llegar al último (el que apunta DE VUELTA a cabeza)
        NodoTexto aux = cabeza;
        while (aux.sig != cabeza) {  // ← Condición diferente: no != null sino != cabeza
            aux = aux.sig;
        }
        // aux es el último nodo

        aux.sig = nuevoNodo;         // Último apunta al nuevo
        nuevoNodo.sig = cabeza;      // Nuevo apunta a la cabeza (cierra el círculo)
    }
}
```

**Diagrama lista circular:**
```
cabeza → [Cancion1] → [Cancion2] → [Cancion3] ↗
              ↑_________________________________|
```

**¿Por qué `do-while` en `siguienteCancion`?**
```java
do {
    if (aux.dato.equals(actual)) {
        return aux.sig.dato;
    }
    aux = aux.sig;
} while (aux != cabeza);
```
En una lista circular, `aux != null` NUNCA es verdadero. El loop nunca pararía. El `do-while` con `aux != cabeza` da exactamente una vuelta.

---

### Caso 9: `BuscadorPuntoMedio` — Algoritmo Tortuga y Liebre

```java
public NodoSimple obtenerMedio(NodoSimple cabeza) {
    if (cabeza == null) {
        return null;
    }

    NodoSimple tortuga = cabeza;   // Avanza 1 nodo por iteración
    NodoSimple liebre = cabeza;    // Avanza 2 nodos por iteración

    while (liebre != null && liebre.sig != null) {
        tortuga = tortuga.sig;       // +1
        liebre = liebre.sig.sig;     // +2
    }
    // Cuando liebre llega al final, tortuga está en la mitad

    return tortuga;
}
```

**Traza — lista [1] → [2] → [3] → [4] → [5]:**
```
Inicio:     tortuga=[1], liebre=[1]
Iter 1:     tortuga=[2], liebre=[3]
Iter 2:     tortuga=[3], liebre=[5]
  liebre.sig = null → sale del while
Retorna tortuga = [3] ✓ (el nodo del medio)
```

**¿Por qué funciona?** Si la liebre va el doble de rápido, cuando llega al final, la tortuga habrá recorrido la mitad.

---

### Caso 10: `TurnosBancarios` — Eliminación por nombre en lista doble

```java
public boolean retirarCliente(String nombre) {
    NodoTexto aux = frente;

    while (aux != null) {
        if (aux.dato.equals(nombre)) {
            if (aux.ant == null) {
                // Es el PRIMERO de la lista (no tiene anterior)
                frente = aux.sig;
                if (frente != null) {
                    frente.ant = null;   // El nuevo frente ya no apunta atrás
                }
            } else {
                // Está en el MEDIO o es el ÚLTIMO
                aux.ant.sig = aux.sig;
                // El anterior ahora apunta al siguiente del eliminado.

                if (aux.sig != null) {
                    aux.sig.ant = aux.ant;
                    // El siguiente apunta atrás al anterior del eliminado.
                }
                // Si aux es el último (aux.sig == null), no hay siguiente que actualizar.
            }
            return true;   // Encontrado y eliminado
        }
        aux = aux.sig;
    }

    return false;   // No encontrado
}
```

**Visualización — eliminar "Maria" de: [Ana] ⇄ [Maria] ⇄ [Luis]:**
```
aux = [Maria]
aux.ant = [Ana] → NO es null, entonces vamos al else

aux.ant.sig = aux.sig → [Ana].sig = [Luis]
aux.sig.ant = aux.ant → [Luis].ant = [Ana]

Resultado: [Ana] ⇄ [Luis]
El nodo [Maria] ya no tiene referencias → GC lo elimina.
```

---

## ⚡ BLOQUE 3 — Recursividad

### Regla de las 2 preguntas:
1. **¿Cuál es el caso base?** (¿cuándo para?)
2. **¿Cómo cada llamada se acerca al caso base?**

---

### Función 1: `factorial(int n)`

```java
public static long factorial(int n) {
    if (n <= 1) {
        return 1;
        // Caso base: factorial(0) = 1, factorial(1) = 1
        // Sin este if, la recursión nunca pararía → StackOverflowError
    }
    return n * factorial(n - 1);
    // Caso recursivo: 5! = 5 × 4! = 5 × 4 × 3! = ...
    // Cada llamada reduce n en 1, acercándonos al caso base.
}
```

**Árbol de llamadas — factorial(5):**
```
factorial(5)
  5 * factorial(4)
        4 * factorial(3)
              3 * factorial(2)
                    2 * factorial(1)
                          return 1
                    return 2 * 1 = 2
              return 3 * 2 = 6
        return 4 * 6 = 24
  return 5 * 24 = 120
```

**Stack de ejecución:** Java apila cada llamada. Con n=5 se abren 5 marcos en la pila. Con n=10000 → StackOverflowError.

---

### Función 4: `invertirCadena(String txt)`

```java
public static String invertirCadena(String txt) {
    if (txt == null || txt.length() <= 1) {
        return txt;
        // Caso base: cadena vacía o de un solo char ya está "invertida"
    }
    return invertirCadena(txt.substring(1)) + txt.charAt(0);
    //      ↑ Invierte el resto                ↑ Pone el primer char AL FINAL
}
```

**Traza — invertirCadena("hola"):**
```
invertirCadena("hola")
  invertirCadena("ola") + 'h'
    invertirCadena("la") + 'o'
      invertirCadena("a") + 'l'
        return "a"          (caso base: length == 1)
      return "a" + 'l' = "al"
    return "al" + 'o' = "alo"
  return "alo" + 'h' = "aloh"
```

---

### Función 5: `fibonacci(int n)` ⚠️ Versión ineficiente

```java
public static int fibonacci(int n) {
    if (n <= 1) {
        return n;
        // fib(0) = 0, fib(1) = 1
    }
    return fibonacci(n - 1) + fibonacci(n - 2);
}
```

**¿Por qué es ineficiente?** `fibonacci(7)` llama a `fibonacci(6)` y `fibonacci(5)`. Pero `fibonacci(6)` también llama a `fibonacci(5)`. El mismo resultado se calcula múltiples veces.

```
fib(5)
├── fib(4)
│   ├── fib(3)       ← calculado
│   └── fib(2)       ← calculado
└── fib(3)           ← calculado DE NUEVO (repetido)
```

Para n=50, esto hace ~2^50 llamadas. La versión con memoización o iterativa sería O(n).

---

### Función 8: `esPalindromo(String palabra)` — Recursión con 2 índices

```java
public static boolean esPalindromo(String palabra) {
    if (palabra == null) return false;
    return revisarPalabra(palabra.toLowerCase(), 0, palabra.length() - 1);
}

private static boolean revisarPalabra(String txt, int ini, int fin) {
    if (ini >= fin) {
        return true;
        // Caso base: los índices se cruzaron o coincidieron.
        // Si llegamos aquí sin fallar, es palíndromo.
    }

    if (txt.charAt(ini) != txt.charAt(fin)) {
        return false;
        // Los extremos no coinciden → NO es palíndromo. Para inmediatamente.
    }

    return revisarPalabra(txt, ini + 1, fin - 1);
    // Si los extremos coinciden, verificamos los siguientes hacia adentro.
}
```

**Traza — "radar":**
```
revisarPalabra("radar", 0, 4)  → 'r' == 'r' ✓
  revisarPalabra("radar", 1, 3)  → 'a' == 'a' ✓
    revisarPalabra("radar", 2, 2)  → ini >= fin → return true
  return true
return true
```

---

### Función 9: `decimalABinario(int n)`

```java
public static String decimalABinario(int n) {
    if (n < 2) {
        return String.valueOf(n);
        // Caso base: 0 → "0", 1 → "1"
    }
    return decimalABinario(n / 2) + (n % 2);
    // La magia: primero calcula los dígitos más significativos (n/2),
    // luego agrega el bit menos significativo (n%2) al FINAL.
}
```

**Traza — decimalABinario(13):**
```
13 / 2 = 6, 13 % 2 = 1
  6 / 2 = 3, 6 % 2 = 0
    3 / 2 = 1, 3 % 2 = 1
      1 < 2 → return "1"
    return "1" + 1 = "11"
  return "11" + 0 = "110"
return "110" + 1 = "1101"

Verificación: 1101 en binario = 8 + 4 + 0 + 1 = 13 ✓
```

---

### Función 10: `mcd(int a, int b)` — Algoritmo de Euclides

```java
public static int mcd(int a, int b) {
    a = Math.abs(a);
    b = Math.abs(b);
    // Trabajamos con valores absolutos (MCD se define para positivos)

    if (b == 0) {
        return a;
        // Caso base: mcd(a, 0) = a
    }

    return mcd(b, a % b);
    // Propiedad: mcd(a, b) = mcd(b, a mod b)
}
```

**Traza — mcd(48, 18):**
```
mcd(48, 18) → mcd(18, 48%18=12) → mcd(18, 12)
mcd(18, 12) → mcd(12, 18%12=6) → mcd(12, 6)
mcd(12, 6)  → mcd(6, 12%6=0) → mcd(6, 0)
mcd(6, 0)   → b == 0 → return 6
```

---

## 🏋️ BLOQUE 4 — Desafíos Avanzados

### Desafío 2: `tieneCiclo(NodoSimple cabeza)` — Floyd's Algorithm

```java
public boolean tieneCiclo(NodoSimple cabeza) {
    NodoSimple lento = cabeza;
    NodoSimple rapido = cabeza;

    while (rapido != null && rapido.sig != null) {
        lento = lento.sig;         // Avanza 1
        rapido = rapido.sig.sig;   // Avanza 2

        if (lento == rapido) {
            return true;   // Se encontraron → hay ciclo
        }
    }

    return false;   // rapido llegó a null → no hay ciclo
}
```

**¿Por qué funciona?** En un ciclo, el rápido siempre "alcanza" al lento porque la diferencia entre ellos reduce en 1 por vuelta (uno avanza 2, el otro 1 → diferencia = 2-1 = 1 por iteración).

**Sin ciclo:**
```
[1] → [2] → [3] → null
rapido llega a null → return false
```

**Con ciclo:**
```
[1] → [2] → [3] → [4]
                    ↑ ← [6] ← [5]  (ciclo: [4]→[5]→[6]→[3])
lento y rapido eventualmente coinciden → return true
```

---

### Desafío 3: `eliminarDesdeFinal` — Nodo fantasma

```java
public NodoSimple eliminarDesdeFinal(NodoSimple cabeza, int n) {
    NodoSimple fantasma = new NodoSimple(0);
    fantasma.sig = cabeza;
    // "fantasma" es un nodo artificial antes de la cabeza.
    // Simplifica eliminar el primer nodo real (cabeza).

    NodoSimple rapido = fantasma;
    NodoSimple lento = fantasma;

    // Adelantamos "rapido" exactamente n+1 posiciones
    int i = 0;
    while (i <= n && rapido != null) {
        rapido = rapido.sig;
        i = i + 1;
    }

    // Ahora lento y rapido avanzan juntos hasta que rapido llega a null.
    // Cuando rapido == null, lento está en el nodo ANTES del que debemos eliminar.
    while (rapido != null) {
        rapido = rapido.sig;
        lento = lento.sig;
    }

    if (lento.sig != null) {
        lento.sig = lento.sig.sig;   // Salta el nodo objetivo
    }

    return fantasma.sig;   // La lista sin el nodo eliminado
}
```

**Visualización — eliminar 2do desde el final de [1,2,3,4,5], n=2:**
```
Con fantasma: [F] → [1] → [2] → [3] → [4] → [5] → null

Avanzar rapido n+1=3 posiciones desde F:
  rapido = [1], [2], [3]

Avanzar juntos hasta rapido == null:
  rapido=[4], lento=[1]
  rapido=[5], lento=[2]
  rapido=null, lento=[3]   ← lento está ANTES del nodo a eliminar ([4])

lento.sig = lento.sig.sig → [3].sig = [5]
Lista final: [1] → [2] → [3] → [5] ✓ (eliminamos [4], el 2do desde el final)
```

---

### Desafío 5: `intercambiarParejas` — Nodo fantasma + restructuración

```java
public NodoSimple intercambiarParejas(NodoSimple cabeza) {
    NodoSimple fantasma = new NodoSimple(0);
    fantasma.sig = cabeza;
    NodoSimple puntero = fantasma;  // puntero está ANTES del par a intercambiar

    while (puntero.sig != null && puntero.sig.sig != null) {
        NodoSimple a = puntero.sig;       // Primer del par
        NodoSimple b = puntero.sig.sig;   // Segundo del par

        a.sig = b.sig;    // 1. a apunta al nodo DESPUÉS del par
        b.sig = a;        // 2. b apunta a a (b queda primero)
        puntero.sig = b;  // 3. El nodo anterior al par apunta a b (nuevo primero)

        puntero = a;      // puntero avanza: a quedó de segundo, es el nuevo "antes del par"
    }

    return fantasma.sig;
}
```

**Traza — [1] → [2] → [3] → [4]:**
```
Iteración 1: puntero=[F], a=[1], b=[2]
  a.sig = b.sig = [3]     → [1] → [3]
  b.sig = a = [1]         → [2] → [1] → [3]
  puntero.sig = b = [2]   → [F] → [2] → [1] → [3]
  puntero = a = [1]

Iteración 2: puntero=[1], a=[3], b=[4]
  a.sig = b.sig = null    → [3] → null
  b.sig = a = [3]         → [4] → [3]
  puntero.sig = b = [4]   → [1] → [4] → [3]
  puntero = a = [3]

Resultado: [F] → [2] → [1] → [4] → [3]
return fantasma.sig = [2] → [1] → [4] → [3] ✓
```

---

### Desafío 9: `clonarConRandom` — Técnica de entrelazado

Este es el algoritmo más sofisticado del taller. Tres pasadas:

```java
public NodoRandom clonarConRandom(NodoRandom cabeza) {
    if (cabeza == null) return null;

    // PASADA 1: Entrelazar copias
    // Lista original: [A] → [B] → [C]
    // Resultado:      [A] → [A'] → [B] → [B'] → [C] → [C']
    NodoRandom aux = cabeza;
    while (aux != null) {
        NodoRandom copia = new NodoRandom(aux.dato);
        copia.sig = aux.sig;    // La copia apunta al siguiente ORIGINAL
        aux.sig = copia;        // El original apunta a SU copia
        aux = copia.sig;        // Avanzamos al siguiente original
    }

    // PASADA 2: Asignar random a las copias
    // La copia de X está en X.sig
    // El random de X apunta a Y → el random de la copia de X debe apuntar a la copia de Y
    // La copia de Y está en Y.sig
    aux = cabeza;
    while (aux != null) {
        if (aux.random != null) {
            aux.sig.random = aux.random.sig;
            // copia_de_aux.random = copia_de_(aux.random)
        }
        aux = aux.sig.sig;  // Saltar la copia, ir al siguiente original
    }

    // PASADA 3: Separar las dos listas
    aux = cabeza;
    NodoRandom inicioCopia = cabeza.sig;  // La copia empieza en el segundo nodo

    while (aux != null) {
        NodoRandom copia = aux.sig;
        aux.sig = copia.sig;       // Original vuelve a apuntar al siguiente original

        if (copia.sig != null) {
            copia.sig = copia.sig.sig;  // Copia apunta al siguiente en la copia
        }

        aux = aux.sig;   // Avanzar al siguiente original
    }

    return inicioCopia;
}
```

---

### Desafío 10: `flatten` — Aplanado recursivo de lista jerárquica

```java
public NodoMulti flatten(NodoMulti cabeza) {
    if (cabeza == null) return null;

    NodoMulti aux = cabeza;

    while (aux != null) {
        if (aux.child != null) {
            NodoMulti siguiente = aux.next;      // Guardamos el siguiente antes de perderlo
            NodoMulti hijo = flatten(aux.child); // 🔄 RECURSIÓN: aplana el hijo primero
            
            aux.next = hijo;                     // Conectamos: aux → lista_hijo_aplanada
            aux.child = null;                    // El hijo ya fue integrado, borrar referencia

            // Llegar al final del hijo para reconectar con "siguiente"
            NodoMulti colaHijo = aux;
            while (colaHijo.next != null) {
                colaHijo = colaHijo.next;
            }

            colaHijo.next = siguiente;   // El final del hijo conecta con el siguiente original
        }
        aux = aux.next;
    }

    return cabeza;
}
```

**Visualización:**
```
Antes:
[1] → [2] → [5]
       ↓child
      [3] → [4]

Después de flatten:
[1] → [2] → [3] → [4] → [5]
```

---

## 🐛 Bugs y Anti-patterns en el código

| Ubicación | Problema | Impacto |
|-----------|----------|---------|
| `buscarValor` | Continúa el loop tras encontrar el valor | O(n) siempre, debería parar con `break`/`return` |
| `buscarValor` | `encontrado = encontrado` (línea inútil) | Ninguno, puro ruido |
| `obtenerMayor` | Retorna -1 para lista vacía | Falla si todos los datos son negativos |
| `estaVacia` | `if (cabeza == null) return true; else return false;` | Funciona, pero podría ser `return cabeza == null;` |
| `HistorialNavegacion.visitar` | Llama `contar()` en cada inserción | O(n²) para n inserciones. Un contador aparte sería O(n). |

---

## 📊 Comparación de Complejidades

| Operación | Lista Simple | Lista Doble | Array |
|-----------|-------------|-------------|-------|
| Insertar inicio | O(1) | O(1) | O(n) |
| Insertar final | O(n) | O(n)* | O(1) amortizado |
| Eliminar inicio | O(1) | O(1) | O(n) |
| Eliminar final | O(n) | O(n)* | O(1) |
| Buscar por valor | O(n) | O(n) | O(n) |
| Acceso por índice | O(n) | O(n) | O(1) |

*En listas dobles con puntero al último nodo sería O(1), pero este código no implementa eso.

---

## 🧩 Patrones que debes reconocer

```
1. RECORRIDO BÁSICO:
   NodoSimple aux = cabeza;
   while (aux != null) { ... aux = aux.sig; }

2. INSERCIÓN AL INICIO (simple):
   nuevo.sig = cabeza;
   cabeza = nuevo;

3. INSERCIÓN AL INICIO (doble):
   nuevo.sig = cabeza;
   cabeza.ant = nuevo;
   cabeza = nuevo;

4. LLEGAR AL ÚLTIMO NODO:
   while (aux.sig != null) { aux = aux.sig; }

5. DOS PUNTEROS (para medio, n desde el final, ciclos):
   NodoSimple lento = cabeza;
   NodoSimple rapido = cabeza;

6. NODO FANTASMA (simplifica eliminar el primero):
   NodoSimple fantasma = new NodoSimple(0);
   fantasma.sig = cabeza;

7. INVERSIÓN DE LISTA:
   NodoSimple anterior = null, actual = cabeza, siguiente;
   while (actual != null) {
       siguiente = actual.sig;
       actual.sig = anterior;
       anterior = actual;
       actual = siguiente;
   }
   cabeza = anterior;

8. CASO BASE RECURSIÓN:
   if (condicion_parada) return valor_base;
   return f(problema_reducido);
```

---

## 🎯 Checklist de comprensión

Después de estudiar este archivo, deberías poder:

- [ ] Dibujar en papel cualquier lista antes y después de cada operación
- [ ] Identificar los 3 casos típicos de eliminación: primero, medio, último
- [ ] Explicar por qué `puntero = puntero.sig` no rompe la lista pero `cabeza = cabeza.sig` sí puede
- [ ] Describir la diferencia entre lista simple, doble y circular
- [ ] Trazar las llamadas recursivas de factorial(4) en papel
- [ ] Explicar el truco del nodo fantasma con tus propias palabras
- [ ] Describir el algoritmo de la tortuga y la liebre
- [ ] Decir cuándo usar lista enlazada vs array

---

*Documento generado para estudio en Google Colab / Jupyter Notebook*