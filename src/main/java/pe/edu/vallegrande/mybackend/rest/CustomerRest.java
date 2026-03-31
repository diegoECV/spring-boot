package pe.edu.vallegrande.mybackend.rest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import pe.edu.vallegrande.mybackend.model.Customer;
import pe.edu.vallegrande.mybackend.service.CustomerService;

@CrossOrigin(origins = "*")          // ✅ Permitir Conexión con Angular
@RestController
@RequestMapping("/v1/api/customer")  // ✅ http://localhost:8085/v1/api/customer
@Tag(name = "Customer API", description = "API for Customer management")
public class CustomerRest {

    // ✅ Inyección del service
    private final CustomerService customerService;

    @Autowired
    public CustomerRest(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    // 🌐🔍 Mapear Endpoint Listar Todos - tipo GET en POSTMAN
    @GetMapping
    @Operation(summary = "Get All Customer", description = "Get All Customer")
    public List <Customer> findAll(){
        return customerService.findAll();
    }

    // 🌐🔍 Mapear Endpoint Listar por Estado - tipo GET en POSTMAN
    @GetMapping("/state/{state}")
    @Operation(summary = "Get Customer By STATE", description = "Get Customer By STATE")
    public List<Customer> findByState(@PathVariable String state) {
        return customerService.findByState(state);
    }

    // 🌐🔍 Mapear Endpoint Listar por ID - tipo GET en POSTMAN
    @GetMapping("/{id}")
    @Operation(summary = "Get Customer By ID", description = "Get Customer By ID")
    public Optional<Customer> findById(@PathVariable Long id) {
        return customerService.findById(id);
    }

    // 🌐✅ Mapear Endpoint Registrar - tipo POST en POSTMAN
    @PostMapping("/save")
    @Operation(summary = "Save Customer", description = "Save Customer with email and rol")
    public ResponseEntity<Map<String, Object>> save(@RequestBody CustomerDTO customerDTO) {
        try {
            if (customerDTO.getDni() == null || customerDTO.getDni().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                        "success", false,
                        "data", new Customer(),
                        "message", "El DNI es requerido"
                    ));
            }

            Customer customer = new Customer();
            customer.setDni(customerDTO.getDni());
            customer.setCellPhone(customerDTO.getCellphone());
            customer.setFirstName(customerDTO.getFirstName());
            customer.setLastName(customerDTO.getLastName());
            customer.setState(customerDTO.getState() != null ? customerDTO.getState() : "A");
            customer.setEmail(customerDTO.getEmail());
            customer.setRol(customerDTO.getRol());

            Customer savedCustomer = customerService.save(customer);

            return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of(
                    "success", true,
                    "data", savedCustomer,
                    "message", "Cliente creado exitosamente"
                ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                    "success", false,
                    "data", new Customer(),
                    "message", "Error al crear cliente: " + e.getMessage()
                ));
        }
    }

    // 🌐✏️ Mapear Endpoint Actualizar - tipo PUT en POSTMAN
    @PutMapping("/update/{id}")
    @Operation(summary = "Update Customer", description = "Update Customer with email and rol")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        try {
            Optional<Customer> optionalCustomer = customerService.findById(id);
            if (optionalCustomer.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                        "success", false,
                        "data", new Customer(),
                        "message", "Cliente no encontrado"
                    ));
            }

            Customer customer = optionalCustomer.get();
            
            if (customerDTO.getDni() != null) customer.setDni(customerDTO.getDni());
            if (customerDTO.getCellphone() != null) customer.setCellPhone(customerDTO.getCellphone());
            if (customerDTO.getFirstName() != null) customer.setFirstName(customerDTO.getFirstName());
            if (customerDTO.getLastName() != null) customer.setLastName(customerDTO.getLastName());
            if (customerDTO.getState() != null) customer.setState(customerDTO.getState());
            if (customerDTO.getEmail() != null) customer.setEmail(customerDTO.getEmail());
            if (customerDTO.getRol() != null) customer.setRol(customerDTO.getRol());

            Customer updatedCustomer = customerService.update(customer);

            return ResponseEntity.ok(Map.of(
                "success", true,
                "data", updatedCustomer,
                "message", "Cliente actualizado exitosamente"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                    "success", false,
                    "data", new Customer(),
                    "message", "Error al actualizar cliente: " + e.getMessage()
                ));
        }
    }

    // 🌐❌ Mapear Endpoint Eliminar (Cambio de Estado) por ID - tipo PATCH en POSTMAN
    @PatchMapping("/delete/{id}")
    @Operation(summary = "Logical Delete Customer", description = "Logical Delete Customer")
    public Customer delete(@PathVariable Long id) {
        return customerService.delete(id);
    }

    // 🌐♻️ Mapear Endpoint Restaurar (Cambio de Estado) por ID - tipo PATCH en POSTMAN
    @PatchMapping("/restore/{id}")
    @Operation(summary = "Logical Restore Customer", description = "Logical Restore Customer")
    public Customer restore(@PathVariable Long id) {
        return customerService.restore(id);
    }

    /**
     * DTO para recibir datos del cliente desde el frontend
     */
    public static class CustomerDTO {
        private String dni;
        private String cellphone;
        private String firstName;
        private String lastName;
        private String state;
        private String email;
        private String rol;

        // Getters y Setters
        public String getDni() { return dni; }
        public void setDni(String dni) { this.dni = dni; }

        public String getCellphone() { return cellphone; }
        public void setCellphone(String cellphone) { this.cellphone = cellphone; }

        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }

        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }

        public String getState() { return state; }
        public void setState(String state) { this.state = state; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getRol() { return rol; }
        public void setRol(String rol) { this.rol = rol; }
    }

}
