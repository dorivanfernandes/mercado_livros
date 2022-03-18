package com.mercadolivro.service

import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository
) {

    val teste = mutableListOf(1)

    fun getAll(name: String?): List<CustomerModel>{
        name?.let {
            return customerRepository.findByNameContaining(it)
        }
        return customerRepository.findAll().toList()
    }

    fun getCustomerById(id: Int): CustomerModel{
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
        if(!customerRepository.existsById(id)){
            throw Exception()
        }

        customerRepository.deleteById(id)
    }
}