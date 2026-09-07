# Equipo - GameZone Unicesar

## Integrantes

| Nombre completo | Codigo estudiantil | Usuario GitHub | Rol | Modulo asignado | Rama feature |
|---|---|---|---|---|---|
| Rafael Alfonso Rivera Restrepo | 1066865616 | holasoyyorafa | Lider Tecnico | Ventas + Integracion + UI + Main | (trabaja sobre develop) |
| Luis Eliecer Mendoza Perpiñan | 1066349711 | leliecermendoza | Desarrollador 1 | Productos | feature/product-module |
| Ivan Andres Rodriguez Rodriguez | 1066869780 | Ivan-rodriguez10 | Desarrollador 2 | Personas | feature/person-module |

## Distribucion de clases

> Nombres tentativos basados en el contexto del negocio (Parte 1). Deben
> confirmarse/ajustarse una vez cerrado el diagrama de clases de la Parte 2.

### Lider Tecnico (Rafael Rivera)
- `Sale` (clase de dominio de ventas)
- `SaleRepository` (clase de persistencia de ventas)
- `SaleService` (clase de servicio de ventas)
- `ConsoleMenu` / `UserInterface` (clase de interfaz de usuario)
- `Main` (clase principal de arranque)

### Desarrollador 1 - Modulo de Productos (Luis Mendoza)
- `Product` (clase abstracta base de productos)
- `VideoGame` (clase derivada 1 - videojuegos)
- `Console` (clase derivada 2 - consolas)
- `ProductRepository` (clase de persistencia de productos)
- `ProductService` (clase de servicio de productos)

### Desarrollador 2 - Modulo de Personas (Ivan Rodriguez)
- `Person` (clase abstracta base de personas)
- `Customer` (clase derivada 1 - clientes)
- `Seller` (clase derivada 2 - vendedores)
- `PersonRepository` (clase de persistencia de personas)
- `PersonService` (clase de servicio de personas)

## Actividades comprometidas por integrante

> Basadas en las actividades minimas del taller. Cada integrante debe
> ajustarlas a commits atomicos concretos (1 a 3 commits por actividad).

### Lider Tecnico (Rafael Rivera)
1. Crear el repositorio en GitHub con configuracion inicial (README, .gitignore, licencia).
2. Configurar las ramas main y develop y activar su proteccion.
3. Configurar el proyecto Maven (pom.xml) y la estructura de paquetes de las cuatro capas.
4. Elaborar el archivo TEAM.md con la informacion del equipo.
5. Implementar la clase del dominio Sale (atributos, constructor, metodos basicos).
6. Implementar el metodo de calculo del total de la venta.
7. Implementar la clase de persistencia SaleRepository.
8. Implementar la clase de servicio SaleService con las reglas de validacion (minimo un producto, verificacion de stock, actualizacion de inventario).
9. Implementar la estructura basica del menu principal de consola.
10. Implementar los submenus de la interfaz de usuario para los tres modulos.
11. Implementar la clase Main con carga inicial de datos e inyeccion de dependencias.
12. Revisar e integrar los Pull Requests de los desarrolladores en develop.
13. Elaborar el README.md final con instrucciones de compilacion y ejecucion.

### Desarrollador 1 (Luis Mendoza)
1. Crear la rama feature/product-module.
2. Implementar la clase abstracta Product con atributos y metodos comunes.
3. Declarar el metodo abstracto de descripcion que las subclases deberan implementar.
4. Implementar la clase VideoGame con sus atributos particulares y su descripcion.
5. Implementar la clase Console con sus atributos particulares y su descripcion.
6. Implementar ProductRepository con metodos de guardado y carga desde archivo.
7. Implementar ProductService con metodos de registro, listado y actualizacion de stock.
8. Documentar todas las clases del modulo con JavaDoc en ingles.
9. Solicitar Pull Requests al Lider Tecnico para integrar el modulo.

### Desarrollador 2 (Ivan Rodriguez)
1. Crear la rama feature/person-module.
2. Implementar la clase abstracta Person con atributos y metodos comunes.
3. Declarar el metodo abstracto/de negocio que las subclases deberan implementar segun el analisis.
4. Implementar la clase Customer con sus atributos particulares.
5. Implementar la clase Seller con sus atributos particulares.
6. Implementar PersonRepository con metodos de guardado y carga desde archivo.
7. Implementar PersonService con metodos de registro y listado.
8. Documentar todas las clases del modulo con JavaDoc en ingles.
9. Solicitar Pull Requests al Lider Tecnico para integrar el modulo.
