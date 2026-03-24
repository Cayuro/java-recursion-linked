# Explicación Completa del Taller: Listas Enlazadas y Recursividad

Este documento está pensado para que entiendas **qué está pasando** en cada ejercicio del archivo `TallerListasRecursividad.java`, no solo para que lo memorices.

La idea es responder siempre estas 3 preguntas:

1. ¿Qué hace el ejercicio?
2. ¿Por qué se hace de esa forma?
3. ¿Qué estás logrando realmente al practicarlo?

---

## Antes de empezar: idea mental clave

En listas enlazadas no trabajas con posiciones directas (como en un arreglo), sino con **referencias** entre nodos.

- En lista simple, cada nodo apunta solo al siguiente (`sig`).
- En lista doble, cada nodo apunta al siguiente y al anterior (`sig` y `ant`).

Entonces el reto principal es: **mover punteros sin romper la cadena**.

---

## Bloque 1: Operaciones Fundamentales (Nivel Básico)

### 1) Insertar Inicio (Simple)

- Qué hace: crea un nodo y lo pone antes de la cabeza.
- Por qué así: es la inserción más fácil porque solo cambias 2 cosas: `nuevoNodo.sig = cabeza` y luego `cabeza = nuevoNodo`.
- Qué logras: entiendes cómo cambiar el punto de entrada de toda la lista sin recorrerla.

### 2) Insertar Final (Simple)

- Qué hace: recorre hasta el último nodo (`sig == null`) y allí conecta el nuevo.
- Por qué así: en lista simple no hay puntero al final, por eso toca caminar nodo por nodo.
- Qué logras: practicas recorrido y conexión al final sin perder referencias.

### 3) Mostrar (Simple)

- Qué hace: imprime cada dato mientras avanza con `while`.
- Por qué así: es el recorrido base que se repite en casi todos los ejercicios.
- Qué logras: dominas el patrón más importante de listas: `while (puntero != null)`.

### 4) Contar Nodos

- Qué hace: recorre y suma 1 por cada nodo.
- Por qué así: contar en lista enlazada obliga a recorrer porque no hay `length` automático.
- Qué logras: entiendes costo lineal O(n) y control de acumuladores.

### 5) Buscar Valor

- Qué hace: compara valor por valor y devuelve `true` si aparece.
- Por qué así: la búsqueda lineal es natural en lista simple.
- Qué logras: entrenas comparación y lógica booleana durante recorrido.

Nota del estilo usado: hay una redundancia intencional de principiante (seguir recorriendo aunque ya encontró), no rompe el resultado.

### 6) Eliminar Primero

- Qué hace: mueve la cabeza al siguiente nodo.
- Por qué así: al perder referencia del primero, ese nodo deja de pertenecer a la lista.
- Qué logras: entiendes que eliminar puede ser solo **reconectar referencias**.

### 7) Vaciar Lista

- Qué hace: pone `cabeza = null`.
- Por qué así: sin cabeza no hay camino para llegar a nodos.
- Qué logras: ves que “vaciar” no siempre implica borrar nodo por nodo manualmente.

### 8) Obtener Mayor

- Qué hace: recorre guardando el máximo actual.
- Por qué así: usas el primer nodo como base y luego comparas con el resto.
- Qué logras: combinas recorrido + decisión (`if`) para obtener una estadística de la lista.

### 9) Insertar Inicio (Doble)

- Qué hace: inserta al principio ajustando `sig` y `ant`.
- Por qué así: en doble enlace debes cuidar dos direcciones, no solo una.
- Qué logras: entiendes la simetría de punteros en lista doble.

### 10) Insertar Final (Doble)

- Qué hace: va al último y conecta `ultimo.sig = nuevo` y `nuevo.ant = ultimo`.
- Por qué así: mantiene coherencia adelante/atrás.
- Qué logras: aprendes a no dejar enlaces “cojos” en listas dobles.

### 11) Mostrar Adelante

- Qué hace: recorre con `sig` desde cabeza.
- Por qué así: validas que los enlaces hacia adelante estén bien.
- Qué logras: depuración básica de estructura doble.

### 12) Mostrar Atrás

- Qué hace: primero llega al final, luego vuelve con `ant`.
- Por qué así: para retroceder necesitas arrancar en el último.
- Qué logras: compruebas que los enlaces hacia atrás sí existen y son correctos.

### 13) Eliminar Último

- Qué hace: ubica último, corta el `sig` del penúltimo.
- Por qué así: al desconectar penúltimo, el último queda fuera de la lista.
- Qué logras: practicas manipulación del extremo final.

### 14) Modificar Datos

- Qué hace: recorre y aplica operación aritmética al `dato`.
- Por qué así: muchas tareas reales no cambian estructura, cambian contenido.
- Qué logras: separas mentalmente “modificar nodos” de “reorganizar nodos”.

### 15) Verificar Estado

- Qué hace: revisa si `cabeza == null`.
- Por qué así: condición mínima para saber si hay elementos.
- Qué logras: hábito de validaciones antes de recorrer/eliminar.

---

## Bloque 2: Casos de Uso Reales (Nivel Intermedio)

### 1) Historial de Navegación con Límite

- Qué hace: mete URL nueva al inicio y, si supera límite, elimina la más vieja al final.
- Por qué así: lo reciente se consulta más y lo antiguo se descarta.
- Qué logras: manejas política de capacidad fija con inserción/eliminación combinadas.

### 2) Cola de Impresión con Prioridad

- Qué hace: urgente va al frente, normal al final.
- Por qué así: simula prioridades sin estructuras avanzadas.
- Qué logras: aplicas reglas de negocio sobre una lista lineal.

### 3) Sistema Undo

- Qué hace: guarda acciones en tope y `undo()` saca la más reciente.
- Por qué así: modelo LIFO (último en entrar, primero en salir).
- Qué logras: entiendes cómo una lista simple implementa una pila.

### 4) Detector de Correos Duplicados

- Qué hace: antes de insertar, recorre para verificar existencia.
- Por qué así: evita duplicados sin usar `Set`.
- Qué logras: validación previa de integridad de datos.

### 5) Inversión de Playlist

- Qué hace: invierte todos los `next` para dejar el orden al revés.
- Por qué así: algoritmo clásico de 3 punteros (`anterior`, `actual`, `siguiente`).
- Qué logras: control fino de reconexión total de una lista.

### 6) Navegador de Galería de Fotos

- Qué hace: permite avanzar o retroceder desde una foto actual.
- Por qué así: lista doble representa navegación bidireccional natural.
- Qué logras: modelas estado actual + movimiento por vecinos.

### 7) Reproductor de Música Circular

- Qué hace: el último apunta al primero para reproducir sin fin.
- Por qué así: evita validar final cada vez que avanzas.
- Qué logras: entiendes listas circulares y recorrido con `do-while`.

### 8) Editor de Texto con Cursor

- Qué hace: inserta un carácter después de una posición.
- Por qué así: simula inserción local según la posición del cursor.
- Qué logras: insertas en medio de la cadena (no solo inicio/fin).

### 9) Punto Medio (Tortuga y Liebre)

- Qué hace: un puntero avanza 1 y otro 2; cuando el rápido termina, el lento queda al centro.
- Por qué así: encuentras medio en una sola pasada.
- Qué logras: técnica de doble velocidad, clave en problemas de listas.

### 10) Gestión de Turnos Bancarios

- Qué hace: elimina cliente por nombre reconectando vecinos.
- Por qué así: debes cubrir caso primero, medio y último sin romper cadena.
- Qué logras: eliminación por criterio, no por posición fija.

---

## Bloque 3: Introducción a la Recursividad

Regla mental de recursividad:

1. Caso base: cuándo paras.
2. Caso recursivo: cómo te acercas al caso base.

### 1) Factorial

- Qué hace: `n * factorial(n-1)` hasta llegar a 1.
- Por qué así: definición matemática natural del factorial.
- Qué logras: aprender descomposición en subproblemas más pequeños.

### 2) Suma de Dígitos

- Qué hace: separa último dígito con `% 10` y continúa con `/ 10`.
- Por qué así: cada llamada reduce el número.
- Qué logras: dominar despiece de enteros recursivamente.

### 3) Potencia `a^b`

- Qué hace: multiplica `a` por potencia de exponente menor.
- Por qué así: replica definición repetitiva de potencia.
- Qué logras: practicar recursión por decremento simple.

### 4) Invertir Cadena

- Qué hace: toma primer carácter y lo coloca al final de la respuesta recursiva.
- Por qué así: cada llamada elimina el primer carácter y reduce problema.
- Qué logras: comprensión de recursión con `String`.

### 5) Fibonacci

- Qué hace: `f(n) = f(n-1) + f(n-2)`.
- Por qué así: sigue la definición clásica de la serie.
- Qué logras: entender árbol de llamadas recursivas (aunque no sea la versión más eficiente).

### 6) Conteo Regresivo

- Qué hace: imprime `n` y llama con `n-1` hasta 0.
- Por qué así: visualiza claramente la condición de parada.
- Qué logras: interiorizar flujo de llamadas recursivas secuenciales.

### 7) Suma de Arreglo

- Qué hace: suma `vec[i]` más suma del resto.
- Por qué así: reduce el arreglo por índice.
- Qué logras: recursión sobre estructuras indexadas.

### 8) Palíndromo

- Qué hace: compara extremos y avanza hacia el centro.
- Por qué así: si extremos son iguales repetidamente, la palabra es palíndroma.
- Qué logras: uso de dos índices en recursividad.

### 9) Decimal a Binario

- Qué hace: divide entre 2 y concatena residuos.
- Por qué así: la conversión binaria se basa en residuos sucesivos.
- Qué logras: combinas matemática discreta con recursión.

### 10) MCD (Euclides)

- Qué hace: `mcd(a,b) = mcd(b, a%b)` hasta que `b` sea 0.
- Por qué así: propiedad matemática del algoritmo de Euclides.
- Qué logras: aplicar recursividad a un algoritmo clásico eficiente.

---

## Bloque 4: Desafíos Avanzados (Listas Enlazadas)

### 1) Fusión de Listas Ordenadas (sin crear nodos)

- Qué hace: mezcla dos listas ya ordenadas usando nodos existentes.
- Por qué así: reutiliza memoria y mantiene orden comparando cabezas.
- Qué logras: punteros de cola para construir resultado incremental.

### 2) Detección de Ciclos (Floyd)

- Qué hace: lento avanza 1, rápido 2; si se encuentran, hay ciclo.
- Por qué así: en ciclo, el rápido alcanza al lento.
- Qué logras: detectar bucles sin memoria extra.

### 3) Eliminar N-ésimo desde el Final (un recorrido)

- Qué hace: separa dos punteros por `n` posiciones y avanza juntos.
- Por qué así: cuando rápido llega al final, lento queda antes del objetivo.
- Qué logras: resolver “desde atrás” sin contar primero.

### 4) Insertion Sort sobre Lista

- Qué hace: toma nodos uno a uno y los inserta en nueva lista ordenada.
- Por qué así: se adapta bien a listas porque insertar en medio es natural.
- Qué logras: ordenar manipulando enlaces en lugar de índices.

### 5) Intercambio de Nodos en Parejas

- Qué hace: cambia cada par adyacente: `(a,b)` pasa a `(b,a)`.
- Por qué así: usa nodo fantasma para simplificar cambios al inicio.
- Qué logras: reordenar estructura local repetidamente sin perder continuidad.

### 6) Lista Doble Circular Multicanal

- Qué hace: cada nodo principal (canal) tiene su propia sublista y la lista principal es circular doble.
- Por qué así: modelo de “lista de listas” con navegación continua.
- Qué logras: diseñar estructuras compuestas (jerárquicas).

### 7) Partición de Lista por valor `x`

- Qué hace: separa en dos cadenas: menores que `x` y mayores/iguales, luego une.
- Por qué así: conserva agrupación sin ordenar completamente.
- Qué logras: clasificar nodos por condición en una sola pasada.

### 8) Suma de Listas como Números

- Qué hace: suma dígitos nodo a nodo con acarreo.
- Por qué así: imita suma manual por columnas.
- Qué logras: manejar simultáneamente dos listas + estado de acarreo.

### 9) Clonación con Puntero Random

- Qué hace: clona nodos intercalados, asigna `random`, luego separa copia.
- Por qué así: evita usar mapa extra y mantiene correspondencia original-copia.
- Qué logras: técnica avanzada de clonación en O(n) con punteros.

### 10) Flattening (Aplanado)

- Qué hace: convierte estructura con `child` en una lista lineal `next`.
- Por qué así: integra sublistas en el flujo principal recursivamente.
- Qué logras: combinar recursividad + reconexión de listas jerárquicas.

---

## ¿Qué estás consiguiendo realmente con todo este taller?

1. Piensas en términos de referencias, no de posiciones.
2. Aprendes a no romper estructuras al editar punteros.
3. Mejoras lógica de casos borde: lista vacía, un nodo, extremos.
4. Entiendes patrones reutilizables para entrevistas y materias avanzadas.
5. Conectas teoría (estructuras) con problemas reales de software.

---

## Mini guía para estudiar mejor este mismo código

1. Ejecuta un método y dibuja la lista en papel antes y después.
2. Cambia un caso borde (lista vacía, 1 nodo, 2 nodos) y observa.
3. En cada método, identifica: puntero que recorre, puntero que reconecta, condición de parada.
4. En recursividad, escribe siempre primero el caso base.
5. Si te pierdes, imprime estados intermedios con `System.out.println`.

---

## Relación rápida con el archivo Java

- Bloque 1: clases `ListaSimpleBasica`, `ListaDobleBasica`.
- Bloque 2: clases de casos (`HistorialNavegacion`, `ColaImpresionPrioridad`, etc.).
- Bloque 3: clase `RecursividadBasica`.
- Bloque 4: clase `DesafiosListas` y nodos auxiliares (`NodoRandom`, `NodoMulti`, etc.).

Si quieres, en el siguiente paso te puedo hacer una **segunda versión** con diagramas ASCII nodo por nodo para los ejercicios más difíciles (reverse, eliminar desde el final, clonar con random y flatten).