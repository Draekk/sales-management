# Sales Management

Este proyecto es una aplicación para la gestión de ventas, desarrollada con Spring Boot y Spring Data JPA. Utiliza MySQL como base de datos y funciona en el puerto 8080. La aplicación permite gestionar ventas a través de una API REST, ofreciendo funcionalidades para crear, leer, actualizar y eliminar registros de ventas. Además, permite la gestión de clientes asociados a sus ventas, proporcionando un CRUD completo para los clientes.

## Requisitos

- Java 17 o superior
- Spring Boot
- MySQL
- Postman

## Configuración

1. Clona el repositorio en tu máquina local.
2. Crea un archivo `.env` en la raíz del proyecto con las siguientes variables:

   ```plaintext
   DB_NAME=db_sales_management
   DB_USER=tu_usuario
   DB_PASSWORD=tu_contraseña
   DB_PORT=3306
   DB_HOST=localhost
   ```

3. Crea una base de datos llamada `db_sales_management` en tu servidor MySQL.

## Rutas

A continuación se presenta una lista de las rutas disponibles en la aplicación:

1. Clientes:

- `GET /api/client/find/all` - Obtiene todos los clientes.
- `GET /api/client/find/id/{id}` - Obtiene un cliente por su ID.
- `GET /api/client/find/name` - Obtiene un cliente por su nombre, requiere un body con el nombre del cliente: `{ "name": "nombre" }`.
- `GET /api/client/find/region` - Obtiene un cliente por su región, requiere un body con la región del cliente: `{ "region": "región" }`.
- `POST /api/client/create` - Crea un nuevo cliente, requiere un body con los datos del cliente: `{ "name": "nombre", "region": "región" }`.
- `PUT /api/client/update` - Actualiza un cliente existente, requiere un body con los datos del cliente: `{ "id": id, "name": "nombre", "region": "región" }`.
- `DELETE /api/client/delete/id/{id}` - Elimina un cliente por su ID.

2. Ventas:

- `GET /api/sale/find/all` - Obtiene todas las ventas.
- `GET /api/sale/find/id/{id}` - Obtiene una venta por su ID.
- `GET /api/sale/find/date` - Obtiene una venta por su fecha.
- `GET /api/sale/find/month` - Obtiene una venta por su mes.
- `GET /api/sale/find/year/{year}` - Obtiene una venta por su año.
- `POST /api/sale/create` - Crea una nueva venta.
- `PUT /api/sale/update` - Actualiza una venta existente.
- `DELETE /api/sale/delete/id/{id}` - Elimina una venta por su ID.

## Pruebas

Para probar las rutas, puedes utilizar Postman. Asegúrate de que el servidor esté en ejecución y utiliza las rutas mencionadas anteriormente para interactuar con la API.

## Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue o un pull request para discutir cualquier cambio que desees realizar.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.
