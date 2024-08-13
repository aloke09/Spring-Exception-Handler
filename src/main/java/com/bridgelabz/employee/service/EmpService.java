package com.bridgelabz.employee.service;

import com.bridgelabz.employee.exept.CustomeExept;
import com.bridgelabz.employee.model.Employee;
import com.bridgelabz.employee.repo.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService
{
    @Autowired
    private EmpRepo repo;

    public List<Employee> getAll() throws CustomeExept {
        List<Employee> list = repo.findAll();
        if(list.isEmpty())
        {
            throw new CustomeExept("Data base is empty!!!");
        }
        return list;
    }

    public Employee getById(int id) throws CustomeExept {
        Employee res = repo.findById(id).orElse(null);
        if(res==null)
        {
            throw new CustomeExept("Employee id->"+id+" is not present in DB");
        }
        return res;
    }

    public Employee insert(Employee e) throws CustomeExept
    {
        if(e.getEmpName()==null)
        {
            throw new CustomeExept("Employee name cannot be null");
        }
        return repo.save(e);
    }

    public Employee update(Employee e) throws CustomeExept
    {
        Employee existingID = repo.findById(e.getEmpId()).orElse(null);
        if(existingID==null)
        {
            throw new CustomeExept("Employee id ->"+e.getEmpId()+" is not present in DB!!/n go for insert operation");
        }
        existingID.setEmpName(e.getEmpName());
        existingID.setPhNo(e.getPhNo());
        existingID.seteMail(e.geteMail());
        existingID.setAge(e.getAge());
        existingID.setDesignation(e.getDesignation());
        existingID.setSalary(e.getSalary());
        return repo.save(existingID);
    }

    public String deleteById(int id) throws CustomeExept {
        Employee del = repo.findById(id).orElse(null);
        if(del==null)
        {
            throw new CustomeExept("Id is not present in DB!!");
        }
        repo.deleteById(id);
        return "record successfully deleted with id "+id;
    }
}
