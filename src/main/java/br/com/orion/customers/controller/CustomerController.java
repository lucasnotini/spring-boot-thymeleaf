package br.com.orion.customers.controller;

import br.com.orion.customers.domain.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {

    private CustomerRepository customerRepo;

    public CustomerController(CustomerRepository customerRepo){
        this.customerRepo = customerRepo;
    }

    //quando esta url for acessada, vai executar o método e carregar pagina index
    @GetMapping("/customers")
    public String customers(Model model){ //MÉTODO LISTAR CLIENTES
        model.addAttribute("customersList", customerRepo.findAll());
        return "customers/index";
    }

}
