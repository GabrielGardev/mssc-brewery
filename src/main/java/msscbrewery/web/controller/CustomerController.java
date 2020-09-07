package msscbrewery.web.controller;

import msscbrewery.services.CustomerService;
import msscbrewery.web.model.CustomerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable UUID customerId){

        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> handlePost(@Valid @RequestBody CustomerDto customerDto){

        CustomerDto savedDto = customerService.saveNewCustomer(customerDto);

        HttpHeaders headers = new HttpHeaders();
        //TODO: add hostname to url
        headers.add("Location", "/api/v1/customer/" + savedDto.getId().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDto> handleUpdate(@PathVariable("customerId") UUID customerId,@Valid @RequestBody CustomerDto customerDto){

        customerService.updateCustomer(customerId, customerDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("customerId") UUID customerId){
        customerService.deleteById(customerId);
    }
}
