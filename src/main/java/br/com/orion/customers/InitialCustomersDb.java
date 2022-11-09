package br.com.orion.customers;

import br.com.orion.customers.domain.Customer;
import br.com.orion.customers.domain.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class InitialCustomersDb implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepo;

    @Override
    public void run(String... args) throws Exception{

        Customer c1 = new Customer("Joao");
        c1.setCpf("98781622455");
        c1.setEmail("joaobola@gmail.com");
        c1.setTelefone("3199984578");

        Customer c2 = new Customer("Ana");
        c2.setCpf("65481678954");
        c2.setEmail("anaclaudia@gmail.com");
        c2.setTelefone("37988455578");

        //salvo/persisto no banco
        customerRepo.save(c1);
        customerRepo.save(c2);

    }

}
