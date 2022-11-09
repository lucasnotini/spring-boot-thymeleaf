package br.com.orion.customers.controller;

import br.com.orion.customers.domain.Customer;
import br.com.orion.customers.domain.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

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

    //crio método para salvar cliente
    @PostMapping("/customers/salvar")
    public String saveCustomer(@ModelAttribute("customer") Customer customer){
        customerRepo.save(customer);
        return "redirect:/customers";
    }

    //crio método para alterar cliente que usa mesma função de salvar do método acima
    @GetMapping("/customers/{id}") // {id} recebe variavel na url e passa como parametro no método
    public String updateCustomer(@PathVariable("id") long id, Model model){
       Optional<Customer> customerOptional = customerRepo.findById(id);
       if (customerOptional.isEmpty()){
           throw new IllegalArgumentException("Cliente inválido.");
       }
       model.addAttribute("customer", customerOptional.get());
       return "customers/form";
    }

    //crio método para excluir
    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable("id") long id){
        Optional<Customer> customerOptional = customerRepo.findById(id);
        if (customerOptional.isEmpty()){
            throw new IllegalArgumentException("Cliente inválido.");
        }

        customerRepo.delete(customerOptional.get());
        return "redirect:/customers";
    }

}
