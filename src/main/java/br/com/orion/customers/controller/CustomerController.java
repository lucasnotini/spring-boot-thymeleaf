package br.com.orion.customers.controller;

import br.com.orion.customers.domain.Customer;
import br.com.orion.customers.domain.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    //crio método para atender requisição de quando clicar no botão inserir, abrindo página html de salvar/editar
    @GetMapping("/customers/new")
    public String newCustomer(@ModelAttribute("customer") Customer customer){
        return "customers/form";
    }

    //crio método para salvar pessoa
    @PostMapping("/customers/salvar")
    public String saveCustomer(@ModelAttribute("customer") Customer customer){
        customerRepo.save(customer);
        return "redirect:/customers";
    }

}
