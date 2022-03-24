package com.mercadolivro.service

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
) {

    val teste = mutableListOf(1)

    fun getAll(name: String?): List<CustomerModel>{
        name?.let {
            return customerRepository.findByNameContaining(it)
        }
        return customerRepository.findAll().toList()
    }

    fun getById(id: Int): CustomerModel{
        return customerRepository.findById(id).orElseThrow()
    }

    fun createCustomer(customerModel: CustomerModel){
        customerRepository.save(customerModel)
    }

    fun updateCustomerById(customerModel: CustomerModel){

        if(!customerRepository.existsById(customerModel.id!!)){
            throw Exception()
        }
        customerRepository.save(customerModel)
    }

    fun deleteCustomerById(id: Int){
        val customer = getById(id)

        bookService.deleteByCustomer(customer)

        customer.status = CustomerStatus.INATIVO

        customerRepository.save(customer)
    }

    fun emailAvailable(email: String): Boolean {
        return !customerRepository.existsByEmail(email)
    }
}