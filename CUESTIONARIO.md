# Cuestionario — Sesión N.° 04: Manejo de Excepciones

> Preguntas tomadas de la Guía 04. Las respuestas se apoyan en las actividades y ejercicios
> de este repositorio.

---

### 1. ¿Qué sucede si intentas abrir un archivo para lectura que no existe? ¿Qué sucede si intentas abrir un archivo para escritura que no existe?

Para **lectura** (por ejemplo `new Scanner(new File(ruta))` o `new FileReader(ruta)`): se lanza
una `FileNotFoundException` (checked, subclase de `IOException`), porque el archivo no puede
encontrarse. Esto se ve en la Actividad 4 al intentar leer `reporte-inexistente.txt`.

Para **escritura** (`new FileWriter(ruta)` o `new PrintWriter(ruta)`): si el archivo no existe,
normalmente Java lo **crea automáticamente**; no se lanza excepción por esa sola razón. Solo
fallaría (con `IOException`/`FileNotFoundException`) si la ruta apunta a un directorio que no
existe, no se tienen permisos de escritura, o el sistema de archivos no puede crear el archivo.

### 2. ¿Cuál es la diferencia entre lanzar una excepción y capturar una excepción?

**Lanzar** (`throw`) es crear un objeto de excepción y transferir de inmediato el control fuera
del punto donde ocurrió el problema, interrumpiendo el flujo normal del método actual.
**Capturar** (`catch`) es recibir ese objeto en un bloque coincidente y decidir qué hacer con
él (mostrar un mensaje, registrar el error, reintentar, etc.), permitiendo que el programa
continúe de forma controlada en lugar de terminar abruptamente.

### 3. ¿Qué es una excepción comprobada (checked)? ¿Qué es una excepción no comprobada (unchecked)? Da un ejemplo de cada una. ¿Qué excepciones necesitas declarar con throws?

Una excepción **checked** es verificada por el compilador: si un método puede lanzarla, debe
capturarla o declararla con `throws`, o el programa no compila. Ejemplo: `IOException` (como en
`ReporteTransacciones`, Actividad 4).

Una excepción **unchecked** es una subclase de `RuntimeException` (o de `Error`); el compilador
no exige manejarla ni declararla. Ejemplo: `IllegalArgumentException` o
`LimiteCreditoExcedidoException` (Actividad 3).

Solo es obligatorio declarar con `throws` las excepciones **checked** que el método no captura
internamente (por ejemplo `SaldoInsuficienteException` en `retirar()`). Las unchecked pueden
lanzarse sin declararlas, aunque documentarlas ayuda a quien usa la clase.

### 4. ¿Por qué no necesitas declarar que tu método podría lanzar una IndexOutOfBoundsException?

Porque `IndexOutOfBoundsException` extiende `RuntimeException`, es decir, es una excepción **no
comprobada**. El compilador no obliga a declararla con `throws` ni a capturarla: se espera que
el programador evite la condición (validando el índice antes de acceder al arreglo o lista) en
lugar de forzar un manejo explícito en cada método.

### 5. Cuando tu programa ejecuta una declaración throw, ¿qué declaración se ejecuta a continuación? ¿Qué sucede si una excepción no tiene una cláusula catch coincidente?

Al ejecutarse un `throw`, se abandona de inmediato el resto del bloque actual y la JVM busca,
subiendo por la pila de llamadas, el primer bloque `catch` cuyo tipo coincida (o sea superclase)
con la excepción lanzada; si en el camino hay un `finally`, este se ejecuta igual.

Si **ningún** método en la pila de llamadas tiene un `catch` adecuado, la excepción llega hasta
`main` sin ser capturada y la JVM **termina el programa**, imprimiendo el *stack trace* en la
salida de error estándar.

### 6. ¿Qué puede hacer tu programa con el objeto de excepción que recibe una cláusula catch?

Puede: leer su mensaje con `getMessage()`, inspeccionar su causa con `getCause()`, revisar
excepciones suprimidas con `getSuppressed()` (ver pregunta 9), imprimir o registrar su rastro de
pila con `printStackTrace()`, decidir una acción de recuperación (reintentar, usar un valor por
defecto), volver a lanzarla (`throw`) tal cual o envuelta en otra excepción, o simplemente
ignorarla (lo cual casi nunca es recomendable).

### 7. ¿Es el tipo del objeto de excepción siempre el mismo que el tipo declarado en la cláusula catch que lo captura? Si no, ¿por qué?

No necesariamente. Un `catch` captura el tipo declarado **y cualquiera de sus subclases**, por
polimorfismo: el objeto real puede ser de un tipo más específico que el declarado en el `catch`.
Por ejemplo, un `catch (Exception e)` también captura una `SaldoInsuficienteException`. Por eso
el **orden de los `catch` importa**: los más específicos deben ir antes que los más generales,
o el compilador marca error (código inalcanzable).

### 8. ¿Cuál es el propósito de la declaración try-with-resources? Da un ejemplo de cómo se puede usar.

Garantizar que los recursos que implementan `AutoCloseable` (`Scanner`, `PrintWriter`,
`FileReader`, conexiones, etc.) se **cierren automáticamente** al salir del bloque `try`, ocurra
o no una excepción, sin necesidad de un `finally` explícito. Ejemplo (usado en la Actividad 4):

```java
try (Scanner lector = new Scanner(new File("reporte-4002.txt"))) {
    while (lector.hasNextLine()) {
        System.out.println(lector.nextLine());
    }
} catch (FileNotFoundException e) {
    System.out.println("Archivo no encontrado: " + e.getMessage());
}
```

### 9. ¿Qué sucede cuando se lanza una excepción, una declaración try-with-resources llama a close, y esa llamada lanza una excepción de un tipo diferente al de la original? ¿Cuál de las excepciones es capturada por una cláusula catch que rodea el bloque? Escribe un programa de ejemplo para probarlo.

Se propaga la excepción **original** (la lanzada dentro del bloque `try`); la excepción lanzada
por `close()` no se pierde, sino que queda **suprimida** y anexada a la original, recuperable
con `getSuppressed()`. El `catch` que rodea el `try-with-resources` recibe la excepción
original, no la de `close()`.

Programa de ejemplo (incluido en el repositorio como
[`cuestionario/PruebaExcepcionesSuprimidas.java`](cuestionario/PruebaExcepcionesSuprimidas.java),
compilado y ejecutado con JDK 21):

```java
static class RecursoProblematico implements AutoCloseable {
    void usar() {
        throw new RuntimeException("Error durante el uso del recurso");
    }
    @Override
    public void close() {
        throw new IllegalStateException("Error al cerrar el recurso");
    }
}

public static void main(String[] args) {
    try (RecursoProblematico recurso = new RecursoProblematico()) {
        recurso.usar();
    } catch (Exception e) {
        System.out.println("Excepcion capturada: " + e.getMessage());
        for (Throwable suprimida : e.getSuppressed()) {
            System.out.println("Excepcion suprimida: " + suprimida.getMessage());
        }
    }
}
```

Salida real del programa:

```
Excepcion capturada: Error durante el uso del recurso
Excepcion suprimida: Error al cerrar el recurso
```

### 10. ¿Qué excepciones pueden lanzar los métodos next y nextInt de la clase Scanner? ¿Son excepciones comprobadas (checked) o no comprobadas (unchecked)?

`next()` y `nextInt()` pueden lanzar `NoSuchElementException` (si no queda ningún token por
leer) e `InputMismatchException` (si el siguiente token no tiene el formato esperado, por
ejemplo `nextInt()` sobre un texto no numérico); si el `Scanner` ya fue cerrado, también pueden
lanzar `IllegalStateException`. Las tres son subclases de `RuntimeException`, por lo tanto son
excepciones **no comprobadas (unchecked)**.
