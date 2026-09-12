<h1 align="center">⚠️ Manejo de Excepciones en Java — Lenguajes de Programación III</h1>

<p align="center">
  <em>try / catch / finally · try-with-resources · excepciones propias · propagación —
  aplicado a un Sistema de Gestión de Cuentas Bancarias.</em>
</p>

<p align="center">
  <img alt="Lenguaje" src="https://img.shields.io/badge/Java-8%2B-orange?logo=openjdk&logoColor=white">
  <img alt="Tema" src="https://img.shields.io/badge/Tema-Excepciones-blue">
  <img alt="Curso" src="https://img.shields.io/badge/Curso-LP%20III-6f42c1">
  <img alt="Sesión" src="https://img.shields.io/badge/Sesi%C3%B3n-04-brightgreen">
  <img alt="Estado" src="https://img.shields.io/badge/Compila-OK-success">
</p>

---

## 📌 Sobre este repositorio

Reúne el código de la **Sesión N.° 04** del curso **Lenguajes de Programación III**
(Escuela Profesional de Ingeniería de Sistemas — Universidad Católica de Santa María),
dedicada al **manejo de excepciones**: `try`/`catch`/`finally`, la cláusula `throws`,
`try-with-resources` y el diseño de excepciones propias.

Cada carpeta es un **mini-programa independiente y ejecutable**, con su `salida-esperada.txt`
junto al código. Todo se **compiló y ejecutó con JDK 21**.

## 👥 Integrantes

| Código | Apellidos y Nombres |
|---|---|
| 2025001659 | Del Carpio Aspilcueta, Farid Andree |
| 2025002309 | Medina Llanquecha, Cesar Manuel |

---

## 🗂️ Estructura del repositorio

```
Manejo-de-Excepciones/
├── actividades/                              Sistema de Gestión de Cuentas Bancarias
│   ├── 01-excepciones-internas-validacion/   IllegalArgumentException + SaldoInsuficienteException
│   ├── 02-excepciones-personalizadas/        transferir() y cerrarCuenta() con excepciones propias
│   ├── 03-limite-credito-propagacion/        CuentaCredito + propagación de una excepción no verificada
│   └── 04-reportes-manejo-recursos/          try-with-resources con PrintWriter y Scanner
├── ejercicios-propuestos/
│   ├── 01-lector-caracteres/                 4 excepciones propias + LeerEntrada (AutoCloseable)
│   ├── 02-calculadora/                       DivisionPorCeroException + catch múltiples
│   ├── 03-numero-negativo/                   IllegalArgumentException en un setter
│   └── 04-registro-estudiantes/              IllegalArgumentException + NoSuchElementException
├── cuestionario/
│   └── PruebaExcepcionesSuprimidas.java      Demo de la pregunta 9 (excepciones suprimidas)
├── CUESTIONARIO.md
└── README.md
```

---

## 🏦 Actividades — Sistema de Gestión de Cuentas Bancarias

### 1️⃣ Excepciones internas y validación de datos
[`actividades/01-excepciones-internas-validacion`](actividades/01-excepciones-internas-validacion)

`CuentaBancaria` valida el saldo inicial y los montos de `depositar()`/`retirar()` con
`IllegalArgumentException` (excepción estándar); un retiro que excede el saldo lanza
`SaldoInsuficienteException` (excepción propia, **checked**).

### 2️⃣ Creación y manejo de excepciones personalizadas
[`actividades/02-excepciones-personalizadas`](actividades/02-excepciones-personalizadas)

Se agregan `transferir()` y `cerrarCuenta()`. `transferir()` lanza `CuentaNoEncontradaException`
si la cuenta destino no existe y propaga `SaldoInsuficienteException` si el origen no tiene
saldo. `cerrarCuenta()` lanza `SaldoNoCeroException` si el saldo no es cero.

### 3️⃣ Límite de crédito y propagación de excepciones
[`actividades/03-limite-credito-propagacion`](actividades/03-limite-credito-propagacion)

`CuentaCredito` extiende `CuentaBancaria` y sobrescribe `retirar()` para permitir sobregiro
hasta un `limiteCredito`. Al superarlo lanza `LimiteCreditoExcedidoException`, una excepción
**no verificada** (extiende `IllegalArgumentException`, igual que el ejemplo de
`InsufficientStockException` de la guía teórica) que **se propaga** desde `retirar()` a
través de un método intermedio hasta ser capturada en `main()`.

### 4️⃣ Generación de reportes y manejo de recursos
[`actividades/04-reportes-manejo-recursos`](actividades/04-reportes-manejo-recursos)

`ReporteTransacciones` escribe el historial de una cuenta a un archivo con
`try-with-resources` (`PrintWriter`) y lo vuelve a leer con `try-with-resources` (`Scanner`).
Lanza `HistorialVacioException` si la cuenta no tiene movimientos, y maneja
`FileNotFoundException` al leer un archivo inexistente.

---

## 📝 Ejercicios propuestos

| # | Carpeta | Idea |
|---|---|---|
| 1 | [`01-lector-caracteres`](ejercicios-propuestos/01-lector-caracteres) | 4 excepciones propias (vocal, número, blanco, salida) según el carácter leído desde teclado. |
| 2 | [`02-calculadora`](ejercicios-propuestos/02-calculadora) | `Calculadora` con `DivisionPorCeroException` y `catch` múltiples. |
| 3 | [`03-numero-negativo`](ejercicios-propuestos/03-numero-negativo) | `Numero.setValor()` rechaza valores negativos con `IllegalArgumentException`. |
| 4 | [`04-registro-estudiantes`](ejercicios-propuestos/04-registro-estudiantes) | `RegistroEstudiantes` con `IllegalArgumentException` y `NoSuchElementException`. |

---

## ▶️ Cómo compilar y ejecutar

Necesitas un **JDK 8 o superior**. Cada carpeta se compila por separado.

```bash
cd actividades/03-limite-credito-propagacion
javac -encoding UTF-8 -d out $(find . -name "*.java")
java -cp out main.Main
```

El ejercicio 1 (`ejercicios-propuestos/01-lector-caracteres`) es **interactivo**: lee
caracteres desde la consola hasta que se presiona `X`. Su `salida-esperada.txt` documenta
una sesión de ejemplo.

---

## 🧠 Conceptos cubiertos

`try / catch / finally` · `throw` / `throws` · `try-with-resources` · `AutoCloseable` ·
`excepciones checked vs. unchecked` · `excepciones personalizadas` · `propagación de excepciones` ·
`excepciones suprimidas` · `catch múltiples (multi-catch)` · `manejo de archivos (`PrintWriter`/`Scanner`)`

---

<p align="center"><sub>
Material con fines educativos — Universidad Católica de Santa María · Ingeniería de Sistemas · Lenguajes de Programación III · Sesión N.° 04
</sub></p>
