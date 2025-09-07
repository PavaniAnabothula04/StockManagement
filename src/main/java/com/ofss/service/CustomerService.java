    package com.ofss.service;

    import java.util.List;
    import org.springframework.stereotype.Service;
    import com.ofss.model.Customer;
    import com.ofss.repositories.CustomerRepository;
    import org.springframework.security.crypto.password.PasswordEncoder;

    @Service
    public class CustomerService {

        private final CustomerRepository customerRepository;
        private final PasswordEncoder passwordEncoder;

        public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
            this.customerRepository = customerRepository;
            this.passwordEncoder = passwordEncoder;
        }

        public List<Customer> getAllCustomers() {
            return customerRepository.findAll();
        }

        public Customer getCustomerByEmail(String email) {
            return customerRepository.findByEmailId(email);
                    // .orElseThrow(() -> new ResourceNotFoundException("Customer not found with emailId: " + emailId));
        }

        public Customer saveCustomer(Customer customer) {
            return customerRepository.save(customer);
        }

        public void deleteCustomer(String email) {
            Customer existing = getCustomerByEmail(email);
            customerRepository.delete(existing);
        }


        // Registration
        public Customer registerCustomer(Customer customer) {
            String normalizedEmail = customer.getEmailId();
            customer.setEmailId(normalizedEmail);

        Customer existingCustomer = customerRepository.findByEmailId(normalizedEmail);
        if (existingCustomer != null) {
            throw new RuntimeException("Email already registered!");
        }
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
            return customerRepository.save(customer);
        }


        // Login
        public Customer login(String email, String password) {
        Customer existingCustomer = customerRepository.findByEmailId(email);
        if (existingCustomer != null && passwordEncoder.matches(password, existingCustomer.getPassword())) {
            return existingCustomer;
        }
        return null;
        }
    }
