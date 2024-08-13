package com.bridgelabz.employee.controller;

import com.bridgelabz.employee.exept.CustomeExept;
import com.bridgelabz.employee.model.Employee;
import com.bridgelabz.employee.model.responseDTO;
import com.bridgelabz.employee.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmpCtrl
{
    @Autowired
    EmpService service;

    @PostMapping("/emp")
    public responseDTO<Employee> insert(@RequestBody Employee e)
    {
        responseDTO<Employee> response = new responseDTO();
        Employee res = null;
        try
        {
            res = service.insert(e);
            response.setStatus(HttpStatus.OK.name());
            response.setMessage("Employee details inserted successfully!!");
            response.setData(res);
            System.out.println(res);
        }
        catch (CustomeExept ex)
        {
            response.setStatus(HttpStatus.OK.name());
            response.setMessage("Employee name cannot be null");
            response.setData(res);
            System.out.println(res);
        }
        return response;//service.insert(e);
    }


    @GetMapping("/emp/{id}")
    public responseDTO<Employee> getById(@PathVariable int id)  {

        responseDTO<Employee> response = new responseDTO();
        Employee result = null;
        try
        {
            result = service.getById(id);
            response.setStatus(HttpStatus.OK.name());
            response.setMessage("Employee Fetched");
            response.setData(result);
            System.out.println(result);
        }
        catch(CustomeExept e)
        {
            response.setStatus(HttpStatus.BAD_REQUEST.name());
            response.setMessage(e.getMessage());
            response.setData(result);
            System.out.println(result);
        }
        return response;
    }

    @GetMapping("/emp")
    public responseDTO<List<Employee>> getAll()
    {
        responseDTO<List<Employee>> response=new responseDTO<>();
        List<Employee> all = null;
        try
        {
            all = service.getAll();
            response.setStatus(HttpStatus.OK.name());
            response.setMessage("All Employee Fetched");
            response.setData(all);
            System.out.println(all);
        }
        catch (CustomeExept ex)
        {
            response.setStatus(HttpStatus.BAD_REQUEST.name());
            response.setMessage(ex.getMessage());
//            response.setData();
            System.out.println(all);
        }
        
        return response;//service.getAll();
    }
    
    @PutMapping("/emp")
    public responseDTO<Employee> updateById(@RequestBody Employee employee)
    {
        responseDTO<Employee> response=new responseDTO<>();
        Employee update = null;
        try
        {
            update = service.update(employee);
            response.setStatus(HttpStatus.OK.name());
            response.setMessage("Employee details updated");
            response.setData(update);
            System.out.println(update);
        }
        catch (CustomeExept ex)
        {
            response.setStatus(HttpStatus.BAD_REQUEST.name());
            response.setMessage(ex.getMessage());
            response.setData(update);
            System.out.println(update);
        }
        return response;
    }
    
    @DeleteMapping("/emp/{id}")
    public responseDTO<Employee> delById(@PathVariable int id)
    {
        responseDTO<Employee> response=new responseDTO<>();
        String s = "";
        try
        {
            s = service.deleteById(id);
            response.setStatus(HttpStatus.OK.name());
            response.setMessage("Employee details deleted");
//            response.setData(s);
            System.out.println(s);
        }
        catch (CustomeExept ex)
        {
            response.setStatus(HttpStatus.BAD_REQUEST.name());
            response.setMessage(ex.getMessage());
//            response.setData(s);
            System.out.println(s);
        }
        return  response;
    }
}
