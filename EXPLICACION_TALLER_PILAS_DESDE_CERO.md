# Explicacion de TallerPilasDesdeCero.java

Este documento explica, de forma guiada, todo lo que hace el archivo `TallerPilasDesdeCero.java`.

## 1) Idea general del archivo

El archivo contiene:

- Una clase principal con `main` que ejecuta 9 ejercicios.
- Implementaciones manuales de estructuras de datos:
  - Pila estatica (con arreglo)
  - Pila dinamica (con nodos enlazados)
  - Cola estatica circular
  - Cola dinamica enlazada
  - Cola de prioridad tipo Min-Heap
- Clases de dominio para cada ejercicio (nivel, transaccion, evento, pasajero, cliente, descarga, proceso, etc.).

La intencion didactica es mostrar como resolver problemas reales usando estructuras desde cero, sin usar `Stack`, `Queue` o `PriorityQueue` de Java.

## 2) Flujo del `main`

En `main` se ejecutan 9 demostraciones, una tras otra:

1. Gestion de niveles de videojuego con pila.
2. Validador basico de etiquetas HTML.
3. Historial bancario y rollback de transacciones.
4. Registro de eventos/errores y exportacion CSV.
5. Lista de espera de vuelo con cola.
6. Simulacion de cajas de supermercado.
7. Gestor de descargas con reintentos.
8. Cola de prioridad manual y heap sort.
9. Planificador de procesos de SO con prioridades y aging.

Cada ejercicio imprime resultados por consola para mostrar su funcionamiento.

## 3) Base de estructuras: nodo, pilas y colas

## 3.1 NodoGenerico<T>

`NodoGenerico<T>` tiene dos campos:

- `dato`: el valor guardado.
- `siguiente`: referencia al siguiente nodo.

Se reutiliza para pilas y colas dinamicas.

## 3.2 PilaEstatica<T>

Implementada con arreglo de nodos y un indice `tope`.

- `apilar`: inserta en `tope` si hay espacio.
- `desapilar`: decrementa `tope` y devuelve ultimo dato.
- `cima`: consulta ultimo elemento sin sacar.
- `estaVacia` y `estaLlena` controlan limites.
- `obtenerEnIndice` permite leer por posicion (util para mostrar rutas).

Complejidad promedio: O(1) en apilar/desapilar/cima.

## 3.3 PilaDinamica<T>

Implementada con nodos enlazados y referencia `cima`.

- `apilar`: inserta nuevo nodo al frente.
- `desapilar`: quita nodo del frente.
- `verCima`: consulta cima sin modificar.
- No tiene limite fijo de capacidad.

Complejidad: O(1) en operaciones principales.

## 3.4 ColaEstatica<T>

Cola circular con arreglo.

- Usa `frente`, `fin` y `tamano`.
- `encolar`: inserta en `fin`, avanza con modulo (`%`).
- `desencolar`: saca de `frente`, avanza con modulo.

Esto evita mover elementos y mantiene O(1).

## 3.5 ColaDinamica<T>

Cola enlazada con punteros `frente` y `fin`.

- `encolar`: agrega al final.
- `desencolar`: remueve del frente.
- Si queda vacia, `fin` vuelve a `null`.

Complejidad: O(1) en encolar/desencolar.

## 4) Ejercicio 1 - Gestion de niveles (pila estatica)

Clases: `Nivel` y `GestorNiveles`.

- `GestorNiveles` guarda una pila de capacidad 15.
- `entrarNivel`: apila el nuevo nivel.
- `salirNivel`: desapila el nivel actual.
- `nivelActual`: mira la cima.
- `profundidad`: cantidad de niveles en la pila.
- `mostrarRuta`: recorre de abajo hacia arriba para mostrar orden cronologico.

Idea clave: una pila modela bien el avance/retroceso de pantallas o niveles.

## 5) Ejercicio 2 - Validador HTML (pila estatica)

Clase: `ValidadorHTML`.

Funcion principal: `validarHTML(String html)`.

Logica:

1. Recorre el texto buscando `<...>`.
2. Detecta errores de etiqueta incompleta (sin `>`).
3. Ignora comentarios/doctype/procesamiento (`!` o `?`).
4. Distingue apertura vs cierre (`</tag>`).
5. Soporta auto-cierre:
   - por lista conocida (`br`, `img`, etc.)
   - por sintaxis explicita (`/>`)
6. En apertura, apila nombre de etiqueta.
7. En cierre, compara contra cima:
   - si no hay apertura previa: error
   - si no coincide: error de anidamiento
8. Al final, todo lo que quede en pila son etiquetas no cerradas.

Metodo auxiliar `extraerNombreEtiqueta` toma solo el nombre y descarta atributos.

## 6) Ejercicio 3 - Transacciones bancarias (pila dinamica)

Clases: `TipoTransaccion`, `Transaccion`, `CuentaBancaria`.

En `CuentaBancaria`:

- `operar` valida monto positivo.
- Deposito: suma saldo y guarda en historial.
- Retiro/transferencia: verifica saldo suficiente, luego descuenta y guarda.
- `revertirUltimas(n)` desapila las ultimas `n` transacciones.
- Despues recalcula saldo desde cero con `recalcularSaldoDesdeCero`.

Por que recalcular:

- Evita errores acumulados.
- Garantiza coherencia usando el historial restante.

Para recalcular:

1. Vacia historial a una pila auxiliar (invierte orden).
2. Vuelve a pasar de auxiliar a historial aplicando montos en orden cronologico.

## 7) Ejercicio 4 - Registro de errores (pila dinamica)

Clases: `NivelEvento`, `Evento`, `RegistradorErrores`.

Funciones:

- `registrar`: apila evento.
- `mostrarUltimos(n)`: saca temporalmente hasta `n`, imprime y restaura.
- `filtrarPorNivel`: recorre toda la pila, filtra coincidencias y restaura estado original.
- `exportarCSV`:
  - invierte en auxiliar para escribir de antiguo a nuevo
  - genera encabezado `nivel,mensaje,timestamp`
  - limpia comas en mensaje
  - restaura pila al terminar

Punto importante: varias operaciones son no destructivas porque reconstruyen la pila original.

## 8) Ejercicio 5 - Lista de espera de vuelo (cola dinamica)

Clases: `Pasajero`, `ListaEsperaVuelo`.

- `unirseListaEspera`: encola y retorna posicion final.
- `asignarAsientoLibre`: desencola al primero (FIFO).
- `posicionActual(pasaporte)`:
  - recorre toda la cola rotando elementos
  - compara pasaporte sin perder el orden original
- `tiempoEsperaEstimado`: posicion x 5 minutos.

Aqui la cola representa turno real de espera.

## 9) Ejercicio 6 - Simulacion supermercado (colas estaticas)

Clases: `Cliente`, `CajaSupermercado`, `SimulacionSupermercado`.

`SimulacionSupermercado`:

- Crea 3 cajas, cada una con capacidad 15.
- Genera `cantidad` clientes con:
  - tiempo de compra aleatorio 1..10
  - llegada incremental por minuto
- Asigna cada cliente a la caja con menos cola.
- Luego procesa cada caja.

`CajaSupermercado.procesarCola` calcula:

- inicio real de atencion (`max(finAnterior, llegadaCliente)`)
- espera individual
- acumulado de espera
- cuantos terminaron en <= 60 minutos

`mostrarEstadisticas` imprime por caja y total:

- atendidos en 60 min
- espera promedio
- caja mas eficiente (menor espera promedio)

## 10) Ejercicio 7 - Cola de descargas con reintentos

Clases: `EstadoDescarga`, `Descarga`, `GestorDescargas`.

Reglas:

- Cada descarga entra como `PENDIENTE`.
- Al procesar, aumenta intentos y suma tiempo simulado (tamanoMB).
- Hay 30% de probabilidad de fallo.
- Si falla y tiene menos de 3 intentos: se reencola.
- Si llega a 3 fallos: `FALLIDA` definitiva.
- Si no falla: `COMPLETADA`.

Contadores globales:

- `completadas`
- `fallidas`
- `reintentadas`
- `tiempoTotalSimulado`

`procesarTodas` consume la cola hasta vaciarla.

## 11) Ejercicio 8 - Cola de prioridad Min-Heap manual

Interfaz: `AccionColaPrioridad<T>` para aplicar transformaciones.

Clase principal: `ColaPrioridad<T extends Comparable<T>>`.

Estructura:

- `heap` como arreglo de objetos.
- `tamano` como cantidad real.

Operaciones:

- `insertar`: agrega al final y corrige con `subir`.
- `extraerMinimo`: saca raiz, mueve ultimo a raiz y corrige con `bajar`.
- `verMinimo`, `isEmpty`, `size`.
- `redimensionar`: duplica capacidad cuando se llena.
- `reconstruirHeap`: heapify desde la mitad hacia arriba.

`aplicarATodos`:

- ejecuta una accion sobre cada elemento.
- luego reordena el heap para conservar propiedad de minimos.

`heapSort`:

1. Inserta todos los datos en la cola de prioridad.
2. Extrae minimos en secuencia.
3. Resultado: arreglo ordenado ascendente.

## 12) Ejercicio 9 - Planificador de tareas de SO

Clases: `ProcesoSO` y `PlanificadorTareasSO`.

`ProcesoSO` implementa `Comparable`:

- Menor numero de prioridad = mas urgente.
- Desempate por `tiempoLlegada`.

En cada `ejecutarSiguiente(quantum)`:

1. Se extrae el proceso mas prioritario.
2. Se suma CPU usada.
3. Ese proceso baja su urgencia (`prioridad + 1`) para repartir CPU.
4. Se aplica aging a los demas (`prioridad - 1`, minimo 1).
5. Se reinserta el proceso ejecutado.

`simularQuantums(cantidad, quantum)` repite ese ciclo e imprime cada quantum.

Resultado: se reduce riesgo de starvation y se reparte mejor el procesador.

## 13) Resumen conceptual rapido

- Pila (LIFO): util para retroceso, historial reciente, balanceo de etiquetas.
- Cola (FIFO): util para turnos y procesamiento en orden de llegada.
- Heap minimo: util para seleccionar rapidamente el elemento de mayor prioridad (menor valor).

El archivo es un taller completo de estructuras de datos aplicadas a casos practicos, con implementaciones manuales y sin dependencias avanzadas de la libreria estandar.
