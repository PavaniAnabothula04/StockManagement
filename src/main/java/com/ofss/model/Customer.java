    package com.ofss.model;

    import com.fasterxml.jackson.annotation.JsonProperty;
    import jakarta.persistence.*;
    //import java.util.ArrayList;
    //import java.util.List;
    //import com.fasterxml.jackson.annotation.JsonIgnore;

    @Entity
    @Table(name = "Customer")
    public class Customer {

        @Id
        private String emailId;
        private String firstName;
        private String lastName;
        @Column(name = "password", nullable = false)
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private String password;
        private String city;
        private String phoneNumber;

        // @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
        // @JsonIgnore
        // private List<Transaction> transactions = new ArrayList<>();

        // Constructors
        public Customer() {
            super();
        }

        public Customer(String emailId, String firstName, String lastName, String city, String phoneNumber) {
            this.emailId = emailId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.city=city;
            this.phoneNumber = phoneNumber;
        }

        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }

        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getEmailId() { return emailId; }
        public void setEmailId(String email) { this.emailId = email; }

        

        public String getPhoneNumber() { return phoneNumber; }
        public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

        // public List<Transaction> getTransactions() { return transactions; }
        // public void setTransactions(List<Transaction> transactions) { this.transactions = transactions; }

        // readability
        @Override
        public String toString() {
            return "Customer{" +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", city='" + city + '\'' +
                    ", emailId='" + emailId + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    '}';
        }
    }
